package GenericUtilities;

import java.sql.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;




/**
 * This Class Contains all generic method related to java
 * @author fllap0570_maheshnarvane
 *
 */
public class JavaUtility{


	public String randome10string() 
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(10);
		return (generatedstring);
	}

	public static String random9NoFlipkart() 
	{
		String skjf=RandomStringUtils.randomNumeric(8);
		String no = "7"+skjf+"9";
		//System.out.println(no);
		return no;
	}

	public static String NoendingwithOdddigit() 
	{
		String sdsads=RandomStringUtils.randomNumeric(8);
		return("7"+sdsads+"7");
	}

	public static String randome5string() 
	{
		String generatedstring1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedstring1);
	}

	public static String random4numeric() 
	{
		String get=RandomStringUtils.randomNumeric(10);
		return ("6"+get);
	}



public static String generateRandomPAN() {
    String part1 = RandomStringUtils.randomAlphabetic(3).toUpperCase();
    String part2 = RandomStringUtils.randomAlphabetic(1).toUpperCase();
    String part3 = RandomStringUtils.randomNumeric(4);
    String part4 = RandomStringUtils.randomAlphabetic(1).toUpperCase();
    
    return part1 + "P" + part2 + part3 + part4;
}

// Example of how to handle a long string literal
public static String getLongString() {
    // Breaking a long string literal into multiple lines
    return "This is a very long string that exceeds the maximum length allowed by the Java " +
           "compiler, so it needs to be split into smaller chunks to avoid compilation errors.";
}

	
	public static String randomMobileNo() 
	{
		String generatedString3 = RandomStringUtils.randomNumeric(9);
		return ("1"+generatedString3);
	}


	public static String randomMonthlySalegreaterthan2() 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(5);
		return ("4"+generatedString2);
	}


	public static String offerMobileNo() 
	{
		String hdgsd=RandomStringUtils.randomNumeric(9);
		return ("6"+hdgsd);
	}


	public static String randomMonthlySaleLesserthan2() 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(5);
		return ("1"+generatedString2);
	}


	public static String randome8Num() 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(8);
		return (generatedString2);
	}



	public int  getRandomNumber() 
	{
		Random r=new Random();
		int ran = r.nextInt(1000);
		return ran;
	}


	public static int convertToInt(String stringData) {
		try {
			return Integer.parseInt(stringData);
		} catch (Exception e) {
			return 0;
		}

	}

	public static long convertToLong(String stringData) {
		try {
			return Long.parseLong(stringData);
		} catch (Exception e) {
			return 0;
		}

	}


	public static double convertToDouble(String stringData){
		try{
			return Double.parseDouble(stringData);
		}
		catch (Exception e){
			return 0;
		}
	}


	public static String randomEmailId() 
	{
		String generatedEmail = RandomStringUtils.randomAlphanumeric(5);
		String Email="a"+generatedEmail+"@gmail.com";
		return Email;
	}



	/**
	 * This method will return current system date 
	 * @return
	 */

	public String getSystemDate() 
	{
		Date d = new Date(0);
		String date=d.toString();
		return date;
	}


	/**
	 * This method will provide the date in a specific format
	 * @return
	 */

	public String getSystemDateInFormat() 
	{
		Date d = new Date(0);
		String [] dArr =d.toString().split(" ");
		String date=dArr[2];
		String month= dArr[1];
		String year = dArr[5];
		String time= dArr[3].replace(":", "-");
		String currentDate= date+" "+month+" "+year+" "+time;
		return currentDate;
	}













}
