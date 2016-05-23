package lab3.Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import lab3.Controller.SortTime;

public class TimeTable extends JTable{
	private DefaultTableModel model;
	private int SortedListSize = 1000; 
	public List<List> times = new ArrayList<List>();
	public int AlgNum = SortTime.AlgNum;
	
	private DefaultTableModel getMyModel(){
		
		model = new DefaultTableModel();
		model.addColumn("Test number");
		model.addColumn("Quick sort");
		model.addColumn("Bubble sort");
		
		return model;
	}
	
	public TimeTable(int arrayNumber) {
		model = getMyModel();
		this.setModel(model);
		for (int testNum = 1; testNum <= arrayNumber; testNum++){
			List<Integer> bufTime = SortTime.sortTime(SortTime.generateList(SortedListSize));
			String[] data = {Integer.toString(testNum), Integer.toString(bufTime.get(0)), Integer.toString(bufTime.get(1))}; 
            model.addRow(data);
            times.add(bufTime);
		} 
	}
}
