package lab2.Model;

import java.util.ArrayList;
import java.util.List;


public class Student {
	public String FIO;
	public int group;
	public List<Exam> exams = new ArrayList<Exam>();
	
	public Student (String FIO, int intGroup, List<Exam> exams) {
		this.FIO = FIO;
		this.group = intGroup;
		for (Exam exam : exams) {
	        this.exams.add(exam);
	    }
	}
	
	public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null)
            return false;

        if (getClass() == obj.getClass()) {
        	Student myStud = (Student) obj;
        	if (this.FIO == myStud.FIO && 
        			this.group == myStud.group && 
        			this.exams == myStud.exams)
        		return true;
        	else
        		return false;
        }
        else
        	return false;
       
    }
	
	public double studMark(){
		double mark = 0;
		for (Exam exam : this.exams) {
	        mark += exam.eval;
	    }
		mark /= this.exams.size();
		return mark;
	}
	public int examMark(String examName){
		for (Exam exam : this.exams) {
	        if (exam.name.equals(examName)){
	        	return exam.eval;
	        }
	    }
		return -1;
	}

}
