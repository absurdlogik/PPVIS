package lab2.Controller;

import lab2.Model.Student;

public class SearchAlg {
	public static boolean isFind(Student stud, String FIO, int evalMin, int evalMax){
		if (stud.FIO.equals(FIO) || (stud.studMark() >= evalMin &&
				stud.studMark() <= evalMax)){
			return true;
		}
		return false;
	}
	public static boolean isFind(Student stud, String FIO, int group){
		if (stud.FIO.equals(FIO) || stud.group == group){
			return true;
		}
		return false;
	}
	public static boolean isFind(Student stud, String FIO, String exam, int evalMin, int evalMax){
		if (stud.FIO.equals(FIO) || (stud.examMark(exam) >= evalMin &&
				stud.examMark(exam) <= evalMax)){
			return true;
		}
		return false;
	}
}
