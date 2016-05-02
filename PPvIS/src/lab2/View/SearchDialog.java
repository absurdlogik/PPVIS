package lab2.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import lab2.Controller.Controller;
import lab2.Controller.SearchAlg;
import lab2.Model.Student;

public class SearchDialog {
	private final String SEARCH_BY_AVERAGE_NUMBER = "By number";
	private final String SEARCH_BY_GROUP_NUMBER = "By group";
	private final String SEARCH_BY_DISCIPLINE = "By discipline";
	

	String searchAlgNum = SEARCH_BY_AVERAGE_NUMBER;
	
	private JTextField FIO1 = new JTextField(20);
	private JTextField evalMin1 = new JTextField(5);
	private JTextField evalMax1 = new JTextField(5);
	
	private JTextField FIO2 = new JTextField(20);
	private JTextField group2 = new JTextField(10);
	
	private JTextField FIO3 = new JTextField(20);
	private JTextField exam3 = new JTextField(10);
	private JTextField evalMin3 = new JTextField(5);
	private JTextField evalMax3 = new JTextField(5);
	
	public List<Student> students = new ArrayList<Student>();
	public TablePanel table = new TablePanel(students);
	
	
	public JDialog create(final List<Student> allStudents, final Controller controller, final TablePanel tablePanel){
		final JDialog searchDialog = new JDialog();

		Box searchBox1 = Box.createHorizontalBox();
		JRadioButton radioButton1 = new JRadioButton();
		radioButton1.setSelected(true);
		radioButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchAlgNum = SEARCH_BY_AVERAGE_NUMBER;
			}
		});
		searchBox1.add(radioButton1);
		searchBox1.add(getSearchBox1());
	
	
		Box searchBox2 = Box.createHorizontalBox();
		final JRadioButton radioButton2 = new JRadioButton();
		radioButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchAlgNum = SEARCH_BY_GROUP_NUMBER;
			}
		});
		searchBox2.add(radioButton2);
		searchBox2.add(getSearchBox2());
		
		Box searchBox3 = Box.createHorizontalBox();
		final JRadioButton radioButton3 = new JRadioButton();
		radioButton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchAlgNum = SEARCH_BY_DISCIPLINE;
			}
		});
		searchBox3.add(radioButton3);
		searchBox3.add(getSearchBox3());
		
		ButtonGroup radioButtons = new ButtonGroup();
        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButtons.add(radioButton3);
        
        
        JButton searchButton = new JButton("Find");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	students = search(allStudents);
                table.setStudents(students);
            }
        });
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (students.size() == 0) {
                    JOptionPane.showMessageDialog(searchDialog, "По вашему запросу не найдено ни одного студента.",
                            "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int size = students.size();
                    controller.removeStudents(students);
                    tablePanel.updateTable();
                    String message = "Удалено " + size + " студентов";
                    JOptionPane.showMessageDialog(searchDialog, message, "Information",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                students.clear();
                table.updateTable();
            }
        });
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(deleteButton);
        buttonBox.add(searchButton);
        
        Box mainBox = Box.createVerticalBox();
        mainBox.add(searchBox1);
        mainBox.add(Box.createRigidArea(new Dimension(12, 12)));
        mainBox.add(searchBox2);
        mainBox.add(Box.createRigidArea(new Dimension(12, 12)));
        mainBox.add(searchBox3);
        mainBox.add(Box.createRigidArea(new Dimension(12, 12)));
        mainBox.add(table);
        mainBox.add(Box.createRigidArea(new Dimension(12, 12)));
        mainBox.add(buttonBox);

        searchDialog.add(mainBox);

        searchDialog.setSize(500,500);
        return searchDialog;
	}
	
	private  Box getSearchBox1(){
		Box box = Box.createHorizontalBox();
		box.setBorder(new TitledBorder("Введите фамилию студента и границу среднего балла"));
		box.add(FIO1);
		box.add(evalMin1);
		box.add(evalMax1);
		return box;
	}
	
	private Box getSearchBox2(){
		Box box = Box.createHorizontalBox();
		box.setBorder(new TitledBorder("Введите фамилию студента и номер группы"));
		box.add(FIO2);
		box.add(group2);
		return box;
	}
	
	private Box getSearchBox3(){
		Box box = Box.createHorizontalBox();
		box.setBorder(new TitledBorder("Введите фамилию студента, дисциплину и границы балла"));
		box.add(FIO3);
		box.add(exam3);
		box.add(evalMin3);
		box.add(evalMax3);
		return box;
	}
	
	
	private List<Student> search(List<Student> allStudents){
		List<Student> students = new ArrayList<Student>();
		String FIO, exam;
		int evalMin, evalMax, group;
		switch(searchAlgNum){
    	case SEARCH_BY_AVERAGE_NUMBER:
    		try {
    			FIO = FIO1.getText();
    		}
    		catch (Exception eGetText) {
    			FIO = "";
    		}
    		try {
    			evalMin = new Integer(evalMin1.getText());
    		}
    		catch (Exception eGetText) {
    			evalMin = 0;
    		}
    		try {
    			evalMax = new Integer(evalMax1.getText());
    		}
    		catch (Exception eGetText) {
    			evalMax = 10;
    		}
    		for (Student student : allStudents) {
                if (SearchAlg.isFind(student, FIO, 
                					evalMin, evalMax)) {
                	students.add(student);
                }
            }
    		break;
    	case SEARCH_BY_GROUP_NUMBER:
    		try {
    			FIO = FIO2.getText();
    		}
    		catch (Exception eGetText) {
    			FIO = "";
    		}
    		try {
    			group = new Integer(group2.getText());
    		}
    		catch (Exception eGetText) {
    			group = -1;
    		}
    		for (Student student : allStudents) {
                if (SearchAlg.isFind(student, FIO, 
                		group)) {
                	students.add(student);
                }
            }
    		break;
    	case SEARCH_BY_DISCIPLINE:
    		try {
    			FIO = FIO3.getText();
    		}
    		catch (Exception eGetText) {
    			FIO = "";
    		}
    		try {
    			exam = exam3.getText();
    		}
    		catch (Exception eGetText) {
    			exam = "";
    		}
    		try {
    			evalMin = new Integer(evalMin3.getText());
    		}
    		catch (Exception eGetText) {
    			evalMin = 0;
    		}
    		try {
    			evalMax = new Integer(evalMax3.getText());
    		}
    		catch (Exception eGetText) {
    			evalMax = 10;
    		}
    		for (Student student : allStudents) {
                if (SearchAlg.isFind(student, FIO, exam,
                		evalMin, evalMax)) {
                	students.add(student);
                }
            }
    		break;
    	}
		
		return students;
	}
}
