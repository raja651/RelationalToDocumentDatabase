package SqlToMongo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SqltoMongo {
	
	public ArrayList<String> readPostgre(String db,String usr,String pwd){
//		String usr ="postgres";
//		String pwd ="1234";
		String url ="jdbc:postgresql://localhost:5432/"+db;
		 ArrayList<String> values = new ArrayList<String>();
		try
		{
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch(Exception e)
		{
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}
		try {
            Connection dateabaseConnection = DriverManager.getConnection(url, usr, pwd);
            System.out.println("Success connecting server!");

            Statement statementObject = dateabaseConnection.createStatement();
            ResultSet rs = statementObject.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public'");
           
            while(rs.next()){
            	values.add(rs.getString("table_name"));
//            	values.add(rs.getString("prod"));
//            	values.add(rs.getString("day"));
//            	values.add(rs.getString("month"));
//            	values.add(rs.getString("year"));
//            	values.add(rs.getString("quant"));
            }
           
		}
		catch(Exception e){
			System.out.println(e);
		}
		 return values;
	}
	
	
	/*Push tables as collection to MongoDB */
	
	public ArrayList<String> pushInMongo(){
		return null;
		
	}

}
