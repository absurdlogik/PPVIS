package lab1;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.border.*;

public class Task3 {
	public static Box getPanel3() {
        final JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(500, 30));
        JButton choiceButton = new JButton("Choice button");
        final JRadioButton radioButton1 = new JRadioButton("1");
        final JRadioButton radioButton2 = new JRadioButton("2");
        final JRadioButton radioButton3 = new JRadioButton("3");

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        choiceButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the text", "Information",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (radioButton1.getText().equals(textField.getText())) {
                    radioButton1.setSelected(!radioButton1.isSelected());
                }
                else if (radioButton2.getText().equals(textField.getText())) {
                    radioButton2.setSelected(!radioButton2.isSelected());
                }
                else if (radioButton3.getText().equals(textField.getText())) {
                    radioButton3.setSelected(!radioButton3.isSelected());
                }
                else {
                	JOptionPane.showMessageDialog(null, "This name does not exist", "Information",
                        JOptionPane.WARNING_MESSAGE);
                }
                textField.setText("");
                
            }
        });
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(choiceButton);
        horizontalBox.add(Box.createHorizontalStrut(10));
        horizontalBox.add(radioButton1);
        horizontalBox.add(radioButton2);
        horizontalBox.add(radioButton3);
        
        Box box = Box.createVerticalBox();
        box.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        box.add(textField);
        box.add(Box.createVerticalStrut(6));
        box.add(horizontalBox);

        return box;
    }

}
