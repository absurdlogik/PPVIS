package lab3.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lab3.Controller.SortTime;

public class TimeTable extends JTable{
	private DefaultTableModel model;
	private int SortedListSize = 1000; 
	public List<Point> times = new ArrayList<Point>();
	public int AlgNum = SortTime.AlgNum;
	
	private DefaultTableModel getMyModel(){
		
		model = new DefaultTableModel();
		model.addColumn("Size");
		model.addColumn("Time, micro");
		
		return model;
	}
	
	public TimeTable() {
		model = getMyModel();
		this.setModel(model);
	}
	public void addRow(Point p){
		String[] data = {Integer.toString(p.x), Integer.toString(p.y)}; 
        model.addRow(data);
        times.add(p);
	}
}
