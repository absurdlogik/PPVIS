package lab2.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import lab2.Controller.Controller;
import lab2.Controller.Parser;
import lab2.Model.MainModel;

public class MainView {
	public MainView (final MainModel theModel, final Controller controller, String name) {
        JFrame theView = new JFrame();
        theView.setName(name);
        theView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theView.setResizable(true);

        final TablePanel tablePanel = new TablePanel(controller.getStudents());

        //Parser fileHandler = new Parser(theModel, tablePanel);

        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        
         
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				Parser fileHandler = new Parser(theModel, tablePanel);
				fileHandler.openFile();
			}

        });
        fileMenu.add(open);
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				Parser fileHandler = new Parser(theModel, tablePanel);
				fileHandler.saveFile();
			}

        });
        fileMenu.add(save);
        
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem addStud = new JMenuItem("Add student");
        addStud.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog addDialog = new AddDialog().create(controller, tablePanel);
				addDialog.setVisible(true);
			}

        });
        toolsMenu.add(addStud);
        
        JMenuItem search = new JMenuItem("Search");
        search.addActionListener(new ActionListener() {
 
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog searchDialog = new SearchDialog().create(controller.getStudents(), controller, tablePanel);
				searchDialog.setVisible(true);
			}

        });
        toolsMenu.add(search);
        
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        
        theView.setJMenuBar(menuBar);

        theView.setVisible(true);
        theView.getContentPane().add(tablePanel);
        theView.setSize(1000, 500);
    }
}
