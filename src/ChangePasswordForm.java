import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ChangePasswordForm extends JInternalFrame implements ActionListener {

	MainForm mainForm;
	ResultSet resS;
	Connect con;
	String oldPW;
	
	JPanel panelForm,panelTitle,panelContain,panelMain;
	JPanel panelLblOldPassword,panelLblNewPassword,panelLblConfirmPassword;
	JPanel panelTxtOldPassword,panelTxtNewPassword,panelTxtConfirmPassword;
	JPanel panelButton;
	
	JLabel lblTitle,lblOldPassword,lblNewPassword,lblConfirmPassword;
	JPasswordField txtOldPassword,txtNewPassword,txtConfirmPassword;
	
	JButton btnSave;
	
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
	
	public void init()
	{
		panelTitle = new JPanel();
		panelForm = new JPanel(new GridLayout(3,2));
		panelLblOldPassword = new JPanel();
		panelLblNewPassword = new JPanel();
		panelLblConfirmPassword = new JPanel();
		panelTxtOldPassword = new JPanel();
		panelTxtNewPassword = new JPanel();
		panelTxtConfirmPassword = new JPanel();
		panelButton = new JPanel();
		panelContain = new JPanel();
		panelMain = new JPanel();
		
		lblTitle = new JLabel("Change Password");
		lblTitle.setFont(new Font("",Font.BOLD,15));
		
		lblOldPassword = new JLabel("Old Password");
		lblNewPassword = new JLabel("New Password");
		lblConfirmPassword = new JLabel("Confirm Password");
		
		txtOldPassword = new JPasswordField();
		txtOldPassword.setPreferredSize(new Dimension(125,23));
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setPreferredSize(new Dimension(125,23));
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setPreferredSize(new Dimension(125,23));
		
		btnSave = new JButton("Save");
		
		panelLblOldPassword.add(lblOldPassword);
		panelLblOldPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelLblNewPassword.add(lblNewPassword);
		panelLblNewPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelLblConfirmPassword.add(lblConfirmPassword);
		panelLblConfirmPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtOldPassword.add(txtOldPassword);
		panelLblConfirmPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtNewPassword.add(txtNewPassword);
		panelLblConfirmPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtConfirmPassword.add(txtConfirmPassword);
		panelLblConfirmPassword.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelButton.add(btnSave);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panelForm.add(panelLblOldPassword);
		panelForm.add(panelTxtOldPassword);
		panelForm.add(panelLblNewPassword);
		panelForm.add(panelTxtNewPassword);
		panelForm.add(panelLblConfirmPassword);
		panelForm.add(panelTxtConfirmPassword);
		
		panelTitle.add(lblTitle);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER,10,2));
		
		panelContain.add(panelForm);
		panelContain.setLayout(new FlowLayout());
		
		panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.Y_AXIS));
		panelMain.add(panelTitle);
		panelMain.add(panelContain);
		panelMain.add(panelButton);
		
		add(panelMain);
		
		btnSave.addActionListener(this);
	}
	
	public void initFrame()
	{
		setSize(320,205);
		setLocation(mainForm.getWidth() / 2 - getWidth() / 2, mainForm.getHeight() / 2 - getHeight() / 2);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public ChangePasswordForm(MainForm mainForm, Connect con) {
		// TODO Auto-generated constructor stub
		super("ViewProfile",true,true,false);
		this.mainForm=mainForm;
		this.con = con;
		init();
		initFrame();

		setVisible(true);
		
		resS = con.executeQuery("SELECT password FROM users WHERE id ="+ mainForm.loginForm.tempID);
		
		try {
			resS.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			oldPW = resS.getString("password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent src) {
		// TODO Auto-generated method stub
		if(src.getSource()==btnSave)
		{
			if(txtOldPassword.getText()=="")
			{
				JOptionPane.showMessageDialog(null,"Old password must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtNewPassword.getText()=="")
			{
				JOptionPane.showMessageDialog(null,"New password must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtConfirmPassword.getText()=="")
			{
				JOptionPane.showMessageDialog(null,"Confirm password must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{			
				if(!txtOldPassword.getText().equals(oldPW))
				{
					JOptionPane.showMessageDialog(null,"Old password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(txtNewPassword.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Old password must be filled", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(txtNewPassword.getText().length()<5||txtNewPassword.getText().length()>20)
				{
					JOptionPane.showMessageDialog(null, "New password length must be between 5 - 20 character", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!alphanumericChecker(txtNewPassword.getText().toString()))
				{
					JOptionPane.showMessageDialog(null, "New password must be alphanumeric", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!txtNewPassword.getText().equals(txtConfirmPassword.getText()))
				{
					JOptionPane.showMessageDialog(null,"Confirm password and new password must be the same", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					con.executeUpdateToUsers(txtNewPassword.getText(), Integer.parseInt(mainForm.loginForm.tempID));
					JOptionPane.showMessageDialog(null,"Change Password Success");
					
					txtOldPassword.setText("");
					txtNewPassword.setText("");
					txtConfirmPassword.setText("");
				}
			}
		}
	}

}
