package lab3;

import javax.swing.JFrame;

import lab3.View.Window;


public class Main {
	public static void main(String[] args) {
		JFrame simpleWindow = new Window("Window");
        simpleWindow.setVisible(true);
        simpleWindow.setLocationRelativeTo(null);
        simpleWindow.setResizable(false);
	}
}
