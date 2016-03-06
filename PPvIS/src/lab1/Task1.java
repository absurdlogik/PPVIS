package lab1;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;

public class Task1 {
	public static Box getPanel1() {
        final ArrayList<String> comboBoxItems = new ArrayList<String>();
        final JComboBox<String> menu = new JComboBox<String>();
        final JTextField textField = new JTextField("Enter the text");
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent event) {
                for (String item : comboBoxItems) {
                    if (item.equals(textField.getText())) {
                        JOptionPane.showMessageDialog(null, "The element already exists", "Information",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                comboBoxItems.add(textField.getText());
                menu.addItem(textField.getText());
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
