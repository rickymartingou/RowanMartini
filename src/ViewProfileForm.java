import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import javax.swing.*;

public class ViewProfileForm extends JInternalFrame implements ActionListener,WindowListener {
	
	Connect con;
	
	MainForm mainForm;
	JPanel finalMainPanel,mainPanel,mainPanel2,panelContain,panelTitle,panelUsername,panelPhone,panelGender,panelButton;
	JPanel panelTxtPhone,panelTxtGender,panelTxtUsername;
	
	JLabel lblUsername,lblPhone,lblGender,lblTitle;
	JTextField txtUsername,txtPhone,txtGender;
	JButton btnSubmit,btnUpdate,btnCancel;
	JRadioButton radioMale,radioFemale;
	
	ButtonGroup radioGroup;
	String radio,userPhone,userGender,userName;
	
	public void setButtonState(boolean state)
	{
		btnUpdate.setEnabled(!state);
		btnCancel.setEnabled(state);
		btnSubmit.setEnabled(state);
	}
	
	public void init()
	{	
		finalMainPanel = new JPanel();
		mainPanel = new JPanel(new GridLayout(3,2));
		mainPanel2 = new JPanel();
		panelTitle = new JPanel();
		panelUsername = new JPanel();
		panelPhone = new JPanel();
		panelGender = new JPanel();
		panelContain = new JPanel();
		panelTxtUsername = new JPanel();
		panelTxtPhone = new JPanel();
		panelTxtGender = new JPanel();
		panelButton = new JPanel();
		
		lblUsername = new JLabel("Username",SwingConstants.CENTER);
		lblPhone = new JLabel("Phone Number",SwingConstants.CENTER);
		lblGender = new JLabel("Gender",SwingConstants.CENTER);
		lblTitle = new JLabel("Profile",SwingConstants.CENTER);
		lblTitle.setFont(new Font("",Font.BOLD,15));
		
		btnSubmit = new JButton("Update");
		btnUpdate = new JButton("Edit");
		btnCancel = new JButton("Cancel");
		
		txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(120, 25));
		txtUsername.setEnabled(false);
		
		txtPhone = new JTextField();
		txtPhone.setPreferredSize(new Dimension(120, 25));
		txtPhone.setEnabled(false);
		
		radioMale = new JRadioButton("Male");
		radioFemale = new JRadioButton("Female");
		radioMale.setEnabled(false);
		radioFemale.setEnabled(false);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(radioMale);
		radioGroup.add(radioFemale);
		
		panelUsername.add(lblUsername);
		panelUsername.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelTxtUsername.add(txtUsername);
		panelTxtUsername.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelPhone.add(lblPhone);
		panelPhone.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelTxtPhone.add(txtPhone);
		panelTxtPhone.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelGender.add(lblGender);
		panelGender.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelTxtGender.add(radioMale);
		panelTxtGender.add(radioFemale);
		panelTxtGender.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelButton.add(btnUpdate);
		panelButton.add(btnSubmit);
		panelButton.add(btnCancel);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		mainPanel.add(panelUsername);
		mainPanel.add(panelTxtUsername);
		mainPanel.add(panelPhone);
		mainPanel.add(panelTxtPhone);
		mainPanel.add(panelGender);
		mainPanel.add(panelTxtGender);
		
		panelTitle.add(lblTitle);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER,10,2));
		
		panelContain.add(mainPanel);
		panelContain.setLayout(new FlowLayout());
		
		mainPanel2.setLayout(new BoxLayout(mainPanel2,BoxLayout.Y_AXIS));
		
		mainPanel2.add(panelTitle);
		mainPanel2.add(panelContain);
		
		finalMainPanel.add(mainPanel2);
		finalMainPanel.add(panelButton);
		
		add(finalMainPanel);
		
		btnUpdate.addActionListener(this);
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);
		
		setButtonState(false);
		
		txtUsername.setText(userName);
		txtUsername.setEnabled(false);
		
		txtPhone.setText(userPhone);
		
		if(userGender.equals("Male"))
		{
			radioMale.setSelected(true);
		}
		else if(userGender.equals("Female"))
		{
			radioFemale.setSelected(true);
		}
	}
	
	private void getUserData()
	{
		con.rs = con.executeQuery("SELECT username,phone,gender FROM users WHERE id = "+Integer.parseInt(mainForm.loginForm.tempID));
		
		try {
			con.rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			userName = con.rs.getString("username");
			userPhone = con.rs.getString("phone");
			userGender = con.rs.getString("gender");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initFrame()
	{
		setSize(320,210);
		setLocation(mainForm.getWidth() / 2 - getWidth() / 2, mainForm.getHeight() / 2 - getHeight() / 2);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public ViewProfileForm(MainForm mainForm, Connect con)
	{
		super("MyProfile",true,true,false);
		this.mainForm=mainForm;
		this.con = con;
		getUserData();
		init();
		initFrame();

		setVisible(true);
	}
	
	private boolean numericChecker(String test){
		int digit = 0;
		
		for(int i=0;i<test.length();i++){
			if(Character.isDigit(test.charAt(i))){
				digit++;
			}
		}
		
		if(digit == test.length())
			return true;
		else
			return false;
	}

	@Override
	public void actionPerformed(ActionEvent src) {
		// TODO Auto-generated method stub
		if(src.getSource()==btnUpdate)
		{
			txtPhone.setEnabled(true);
			radioMale.setEnabled(true);
			radioFemale.setEnabled(true);
			setButtonState(true);
		}
		else if(src.getSource()==btnSubmit)
		{
			txtPhone.setEnabled(false);
			radioMale.setEnabled(false);
			radioFemale.setEnabled(false);
			
			if(radioMale.isSelected())
			{
				radio = "Male";
			}
			else if(radioFemale.isSelected())
			{
				radio = "Female";
			}
			
			if(txtPhone.getText().equals(""))
			{
				txtPhone.setText(userPhone);
				
				if(userGender.equals("Male"))
				{
					radioMale.setSelected(true);
				}
				else if(userGender.equals("Female"))
				{
					radioFemale.setSelected(true);
				}
				JOptionPane.showMessageDialog(null,"Phone number must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(numericChecker(txtPhone.getText().toString()) == false)
			{
				txtPhone.setText(userPhone);
				
				if(userGender.equals("Male"))
				{
					radioMale.setSelected(true);
				}
				else if(userGender.equals("Female"))
				{
					radioFemale.setSelected(true);
				}
				JOptionPane.showMessageDialog(null,"Phone Number must be numeric", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtPhone.getText().length() != 11)
			{
				txtPhone.setText(userPhone);
				
				if(userGender.equals("Male"))
				{
					radioMale.setSelected(true);
				}
				else if(userGender.equals("Female"))
				{
					radioFemale.setSelected(true);
				}
				JOptionPane.showMessageDialog(null, "Phone Number must be 11 digits", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(!radioMale.isSelected()&&!radioFemale.isSelected())
			{
				txtPhone.setText(userPhone);
				
				if(userGender.equals("Male"))
				{
					radioMale.setSelected(true);
				}
				else if(userGender.equals("Female"))
				{
					radioFemale.setSelected(true);
				}
				JOptionPane.showMessageDialog(null,"Gender must be selected", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				con.executeUpdateProfileToUsers(txtPhone.getText().toString(),radio,Integer.parseInt(mainForm.loginForm.tempID));
				JOptionPane.showMessageDialog(null,"Update Success !");
			}
	
			setButtonState(false);
		}
		else if(src.getSource()==btnCancel)
		{	
			txtPhone.setEnabled(false);
			radioMale.setEnabled(false);
			radioFemale.setEnabled(false);	
			setButtonState(false);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		mainForm.enableMenu(false);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		mainForm.enableMenu(false);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
