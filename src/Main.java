
public class Main {

	Connect con;
	
	public Main()
	{
		con = new Connect();
		LoginForm loginForm = new LoginForm(con);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
