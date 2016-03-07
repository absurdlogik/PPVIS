package lab1;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
        JFrame simpleWindow = new Window().createFrame("Window");
        simpleWindow.setVisible(true);
        simpleWindow.setLocationRelativeTo(null);
        simpleWindow.setResizable(false);
    }
}