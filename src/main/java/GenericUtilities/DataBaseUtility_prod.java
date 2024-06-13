package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import PropertyFileConfig.ObjectReaders;

public class DataBaseUtility_prod {

	
	
	static Driver driverRef;   // static
	static Connection con; // static
	public static String otp;
	public static String loanCode;
	
	
	/**
	 * This method will establish the connection with database
	 * @author fllap0570_maheshnarvane 
	 * @throws SQLException 
	 * @throws Throwable 
	 */
	public static void connectToDB() throws SQLException, Throwable 
	{    
			
		Thread.sleep(2000);
		driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		con=DriverManager.getConnection(ObjectReaders.readers.Prod_DB_URL(),ObjectReaders.readers.Prod_DB_Username(), ObjectReaders.readers.Prod_DB_Password());
	}                                 
	
	
	/**
	 * This method will close the database connection
	 * @author fllap0570_maheshnarvane
	 * @return 
	 * @throws SQLException 
	 */
	public static  void closeDB() throws SQLException 
	{
		
	        con.close();
	}
	
	
	/**
	 * This method will fetch the value of the Key given from property file
	 * @author fllap0570_maheshnarvane
	 * @return query
	 * @throws SQLException 
	 */
	
	public static String getQuery(String propertyFileKey, String whereCondition)
	{
		String query = String.format( ThreadLocalClass.getpropfile().getPropertiesData(propertyFileKey), whereCondition);
		return query;
	}
	
	
	/**
	 * This method used to execute the query in DB and return the result
	 * @author fllap0570_maheshnarvane
	 * @return data
	 * @throws SQLException 
	 */
	
	public static  String  ExecuteQuery(String query) throws Throwable 
	{
		ResultSet result=con.createStatement().executeQuery(query);
		String data=null;
		while(result.next()) 
		{
			data=result.getString(1);
		    break;
		}
		return data;
	
	}

	
	
	
	
	
}
