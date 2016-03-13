package lab1;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.border.*;
import javax.swing.table.*;

public class Task5 {
	
	private static DefaultTableModel model;
	
	private static DefaultTableModel getModel(){
		
		model = new DefaultTableModel();
		model.addColumn("Column 1");
		model.addColumn("Column 2");
		
		return model;
	}
	
	public static Box getPanel5() {
        final JTable table = new JTable(getModel());
		table.setColumnSelectionAllowed(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setMaximumSize(new Dimension(500, 200));
        
        final JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(500, 30));

        JButton addTextButton = new JButton("Button 1");
        addTextButton.addMouseListener(new MouseAdapter() {
            
        	@Override
            public void mouseClicked(MouseEvent e) {
        		if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the text", "Information",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
        		String[] data = {textField.getText(), null}; 
                model.addRow(data);
                textField.setText("");
            }
        });
        JButton textMoveButton1 = new JButton("Button 2");
        textMoveButton1.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "You haven't selected a line",
                            "Information", JOptionPane.WARNING_MESSAGE);
                }
        		else if (table.getSelectedColumn() == 1) {
                    JOptionPane.showMessageDialog(null, "Please select the first column",
                            "Information", JOptionPane.WARNING_MESSAGE);
                } 
        		else {
        			Object data =  table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
        			if (data != null) {
        				table.setValueAt(data, table.getSelectedRow(), 1);
        				table.setValueAt(null, table.getSelectedRow(), 0);
        			}
        			else {
                        JOptionPane.showMessageDialog(null, "Column is empty", "Information",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
        		}
            }
        });

        JButton textMoveButton2 = new JButton("Button 3");
        textMoveButton2.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		if (table.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "You haven't selected a line",
                            "Information", JOptionPane.WARNING_MESSAGE);
                }
        		else if (table.getSelectedColumn() == 0) {
                    JOptionPane.showMessageDialog(null, "Please select the second column",
                            "Information", JOptionPane.WARNING_MESSAGE);
                } 
        		else {
        			Object data =  table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
        			if (data != null) {
        				table.setValueAt(data, table.getSelectedRow(), 0);
        				table.setValueAt(null, table.getSelectedRow(), 1);
        			}
        			else {
                        JOptionPane.showMessageDialog(null, "Column is empty", "Information",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
        		}
            }
        });

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(addTextButton);
        horizontalBox.add(Box.createHorizontalStrut(6));
        horizontalBox.add(textMoveButton1);
        horizontalBox.add(Box.createHorizontalStrut(6));
        horizontalBox.add(textMoveButton2);

        Box box = Box.createVerticalBox();
        box.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(12, 12, 12, 12)));
        box.add(textField);
        box.add(Box.createVerticalStrut(7));
        box.add(horizontalBox);
        box.add(Box.createVerticalStrut(7));
        box.add(tableScrollPane);

        return box;
    }

}

class TableAdapter extends MouseAdapter {
    JTable table;
    int col1;
    int col2;

    TableAdapter(JTable table, int col1, int col2) {
        this.table = table;
        this.col1 = col1;
        this.col2 = col2;
    }

    public void mouseClicked(MouseEvent e) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "You haven't selected a line",
                    "Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ((col1 == 0) && (table.getSelectedColumn() == 0)) {
            JOptionPane.showMessageDialog(null, "You must select the first column",
                    "Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if ((col1 == 1) && (table.getSelectedColumn() == 1)) {
            JOptionPane.showMessageDialog(null, "You must select the first column",
                    "Information", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) != null) {
            table.setValueAt(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()),
                    table.getSelectedRow(), col1);
            table.setValueAt(null, table.getSelectedRow(), col2);
        }
    }
}