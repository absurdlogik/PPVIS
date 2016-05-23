package lab3.Model;

import java.util.ArrayList;

public class GraphPoints {
	public ArrayList <Point> points;
	public GraphPoints(){
		points = new ArrayList<Point>();
	}
	public void addPoint(int x, int y){
		points.add(new Point(x, y));
	}
}
