import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.*;

public class LoginForm extends JFrame implements ActionListener,MouseListener {
	
	MainForm mainForm;
	Connect con;
	
	public String tempID="";
	
	JPanel panelLblUsername,panelLblPassword,panelTxtUsername,panelTxtPassword,panelLblTitle,panelButton,panelRegis,panelRegisFinal;
	JPanel panelForm,panelContain,panelSouth;
	
	JLabel lblTitle;
	JLabel lblUserName;
	JLabel lblPassword;
	JLabel lblRegister,lblClickHere;
	
	JTextField txtUserName;
	JPasswordField txtPassword;
	
	JButton btnLogin;
	
	public boolean isLoginUser=false;
	public boolean isLoginAdmin=false;
	
	String userName,userPassword;
	
	private void init()
	{
		panelRegisFinal = new JPanel();
		panelLblTitle = new JPanel();
		panelLblUsername = new JPanel();
		panelTxtUsername = new JPanel();
		panelLblPassword = new JPanel();
		panelTxtPassword = new JPanel();
		panelButton = new JPanel();
		panelRegis = new JPanel();
		panelContain = new JPanel();
		panelSouth = new JPanel();
		
		panelForm = new JPanel(new GridLayout(4,1));
		panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.Y_AXIS));
		
		lblTitle = new JLabel("Login",SwingConstants.CENTER);
		lblTitle.setFont(new Font("", Font.BOLD, 15));
		
		lblUserName = new JLabel("Username");
		lblPassword = new JLabel("Password");
		lblRegister = new JLabel("Dont Have an Account ?");
		lblClickHere = new JLabel("<html><font color=#0000FF>Click Here to Register !</html>");
		
		txtUserName = new JTextField();
		txtUserName.setPreferredSize(new Dimension(250,25));
		txtPassword = new JPasswordField();
		txtPassword.setPreferredSize(new Dimension(250,25));
		
		btnLogin = new JButton("Login");
		
		panelLblTitle.setLayout(new FlowLayout(FlowLayout.CENTER,10,2));
		panelLblTitle.add(lblTitle);
		
		panelLblUsername.add(lblUserName);
		panelLblUsername.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		
		panelTxtUsername.add(txtUserName);
		panelTxtUsername.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		
		panelLblPassword.add(lblPassword);
		panelLblPassword.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		
		panelTxtPassword.add(txtPassword);
		panelTxtPassword.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		
		panelButton.add(btnLogin);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER,10,2));
		
		panelRegis.add(lblRegister);
		panelRegisFinal.setLayout(new FlowLayout());
		panelRegisFinal.add(panelRegis);
		panelRegisFinal.add(lblClickHere);
		
		panelForm.add(panelLblUsername);
		panelForm.add(panelTxtUsername);
		panelForm.add(panelLblPassword);
		panelForm.add(panelTxtPassword);
		
		panelSouth.add(panelButton);
		panelSouth.add(panelRegisFinal);
		
		panelContain.add(panelForm,"North");
		panelContain.add(panelSouth,"Center");
		
		add(panelLblTitle,"North");
		add(panelContain,"Center");
		
		lblClickHere.addMouseListener(this);
		btnLogin.addActionListener(this);
	}
	
	private void initFrame()
	{
		setSize(300, 240);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public LoginForm(Connect con) {
		// TODO Auto-generated constructor stub
		this.con=con;
		init();
		initFrame();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent src) {
		// TODO Auto-generated method stub
		if(src.getSource()==btnLogin)
		{
			if(txtUserName.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Username must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(txtPassword.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Password must be filled", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				con.rs = con.executeQuery("SELECT * FROM users WHERE username = '"
						+ txtUserName.getText() + "' AND password = '"
						+ txtPassword.getText() + "'");
				
				try {
					if (con.rs.next()) {
						JOptionPane.showMessageDialog(null, "Welcome, "
								+ txtUserName.getText());
						userName = txtUserName.getText().toString();
						userPassword = txtPassword.getText().toString();

						this.dispose();
						if(con.rs.getString("role").equals("Admin"))
						{
							isLoginAdmin=true;
						}
						else
						{
							isLoginUser=true;
						}
						
						tempID = con.rs.getString("id").toString();
						MainForm mainForm = new MainForm(this,con);
						
					} else
						JOptionPane.showMessageDialog(null,"Invalid username/password");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(lblClickHere.getMouseListeners() != null)
		{
			dispose();
			RegisterForm registerForm = new RegisterForm(this,con);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
