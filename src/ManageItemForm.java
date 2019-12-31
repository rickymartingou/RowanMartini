import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class ManageItemForm extends JInternalFrame implements ActionListener,MouseListener {
	
	MainForm mainForm;
	Connect con;
	Statement state;
	
	JPanel panelTable,panelTop,panelBottom,panelTitle,panelMain;
	JPanel panelItemName,panelItemQuantity,panelItemPrice,panelTxtItemName,panelTxtItemQuantity,panelTxtItemPrice,panelLblID,panelTxtID;
	JPanel panelForm,panelContainForm,panelButton,panelMainButton;
	JPanel panelBtnInsert,panelBtnUpdate,panelBtnDelete,panelBtnSave,panelBtnCancel;
	
	JLabel lblItemName,lblItemQuantity,lblItemPrice,lblID,lblTitle;
	JTextField txtItemName,txtItemPrice,txtId;
	JSpinner txtItemQuantity;
	JButton btnInsert,btnUpdate,btnDelete,btnSave,btnCancel;
	JTable tableItem;
	DefaultTableModel dtm;
	JScrollPane scroll;
	
	int lastID,countData=0;
	boolean isInsert=false,isUpdate=false,isSelectTable=false;
	Vector<Object> tHeader,tContent;
	
	private void clearTable()
	{
		for(int i=0;i<countData;i++)
		{
			dtm.removeRow(0);
		}
		countData=0;
	}
	
	private void fillTable()
	{
		con.rs = con.executeQuery("SELECT * FROM menus");
		
		try {
			while (con.rs.next()) {
				tContent = new Vector<Object>();
				for (int i = 1; i <= con.rsm.getColumnCount(); i++)
				{
					tContent.add(con.rs.getObject(i) + "");
				}
				countData++;
				dtm.addRow(tContent);
			}
			System.out.println(countData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initTable()
	{
		tHeader = new Vector<Object>();
		tHeader.add("Menu ID");
		tHeader.add("Name");
		tHeader.add("Quantity");
		tHeader.add("Price");
		
		dtm = new DefaultTableModel(tHeader,0);
		
		fillTable();
		tableItem = new JTable(dtm);
		tableItem.setFillsViewportHeight(true);
		tableItem.getTableHeader().setReorderingAllowed(false);
		tableItem.getTableHeader().setResizingAllowed(false);
		tableItem.setDefaultEditor(Object.class,null);
		tableItem.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableItem.addMouseListener(this);
	}
	
	private void init()
	{
		panelMain = new JPanel();
		panelTable = new JPanel();
		panelTop = new JPanel();
		panelTitle = new JPanel();
		panelBottom = new JPanel();
		panelButton = new JPanel();
		panelMainButton = new JPanel();
		panelContainForm = new JPanel();
		
		panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.Y_AXIS));
		panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.Y_AXIS));
		panelBottom.setLayout(new BoxLayout(panelBottom, BoxLayout.X_AXIS));
		
		panelItemName = new JPanel();
		panelItemQuantity = new JPanel();
		panelItemPrice = new JPanel();
		panelTxtItemName = new JPanel();
		panelTxtItemQuantity = new JPanel();
		panelTxtItemPrice = new JPanel();
		panelLblID = new JPanel();
		panelTxtID = new JPanel();
		
		panelBtnInsert = new JPanel();
		panelBtnUpdate = new JPanel();
		panelBtnDelete = new JPanel();
		panelBtnSave = new JPanel();
		panelBtnCancel = new JPanel();
		
		panelForm = new JPanel(new GridLayout(4,2));
		
		lblTitle = new JLabel("Manage Menu");
		lblTitle.setFont(new Font("",Font.BOLD,15));
		lblItemName = new JLabel("Menu Name");
		lblItemQuantity = new JLabel("Menu Quantity");
		lblItemPrice = new JLabel("Menu Price");
		lblID = new JLabel("Menu ID");
		
		txtItemName = new JTextField();
		txtItemName.setPreferredSize(new Dimension(200,25));
		
		txtItemQuantity = new JSpinner();
		txtItemQuantity.setPreferredSize(new Dimension(200, 25));
		
		txtItemPrice = new JTextField();
		txtItemPrice.setPreferredSize(new Dimension(200,25));
		
		txtId = new JTextField();
		txtId.setPreferredSize(new Dimension(200,25));
		
		btnInsert = new JButton("Insert");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		btnSave = new JButton("Submit");
		btnCancel = new JButton("Cancel");
		
		btnInsert.setPreferredSize(new Dimension(80,25));
		btnUpdate.setPreferredSize(new Dimension(80,25));
		btnDelete.setPreferredSize(new Dimension(80,25));
		btnSave.setPreferredSize(new Dimension(80,25));
		btnCancel.setPreferredSize(new Dimension(80,25));
		
		DefaultTableModel dtm = new DefaultTableModel();
		
		scroll = new JScrollPane(tableItem);
		
		scroll.setPreferredSize(new Dimension(550,200));	
		
		panelTable.add(scroll);
		panelTable.setLayout(new FlowLayout());
		
		panelTitle.add(lblTitle);
		panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		panelItemName.add(lblItemName);
		panelItemName.setLayout(new FlowLayout(FlowLayout.LEFT,10,12));
		
		panelItemQuantity.add(lblItemQuantity);
		panelItemQuantity.setLayout(new FlowLayout(FlowLayout.LEFT,10,12));
		
		panelItemPrice.add(lblItemPrice);
		panelItemPrice.setLayout(new FlowLayout(FlowLayout.LEFT,10,12));
		
		panelLblID.add(lblID);
		panelLblID.setLayout(new FlowLayout(FlowLayout.LEFT,10,12));
		
		panelTxtItemName.add(txtItemName);
		panelTxtItemName.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		
		panelTxtItemPrice.add(txtItemPrice);
		panelTxtItemPrice.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		
		panelTxtItemQuantity.add(txtItemQuantity);
		panelTxtItemQuantity.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		
		panelTxtID.add(txtId);
		panelTxtID.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		
		panelForm.add(panelLblID);
		panelForm.add(panelTxtID);
		panelForm.add(panelItemName);
		panelForm.add(panelTxtItemName);
		panelForm.add(panelItemPrice);
		panelForm.add(panelTxtItemPrice);
		panelForm.add(panelItemQuantity);
		panelForm.add(panelTxtItemQuantity);
		
		panelContainForm.add(panelForm);
		
		panelTop.add(panelTitle,"North");
		panelTop.add(panelTable,"West");
		
		panelBtnInsert.add(btnInsert);
		panelBtnInsert.setLayout(new FlowLayout(FlowLayout.RIGHT,0,3));
		
		panelBtnUpdate.add(btnUpdate);
		panelBtnUpdate.setLayout(new FlowLayout(FlowLayout.RIGHT,0,3));
		
		panelBtnDelete.add(btnDelete);
		panelBtnDelete.setLayout(new FlowLayout(FlowLayout.RIGHT,0,3));
		
		panelBtnSave.add(btnSave);
		panelBtnSave.setLayout(new FlowLayout(FlowLayout.RIGHT,0,3));
		
		panelBtnCancel.add(btnCancel);
		panelBtnCancel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,3));
		
		panelButton.add(panelBtnInsert);
		panelButton.add(panelBtnUpdate);
		panelButton.add(panelBtnDelete);
		panelButton.add(panelBtnSave);
		panelButton.add(panelBtnCancel);
		
		panelMainButton.add(panelButton);
		panelMainButton.setLayout(new FlowLayout(FlowLayout.CENTER,7,5));
		
		panelBottom.add(panelContainForm);
		panelBottom.add(panelMainButton);
		
		panelMain.add(panelTop,"North");
		panelMain.add(panelBottom,"South");
		
		add(panelMain);
		
		btnCancel.addActionListener(this);
		btnInsert.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSave.addActionListener(this);
		btnUpdate.addActionListener(this);

	}
	
	private void initFrame()
	{
		setSize(new Dimension(600,470));
		setLocation(mainForm.getWidth() / 2 - getWidth() / 2, mainForm.getHeight() / 2 - getHeight() / 2);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public ManageItemForm(MainForm mainForm, Connect con) {
		// TODO Auto-generated constructor stub
		super("Manage Menu",true,true,false);
		this.mainForm = mainForm;
		this.con=con;
		initTable();
		init();
		initFrame();
		setVisible(true);
		
		txtId.setEnabled(false);
		txtItemName.setEnabled(false);
		txtItemPrice.setEnabled(false);
		txtItemQuantity.setEnabled(false);
		buttonState(false);
		
		con.rs = con.executeQuery("SELECT id FROM menus ORDER BY id DESC LIMIT 0,1");
		
		try {
			con.rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			lastID = con.rs.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buttonState(boolean isButtonActive)
	{
		btnInsert.setEnabled(!isButtonActive);
		btnUpdate.setEnabled(!isButtonActive);
		btnDelete.setEnabled(!isButtonActive);
		btnSave.setEnabled(isButtonActive);
		btnCancel.setEnabled(isButtonActive);
	}
	
	private void clear()
	{
		txtId.setText("");
		txtItemName.setText("");
		txtItemPrice.setText("");
		txtItemQuantity.setValue(0);
		tableItem.clearSelection();
		isInsert=false;
		isUpdate=false;
	}
	
	private boolean checkNumeric(String test)
	{
		int digit=0;
		for(int i=0;i<test.length();i++)
		{
			if(Character.isDigit(test.charAt(0)))
			{
				digit++;
			}
		}
		
		if(digit==test.length())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(tableItem.getSelectedRow()!=-1)
		{
			isSelectTable=true;
			txtId.setText(tableItem.getValueAt(tableItem.getSelectedRow(),0).toString());
			txtItemName.setText(tableItem.getValueAt(tableItem.getSelectedRow(),1).toString());
			txtItemPrice.setText(tableItem.getValueAt(tableItem.getSelectedRow(),3).toString());
			txtItemQuantity.setValue(Integer.parseInt(tableItem.getValueAt(tableItem.getSelectedRow(),2).toString()));
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

	@Override
	public void actionPerformed(ActionEvent src) {
		// TODO Auto-generated method stub
		if(src.getSource()==btnInsert)
		{
			clear();
			txtId.setText((lastID+1)+"");
			txtItemName.setEnabled(true);
			txtItemPrice.setEnabled(true);
			txtItemQuantity.setEnabled(true);
			isInsert=true;
			buttonState(true);		
		}
		else if(src.getSource()==btnCancel)
		{
			isSelectTable = false;
			txtItemName.setEnabled(false);
			txtItemPrice.setEnabled(false);
			txtItemQuantity.setEnabled(false);
			buttonState(false);
			clear();
		}
		else if(src.getSource()==btnUpdate)
		{
			if(isSelectTable==true)
			{
				isUpdate=true;
				txtItemName.setEnabled(true);
				txtItemPrice.setEnabled(true);
				txtItemQuantity.setEnabled(true);
				buttonState(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Please select the Drink to Update", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(src.getSource()==btnDelete)
		{
			if(isSelectTable==true)
			{
				int reply = JOptionPane.showConfirmDialog(null,"Are you sure ?","Once Deleted Cannot be Undone",JOptionPane.YES_NO_OPTION);
				
				if(reply==JOptionPane.YES_OPTION)
				{
					String query = "DELETE FROM menus WHERE id = "+tableItem.getValueAt(tableItem.getSelectedRow(),0);
					con.executeUpdateDelete(query);
					JOptionPane.showMessageDialog(null,"Delete Success");
					
					clearTable();
					fillTable();
					clear();
				}
				else
				{
					isSelectTable = false;
					clear();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Please select the Drink to Delete", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(src.getSource()==btnSave)
		{
			if(isInsert==true)
			{
				if(txtItemName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill the Item Name");
				}
				else if(txtItemName.getText().length()<5||txtItemName.getText().length()>20)
				{
					JOptionPane.showMessageDialog(null, "Item Name length must be between 5 - 20 character");
				}
				else if(txtItemPrice.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fillthe Item Price");
				}
				else if(Integer.parseInt(txtItemPrice.getText().toString())<=10000)
				{
					JOptionPane.showMessageDialog(null, "Item Price must be more than 10000");
				}
				else if(checkNumeric(txtItemPrice.getText().toString())==false)
				{
					JOptionPane.showMessageDialog(null, "Price must be numeric !");
				}
				else if(Integer.parseInt(txtItemQuantity.getValue().toString())==0)
				{
					JOptionPane.showMessageDialog(null, "Quantity must be more than 0");
				}
				else
				{
					con.executeInsertToItems(Integer.parseInt(txtId.getText()),txtItemName.getText(),
							Integer.parseInt(txtItemQuantity.getValue().toString()), Integer.parseInt(txtItemPrice.getText()));
					JOptionPane.showMessageDialog(null,"Insert Success !");
					
					lastID += 1;
				}
			}
			else if(isUpdate==true)
			{
				if(txtItemName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Fill the Item's Name !");
				}
				else if(txtItemPrice.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Fill the Item's Price !");
				}
				else if(Integer.parseInt(txtItemQuantity.getValue().toString())<=0)
				{
					JOptionPane.showMessageDialog(null,"Quantity must be more than 0");
				}
				else
				{
					con.executeUpdateToItems(txtItemName.getText().toString(),Integer.parseInt(txtItemQuantity.getValue().toString()),
							Integer.parseInt(txtItemPrice.getText()),Integer.parseInt(tableItem.getValueAt(tableItem.getSelectedRow(),0).toString()));
					JOptionPane.showMessageDialog(null,"Update Success !");
				}
			}
			
			clearTable();
			fillTable();
			clear();
			buttonState(false);
			txtItemName.setEnabled(false);
			txtItemPrice.setEnabled(false);
			txtItemQuantity.setEnabled(false);
		}
	}

}
