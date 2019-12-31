import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class BuyItemForm extends JInternalFrame implements ActionListener,MouseListener,ChangeListener{
	
	MainForm mainForm;
	Connect con;
	ResultSet resS;
	
	int countTransactions,lastID;
	
	String id,price,qty,totalPrice,name;
	int row,rowI,countCheckOut=0,rowCartCount=0;
	int userID;
	Integer rowItem,ItemValue,CartValue;
	Date date;
	
	JPanel panelMain,panelItemTable,panelForm,panelContain,panelFinal,panelBottom,panelCart,panelFormFinal,panelBottomFinal;
	JPanel panelLblID,panelLblPrice,panelLblQuantity,panelLblTotal,panelLblTitle,panelTable,panelButton;
	JPanel panelTxtID,panelTxtPrice,panelTxtQuantity,panelTxtTotal,panelBtnAdd,panelBtnRemove,panelBtnCheck,panelBtnCart;
	JPanel panelContainKiri,panelContainKanan;
	
	JLabel lblID,lblPrice,lblQuantity,lblTotal,lblTitle;
	JTextField txtID,txtPrice,txtTotal;
	JSpinner spinQty;
	JScrollPane scrollItem , scrollCart;
	JTable itemTable,cartTable;
	JButton btnAdd,btnRemove,btnCheckOut;
	DefaultTableModel dtmItem,dtmCart;
	
	Vector<Object> tHeader,tContent;
	Vector<Object> tHeaderDetail,tContentDetail;
	boolean isAddNew = true,isFound=false;
	int countItem=0;
	
	private void clearTable()
	{
		for(int i=0;i<countItem;i++)
		{
			dtmItem.removeRow(0);
		}
		countItem=0;
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
				dtmItem.addRow(tContent);
				countItem++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initItemTable()
	{
		tHeader = new Vector<Object>();

		tHeader.add("Menu ID");
		tHeader.add("Name");
		tHeader.add("Quantity");
		tHeader.add("Price");
		
		dtmItem = new DefaultTableModel(tHeader,0);
		
		fillTable();
		itemTable = new JTable(dtmItem);
		
	}
	
	private void init()
	{	
		panelFinal = new JPanel(new GridLayout(2,1));
		panelMain = new JPanel(new GridLayout(2,1));
		panelItemTable = new JPanel();
		panelForm = new JPanel(new GridLayout(4,2));
		panelContain = new JPanel();
		panelCart = new JPanel();
		panelBtnCart = new JPanel(new GridLayout(1,2));
		panelFormFinal = new JPanel();
		panelBottom = new JPanel(new GridLayout(1,2));
		panelBottomFinal = new JPanel(new GridLayout(2,1));
		panelButton = new JPanel();
		
		panelLblID = new JPanel();
		panelLblPrice = new JPanel();
		panelLblQuantity = new JPanel();
		panelLblTotal = new JPanel();
		panelLblTitle = new JPanel();
		panelTable = new JPanel();
		
		panelTxtID = new JPanel();
		panelTxtPrice = new JPanel();
		panelTxtQuantity = new JPanel();
		panelTxtTotal = new JPanel();
		panelBtnAdd = new JPanel();
		panelBtnRemove = new JPanel();
		panelBtnCheck = new JPanel();
		
		lblID = new JLabel("Menu ID");
		lblPrice = new JLabel("Price");
		lblQuantity = new JLabel("Quantity");
		lblTotal = new JLabel("Total Price");
		lblTitle = new JLabel("Buy Drink");
		lblTitle.setFont(new Font("",Font.BOLD,15));
		
		txtID = new JTextField();
		txtID.setPreferredSize(new Dimension(170,25));
		
		txtPrice = new JTextField();
		txtPrice.setPreferredSize(new Dimension(170,25));
		
		txtTotal = new JTextField();
		txtTotal.setPreferredSize(new Dimension(170,25));
		
		spinQty = new JSpinner();
		spinQty.setPreferredSize(new Dimension(170,25));

		tHeaderDetail = new Vector<Object>();
		tHeaderDetail.add("Menu ID");
		tHeaderDetail.add("Name");
		tHeaderDetail.add("Price");
		tHeaderDetail.add("Quantity");
		tHeaderDetail.add("Total Price");
		
		dtmCart = new DefaultTableModel(tHeaderDetail,0);
		cartTable = new JTable(dtmCart);
		
		scrollItem = new JScrollPane(itemTable);
		scrollCart = new JScrollPane(cartTable);
		
		scrollItem.setPreferredSize(new Dimension(750,200));
		scrollCart.setPreferredSize(new Dimension(370,190));
		
		btnAdd = new JButton("Add to Cart");
		btnRemove = new JButton("Remove All");
		btnCheckOut = new JButton("Check Out");
		
		panelLblID.add(lblID);
		panelLblID.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelLblPrice.add(lblPrice);
		panelLblPrice.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelLblQuantity.add(lblQuantity);
		panelLblQuantity.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelLblTotal.add(lblTotal);
		panelLblTotal.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtID.add(txtID);
		panelTxtID.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtPrice.add(txtPrice);
		panelTxtPrice.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtQuantity.add(spinQty);
		panelTxtQuantity.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelTxtTotal.add(txtTotal);
		panelTxtTotal.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelLblTitle.add(lblTitle);
		panelLblTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		panelTable.add(scrollItem);
		panelTable.setLayout(new FlowLayout());
		
		btnAdd.setPreferredSize(new Dimension(100, 25));
		panelBtnAdd.add(btnAdd);
		panelBtnAdd.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		btnRemove.setPreferredSize(new Dimension(100, 25));
		panelBtnRemove.add(btnRemove);
		panelBtnRemove.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		btnCheckOut.setPreferredSize(new Dimension(100, 25));
		panelBtnCheck.add(btnCheckOut);
		panelBtnCheck.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		panelCart.add(scrollCart,"North");
		panelCart.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panelForm.add(panelLblID);
		panelForm.add(panelTxtID);
		
		panelForm.add(panelLblPrice);
		panelForm.add(panelTxtPrice);
		
		panelForm.add(panelLblQuantity);
		panelForm.add(panelTxtQuantity);
		
		panelForm.add(panelLblTotal);
		panelForm.add(panelTxtTotal);
		
		panelContain.add(panelForm,"North");
		
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelButton.add(panelBtnAdd);
		panelButton.add(panelBtnRemove);
		panelButton.add(panelBtnCheck);
		
		panelFormFinal.setLayout(new BoxLayout(panelFormFinal, BoxLayout.Y_AXIS));
		panelFormFinal.add(panelContain);
		panelFormFinal.add(panelButton);
		
		panelBottom.add(panelFormFinal);
		panelBottom.add(panelCart);
		
		panelItemTable.setLayout(new BoxLayout(panelItemTable, BoxLayout.Y_AXIS));
		panelItemTable.add(panelLblTitle);
		panelItemTable.add(panelTable);
		panelItemTable.add(panelBottom);
		
		add(panelItemTable);
		
		txtID.setEditable(false);
		txtPrice.setEditable(false);
		txtTotal.setEditable(false);
		
		itemTable.addMouseListener(this);
		cartTable.addMouseListener(this);
		spinQty.addChangeListener(this);
		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		btnCheckOut.addActionListener(this);
	}
	
	private void initFrame()
	{
		setSize(new Dimension(800,500));
		setLocation(mainForm.getWidth() / 2 - getWidth() / 2, mainForm.getHeight() / 2 - getHeight() / 2);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public BuyItemForm(MainForm mainForm, Connect con) {
		// TODO Auto-generated constructor stub
		super("Buy Drink",true,true,false);
		this.mainForm = mainForm;
		this.con = con;
		initItemTable();
		init();
		initFrame();
		setVisible(true);
		
		itemTable.setFillsViewportHeight(true);
		itemTable.getTableHeader().setReorderingAllowed(false);
		itemTable.getTableHeader().setResizingAllowed(false);
		itemTable.setDefaultEditor(Object.class,null);
		itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		cartTable.setFillsViewportHeight(true);
		cartTable.getTableHeader().setReorderingAllowed(false);
		cartTable.getTableHeader().setResizingAllowed(false);
		cartTable.setDefaultEditor(Object.class,null);
		cartTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		resS = con.executeQuery("SELECT COUNT(*) from transactions");
		try {
			resS.next();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			countTransactions = resS.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void clear()
	{
		txtID.setText("");
		txtPrice.setText("");
		txtTotal.setText("");
		spinQty.setValue(0);
		itemTable.clearSelection();
		cartTable.clearSelection();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(itemTable.getSelectedRow()!=-1)
		{
			txtID.setText(itemTable.getValueAt(itemTable.getSelectedRow(),0).toString());
			txtPrice.setText(itemTable.getValueAt(itemTable.getSelectedRow(),3).toString());
			spinQty.setValue(1);
			txtTotal.setText((Integer.parseInt(txtPrice.getText())*1)+"");
			
			id = txtID.getText().toString();
			price = txtPrice.getText().toString();
			name = itemTable.getValueAt(itemTable.getSelectedRow(),1).toString();
			qty = spinQty.getValue().toString();
			totalPrice = txtTotal.getText().toString();
			row = itemTable.getSelectedRow();
		}
		else if(cartTable.getSelectedRow()!=-1)
		{
			rowItem = Integer.parseInt((dtmCart.getValueAt(cartTable.getSelectedRow(),0).toString()))-Integer.parseInt("1");
			if(rowItem<0)
			{
				rowItem=1;
			}
			rowI = cartTable.getSelectedRow();
			
			ItemValue = Integer.parseInt(dtmItem.getValueAt(rowItem,2).toString());
			CartValue = Integer.parseInt(dtmCart.getValueAt(rowI, 2).toString());
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
		if(src.getSource()==btnAdd)
		{
			if(itemTable.getSelectedRow()!=-1)
			{
				if(Integer.parseInt(spinQty.getValue().toString())>Integer.parseInt(dtmItem.getValueAt(itemTable.getSelectedRow(),2).toString()))
				{
					JOptionPane.showMessageDialog(null,"Not enough stock", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int i;
					for(i=0;i<rowCartCount;i++)
					{
						if(id.equals(dtmCart.getValueAt(i,0).toString()))
						{
							isFound=true;
							break;
						}
					}
					
					if(isFound==true)
					{
						dtmCart.setValueAt(Integer.parseInt(dtmCart.getValueAt(i,3).toString())+Integer.parseInt(spinQty.getValue().toString()),i,3);
						dtmCart.setValueAt(Integer.parseInt(dtmCart.getValueAt(i,4).toString())+Integer.parseInt(txtTotal.getText().toString()), i, 4);
						dtmItem.setValueAt(Integer.parseInt(dtmItem.getValueAt(row, 2).toString())-Integer.parseInt(qty),row,2);
						isFound=false;
					}
					else
					{
						isAddNew=true;
					}
					
					if(isAddNew == true)
					{
						tContentDetail = new Vector<Object>();
						tContentDetail.add(id);
						tContentDetail.add(name);
						tContentDetail.add(price);
						tContentDetail.add(qty);
						tContentDetail.add(totalPrice);
						dtmCart.addRow(tContentDetail);
						dtmItem.setValueAt(Integer.parseInt(dtmItem.getValueAt(row, 2).toString())-Integer.parseInt(qty),row,2);
						rowCartCount++;
						clear();
						isAddNew = false;
						
					}
				}	
			}
		}
		else if(src.getSource()==btnRemove)
		{
			clearTable();
			fillTable();
			
			for(int i=0;i<rowCartCount;i++)
			{
				dtmCart.removeRow(0);
			}
			
			rowCartCount=0;
			clear();
		}
		else if(src.getSource()==btnCheckOut)
		{
			if(dtmCart.getRowCount()!=0)
			{
				con.rs = con.executeQuery("SELECT id FROM users WHERE username = '"+mainForm.loginForm.userName+"'");
				try {
					con.rs.next();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					userID = Integer.parseInt(con.rs.getString("id"));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String theDate = sdf.format(date);
				
				if(countTransactions!=0)
				{
					resS = con.executeQuery("SELECT id FROM transactions ORDER BY id DESC LIMIT 0,1");
					
					try {
						resS.next();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					try {
						lastID = resS.getInt(1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//INSERT
				if(countTransactions==0)
				{
					con.executeInsertToTransactions(1,Integer.parseInt(mainForm.loginForm.tempID), theDate);
					
					for(int i=0;i<cartTable.getRowCount();i++)
					{
						con.executeInsertToDetailTransactions(1, Integer.parseInt(cartTable.getValueAt(i,0).toString()), 
								Integer.parseInt(cartTable.getValueAt(i,2).toString()),Integer.parseInt(cartTable.getValueAt(i, 1).toString()));
					}
					
					for(int j=0;j<itemTable.getRowCount();j++)
					{
						con.executeUpdateToItems(Integer.parseInt(itemTable.getValueAt(j,2).toString()),Integer.parseInt(itemTable.getValueAt(j, 0).toString()));
					}
				}
				else
				{
					con.executeInsertToTransactions(lastID+1,Integer.parseInt(mainForm.loginForm.tempID), theDate);
					
					for(int i=0;i<cartTable.getRowCount();i++)
					{
						con.executeInsertToDetailTransactions(lastID+1, Integer.parseInt(cartTable.getValueAt(i,0).toString()), 
								Integer.parseInt(cartTable.getValueAt(i,3).toString()),Integer.parseInt(cartTable.getValueAt(i, 2).toString()));
					}
					
					for(int j=0;j<itemTable.getRowCount();j++)
					{
						con.executeUpdateToItems(Integer.parseInt(itemTable.getValueAt(j,2).toString()),Integer.parseInt(itemTable.getValueAt(j, 0).toString()));
					}
				}
				for(int i = 0;i<rowCartCount;i++)
				{
					dtmCart.removeRow(0);
				}
				rowCartCount=0;
				clear();
				JOptionPane.showMessageDialog(null, "Check Out Complete");
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Cart must not be empty", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		qty = spinQty.getValue().toString();
		txtTotal.setText(Integer.parseInt(spinQty.getValue().toString())*Integer.parseInt((itemTable.getValueAt(itemTable.getSelectedRow(),3).toString()))+"");
		totalPrice = Integer.parseInt(spinQty.getValue().toString())*Integer.parseInt((itemTable.getValueAt(itemTable.getSelectedRow(),3).toString()))+"";
	}

}
