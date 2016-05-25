package lab3.Controller;

import lab3.Model.Point;
import lab3.Model.TimeTable;
import lab3.View.Graph;

public class ThreadUpdateGraph extends Thread{
	int minSize, maxSize, step;
	private TimeTable table;
	private Graph graph;
	
	public ThreadUpdateGraph(int minSize, int maxSize, int step, 
			TimeTable table, Graph graph){
		this.minSize = minSize;
		this.maxSize = maxSize;
		this.step = step;
		this.graph = graph;
		this.table = table;
	}
	@Override
    public void run() {
		for (int size = minSize; size <= maxSize; size+=step){
        	Point p = new Point(size/step, (int) SortTime.sortAverageTime(size, 20));
        	graph.addPoint(p);
        	table.addRow(p);
        }
	}
}
