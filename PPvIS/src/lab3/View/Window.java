package lab3.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Tests.lab3.Controller.MyMouseAdapter;
import lab1.MyButton;
import lab1.Task1;
import lab3.Model.TimeTable;

public class Window extends JFrame{
	private TimeTable table;
	private Graph graph;
	private JPanel mainPanel;
	
	public Window(String name) {
        setName(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        Box mainPanelBox = Box.createHorizontalBox();
        mainPanelBox.add(getGraph(20));
        mainPanelBox.add(getButtons());
        mainPanel.add(mainPanelBox);
        setContentPane(mainPanel);
        setSize(1000, 600);
    }
	
	private Box getButtons(){
		Box box = Box.createVerticalBox();
		box.setMaximumSize(new Dimension(100, 100));
        final JTextField textField = new JTextField("");
        box.add(textField);
		final JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		try {
        			int masNum = new Integer (textField.getText());
        			updateWindow(masNum);
        		}
        		catch (Exception e) {
        			JOptionPane.showMessageDialog(null, "Please enter the number", "Information",
                            JOptionPane.WARNING_MESSAGE);
                    return;
        		}
            }
        });
        box.add(updateButton);
        final JButton scaleUpButton = new JButton("Scale Up");
        scaleUpButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		graph.scaleXUp();
            }
        });
        box.add(scaleUpButton);
        final JButton scaleDownButton = new JButton("Scale Down");
        scaleDownButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		graph.scaleXDown();
            }
        });
        box.add(scaleDownButton);
		
		return box;	
	}
	private Box getGraph(int masNum){
		Box box = Box.createHorizontalBox();
		table = new TimeTable(masNum);
        JScrollPane tableScroll = new JScrollPane(table);
		box.add(tableScroll);
		graph = new Graph();
        JScrollPane graphScroll = new JScrollPane(graph);
        MyMouseAdapter adapter = new MyMouseAdapter(graphScroll);
        graph.addMouseListener(adapter);
        graph.addMouseMotionListener(adapter);
        box.add(graphScroll);
        return box;
	}
	public void updateWindow(int masNum) {
		mainPanel.removeAll();
		mainPanel.updateUI();
        Box mainPanelBox = Box.createHorizontalBox();
        mainPanelBox.add(getGraph(masNum));
        mainPanelBox.add(getButtons());
        add(mainPanelBox);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
	
}