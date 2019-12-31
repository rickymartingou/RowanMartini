import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.omg.PortableServer.ServantRetentionPolicyOperations;

public class MainForm extends JFrame implements ActionListener {

	Connect con;
	
	ViewProfileForm viewProfileForm;
	TransactionHistoryForm historyForm;
	BuyItemForm buyItemForm;
	ChangePasswordForm changePasswordForm;
	ManageItemForm manageItemForm;
	LoginForm loginForm;
	
	JDesktopPane desktopPane;
	
	JMenuBar menuBar;
	JMenu menuUser;
	JMenu menuTransaction;
	JMenu menuAdmin;
	
	JMenuItem menuItemViewProfile;
	JMenuItem menuItemLogout;
	JMenuItem menuItemBuyItem;
	JMenuItem menuItemViewTransaction;
	JMenuItem menuItemChangePassword;
	JMenuItem menuItemManageItem;
	
	BufferedImage img;
	
	public void init()
	{
		try {
			img = ImageIO.read(new File("Assets/bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		desktopPane = new JDesktopPane()
		{
			@Override
			public void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(img, 0, 0,desktopPane.getWidth(),desktopPane.getHeight(), this);
			}
		};
		
		menuBar = new JMenuBar();
		
		menuUser = new JMenu("User");
		menuTransaction = new JMenu("Transaction");
		menuAdmin = new JMenu("Manage");
		
		menuItemViewProfile = new JMenuItem("View Profile");
		menuItemLogout =  new JMenuItem("Logout");
		menuItemBuyItem =  new JMenuItem("Buy Drink");
		menuItemViewTransaction =  new JMenuItem("View Transaction");
		menuItemChangePassword =  new JMenuItem("Change Password");
		menuItemManageItem =  new JMenuItem("Manage Menu");
		
		menuBar.add(menuUser);
		menuBar.add(menuTransaction);
		menuBar.add(menuAdmin);
		
		menuUser.add(menuItemViewProfile);
		menuUser.add(menuItemChangePassword);
		menuUser.add(menuItemLogout);
		menuTransaction.add(menuItemBuyItem);
		menuTransaction.add(menuItemViewTransaction);
		menuAdmin.add(menuItemManageItem);
		
		menuItemLogout.addActionListener(this);
		menuItemViewProfile.addActionListener(this);
		menuItemBuyItem.addActionListener(this);
		menuItemViewTransaction.addActionListener(this);
		menuItemChangePassword.addActionListener(this);
		menuItemManageItem.addActionListener(this);
	}
	
	public void initFrame()
	{
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void enableMenu(boolean isOpen)
	{
		menuAdmin.setEnabled(!isOpen);
		menuTransaction.setEnabled(!isOpen);
		menuUser.setEnabled(!isOpen);
	}
	
	public MainForm(LoginForm loginForm, Connect con) {
		// TODO Auto-generated constructor stub
		this.con = con;
		this.loginForm = loginForm;
		init();
		initFrame();
		
		setContentPane(desktopPane);
		
		setJMenuBar(menuBar);
		
		setVisible(true);
		
		menuAdmin.setVisible(loginForm.isLoginAdmin);
		menuItemBuyItem.setVisible(loginForm.isLoginUser);
		menuItemViewProfile.setVisible(loginForm.isLoginUser);
		menuItemChangePassword.setVisible(loginForm.isLoginUser);
		
		enableMenu(false);
		setTitle("Rowan Martini");
	}

	@Override
	public void actionPerformed(ActionEvent src) {
		// TODO Auto-generated method stub
		
		if(src.getSource()==menuItemViewProfile)
		{
			viewProfileForm = new ViewProfileForm(this,con);
			desktopPane.add(viewProfileForm);
			viewProfileForm.setVisible(true);
		}
		else if(src.getSource()==menuItemViewTransaction)
		{
			historyForm = new TransactionHistoryForm(this,con);
			desktopPane.add(historyForm);
			historyForm.setVisible(true);
		}
		else if(src.getSource()==menuItemBuyItem)
		{
			buyItemForm = new BuyItemForm(this,con);
			desktopPane.add(buyItemForm);
			buyItemForm.setVisible(true);
		}
		else if(src.getSource()==menuItemChangePassword)
		{
			changePasswordForm = new ChangePasswordForm(this,con);
			desktopPane.add(changePasswordForm);
			changePasswordForm.setVisible(true);
		}
		else if(src.getSource()==menuItemManageItem)
		{
			manageItemForm = new ManageItemForm(this,con);
			desktopPane.add(manageItemForm);
			manageItemForm.setVisible(true);
		}
		else if(src.getSource()==menuItemLogout)
		{
			dispose();
			loginForm = new LoginForm(con);
			loginForm.isLoginAdmin=false;
			loginForm.isLoginUser=false;
		}
	}

}
