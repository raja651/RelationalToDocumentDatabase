package SqlToMongo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

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
		int columnCount = 0;
		String[] columnNames;
		ArrayList<String> tableValues;
		try {
			Connection dataabaseConnection = null;
			dataabaseConnection = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Success connecting server!");

			Statement statementObject = dataabaseConnection.createStatement();
			
			String s ="Select count(*) FROM information_schema.columns WHERE table_schema='public'AND table_name="+"'"+tableName+"'";
			ResultSet rs = statementObject.executeQuery(s);			
			
			while(rs.next()){				
				columnCount=rs.getInt("count");
			}
			System.out.println(columnCount);
			
			String sqll = "SELECT * FROM information_schema.columns WHERE table_schema='public'AND table_name="+"'"+tableName+"'";
			rs= statementObject.executeQuery(sqll);					
			
			columnNames = new String[columnCount];		
			int i=0;
			while (rs.next()) {			
				columnNames[i]=rs.getString("column_name");
				i++;
			}			

			String sql = "SELECT * FROM "+ tableName;
			rs = statementObject.executeQuery(sql);			
																						
			tableValues = new ArrayList<String>();
			int k;
			while(rs.next()){
				for(k=1; k<columnCount;k++)
					tableValues.add(rs.getString(k));				
			}
			for (String temp : tableValues) {
				System.out.println(temp);
			}

			System.out.println(tableValues);
			dataabaseConnection.close();	
			
			/* call mongo connector and push the values in MongoDB*/
			mongoConnector(columnCount,columnNames,tableValues,tableName);

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
	
	public void mongoConnector(int columnCount, String[] columnNames, ArrayList<String> tableValues, String tableName){   
		try{   
			
	         // To connect to mongodb server
	         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
				
	         // Now connect to your databases
	         DB db = mongoClient.getDB( "test" );
	         System.out.println("Connect to database successfully");
				
//	         boolean auth = db.authenticate(myUserName, myPassword);
//	         System.out.println("Authentication: "+auth);
				
	         DBCollection coll = db.createCollection(tableName, null);
	         System.out.println("Collection created successfully");
	         
	         BasicDBObject inQuery = new BasicDBObject();
		    	
		    	System.out.println(inQuery);		    	
		    	coll.insert(inQuery);
	         
				
//	         DBCollection collc = db.getCollection("mycol");
//	         System.out.println("Collection mycol selected successfully");
	         
	         
	      }catch(Exception e){
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
		
	}

}
