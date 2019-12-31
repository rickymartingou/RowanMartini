import java.sql.*;

public class Connect {

    Statement stmt,state;
    ResultSet rs;
    ResultSetMetaData rsm;
    PreparedStatement pStat;
    Connection con;

    public Connect() {
        try{  
            Class.forName("com.mysql.jdbc.Driver");

            // Nanti prk nya diubah sesuai dengan nama db yang mau di connect
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rowanmartini","root","");  
            stmt = con.createStatement();
            state = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
            
            System.out.println("Connected to the database..");
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }
    
    public ResultSet executeUpdateDelete(String query)
    {
    	try {
			state.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return rs;
    }

    public ResultSet executeQuery(String query){
        try{
            rs = stmt.executeQuery(query);
            rsm = rs.getMetaData();
        }
        catch(Exception e){
            System.out.println(e);
        }

        return rs;
    }

    public void executeUpdate(String query){
        try{
            stmt.executeUpdate(query);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void executePStatement(String query) {
		try {
			pStat = con.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error Connection PSTATEMENT");
		}
	}
    
    public void executeUpdateToItems(int quantity,int itemId) {
		try {
			pStat = con.prepareStatement("UPDATE menus SET quantity = ? WHERE id = ?");
			pStat.setInt(1,quantity);
			pStat.setInt(2,itemId);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void executeUpdateProfileToUsers(String phone,String gender,int id) {
		try {
			pStat = con.prepareStatement("UPDATE users SET phone=?,gender=? WHERE id = ?");
			pStat.setString(1, phone);
			pStat.setString(2, gender);
			pStat.setInt(3, id);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void executeUpdateToUsers(String Password,int userID) {
		try {
			pStat = con.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
			pStat.setString(1,Password);
			pStat.setInt(2, userID);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void executeDeleteToItems(int itemID)
    {
    	try {
			pStat = con.prepareStatement("DELETE items WHERE ItemId=?");
			pStat.setInt(1,itemID);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void executeUpdateToItems(String itemName, int itemQuantity,int price,int itemID) {
		try {
			pStat = con.prepareStatement("UPDATE menus SET name = ?,quantity=?,price=? WHERE id = ?");
			pStat.setString(1, itemName);
			pStat.setInt(2,itemQuantity);
			pStat.setInt(3,price);
			pStat.setInt(4,itemID);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void executeInsertToItems(int itemID,String itemName, int itemQuantity,int price) {
		try {
			pStat = con.prepareStatement("INSERT INTO menus VALUES(?, ?, ?,?)");
			pStat.setInt(1, itemID);
			pStat.setString(2, itemName);
			pStat.setInt(3,itemQuantity);
			pStat.setInt(4,price);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void executeInsertToTransactions(int transactionId,int userID, String theDate) {
		try {
			pStat = con.prepareStatement("INSERT INTO transactions VALUES(?, ?, ?)");
			pStat.setInt(1, transactionId);
			pStat.setInt(2, userID);
			pStat.setString(3,theDate);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void executeInsertToDetailTransactions(int transactionId,int itemID,int Quantity,int Price) {
		try {
			pStat = con.prepareStatement("INSERT INTO detailtransactions VALUES(?, ?, ?, ?)");
			pStat.setInt(1, transactionId);
			pStat.setInt(2, itemID);
			pStat.setInt(3, Quantity);
			pStat.setInt(4,Price);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void executeInsertToUsers(int id,String username,String password,String phone,String gender,String userRole) {
		try {
			pStat = con.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?,?)");
			pStat.setInt(1, id);
			pStat.setString(2, username);
			pStat.setString(3, password);
			pStat.setString(4, phone);
			pStat.setString(5, gender);
			pStat.setString(6, userRole);
			pStat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}