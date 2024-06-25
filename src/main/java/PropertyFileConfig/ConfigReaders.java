package PropertyFileConfig;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ConfigReaders 
{

	String getQA_DBURL() throws Throwable;
	String getDBUSERNAME() throws Throwable;
	String getDBPASSWORD() throws Throwable;
	String getBrowser() throws Throwable;
	String getNLP_1()throws Throwable;
	String getNLP_3()throws Throwable;
	String getNLP_5()throws Throwable;
	String getNLP_6()throws Throwable;
	String Offers()throws Throwable;
	String Amazon()throws Throwable;
	String Flipkart()throws Throwable;
	String BankStatement() throws Throwable;
	String UploadDocument()throws Throwable;
	String Prod_DB_URL() throws Throwable;
	String Prod_DB_Username() throws Throwable;
	String Prod_DB_Password() throws Throwable;
	String getQuery(String key) throws Throwable;
	void OpenPropertyfile() throws IOException;
	String getAuthToken(String auth) throws Throwable;
	String getAuthorization() throws Throwable;
	String get_FullName() throws Throwable;
	String getPinelabs()throws Throwable;
	String getMyntra()throws Throwable;
	String getMSSwipe()throws Throwable;
	String getPaisaBazar()throws Throwable;
	String getYatra()throws Throwable;
	String getXiaomi()throws Throwable;
	String getMeesho()throws Throwable;
	String getswiggy()throws Throwable;
	String getBajajF()throws Throwable;
	String getZomato()throws Throwable;
	String getTruecaller()throws Throwable;
	String getTally()throws Throwable;
	String getEasyPay()throws Throwable;
	String getFinanceBuddha()throws Throwable;
	String getFinePedia()throws Throwable;
	String getJumboTailEcom()throws Throwable;
	String getDunzo()throws Throwable;
	String getClearTax()throws Throwable;
	String getAirpay()throws Throwable;
	String getEntero()throws Throwable;
	String getFineway()throws Throwable;
	String getRetailio()throws Throwable;
	String getFino()throws Throwable;
	String getVakil_search()throws Throwable;
	String getBank_Open()throws Throwable;
	String getMarg_ERP()throws Throwable;
	String getDefault()throws Throwable;
	String getRazor_pay_Authorization() throws Throwable;
    String getFlow_ahl()throws Throwable;
    String getInvoice_Uat()throws Throwable;
    String getDev_DB_URL()throws Throwable;
    String getDev_QA_Username()throws Throwable;
    String getDev_QA_Password()throws Throwable;
	String getNykaa_Token()throws Throwable;
	String getFlow_AHL_app()throws Throwable;
	String getPatym()throws Throwable;
	String getQA_Dev_DBURL() throws Throwable;
	String getQA_Dev_DBUSERNAME() throws Throwable;
	String getQA_Dev_DBPASSWORD() throws Throwable;
	
	
	
	

	

	

	
	
	
	
	
}
