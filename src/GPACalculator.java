import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GPACalculator extends JFrame implements ActionListener{

	// PRIVATE INSTANCE VARIABLES+++++++++++++++++++++++++++++++++
	private int _currentNumCourses=0;
	public int hours=0,totalHours=0,gradeIndex=0,qualityPoints=0,currentHours=0;
	double grade=0,currentGPA=0,_GPA=0;
	private JPanel _contentPane;
	private JLabel _creditLabel, _GPALabel, _numCoursesLabel,_courseNumLabel,_courseCodeLabel,_creditComboLabel,_gradeLabel,_newGPALabel;
	private JTextField _hoursTextField, _currentGPATextField;
	private JComboBox<String> _numCoursesComboBox;
	private JButton _calculateButton;
	private String[] _numOfCourses;
	private ArrayList<Courses> _coursesArrayList;
	
	private double[] _pointsArray= {4.5,4,3.5,3,2.5,2,1.5,1,0};
	
	
	// CONSTRUCTOR +++++++++++++++++++++++++++++++++++++++++++++++
	public GPACalculator() {
		
		//Initialize the content pane and add components to it.
		this._initialize();
		this._fillCoursesArray();
		this._addUIComponents();	
	}
	
	// PRIVATE METHODS +++++++++++++++++++++++++++++++++++++++++++
	private void _initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 450);			
		this.setResizable(false);
		this._contentPane = new JPanel();
		this._contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this._contentPane);
		
		// Use Absolute Layout
		this._contentPane.setLayout(null);			
		// Position frame in center of window
		this.setLocationRelativeTo(null);
		
	}
	
	//Initialize coursesArray
	private void _fillCoursesArray() {
		
		this._numOfCourses=new String[10];
		//Assign number of courses from 1 to maximum number
		for(int cnt=0;cnt<this._numOfCourses.length;cnt++) {
			this._numOfCourses[cnt]=cnt+1+"";
		}
	}

	//Add Labels and text fields
	private void _addUIComponents() {	
		
		//Credit Hours earned Label
		this._creditLabel= new JLabel("Credit Hours Earned:");
	    this._creditLabel.setBounds(10, 13, 125, 14);
		this._contentPane.add(this._creditLabel);
		
		//Credit Hours Earned Text Field
		this._hoursTextField = new JTextField(currentHours+"");
		this._hoursTextField.setBackground(new Color(0, 255, 255));
		this._hoursTextField.setBounds(145, 10, 50, 20);
		this._contentPane.add(this._hoursTextField);
		
		//Current GPA Label
		this._GPALabel = new JLabel("Current GPA:");
		this._GPALabel.setBounds(315, 13, 85, 14);
		this._contentPane.add(this._GPALabel);
		
		//Current GPA Text Field
		this._currentGPATextField = new JTextField(""+currentGPA);
		this._currentGPATextField.setBackground(new Color(0, 255, 255));
		this._currentGPATextField.setBounds(400, 10, 50, 20);
		this._contentPane.add(this._currentGPATextField);
		
		//Number of Courses Label
		this._numCoursesLabel = new JLabel("Number of Courses:");
		this._numCoursesLabel.setBounds(275, 44, 125, 14);
		this._contentPane.add(this._numCoursesLabel);
		
		//Number of Courses Combo Box
		this._numCoursesComboBox = new JComboBox<String>();
		this._numCoursesComboBox.setModel(new DefaultComboBoxModel(this._numOfCourses));
		this._numCoursesComboBox.setSelectedIndex(this._currentNumCourses);
		this._numCoursesComboBox.setBounds(400, 41, 50, 20);
		this._contentPane.add(this._numCoursesComboBox);
		
		//Register event handler for numCourses combo box
		this._numCoursesComboBox.addActionListener(this);
		
		//Register Event handler for calculate button
		if(Arrays.asList(this._contentPane.getComponents()).contains(this._calculateButton))
		{
			this._calculateButton.addActionListener(this);
		}
		
	}
	
	//Add Labels
	private void _addTempComponents() {	
		//Course No. Label
		this._courseNumLabel= new JLabel("Nr.");
		this._courseNumLabel.setBounds(10,65+22,20,20);
		this._contentPane.add(this._courseNumLabel);
		
		//Course Code Label
		this._courseCodeLabel= new JLabel("Course Code:");
		this._courseCodeLabel.setBounds(50,65+22,80,20);
		this._contentPane.add(this._courseCodeLabel);
		
		//Credit Hours Label
		this._creditComboLabel= new JLabel("Credit Hours:");
		this._creditComboLabel.setBounds(200,65+22,75,20);
		this._contentPane.add(this._creditComboLabel);
		
		//Grades Label
		this._gradeLabel= new JLabel("Grade:");
		this._gradeLabel.setBounds(350,65+22,75,20);
		this._contentPane.add(this._gradeLabel);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource()==this._numCoursesComboBox)
		{
			int index;
			//Create a new array List
			this._coursesArrayList = new ArrayList<Courses>();
			this._currentNumCourses=this._numCoursesComboBox.getSelectedIndex();
			
			//Validate current credit hours and current GPA text fields
			this._validateInput();
			
			this._contentPane.removeAll();
			this._addUIComponents();
			this._addTempComponents();
			
			//Populate array list with objects of Courses class
			for(index = 0; index <= this._currentNumCourses; index++) {
				this._coursesArrayList.add(new Courses(this._contentPane,index+1));					
			}
			
			//Add calculate button and new GPA label
			this._addBottomComponents(index);
			//Update UI to render new added components
			this._contentPane.updateUI();
		}	
		
		if(event.getSource()==this._calculateButton)
		{
			int index;
			qualityPoints=0;
			totalHours=0;
				
			//Calculate total hours and total quality points
			for(index=0;index<=this._currentNumCourses;index++)
			{
				//Get credit hours for each course
				hours=Integer.parseInt(this._coursesArrayList.get(index).hoursComboBox.getSelectedItem()+"");
				//Add hours for all courses
				totalHours+=hours;
				
				//Get grade index for each course
				gradeIndex=this._coursesArrayList.get(index).gradeComboBox.getSelectedIndex();
				//Get points for grade obtained
				grade=this._pointsArray[gradeIndex];
				qualityPoints+=hours*grade;
			}	
			
			//Obtain current GPA and current credit hours from respective text fields
			this._validateInput();
			
			totalHours+=currentHours;
			qualityPoints+=currentGPA*currentHours;
			this._GPA=(double)qualityPoints/totalHours;
			
			//Display GPA in the label
			this._newGPALabel.setText(String.format("Your GPA is: %.2f",this._GPA));
			
			this._contentPane.updateUI();
		}
	}

	private void _validateInput() {
		
		//Validate current hours and current GPA. Set them to 0 if they are invalid
		try {
			currentHours=Integer.parseInt(this._hoursTextField.getText());
			
		} catch (Exception e) {
			this._hoursTextField.setText("0");
			currentHours=0;
		}
		try {
			currentGPA=Double.parseDouble(this._currentGPATextField.getText());
			
		} catch (Exception e) {
			this._currentGPATextField.setText("0");
			currentGPA=0;
		}
	}

	//Add calculate button and GPA label
	private void _addBottomComponents(int index) {
		
		//Calculate Button
		this._calculateButton=new JButton("Calculate");
		this._calculateButton.setBounds(350,65+22*(index+3),100,20);
		this._contentPane.add(this._calculateButton);
		
		//New GPA Label
		this._newGPALabel=new JLabel(String.format("Your GPA is: %.2f",this._GPA));
		this._newGPALabel.setBounds(175,375,150,20);
		this._contentPane.add(this._newGPALabel);
		
		this._calculateButton.addActionListener(this);
	}
}
