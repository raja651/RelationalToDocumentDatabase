package SqlToMongo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SqltoMongo {

	public ArrayList<String> readPostgre(String db, String usr, String pwd) {
		// String usr ="postgres";
		// String pwd ="1234";
		String url = "jdbc:postgresql://localhost:5432/"+db;
		ArrayList<String> values = new ArrayList<String>();
		postgresDriver();
		try {
			Connection databaseConnection = null;
		    databaseConnection = DriverManager.getConnection(url,usr, pwd);
			System.out.println("Success connecting server!");

			Statement statementObject = databaseConnection.createStatement();
			ResultSet rs = statementObject
					.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public'");

			while (rs.next()) {
				values.add(rs.getString("table_name"));
				// values.add(rs.getString("prod"));
				// values.add(rs.getString("day"));
				// values.add(rs.getString("month"));
				// values.add(rs.getString("year"));
				// values.add(rs.getString("quant"));
			}
			databaseConnection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return values;
	}

	/* Push tables as collection to MongoDB */

	public ArrayList<String> pushInMongo(String db, String usr, String pwd,String tableName) {

		String url = "jdbc:postgresql://localhost:5432/"+db;
		postgresDriver();
		String[] columnNames;
		ArrayList tableValues;
		try {
			Connection dataabaseConnection = null;
			dataabaseConnection = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement statementObject = dataabaseConnection.createStatement();
			int rowcount = 0;
			String s ="Select count(*) from "+ tableName;
			ResultSet rs = statementObject.executeQuery(s);
			System.out.println(rs);
				
			
			String sqll = "SELECT * FROM information_schema.columns WHERE table_schema='public'AND table_name="+"'"+tableName+"'";
			rs= statementObject.executeQuery(sqll);					
			
			columnNames = new String[rowcount];		
			int i=0;
			while (rs.next()) {			
				columnNames[i]=rs.getString("column_name");
				i++;
			}
			for(String col :columnNames){
				System.out.println(col);
			}

			String sql = "SELECT * FROM "+ tableName;
			rs = statementObject.executeQuery(sql);
			System.out.println("1111111111111111111111111111111111111");
			System.out.println(rs.getRow());
			System.out.println(rs.getFetchSize());
			System.out.println(rs.toString().length());
			System.out.println("2222222222222222222222222222222222222222");
			tableValues = new ArrayList();
			int k;
//			while(rs.next()){
//				for(k=0; k<rs1.getRow();k++)
//					tableValues.add(rs.getString(k));				
//			}
			
			dataabaseConnection.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

		return null;

	}

	public void postgresDriver() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Success loading Driver!");
		}

		catch (Exception e) {
			System.out.println("Fail loading Driver!");
			e.printStackTrace();
		}
	}

}
