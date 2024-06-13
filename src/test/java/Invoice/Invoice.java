package Invoice;

import java.sql.SQLException;

import org.junit.After;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import POM_Invoice.landing;
import POM_Invoice.partner;
import POM_Invoice.vendor;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;

public class Invoice extends BaseClass{


	String invoice_No;
	String otp;
	String lmsLoanId;
	DocumentUpload doc= new DocumentUpload();

	@BeforeClass
	public void launch_url() throws Throwable 
	{
		String URL=ObjectReaders.readers.getInvoice_Uat();
		driver.get(URL);
		DataBaseUtility.connectToDB();
	}

	@Test(priority=1)
	public void Vendors_Portal() 
	{
		landing  lp = new landing(BaseClass.driver);

		lp.Email("vendor2023@flexiloans.in");
		lp.Password("zkV2nhdBntuD5pYxrhH2n2Pp3mHkK1603");
		lp.SignIn();
	}

	@Test(priority=2)
	public void Create_Invoice() throws InterruptedException 
	{
		vendor lp = new vendor(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.partner_company );
		lp.partner_company();
		lp.Lendor_company();
		Thread.sleep(1000);
		invoice_No=lp.Invoice_No(jUtil.randome10string());
		Thread.sleep(1000);
		lp.Invoice_Value("20000");
		Thread.sleep(1000);
		lp.ChooseFile(doc.documentWorkFlow(Filepath.BankStatementFilePath));
		Thread.sleep(1000);
		lp.Submit();
		Thread.sleep(1000);
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Search );
		lp.Search(invoice_No);
		lp.Logout();

	}


	@Test(priority=3)
	public void partner_Portal() 
	{
		landing  lp = new landing(BaseClass.driver);

		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Email );
		lp.Email("partner2023@flexiloans.in");
		lp.Password("xdR8GVHwJgcvnLwtd0qEbTz76AWI84740");
		lp.SignIn();

	}
	
	@Test(priority=4)
	public void Partner_index() throws SQLException, InterruptedException 
	{
		partner lp = new partner(BaseClass.driver);
		
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Transection );

		lp.Transection();
		wUtil.waitForElementToBeVisible(BaseClass.driver, lp.Search );
		lp.Search(invoice_No);
		lp.Maker_Approve();
		lp.Submit();
		lp.CheckBox();
		lp.Checker_Approve();
		lp.Save_Btn();
		String query="select approvalKey from invoice_db_dev.TransactionEntries where invoiceNo='"+invoice_No+"'";
		otp=DataBaseUtility.ExecuteQuery(query);
		lp.Otp(otp);
		lp.Final_Submit();
		String query1="select lmsLoanId from invoice_db_dev.TransactionEntries where invoiceNo='"+invoice_No+"'";
		lmsLoanId=dbUtil_Dev.ExecuteQuery(query1);
		Thread.sleep(20000);

	}

     @AfterClass
     public void print() 
     {
    	 System.out.println("invoice_No  ="+invoice_No);
    	 System.out.println("lmsLoanId  ="+lmsLoanId);
     }








}
