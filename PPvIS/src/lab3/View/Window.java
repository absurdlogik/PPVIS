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
import lab3.Controller.ThreadUpdateGraph;
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
        graph = new Graph();
        table = new TimeTable();
        Box mainPanelBox = Box.createHorizontalBox();
        mainPanelBox.add(getGraph());
        mainPanelBox.add(getButtons());
        mainPanel.add(mainPanelBox);
        setContentPane(mainPanel);
        setSize(1000, 600);
    }
	
	private Box getButtons(){
		Box box = Box.createVerticalBox();
		box.setMaximumSize(new Dimension(100, 100));
        final JTextField minSizeField = new JTextField("Minimal size");
        final JTextField maxSizeField = new JTextField("Maximal size");
        final JTextField stepField = new JTextField("Step");
        Box sizeFieldBox = Box.createHorizontalBox();
        sizeFieldBox.add(minSizeField);
        sizeFieldBox.add(maxSizeField);
        box.add(sizeFieldBox);
        box.add(stepField);
		final JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		try {
        			int minSize = new Integer (minSizeField.getText());
        			minSizeField.setText("Minimal size");
        			int maxSize = new Integer (maxSizeField.getText());
        			maxSizeField.setText("Maximal size");
        			int step = new Integer (stepField.getText());
        			stepField.setText("Step");
        			updateWindow(minSize, maxSize, step);
        			updateGraph(minSize, maxSize, step);
        		}
        		catch (Exception e) {
        			JOptionPane.showMessageDialog(null, "Please enter the numbers", "Information",
                            JOptionPane.WARNING_MESSAGE);
                    return;
        		}
            }
        });
        box.add(updateButton);
        
        /*Box scaleXBox = Box.createHorizontalBox();
        final JButton scaleXUpButton = new JButton("Scale X Up");
        scaleXUpButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		graph.scaleXUp();
            }
        });
        scaleXBox.add(scaleXUpButton);
        final JButton scaleXDownButton = new JButton("Scale X Down");
        scaleXDownButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		graph.scaleXDown();
            }
        });
        scaleXBox.add(scaleXDownButton);
        box.add(scaleXBox);*/
        
        Box scaleYBox = Box.createHorizontalBox();
        final JButton scaleYUpButton = new JButton("Scale Y Up");
        scaleYUpButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		graph.scaleYUp();
            }
        });
        scaleYBox.add(scaleYUpButton);
        final JButton scaleYDownButton = new JButton("Scale Y Down");
        scaleYDownButton.addActionListener(new ActionListener()  {
        	
        	@Override
			public void actionPerformed(ActionEvent event) {
        		graph.scaleYDown();
            }
        });
        scaleYBox.add(scaleYDownButton);
        box.add(scaleYBox);
		
		return box;	
	}
	private Box getGraph(){
		Box box = Box.createHorizontalBox();
        JScrollPane tableScroll = new JScrollPane(table);
		box.add(tableScroll);
        JScrollPane graphScroll = new JScrollPane(graph);
        MyMouseAdapter adapter = new MyMouseAdapter(graphScroll);
        graph.addMouseListener(adapter);
        graph.addMouseMotionListener(adapter);
        box.add(graphScroll);
        return box;
	}
	public void updateWindow(int minSize, int maxSize, int step) {
		mainPanel.removeAll();
		mainPanel.updateUI();
        graph = new Graph(minSize, maxSize, step);
        table = new TimeTable();
        Box mainPanelBox = Box.createHorizontalBox();
        mainPanelBox.add(getGraph());
        mainPanelBox.add(getButtons());
        add(mainPanelBox);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
	private void updateGraph(int minSize, int maxSize, int step){
		Thread thread = new ThreadUpdateGraph(minSize, maxSize, step, table, graph);
        thread.start();
	}
	
}