package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Reporter;

import PropertyFileConfig.ObjectReaders;

public class DataBaseUtility_Dev {

	
	static Statement stmt;
	static  Connection con;
	static ResultSet Result;


	/**
	 * @throws Throwable 
	 * @Description : This method is used to connect Database server
	 */
	public static void connectToDB() throws Throwable {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.setLoginTimeout(10);

			con = DriverManager.getConnection(ObjectReaders.readers.getDev_DB_URL(), ObjectReaders.readers.getDev_QA_Username(), ObjectReaders.readers.getDev_QA_Password());

			stmt = con.createStatement();
			Reporter.log(" DB Connection Successfull");
		}
		catch (Exception e){
			e.printStackTrace();
			throw new Exception("DataBase Connection was not successfull");
		}
	}


	
	
	/**
	 * This method will   Execute the  Query & return the result set
	 * @param Query
	 * @throws SQLException 
	 */
	public static String  ExecuteQuery(String Query) throws SQLException
	{     
		Result=stmt.executeQuery(Query);
		 
		String data=null;
		try {
			if(Result.next()) {
			data = Result.getString(1);
			}
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * This method will   Execute the update Query
	 * @param Query
	 */
	public static long executeUpdateQuery(String Query)
	{
		long row=0;
		try {
			row = stmt.executeLargeUpdate(Query);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	
	
	public static String getQuery(String propertyFileKey, String whereCondition){
		String query = String.format(ThreadLocalClass.getpropfile().getPropertiesData(propertyFileKey), whereCondition);
		return query;
	}


	
	/**
	 * This Method is used to close the statment and connection
	 * @throws SQLException
	 */
	public static void closeDB() throws SQLException

	{

		stmt.close();
		con.close();
		Reporter.log(" DB Connection Closed Successfull");
	}

	/**
	 * This Method is used to Check the connection status
	 */

	public boolean isDbConnected() {
		boolean flag=false;
		try {
			if (con != null && !con.isClosed())
				flag= true;
			else
				flag= false;
		} catch (Exception e) {
			flag=false;
		}
		return flag;

	}
	
	
	
}
