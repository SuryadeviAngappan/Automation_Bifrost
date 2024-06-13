package BRE;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BS_Upload.Upload_BS;
import GenericUtilities.Commanparameter;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.Filepath;
import GenericUtilities.ThreadLocalClass;
import LoanCode_Generater.Create_New_Loancode;
import Pract.Doc;
import WorkFlowLibrary.DocumentUpload;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class BRE_Rules {

	BRE_Facts fact = new BRE_Facts();
	DocumentUpload upload = new DocumentUpload();
	Create_New_Loancode create= new Create_New_Loancode();
	DataBaseUtility dbUtil = new DataBaseUtility();
	Doc doc = new Doc();
	String loancode;
	String url="https://console-staging.flexiloans.com/scoringservice/bre/epimoney";

	@BeforeClass
	public void pre_run() throws Throwable 
	{

//		Reporter.log("--- db conection successfull ---",true);
		loancode =create.IP_Qalified();   
		doc.BS(loancode);
		System.out.println("loancode is  = "+loancode);
		Thread.sleep(40000);

	}

	@Test
	public void Ecom_Partner_Risk_Grade_A() throws Throwable 
	{

		fact.Ecom_Partner_Risk_Grade_A(loancode);

	}

	@Test
	public void Ecom_Partner_Risk_Grade_B() throws Throwable 
	{

		fact.Ecom_Partner_Risk_Grade_B(loancode);

	}

	@Test
	public void Ecom_Partner_Risk_Grade_C() throws Throwable 
	{

		fact.Ecom_Partner_Risk_Grade_c(loancode);

	}

	@Test
	public void Ecom_Partner_Risk_Grade_D() throws Throwable 
	{

		fact.Ecom_Partner_Risk_Grade_D(loancode);

	}

	@Test
	public void Ecom_Partner_Risk_Grade_E() throws Throwable 
	{

		fact.Ecom_Partner_Risk_Grade_E(loancode);

	}

	@Test
	public void Direct_Risk_Grade_A() 
	{
		fact.Direct_Risk_Grade_A(loancode);
	}

	@Test
	public void Direct_Risk_Grade_B() 
	{
		fact.Direct_Risk_Grade_B(loancode);
	}

	@Test
	public void Direct_Risk_Grade_C() 
	{
		fact.Direct_Risk_Grade_C(loancode);
	}

	@Test
	public void Direct_Risk_Grade_D() 
	{
		fact.Direct_Risk_Grade_D(loancode);
	}

	@Test
	public void Direct_Risk_Grade_E() 
	{
		fact.Direct_Risk_Grade_E(loancode);
	}

	@Test
	public void POS_Risk_Grade_A() 
	{
		fact.POS_Risk_Grade_A(loancode);
	}

	@Test
	public void POS_Risk_Grade_B() 
	{
		fact.POS_Risk_Grade_B(loancode);
	}

	@Test
	public void POS_Risk_Grade_C() 
	{
		fact.POS_Risk_Grade_C(loancode);
	}

	@Test
	public void POS_Risk_Grade_D() 
	{
		fact.POS_Risk_Grade_D(loancode);
	}

	@Test
	public void POS_Risk_Grade_E() 
	{
		fact.POS_Risk_Grade_E(loancode);
	}

	@Test
	public void FoodTech_Risk_Grade_A() 
	{
		fact.FoodTech_Risk_Grade_A(loancode);
	}

	@Test
	public void FoodTech_Risk_Grade_B() 
	{
		fact.FoodTech_Risk_Grade_B(loancode);
	}

	@Test
	public void FoodTech_Risk_Grade_C() 
	{
		fact.FoodTech_Risk_Grade_C(loancode);
	}

	@Test
	public void FoodTech_Risk_Grade_D() 
	{
		fact.FoodTech_Risk_Grade_D(loancode);
	}

	@Test
	public void FoodTech_Risk_Grade_E() 
	{
		fact.FoodTech_Risk_Grade_E(loancode);
	}

	@Test
	public void Top_Up_Direct_Risk_Grade_A() 
	{
		fact.Top_Up_Direct_Risk_Grade_A(loancode);
	}

	@Test
	public void Top_Up_Direct_Risk_Grade_B() 
	{
		fact.Top_Up_Direct_Risk_Grade_B(loancode);
	}

	@Test
	public void Top_Up_Direct_Risk_Grade_C() 
	{
		fact.Top_Up_Direct_Risk_Grade_C(loancode);
	}

	@Test
	public void Top_Up_Direct_Risk_Grade_D() 
	{
		fact.Top_Up_Direct_Risk_Grade_D(loancode);
	}

	@Test
	public void Top_Up_Direct_Risk_Grade_E() 
	{
		fact.Top_Up_Direct_Risk_Grade_E(loancode);
	}


	@AfterMethod
	public void call() 
	{
		HashMap map = new HashMap();
		map.put("loan_code", loancode);

		ValidatableResponse res = given().body(map).contentType("application/json").when().post(url).then().log().all();
		String response = res.extract().body().asPrettyString();
		
	}



}
