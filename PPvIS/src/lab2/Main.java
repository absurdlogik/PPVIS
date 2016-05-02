package lab2;

import lab2.Controller.Controller;
import lab2.Model.MainModel;
import lab2.View.MainView;

public class Main {
	public static void main(String[] args) {
		MainModel theModel = new MainModel();

        Controller theController = new Controller(theModel);

        new MainView(theModel, theController, "");
	}
}
