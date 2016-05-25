package lab3.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.swing.JPanel;

import lab3.Model.GraphPoints;
import lab3.Model.Point;

public class Graph extends JPanel {
	private int centerx=10;
	private int centery=10;
	private int scalex=25;
	private int scaley=25;
	private int sizeX=25;

    private double coeffX, coeffY;
	
    GraphPoints times;
	public int algNum;
	public Graph() {
		times = new GraphPoints();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));
        setSize(new Dimension(600, 400));
        repaint();
    }
	public Graph(int minSize, int maxSize, int step) {
		scalex = maxSize/step;
		sizeX = maxSize;
		times = new GraphPoints();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));
        setSize(new Dimension(600, 400));
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
        for (int scaleNum = 1; scaleNum<scaley; scaleNum++){
        	g.drawLine(centerx/2, (int)(scaleNum*coeffY*1000-centery), centerx*3/2, (int)(scaleNum*coeffY*1000-centery));
        }
        g.drawString(Integer.toString(scaley)+"mS", 2*centerx , 2*centery);
        g.drawLine(0, getHeight()-centery, getWidth(), getHeight()-centery);
        for (int scaleNum = 1; scaleNum<scalex; scaleNum++){
        	g.drawLine((int)(scaleNum*coeffX+centerx), getHeight()-centery/2, (int) (scaleNum*coeffX+centerx), getHeight()-centerx*3/2);
        }
        g.drawString("Size"+Integer.toString(scalex), (int)(getWidth()-(centery*5)) , getHeight()-centerx*2);
      
        Point prevPoint = new Point(0, 0);
        for (Point point : times.points){
        	paintLine(g, prevPoint, point);
        	prevPoint = point;
        }
	}
	private void paintLine(Graphics g, Point p1, Point p2){
		int x1 = (int) (centerx+p1.x*coeffX);
		int y1 = (int) (getHeight()-centery-p1.y*coeffY);
		int x2 = (int) (centerx+p2.x*coeffX);
		int y2 = (int) (getHeight()-centery-p2.y*coeffY);
		g.drawLine(x1, y1, x2, y2);
	}
	public void addPoint(Point p){
		times.addPoint(p);
		updateG();
	}
	public void scaleYUp(){
    	if (scaley<10){
    		scaley +=1;
    	}
    	else if (scaley<100){
    		scaley += 5;
    	}
		repaint();
    }
    public void scaleYDown(){
    	if (scaley>10){
    		scaley -= 5;
    	}
    	else if (scaley > 1){
    		scaley -= 1;
    	}
    	repaint();
    }
    
    public void scaleXUp(){
    	if (scalex<100){
    		scalex +=10;
    	}
    	else if (scalex<1000){
    		scalex += 5;
    	}
		repaint();
    }
    public void scaleXDown(){
    	if (scalex>100){
    		scalex -= 50;
    	}
    	else if (scalex > 10){
    		scalex -= 10;
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
