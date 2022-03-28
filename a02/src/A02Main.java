
public class A02Main {

	// The purpose of this class is to start the program and display the GUI

	public static void main(String[] args) {
		A02MiddleTier middleTier = new A02MiddleTier();
		A02FrontEnd window = new A02FrontEnd(middleTier);
		window.setVisible(true);
	}

}
