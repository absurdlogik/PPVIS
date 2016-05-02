package lab2.Controller;

import lab2.Model.*;

import java.util.List;

public class Controller {
    public MainModel theModel;

    public Controller(MainModel theModel) {
        this.theModel = theModel;
    }

    public void addStudent(Student newStudent) {
        theModel.students.add(newStudent);
    }

    public List<Student> getStudents() {
        return theModel.students;
    }

    public int removeStudents(List<Student> Students) {
        int i = 0;
        for (Student removeStudent : Students) {
            for (Student modelStudent : theModel.students) {
                if (modelStudent.equals(removeStudent)) {
                    theModel.students.remove(modelStudent);
                    i++;
                    break;
                }
            }
        }
        return i;
    }



}