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
        mainPanel.add(Task1.getPanel1());
        mainPanel.add(Task2.getPanel2());
        mainPanel.add(Task3.getPanel3());
        mainPanel.add(Task4.getPanel4());
        //mainPanel.add(task4.getPanel4());
        //mainPanel.add(task5.getPanel5());

        frame.setContentPane(mainPanel);
        frame.setSize(500, 350);
        return frame;
    }
}
