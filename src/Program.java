/*
 *Author             : Raveena Tayal
 *Student#           : 300813330
 *Last modified      : 11/19/2015
 *Program description: Create a GUI application to calculate GPA. 
 *Allow the student to enter course details and show the new GPA.                     
 */
public class Program {

	public static void main(String[] args) {
		try {
			GPACalculator frame = new GPACalculator();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
