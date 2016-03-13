package lab1;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class MyButton extends JButton{
	MyButton(String s){
		super(s);
		setPreferredSize(new Dimension(100, 30));
		setMaximumSize(new Dimension(150, 30));
		setMinimumSize(new Dimension(150, 30));
		final Timer time = new Timer();
		
		final String str = modifyText(s);
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
		setText(" " + str.substring(0, 15) + " ");
	}
	public String modifyText(String s){
		int i = 15 - s.length();
    	if (i > 0){
    		for (int j = 0; j < i; j++){
    			s += " ";
    		}
    	}
    	else
    	{
    		s += " ";
    	}
		return s;
	}
}
