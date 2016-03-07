package lab1;

import javax.swing.*;

import java.awt.*;

public class Window {
	public JFrame createFrame(String name) {
        JFrame frame = new JFrame();
        frame.setName(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        
        Box box = Box.createVerticalBox();
        
        box.add(Task1.getPanel1());
        box.add(Task2.getPanel2());
        box.add(Task3.getPanel3());
        box.add(Task4.getPanel4());
        box.add(Task5.getPanel5());

        mainPanel.add(box);

        frame.setContentPane(mainPanel);
        frame.setSize(500, 540);
        return frame;
    }
}
