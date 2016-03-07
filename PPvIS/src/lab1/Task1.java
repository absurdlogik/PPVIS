package lab1;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class Task1 {
	public static Box getPanel1() {
        final ArrayList<String> comboBoxItems = new ArrayList<String>();
        final JComboBox<String> menu = new JComboBox<String>();
        menu.setMaximumSize(new Dimension(500, 30));
        final JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(500, 30));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the text", "Information",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                for (String item : comboBoxItems) {
                    if (item.equals(textField.getText())) {
                        JOptionPane.showMessageDialog(null, "The element already exists", "Information",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                comboBoxItems.add(textField.getText());
                menu.addItem(textField.getText());
                textField.setText("");
            }
        });
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(addButton);
        horizontalBox.add(Box.createHorizontalStrut(10));
        horizontalBox.add(menu);

        Box box = Box.createVerticalBox();
        box.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        box.add(textField);
        box.add(Box.createVerticalStrut(6));
        box.add(horizontalBox);

        return box;
    }

}
