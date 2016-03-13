package lab1;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class MyButton extends JButton{
	MyButton(String s){
		super(s);
		setPreferredSize(new Dimension(150, 30));
		final Timer time = new Timer();
		int i = 15 - s.length();
    	for (int j = 0; j < i; j++){
    		s += " ";
    	}
		final String str = s;
		mySetText(str);
		
        time.schedule(new TimerTask() {
            String bufStr = str;
            @Override
            public void run() { 
            	bufStr = bufStr.substring(1)+bufStr.substring(0, 1);
            	mySetText(bufStr);
            }
        }, 100, 100);
	}
	public void mySetText(String str){
		if (str.length() < 15){
        	int i = 15 - str.length();
        	for (int j = 0; j < i; j++){
        		str += " ";
        	}
        	setText("|" + str + "|");
        }
		else {
			setText("|" + str.substring(0, 13) + "|");
		}
	}
}
