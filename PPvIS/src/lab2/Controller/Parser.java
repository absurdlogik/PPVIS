package lab2.Controller;

import lab2.Model.Exam;
import lab2.Model.MainModel;
import lab2.Model.Student;
import lab2.View.TablePanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.stream.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private TablePanel table;
    public MainModel theModel;

    public Parser(MainModel theModel, TablePanel table) {
        this.table = table;
        this.theModel = theModel;
    }

    public void saveFile() {
        try {
            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                XMLOutputFactory output = XMLOutputFactory.newInstance();
                String filePath = fc.getSelectedFile() + "." + "xml";
				XMLStreamWriter writer = output.createXMLStreamWriter
                        (new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
                
                //writer.writeStartDocument("UTF-8", "1.0");
                writer.writeStartElement("Students");
                for (Student student : table.getStudents()) {
                    writer.writeStartElement("Student");
                    writer.writeAttribute("FIO", student.FIO);
                    writer.writeAttribute("Group", Integer.toString(student.group));
                    for (Exam exam : student.exams){
                    	writer.writeStartElement("Exam");
                    	writer.writeAttribute("Name", exam.name);
                        writer.writeAttribute("Eval", Integer.toString(exam.eval));
                        writer.writeEndElement();
                    }                    
                    writer.writeEndElement();
                }
                writer.writeEndElement();
                writer.writeEndDocument();
                writer.flush();
            }
        } catch (Exception eSave) {
        	System.err.println(eSave.getMessage());
            JOptionPane.showMessageDialog
                    (null, "Can't save file", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

  public void openFile() {
      JFileChooser fc = new JFileChooser();
      fc.setFileFilter(new FileNameExtensionFilter(".xml", "xml"));
      if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          String fileName = fc.getSelectedFile().getPath();

          try {
              String FIO = "";
              int group = 0;
              List<Exam> exams = new ArrayList<>();
              String name = "";
              int eval = 0;
              table.getStudents().clear();

              FileInputStream stream = new FileInputStream(fileName);
              XMLInputFactory input = XMLInputFactory.newInstance();
              XMLStreamReader reader = input.createXMLStreamReader(stream);
              while (reader.hasNext()) {
                  reader.next();
                  if (reader.isStartElement()) {
                      if (reader.getLocalName().equals("Student")) {
                          FIO = reader.getAttributeValue(null, "FIO");
                          group = new Integer(reader.getAttributeValue(null, "Group"));
                      }
                      if (reader.getLocalName().equals("Exam")) {
                    	  name = reader.getAttributeValue(null, "Name");
                    	  eval = new Integer(reader.getAttributeValue(null, "Eval"));
                      }
                  } 
                  else if (reader.isEndElement()) {
                      if (reader.getLocalName().equals("Student")) {
                          table.getStudents().add(new Student(FIO, group, exams));
                          exams.clear();
                      }
                      if (reader.getLocalName().equals("Exam")) {
                    	  exams.add(new Exam(name, eval));
                      }
                  }
              }
              table.updateTable();
          } catch (Exception e) {
        	  System.err.println(e.getMessage());
              JOptionPane.showMessageDialog
                      (null, "Can't open file", "ERROR", JOptionPane.ERROR_MESSAGE);
          }
      }
  }
}