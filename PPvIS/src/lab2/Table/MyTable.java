package lab2.Table;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;


public class MyTable {
	
	public JTable table;
	int columns;
	private int countOnPage;
	private final int NUMBER_OF_PREV_COLUMNS = 2;
	private final int NUMBER_OF_EXAM_COLUMNS = 2;
	
	public MyTable(int columns, int count){
		this.columns = columns;
		this.countOnPage = count;
	}
	
	public Box getBox() {

	    JTable table = makeTable();
	    
	    JScrollPane scroll = new JScrollPane( table );
	    Box box = Box.createVerticalBox();
        box.setBorder(new CompoundBorder(new EtchedBorder(), new EmptyBorder(12, 12, 12, 12)));
        box.add(scroll);
        return box;
	  }
	
	public JTable makeTable() {
		Object[] studentsTableHeader = new Object[NUMBER_OF_PREV_COLUMNS+NUMBER_OF_EXAM_COLUMNS*this.columns];
		
		
	    DefaultTableModel model = new DefaultTableModel();
	    model.setDataVector(new Object[][]{}, studentsTableHeader);

	    JTable table = new JTable(model) {
	      protected JTableHeader createDefaultTableHeader() {
	          return new GroupableTableHeader(columnModel);
	      }
	    };
	    
	    
	    TableColumnModel columnModel = table.getColumnModel();
	    ColumnGroup students = new ColumnGroup("Students");
	    students.add(columnModel.getColumn(0));
	    students.add(columnModel.getColumn(1));
	    ColumnGroup exams = new ColumnGroup("Exams");
	    for (int examNum = 0; examNum < this.columns; examNum++){
	    	ColumnGroup ex = new ColumnGroup(Integer.toString(examNum+1));
		    ex.add(columnModel.getColumn(NUMBER_OF_PREV_COLUMNS+NUMBER_OF_EXAM_COLUMNS*examNum));
		    ex.add(columnModel.getColumn(NUMBER_OF_PREV_COLUMNS+NUMBER_OF_EXAM_COLUMNS*examNum+1));
		    exams.add(ex);
	    }
	    students.add(exams);
	    
	    GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
	    //getStudentsTableHeader(header, columns);
	    header.addColumnGroup(students);
	    
	    String[] data = {null};
	    for (int i = 0; i < this.countOnPage; i++){
	    	((DefaultTableModel) model).addRow(data);
	    }
	    
	    return table;
	}
	void getStudentsTableHeader(Object[] studentsTableHeader, int columns){
		studentsTableHeader[0] = "Name";
		studentsTableHeader[1] = "Group";
		for (int examNum = 0; examNum < columns; examNum++){
			studentsTableHeader[NUMBER_OF_PREV_COLUMNS+NUMBER_OF_EXAM_COLUMNS*examNum] = "Exam";
			studentsTableHeader[NUMBER_OF_PREV_COLUMNS+NUMBER_OF_EXAM_COLUMNS*examNum+1] = "Mark";
		}
	}
}
