import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TransactionHistoryForm extends JInternalFrame implements ItemListener{

	MainForm mainForm;
	Connect con;
	
	JPanel panelTitle,panelTableHeader,panelTableDetail;
	JPanel panelHeader,panelDetail,panelContainHeader,panelContainDetail,panelMaster;
	JPanel panelDummy;
	JPanel panelButton,panelForm,panelFormFinal;
	JPanel panelLblID,panelTxtID,panelLblUsername,panelTxtUsername;
	JPanel panelLblDate,panelLblTotalPrice, panelTxtDate,panelTxtTotalPrice;
	
	DefaultTableModel dtmHeader,dtmDetail;
	JTable tableHeaderHistory,tableDetailHistory;
	
	JLabel lblTitle,lblID,lblDate,lblTotalPrice, txtDate, txtTotalPrice, lblUsername, txtUsername;
	
	JComboBox comboID;
	
	JScrollPane scrollHeader,scrollDetail;
	
	Vector<Object> vecId = new Vector<Object>();
	Vector<Object> tHeader,tContent;
	Vector<Object> tHeaderDetail,tContentDetail;
	
	int temp=0,countData=0;
	boolean isSelectTable=false,isComboSelected=false;
	String status;
	
	private void initTable()
	{
		tHeader = new Vector<Object>();
		tHeader.add("Transaction ID");
		if(mainForm.loginForm.isLoginAdmin)
		{
			tHeader.add("Username");
		}
		tHeader.add("Date");
		dtmHeader = new DefaultTableModel(tHeader, 0);
		
		tHeaderDetail = new Vector<Object>();
		tHeaderDetail.add("Transaction ID");
		tHeaderDetail.add("Menu ID");
		tHeaderDetail.add("Quantity");
		tHeaderDetail.add("Price");
		dtmDetail = new DefaultTableModel(tHeaderDetail,0);
		
		if(mainForm.loginForm.isLoginUser)
		{
			con.rs = con.executeQuery("SELECT * FROM transactions WHERE id = "+mainForm.loginForm.tempID);
		}
		else if(mainForm.loginForm.isLoginAdmin)
		{
			con.rs = con.executeQuery("SELECT * FROM transactions");
		}
		fillTableAdmin();
	}
	
	private void clearTableAdmin()
	{
		for(int i=0;i<countData;i++)
		{
			dtmHeader.removeRow(0);
		}
	}
	
	private void fillTableAdmin()
	{
		if(mainForm.loginForm.isLoginAdmin)
		{
			con.rs = con.executeQuery("SELECT transactions.id,users.username,date FROM transactions"
				+ " INNER JOIN users ON transactions.userId=users.id");
		}
		else if(mainForm.loginForm.isLoginUser)
		{
			con.rs = con.executeQuery("SELECT id,date FROM transactions WHERE userId="+mainForm.loginForm.tempID);
		}
		vecId.addElement("--SELECT--");
		try {
			while (con.rs.next()) {
				tContent = new Vector<Object>();
				for (int i = 1; i <= con.rsm.getColumnCount(); i++)
				{
					tContent.add(con.rs.getObject(i) + "");
					
					if(i == 1)
						vecId.add(con.rs.getObject(1)+"");
					
				}
				countData++;
				dtmHeader.addRow(tContent);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void init()
	{	
		panelTitle = new JPanel();
		panelTableHeader = new JPanel(new GridLayout(4,2));
		panelTableDetail = new JPanel();
		panelHeader = new JPanel(new GridLayout(2,1));
		panelDetail = new JPanel(new GridLayout(2,1));
		panelMaster = new JPanel(new GridLayout(2,1));
		panelDummy = new JPanel();
		panelContainHeader = new JPanel();
		panelContainDetail = new JPanel();
		panelButton = new JPanel();
		panelForm = new JPanel(new GridLayout(2,2));
		
		tableDetailHistory = new JTable(dtmDetail);
		tableHeaderHistory = new JTable(dtmHeader);
		
		scrollDetail = new JScrollPane(tableDetailHistory);
		scrollHeader = new JScrollPane(tableHeaderHistory);
		
		scrollDetail.setPreferredSize(new Dimension(400,150));
		scrollHeader.setPreferredSize(new Dimension(400,150));
		
		lblTitle = new JLabel("Transaction");
		lblTitle.setFont(new Font("",Font.BOLD,15));
		
		//New		
		lblID = new JLabel("Transaction ID");
		lblDate = new JLabel("Date");
		lblTotalPrice = new JLabel("Total Price");
		txtDate = new JLabel("");
		txtTotalPrice = new JLabel("");
		lblUsername = new JLabel("Username");
		txtUsername = new JLabel("");
		comboID = new JComboBox<>(vecId);
		comboID.setPreferredSize(new Dimension(120,20));
		
		panelLblDate = new JPanel();
		panelLblDate.add(lblDate);
		panelLblDate.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelLblTotalPrice = new JPanel();
		panelLblTotalPrice.add(lblTotalPrice);
		panelLblTotalPrice.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelTxtDate = new JPanel();
		panelTxtDate.add(txtDate);
		panelTxtDate.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelTxtTotalPrice = new JPanel();
		panelTxtTotalPrice.add(txtTotalPrice);
		panelTxtTotalPrice.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelLblID = new JPanel();
		panelLblID.add(lblID);
		panelLblID.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelTxtID = new JPanel();
		panelTxtID.add(comboID);
		panelTxtID.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelLblUsername = new JPanel();
		panelLblUsername.add(lblUsername);
		panelLblUsername.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		
		panelTxtUsername = new JPanel();
		panelTxtUsername.add(txtUsername);
		panelTxtUsername.setLayout(new FlowLayout(FlowLayout.LEFT,10,2));
		//New
		
		panelTitle.add(lblTitle);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panelTableHeader.add(panelLblID);
		panelTableHeader.add(panelTxtID);
		
		if(mainForm.loginForm.isLoginAdmin){
			panelTableHeader.add(panelLblUsername);
			panelTableHeader.add(panelTxtUsername);
		}
		
		panelTableHeader.add(panelLblDate);
		panelTableHeader.add(panelTxtDate);
		panelTableHeader.add(panelLblTotalPrice);
		panelTableHeader.add(panelTxtTotalPrice);
		
		panelContainHeader.add(panelTableHeader);
		panelContainHeader.setLayout(new FlowLayout());
		
		panelTableDetail.add(scrollDetail);
		panelTableDetail.setLayout(new FlowLayout());
		
		panelDummy.setLayout(new BoxLayout(panelDummy, BoxLayout.Y_AXIS));
		panelDummy.add(panelTitle);
		panelDummy.add(panelContainHeader);
		panelDummy.add(panelTableDetail);
		
		add(panelDummy);
		
		comboID.addItemListener(this);
		
		tableDetailHistory.setFillsViewportHeight(true);
		tableDetailHistory.getTableHeader().setReorderingAllowed(false);
		tableDetailHistory.getTableHeader().setResizingAllowed(false);
		tableDetailHistory.setDefaultEditor(Object.class,null);
		tableDetailHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tableHeaderHistory.setFillsViewportHeight(true);
		tableHeaderHistory.getTableHeader().setReorderingAllowed(false);
		tableHeaderHistory.getTableHeader().setResizingAllowed(false);
		tableHeaderHistory.setDefaultEditor(Object.class,null);
		tableHeaderHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	private void initFrame()
	{
		setSize(new Dimension(450,390));
		
		setLocation(mainForm.getWidth() / 2 - getWidth() / 2, mainForm.getHeight() / 2 - getHeight() / 2);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public TransactionHistoryForm(MainForm mainForm, Connect con) {
		// TODO Auto-generated constructor stub
		super("Transaction",true,true,false);
		this.mainForm = mainForm;
		this.con = con;
		initTable();
		initFrame();
		init();
		
		setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(comboID.getSelectedItem().toString() != "--SELECT--")
		{	
			int totalPrice = 0;
			isSelectTable=true;
			
			for(int i=0;i<temp;i++)
			{
				dtmDetail.removeRow(0);
			}
			String query = comboID.getSelectedItem().toString();
			temp=0;
			con.rs = con.executeQuery("SELECT transactionId,menus.name,detailtransactions.quantity,detailtransactions.price FROM detailtransactions" 
			+ " INNER JOIN menus ON detailtransactions.menuId=menus.id" +" WHERE transactionId ="+query);
			
			try {
				while (con.rs.next()) {
					tContentDetail = new Vector<Object>();
					for (int i = 1; i <= con.rsm.getColumnCount(); i++)
					{
						tContentDetail.add(con.rs.getObject(i) + "");
						
						if(i == 4)
						totalPrice += Integer.parseInt(con.rs.getObject(4).toString());
					}
					dtmDetail.addRow(tContentDetail);
					temp++;
				}
				
				tableDetailHistory = new JTable(dtmDetail);
			} catch (SQLException sr) {
				// TODO Auto-generated catch block
				sr.printStackTrace();
			}
			
			txtTotalPrice.setText(totalPrice+"");
			
			
			con.rs = con.executeQuery("SELECT date FROM transactions WHERE id="+query);
			
			try {
				con.rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				txtDate.setText(con.rs.getString("date"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(mainForm.loginForm.isLoginAdmin){
				con.rs = con.executeQuery("SELECT username FROM transactions tr JOIN users u on u.id = tr.userId WHERE tr.id="+query);
				
				try {
					con.rs.next();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					txtUsername.setText(con.rs.getString("username"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
