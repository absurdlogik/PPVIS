package lab1;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.border.*;

public class Task4 {
	public static Box getPanel4() {
        final JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(500, 30));
        final JCheckBox checkBox1 = new JCheckBox("1");
        final JCheckBox checkBox2 = new JCheckBox("2");
        final JCheckBox checkBox3 = new JCheckBox("3");
        JButton choiceButton = new MyButton("Choice Button");
        choiceButton.addActionListener(new ActionListener()  {

        	@Override
        	public void actionPerformed(ActionEvent event) {
        		if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the text", "Information",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
        		else if (checkBox1.getText().equals(textField.getText())) {
                    checkBox1.setSelected(!checkBox1.isSelected());
                }
                else if (checkBox2.getText().equals(textField.getText())) {
                    checkBox2.setSelected(!checkBox2.isSelected());
                }
                else if (checkBox3.getText().equals(textField.getText())) {
                    checkBox3.setSelected(checkBox3.isSelected());
                }
                else {
                	JOptionPane.showMessageDialog(null, "This name does not exist", "Information",
                        JOptionPane.WARNING_MESSAGE);
                	textField.setText("");
                }
        		//textField.setText("");
            }
        });
        
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(choiceButton);
        horizontalBox.add(Box.createHorizontalStrut(10));
        horizontalBox.add(checkBox1);
        horizontalBox.add(checkBox2);
        horizontalBox.add(checkBox3);
        
        Box box = Box.createVerticalBox();
        box.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        box.add(textField);
        box.add(Box.createVerticalStrut(6));
        box.add(horizontalBox);

        return box;
    }
}
