package lab3.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.swing.JPanel;

import lab3.Model.GraphPoints;

public class Graph extends JPanel {
	private int centerx=10;
	private int centery=10;
	private int scalex=25;
	private int scaley=25;

    private double coeffX, coeffY;
	
    GraphPoints times;
	public int algNum;
	
	public Graph() {
		times = new GraphPoints();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 600));
        setSize(new Dimension(600, 600));
        repaint();
    }
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
		coeffX = Math.floor(getWidth()/(scalex));
		coeffY = Math.floor(getHeight()/scaley)/1000;
        
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
            	if(e.isControlDown()){
	                if (e.getWheelRotation() < 0) {
	                    setSize((int) Math.floor(getWidth() + 20),
	                            (int) Math.floor(getHeight() + 20));
	                    setPreferredSize(new Dimension((int) Math.floor(getWidth() + 20),
	                            (int) Math.floor(getHeight() + 20)));
	                    repaint();
	                } else if (e.getWheelRotation() > 0) {
	                    setSize((int) Math.floor(getWidth() - 20),
	                            (int) Math.floor(getHeight() - 20));
	                    setPreferredSize(new Dimension((int) Math.floor(getWidth() - 20),
	                            (int) Math.floor(getHeight() - 20)));
	                    repaint();
	                }
            	}
            }
        });
        
        g.drawLine(centerx, 0, centerx, getHeight());
        for (int scaleNum = 1; scaleNum*coeffY*1000< getHeight(); scaleNum++){
        	g.drawLine(centerx/2, (int)(scaleNum*coeffY*1000-centery), centerx*3/2, (int)(scaleNum*coeffY*1000-centery));
        }
        g.drawString(Integer.toString(scaley-1)+"mS", 2*centerx , (int) (coeffY*1000-centery));
        g.drawLine(0, getHeight()-centery, getWidth(), getHeight()-centery);
        for (int scaleNum = 1; scaleNum*coeffX+2*centerx< getWidth(); scaleNum++){
        	g.drawLine((int)(scaleNum*coeffX+centerx), getHeight()-centery/2, (int) (scaleNum*coeffX+centerx), getHeight()-centerx*3/2);
        }
        g.drawString("Size"+Integer.toString(scalex), (int)(getWidth()-(coeffX+centerx)) , getHeight()-centerx*2);
        /*for (int bufAlgNum = 0; bufAlgNum < algNum; bufAlgNum++){
        	int prevX = centerx;
        	int prevY = centery;
        	for (List time : times){
        		g.drawLine(prevX, getHeight()-prevY, (int) (prevX+coeffX), getHeight()-(int)(centery+(int)(time.get(bufAlgNum))*coeffY));
        		prevX+=coeffX;
        		prevY=(int)(centery+(int)time.get(bufAlgNum)*coeffY);
        	}
        }*/
	}
	public void paintLine(Graphics g, Point p1, Point p2){
		//g.drawLine();
	}
	
	public void scaleXUp(){
    	if (scaley<10){
    		scaley +=1;
    	}
    	else if (scaley<100){
    		scaley += 5;
    	}
		repaint();
    }
    public void scaleXDown(){
    	if (scaley>10){
    		scaley -= 5;
    	}
    	else if (scaley > 1){
    		scaley -= 1;
    	}
    	repaint();
    }
    public void updateG(){
    	removeAll();
        updateUI();
        revalidate();
        repaint();
    }
	
}
