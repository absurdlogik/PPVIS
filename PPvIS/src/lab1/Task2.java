package lab1;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.border.*;

public class Task2 {
	public static Box getPanel2() {
        final JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(500, 30));
        final JButton renameButton = new JButton("Rename button");
        final JButton exchangeButton = new JButton("Exchange");
        exchangeButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the text", "Information",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String tempButton = exchangeButton.getText();
                exchangeButton.setText(renameButton.getText());
                renameButton.setText(tempButton);
                textField.setText("");
            }
        });
        renameButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
                exchangeButton.setText(textField.getText());
            }
        });
        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(renameButton);
        horizontalBox.add(Box.createHorizontalStrut(20));
        horizontalBox.add(exchangeButton);

        Box box = Box.createVerticalBox();
        box.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(10, 10, 10, 10)));
        box.add(textField);
        box.add(Box.createVerticalStrut(6));
        box.add(horizontalBox);

        return box;
    }
}
