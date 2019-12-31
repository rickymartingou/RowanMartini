import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class RegisterForm extends JFrame implements ActionListener {
	
	LoginForm loginForm;
	Connect con;
	
	JPanel panelPhone,panelUsername,panelPassword,panelConfirm,panelGender,panelTitle;
	JPanel panelTxtPhone,panelTxtUsername,panelTxtPassword,panelTxtConfirm,panelTxtGender,panelCheck;
	JPanel panelForm,panelContain,panelForm2,panelButton;
	
	JLabel lblPhone,lblUsername,lblPassword,lblConfirmPassword,lblGender,lblTitle;
	JTextField txtPhone,txtUsername;
	JPasswordField txtPassword,txtConfirmPassword;
	JRadioButton radioMale,radioFemale;
	ButtonGroup radioGroup;
	JCheckBox checkBox;
	JButton btnRegister,btnCancel;
	
	int lastID;
	String gender="",pw="";
	
	private void init()
	{
		panelForm = new JPanel(new GridLayout(6,2));
		panelForm2 = new JPanel();
		panelContain = new JPanel();
		panelPhone= new JPanel();
		panelUsername = new JPanel();
		panelPassword = new JPanel();
		panelConfirm = new JPanel();
		panelGender = new JPanel();
		panelTitle = new JPanel();
		
		panelTxtPhone = new JPanel();
		panelTxtUsername = new JPanel();
		panelTxtPassword = new JPanel();
		panelTxtConfirm = new JPanel();
		panelTxtGender = new JPanel();
		panelCheck = new JPanel();
		
		panelButton = new JPanel();
		
		lblTitle = new JLabel("Register",SwingConstants.CENTER);
		lblTitle.setFont(new Font("", Font.BOLD, 15));
		lblPhone = new JLabel("Phone Number");
		lblUsername = new JLabel("Username");
		lblPassword = new JLabel("Password");
		lblConfirmPassword = new JLabel("Confirm Password");
		lblGender = new JLabel("Gender");
		
		txtPhone = new JTextField();
		txtPhone.setPreferredSize(new Dimension(130, 25));
		
		txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(130, 25));
		
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(130, 25));
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setPreferredSize(new Dimension(130, 25));
		
		radioMale = new JRadioButton("Male");
		radioFemale = new JRadioButton("Female");
		
		radioGroup = new ButtonGroup();
		radioGroup.add(radioMale);
		radioGroup.add(radioFemale);
		
		checkBox = new JCheckBox("<html><font color=#0000FF>Terms and Condition</html>");
		
		btnRegister = new JButton("Register");
		btnCancel = new JButton("Cancel");
		
		panelTitle.add(lblTitle);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
		
		panelUsername.add(lblUsername);
		panelUsername.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtUsername.add(txtUsername);
		panelTxtUsername.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelPassword.add(lblPassword);
		panelPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtPassword.add(txtPassword);
		panelTxtPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelConfirm.add(lblConfirmPassword);
		panelConfirm.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtConfirm.add(txtConfirmPassword);
		panelTxtConfirm.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelPhone.add(lblPhone);
		panelPhone.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtPhone.add(txtPhone);
		panelTxtPhone.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelGender.add(lblGender);
		panelGender.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtGender.add(radioMale);
		panelTxtGender.add(radioFemale);
		panelTxtGender.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelCheck.add(checkBox);
		panelCheck.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelButton.add(btnRegister);
		panelButton.add(btnCancel);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		
		panelForm.add(panelUsername);
		panelForm.add(panelTxtUsername);
		panelForm.add(panelPassword);
		panelForm.add(panelTxtPassword);
		panelForm.add(panelConfirm);
		panelForm.add(panelTxtConfirm);
		panelForm.add(panelPhone);
		panelForm.add(panelTxtPhone);
		panelForm.add(panelGender);
		panelForm.add(panelTxtGender);
		panelForm.add(panelCheck);
		panelForm.add(new JPanel());
		
		panelForm2.add(panelForm);
		
		panelContain.add(panelForm2, "North");
		
		add(panelTitle,"North");
		add(panelContain,"West");
		add(panelButton,"South");
		
		btnRegister.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	private void initFrame()
	{
		setSize(340,320);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	private boolean alphanumericChecker(String test)
	{
		int charCount=0,digitCount=0;
		
		for(int i=0;i<test.length();i++)
		{
			if(Character.isLetter(test.charAt(i)))
			{
				charCount=1;
			}
			else if(Character.isDigit(test.charAt(i)))
			{
				digitCount=1;
			}
		}
		if(charCount==1&&digitCount==1)
		{
			return true;
		}
		else
		{
			return false;
		}
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
	
	public RegisterForm(LoginForm loginForm, Connect con) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.loginForm = loginForm;
		init();
		initFrame();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent src) {
		// TODO Auto-generated method stub
		if(src.getSource()==btnRegister)
		{
			if(txtUsername.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Username must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtUsername.getText().length()<5||txtUsername.getText().length()>20)
			{
				JOptionPane.showMessageDialog(null, "Username length must be between 5-20 character", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtPassword.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Password must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtPassword.getText().length()<5||txtPassword.getText().length()>20)
			{
				JOptionPane.showMessageDialog(null, "Password length must be between 5-20 character", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(alphanumericChecker(txtPassword.getText().toString())==false)
			{
				JOptionPane.showMessageDialog(null,"Password must contain at least 1 alphabet and 1 digit", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtConfirmPassword.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Confirm Password must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(!txtPassword.getText().equals(txtConfirmPassword.getText()))
			{
				JOptionPane.showMessageDialog(null, "Password and confirm password must be the same.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtPhone.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Phone number must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(numericChecker(txtPhone.getText().toString()) == false)
			{
				JOptionPane.showMessageDialog(null, "Phone number must be numeric", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtPhone.getText().length() != 11)
			{
				JOptionPane.showMessageDialog(null, "Phone number must be 11 digits", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(!radioMale.isSelected()&&!radioFemale.isSelected())
			{
				JOptionPane.showMessageDialog(null, "Gender must be selected", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(!checkBox.isSelected())
			{
				JOptionPane.showMessageDialog(null, "Terms and condition must be accepted", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(radioMale.isSelected())
				{
					gender="Male";
				}
				else
				{
					gender="Female";
				}
				
				pw=txtPassword.getText().toString();
				
				con.rs = con.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 0,1");
				
				try {
					con.rs.next();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					lastID = con.rs.getInt("id")+1;
					System.out.println(lastID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				con.rs = con.executeQuery("SELECT username FROM users WHERE username = '"+ txtUsername.getText().toString()+"'");
				
				try {
					if (con.rs.next()) 
					{
						JOptionPane.showMessageDialog(null, "This Username is already exist !", "Error", JOptionPane.ERROR_MESSAGE);
					} 
					else
					{
						con.executeInsertToUsers(lastID,txtUsername.getText().toString(),pw,txtPhone.getText().toString(),gender,"User");
						
						dispose();
						LoginForm loginForm = new LoginForm(con);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(src.getSource()==btnCancel)
		{
			dispose();
			LoginForm loginForm = new LoginForm(con);
		}
	}

}
