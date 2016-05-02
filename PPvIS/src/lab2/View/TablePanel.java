package lab2.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import lab2.Model.Student;
import lab2.Table.MyTable;

public class TablePanel extends JPanel{
	private List<Student> students;
	int columns = 4;
    private int currentPage = 1;
    private int countOnPage = 10;
    
    public TablePanel(List<Student> students){
    	this.students = students;
    	setLayout(new BorderLayout());
        updateTable();
    }
    
    private JTable makeTable() {
        JTable table = new MyTable(this.columns, this.countOnPage).makeTable();

        table.setEnabled(false);
        int firstStudOnPage = countOnPage * (currentPage - 1);
        for (int x = 0, student = firstStudOnPage; 
        		x < countOnPage && student < this.students.size(); 
        		x++, student++) {
            table.setValueAt(this.students.get(student).FIO, x, 0);
            table.setValueAt(this.students.get(student).group, x, 1);
            for (int exam = 0; exam < this.columns && 
            		exam < this.students.get(student).exams.size(); exam++){
             	table.setValueAt(this.students.get(student).exams.get(exam).name, x, 2+2*exam);
                table.setValueAt(this.students.get(student).exams.get(exam).eval, x, 2+2*exam+1);
       		}
        }
        return table;
    }
    
    void nextPage(){
    	if (currentPage*countOnPage < this.students.size()){
    		currentPage++;
    	}
    }
    void prevPage(){
    	if (currentPage > 1){
    		currentPage--;
    	}
    }
    void firstPage(){
    	currentPage = 1;
    }
    void lastPage(){
    	currentPage = (this.students.size() - 1)/countOnPage + 1;
    }
    
    private Box makeButtons(){
    	Box hBox = Box.createHorizontalBox();
    	Box vBox = Box.createVerticalBox();
    	
    	String statusBar = "Page " + this.currentPage + "/" + (this.students.size()/countOnPage + 1);
    	vBox.add(new JLabel(statusBar));
    	
    	JButton firstButton = new JButton("First");
        hBox.add(firstButton);
    	firstButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstPage();
                updateTable();
            }
        });
        JButton prevButton = new JButton("Prev");
        hBox.add(prevButton);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevPage();
                updateTable();
            }
        });
        JButton nextButton = new JButton("Next");
        hBox.add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	nextPage();
                updateTable();
            }
        });
        JButton lastButton = new JButton("Last");
        hBox.add(lastButton);
        lastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastPage();
                updateTable();
            }
        });
        vBox.add(makeCount());
    	vBox.add(hBox);
		return vBox;
    }
    
    private Box makeCount(){
    	Box hBox = Box.createHorizontalBox();
    	final JTextField textField = new JTextField(Integer.toString(countOnPage));
    	textField.setMaximumSize(new Dimension(100, 30));
    	final JButton changeButton = new JButton("Change count");
        changeButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		int bufCount = new Integer(textField.getText());
        		if (bufCount > 1){
        			countOnPage = bufCount;
                    updateTable();
        		}
            }
        });
        hBox.add(textField);
        hBox.add(changeButton);
		return hBox;
    }
    
    
    public void updateTable() {
        removeAll();
        updateUI();
        Box vBox = Box.createVerticalBox();
        vBox.add(new JScrollPane(makeTable()));
        vBox.add(makeButtons());
        add(vBox);
        revalidate();
        repaint();
    }

	public List<Student> getStudents() {
		return this.students;
	}
	public void setStudents(List<Student> students){
    	this.students = students;
    	updateTable();
	}
}
