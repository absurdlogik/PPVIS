package lab2.View;

import lab2.Controller.Controller;
import lab2.Model.Exam;
import lab2.Model.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by РљРѕРЅСЃС‚Р°РЅС‚РёРЅ on 12.03.2016.
 */
public class AddDialog {
    private JTextField FIO = new JTextField(40);
    private JTextField group = new JTextField(10);
    private JTextField name = new JTextField(10);
    private JTextField eval = new JTextField(10);
   

    Controller controller;

    public JDialog create(final Controller controller, final TablePanel tablePanel) {
        this.controller = controller;
        
        final List<Exam> exams = new ArrayList<>();
        
        final JDialog addDialog = new JDialog();

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(6, 6, 6, 6));

        Box box1 = Box.createHorizontalBox();
        box1.add(this.FIO);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(this.group);
        box1.setBorder(new TitledBorder("Введите имя и группу"));

        Box box2 = Box.createHorizontalBox();
        box2.add(name);
        box2.add(Box.createHorizontalStrut(6));
        box2.add(eval);
        box2.setBorder(new TitledBorder("Введите экзамен и балл"));
        
        JButton addExamButton = new JButton("Add exam");
        addExamButton.addActionListener(new ActionListener() {
        	int intEval;
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (exams.size() >= tablePanel.columns){
            		JOptionPane.showMessageDialog(null, "Введено максимальное количество экзаменов",
            				"Information", JOptionPane.WARNING_MESSAGE); 
            		return;
            	}
            	try {
                    intEval = new Integer(eval.getText());
                    if (intEval > 10 || intEval < 1){
                    	JOptionPane.showMessageDialog(null, "Неверный формат строки!", "Information",
                                JOptionPane.WARNING_MESSAGE); 
                    	return;
                    }
                    exams.add(new Exam(name.getText(), intEval));
                    name.setText("");
                    eval.setText("");
                }
            	catch (NumberFormatException e1) {  
                    System.err.println("Неверный формат строки!");  
                }   
            }
        });
        
        Box box3 = Box.createVerticalBox();
        box3.add(box2);
        box3.add(Box.createVerticalStrut(6));
        box3.add(addExamButton);
        
        Box box4 = Box.createVerticalBox();
        box4.add(box3);
        box4.add(Box.createVerticalStrut(6));
        box4.add(box1);
        
        JButton addStudentButton = new JButton("Add student");
        addStudentButton.addActionListener(new ActionListener() {
            int intGroup;
        	@Override
            public void actionPerformed(ActionEvent e) {
        		try {
                    intGroup = new Integer(group.getText());
                    controller.addStudent(new Student(FIO.getText(), 
                    		intGroup, exams));
                    tablePanel.updateTable();
                    group.setText("");
                    FIO.setText("");
                    exams.clear();
                }
            	catch (NumberFormatException e1) {  
                    System.err.println("Неверный формат строки!");  
                }
            }
        });

        mainBox.add(box4);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(addStudentButton);
        

        addDialog.add(mainBox);

        addDialog.pack();
        return addDialog;
    }
   
}
