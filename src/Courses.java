import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Courses {

	//PUBLIC VARIABLES+++++++++++++++++++++++++++++++++++++++
	public int index;
	public JLabel courseNumLabel;
	public JTextField courseCodeTextField;
	public JComboBox<String> hoursComboBox, gradeComboBox;
	public JPanel contentPane;
	public String[] gradeArray= {"A+ 90-100", "A: 80-89","B+ 75-79", "B: 70-74","C+ 65-69", "C: 60-64","D+ 55-59", "D: 50-54","F: 0-49"};
	
	//CONSTRUCTOR++++++++++++++++++++++++++++++++++++++++++++
	public Courses(JPanel contentPane,int index) {
		
		this.index=index;
		this.contentPane=contentPane;
		this.courseNumLabel=new JLabel();
		this.courseCodeTextField=new JTextField();
		this.hoursComboBox=new JComboBox<String>();
		this.gradeComboBox=new JComboBox<String>();
		
		this._initializeComponents();
	}

	//Create GUI Components dynamically
	private void _initializeComponents()
	{
		//Setting the vertical position of each component
		int y=65+22+this.index*22;
		
		//Course No. Label
		this.courseNumLabel.setText(this.index+"");
		this.courseNumLabel.setBounds(10,y,20,20);
		this.contentPane.add(this.courseNumLabel);
		
		//Course Code Text Field
		this.courseCodeTextField.setBounds(50,y,75,20);
		this.courseCodeTextField.setBackground(new Color(0, 255, 255));
		this.contentPane.add(this.courseCodeTextField);
		
		//Credit Hours Combo Box
		this.hoursComboBox.setBounds(200,y,75,20);
		this.hoursComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"2", "3", "4"}));
		this.contentPane.add(this.hoursComboBox);
		
		//Grades Combo Box
		this.gradeComboBox.setBounds(350,y,100,20);
		this.gradeComboBox.setModel(new DefaultComboBoxModel<String>(gradeArray));
		this.contentPane.add(this.gradeComboBox);
		
		this.contentPane.updateUI();
	}
}
