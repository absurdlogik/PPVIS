package lab1;

import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class MyButton extends JButton{
	MyButton(String str){
		super(str);
		setPreferredSize(new Dimension(100, 30));
		setMaximumSize(new Dimension(150, 30));
		setMinimumSize(new Dimension(150, 30));
		final Timer merqueeTimer = new Timer();
		
		final String modificatedText = modifyText(str);
		mySetText(modificatedText);
		
		merqueeTimer.schedule(new TimerTask() {
            String bufStr = modificatedText;
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
	public String modifyText(String str){
		int strMissingLength = 15 - str.length();
    	if (strMissingLength > 0){
    		for (int i = 0; i < strMissingLength; i++){
    			str += " ";
    		}
    	}
    	else
    	{
    		str += " ";
    	}
		return str;
	}
}
