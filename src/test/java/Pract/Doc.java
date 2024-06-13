package Pract;

import org.testng.annotations.Test;

import GenericUtilities.BaseClass;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import PropertyFileConfig.ObjectReaders;
import WorkFlowLibrary.DocumentUpload;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;

public class Doc {

	DocumentUpload lp = new DocumentUpload();

	@Test
	public void aadhar(String loancode) throws Throwable 
	{
		DataBaseUtility.connectToDB();
		//  String loancode="660511bar2mlb";
		String token="Bearer eyJraWQiOiJySVwvRTAwZmJVKzVCbFBPTTJjRXZRd0U1cHdVK29nbFl4Y2o3YStvVzA4TT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0bGxmODdhcmtkNHQyaTBrdWgxcWRpNTJycyIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoicGFydG5lclwvcmVkaXJlY3RsaW5rLnJlYWQgZW1hbmRhdGVcL2JhbmstZWxpZ2liaWxpdHkucmVhZCBsbXNcL3JlcGF5bWVudC53cml0ZSBsZWFkXC9yZWFkIGxvYW5hcHBsaWNhdGlvblwvc3RhdHVzLnJlYWQgbGVhZFwvY3JlYXRlIGxvYW5hcHBsaWNhdGlvblwvYXBwcm92ZS5yZWFkIGMyd1wvaW5pdC5yZWFkIGFnZW5jeVwvb3JkZXIuZmV0Y2ggZW1hbmRhdGVcL3VwZGF0ZSBsb2FuYXBwbGljYXRpb25cL2Rpc2J1cnNlLnJlYWQgbG9hbmFwcGxpY2F0aW9uXC9jaGVja3BvaW50cy5yZWFkIGxtc1wvcmVwYXltZW50LnJlYWQgbG9hbmFwcGxpY2F0aW9uXC9iYW5rQWNjb3VudC53cml0ZSBwYXJ0bmVyXC93cml0ZSBjMndcL2luaXQud3JpdGUgZG9jdW1lbnRcL2RlbGV0ZSBlbWFuZGF0ZVwvY3JlYXRlIHBlbm55ZHJvcFwvY3JlYXRlIGNhbGxiYWNrXC93cml0ZSBkb2N1bWVudFwvY3JlYXRlIGNhbGxiYWNrXC9yZWFkIGxlYWRcL3VwZGF0ZSBsb2FuYXBwbGljYXRpb25cL3BheW91dC5jcmVhdGUgcGFydG5lclwvcmVkaXJlY3Qud3JpdGUgbG1zXC9zZXJ2aWNlLndyaXRlIGMyd1wvZG9jdW1lbnQucmVhZCBsb2FuYXBwbGljYXRpb25cL2RlZHVwZS5yZWFkIHBhcnRuZXJcL3JlZGlyZWN0bGluay53cml0ZSBjMndcL3dyaXRlIGMyd1wvc2lnbi5yZWFkIGRvY3VtZW50XC9yZWFkIHBhcnRuZXJcL21lcmNoYW50T2ZmZXIuY3JlYXRlIGRvY3VtZW50XC9sb29rdXAucmVhZCBsbXNcL3ByZWNsb3N1cmUucmVhZCBiYW5rXC9yZWFkIGxvYW5hcHBsaWNhdGlvblwvbG9hbk9mZmVyLndyaXRlIGVtYW5kYXRlXC9iYW5rLWVsaWdpYmlsaXR5LndyaXRlIGxtc1wvc2VydmljZS5yZWFkIGxlYWRcL2xvYW5hcHBsaWNhdGlvbi5yZWFkIHBhcnRuZXJcL21vY2tkYXRhLmNyZWF0ZSBjMndcL3NpZ24ud3JpdGUgYzJ3XC9yZWFkIGRvY3VtZW50XC91cGRhdGUgbG9hbmFwcGxpY2F0aW9uXC9yZXBheW1lbnQucmVhZCBwYXJ0bmVyXC9yZWFkIHBhcnRuZXJcL3JlZGlyZWN0LnJlYWQgbG1zXC9wcmVjbG9zdXJlLndyaXRlIiwiYXV0aF90aW1lIjoxNjU4NzUxMzg0LCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGgtMS5hbWF6b25hd3MuY29tXC9hcC1zb3V0aC0xX0NxNlRwQTVGciIsImV4cCI6MTY1ODc1NDk4NCwiaWF0IjoxNjU4NzUxMzg0LCJ2ZXJzaW9uIjoyLCJqdGkiOiI3YmJhNjVkYS1iZjMzLTRkOTQtOTU4YS1kY2IyZjZlMTdkZTkiLCJjbGllbnRfaWQiOiI0bGxmODdhcmtkNHQyaTBrdWgxcWRpNTJycyJ9.Mq8inQprQHmZq8eTtTQHVKlMzDvLa5CLzTsHGJiWuTWEg77_pgBECP078H25Sl_-lH1Mdzdp1jN8ehyp3zZdKtZqH8loOpAimlNmSZHp6w3FHvJWk-XZjTSDS6zEiV-oSo92RLEooWsEZFDtQfZu0xE0rZzG5eJ4kMZlyOkB0q3eUVWjf68njE_HwYEAlsOMPD3TfXqkz2CAG6KOeZlBAIN9KOuQdj85aqa6xQTzWIV5KDzeNzgykiId6NqXY_--UzFv6xnjbALD759JVvds3wA4pXuF0Wz5m_H80N9NH5z9wzyZPFzYfZq3-sA-u9q6vT8DVsmKlsMnC2wF-glcpQ";
		String query2="update loan_finance_detail set monthly_total_sales='600000' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query2); 

		File file= new File("."+Filepath.DocumentFilePath);
		Response response = given().contentType("multipart/form-data").multiPart("file",file)
				.multiPart("document_category", "res_address_proof")
				.multiPart("loan_code", loancode)
				.multiPart("document_type", "aadhar")
				.multiPart("source","CKYC")
				.header("Authorization", token).log().all()
				.when().post("https://console-staging.flexiloans.com/documentservice");
		String print= response.prettyPrint();
		System.out.println(print);


	}


	@Test
	public void Gst_Cerificate(String loancode) 
	{

		String token="Bearer eyJraWQiOiJySVwvRTAwZmJVKzVCbFBPTTJjRXZRd0U1cHdVK29nbFl4Y2o3YStvVzA4TT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0bGxmODdhcmtkNHQyaTBrdWgxcWRpNTJycyIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoicGFydG5lclwvcmVkaXJlY3RsaW5rLnJlYWQgZW1hbmRhdGVcL2JhbmstZWxpZ2liaWxpdHkucmVhZCBsbXNcL3JlcGF5bWVudC53cml0ZSBsZWFkXC9yZWFkIGxvYW5hcHBsaWNhdGlvblwvc3RhdHVzLnJlYWQgbGVhZFwvY3JlYXRlIGxvYW5hcHBsaWNhdGlvblwvYXBwcm92ZS5yZWFkIGMyd1wvaW5pdC5yZWFkIGFnZW5jeVwvb3JkZXIuZmV0Y2ggZW1hbmRhdGVcL3VwZGF0ZSBsb2FuYXBwbGljYXRpb25cL2Rpc2J1cnNlLnJlYWQgbG9hbmFwcGxpY2F0aW9uXC9jaGVja3BvaW50cy5yZWFkIGxtc1wvcmVwYXltZW50LnJlYWQgbG9hbmFwcGxpY2F0aW9uXC9iYW5rQWNjb3VudC53cml0ZSBwYXJ0bmVyXC93cml0ZSBjMndcL2luaXQud3JpdGUgZG9jdW1lbnRcL2RlbGV0ZSBlbWFuZGF0ZVwvY3JlYXRlIHBlbm55ZHJvcFwvY3JlYXRlIGNhbGxiYWNrXC93cml0ZSBkb2N1bWVudFwvY3JlYXRlIGNhbGxiYWNrXC9yZWFkIGxlYWRcL3VwZGF0ZSBsb2FuYXBwbGljYXRpb25cL3BheW91dC5jcmVhdGUgcGFydG5lclwvcmVkaXJlY3Qud3JpdGUgbG1zXC9zZXJ2aWNlLndyaXRlIGMyd1wvZG9jdW1lbnQucmVhZCBsb2FuYXBwbGljYXRpb25cL2RlZHVwZS5yZWFkIHBhcnRuZXJcL3JlZGlyZWN0bGluay53cml0ZSBjMndcL3dyaXRlIGMyd1wvc2lnbi5yZWFkIGRvY3VtZW50XC9yZWFkIHBhcnRuZXJcL21lcmNoYW50T2ZmZXIuY3JlYXRlIGRvY3VtZW50XC9sb29rdXAucmVhZCBsbXNcL3ByZWNsb3N1cmUucmVhZCBiYW5rXC9yZWFkIGxvYW5hcHBsaWNhdGlvblwvbG9hbk9mZmVyLndyaXRlIGVtYW5kYXRlXC9iYW5rLWVsaWdpYmlsaXR5LndyaXRlIGxtc1wvc2VydmljZS5yZWFkIGxlYWRcL2xvYW5hcHBsaWNhdGlvbi5yZWFkIHBhcnRuZXJcL21vY2tkYXRhLmNyZWF0ZSBjMndcL3NpZ24ud3JpdGUgYzJ3XC9yZWFkIGRvY3VtZW50XC91cGRhdGUgbG9hbmFwcGxpY2F0aW9uXC9yZXBheW1lbnQucmVhZCBwYXJ0bmVyXC9yZWFkIHBhcnRuZXJcL3JlZGlyZWN0LnJlYWQgbG1zXC9wcmVjbG9zdXJlLndyaXRlIiwiYXV0aF90aW1lIjoxNjU4NzUxMzg0LCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGgtMS5hbWF6b25hd3MuY29tXC9hcC1zb3V0aC0xX0NxNlRwQTVGciIsImV4cCI6MTY1ODc1NDk4NCwiaWF0IjoxNjU4NzUxMzg0LCJ2ZXJzaW9uIjoyLCJqdGkiOiI3YmJhNjVkYS1iZjMzLTRkOTQtOTU4YS1kY2IyZjZlMTdkZTkiLCJjbGllbnRfaWQiOiI0bGxmODdhcmtkNHQyaTBrdWgxcWRpNTJycyJ9.Mq8inQprQHmZq8eTtTQHVKlMzDvLa5CLzTsHGJiWuTWEg77_pgBECP078H25Sl_-lH1Mdzdp1jN8ehyp3zZdKtZqH8loOpAimlNmSZHp6w3FHvJWk-XZjTSDS6zEiV-oSo92RLEooWsEZFDtQfZu0xE0rZzG5eJ4kMZlyOkB0q3eUVWjf68njE_HwYEAlsOMPD3TfXqkz2CAG6KOeZlBAIN9KOuQdj85aqa6xQTzWIV5KDzeNzgykiId6NqXY_--UzFv6xnjbALD759JVvds3wA4pXuF0Wz5m_H80N9NH5z9wzyZPFzYfZq3-sA-u9q6vT8DVsmKlsMnC2wF-glcpQ";
		//  String loancode="6628a77c24ql4";
		File file= new File("."+Filepath.GSTCertficate);
		Response response = given().contentType("multipart/form-data").multiPart("file",file)
				.multiPart("document_category", "company_kyc")
				.multiPart("loan_code", loancode)
				.multiPart("document_type", "gst_registration")             
				.header("Authorization", token).log().all()
				.when().post("https://console-staging.flexiloans.com/documentservice");
		String print= response.getBody().asPrettyString();
		System.out.println(print);


	}

	@Test
	public void BS(String loancode) throws Throwable 
	{
		DataBaseUtility.connectToDB();
		//   String loancode="65a8fcbbp2cah";
		String token="Bearer eyJraWQiOiJySVwvRTAwZmJVKzVCbFBPTTJjRXZRd0U1cHdVK29nbFl4Y2o3YStvVzA4TT0iLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiI0bGxmODdhcmtkNHQyaTBrdWgxcWRpNTJycyIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoicGFydG5lclwvcmVkaXJlY3RsaW5rLnJlYWQgZW1hbmRhdGVcL2JhbmstZWxpZ2liaWxpdHkucmVhZCBsbXNcL3JlcGF5bWVudC53cml0ZSBsZWFkXC9yZWFkIGxvYW5hcHBsaWNhdGlvblwvc3RhdHVzLnJlYWQgbGVhZFwvY3JlYXRlIGxvYW5hcHBsaWNhdGlvblwvYXBwcm92ZS5yZWFkIGMyd1wvaW5pdC5yZWFkIGFnZW5jeVwvb3JkZXIuZmV0Y2ggZW1hbmRhdGVcL3VwZGF0ZSBsb2FuYXBwbGljYXRpb25cL2Rpc2J1cnNlLnJlYWQgbG9hbmFwcGxpY2F0aW9uXC9jaGVja3BvaW50cy5yZWFkIGxtc1wvcmVwYXltZW50LnJlYWQgbG9hbmFwcGxpY2F0aW9uXC9iYW5rQWNjb3VudC53cml0ZSBwYXJ0bmVyXC93cml0ZSBjMndcL2luaXQud3JpdGUgZG9jdW1lbnRcL2RlbGV0ZSBlbWFuZGF0ZVwvY3JlYXRlIHBlbm55ZHJvcFwvY3JlYXRlIGNhbGxiYWNrXC93cml0ZSBkb2N1bWVudFwvY3JlYXRlIGNhbGxiYWNrXC9yZWFkIGxlYWRcL3VwZGF0ZSBsb2FuYXBwbGljYXRpb25cL3BheW91dC5jcmVhdGUgcGFydG5lclwvcmVkaXJlY3Qud3JpdGUgbG1zXC9zZXJ2aWNlLndyaXRlIGMyd1wvZG9jdW1lbnQucmVhZCBsb2FuYXBwbGljYXRpb25cL2RlZHVwZS5yZWFkIHBhcnRuZXJcL3JlZGlyZWN0bGluay53cml0ZSBjMndcL3dyaXRlIGMyd1wvc2lnbi5yZWFkIGRvY3VtZW50XC9yZWFkIHBhcnRuZXJcL21lcmNoYW50T2ZmZXIuY3JlYXRlIGRvY3VtZW50XC9sb29rdXAucmVhZCBsbXNcL3ByZWNsb3N1cmUucmVhZCBiYW5rXC9yZWFkIGxvYW5hcHBsaWNhdGlvblwvbG9hbk9mZmVyLndyaXRlIGVtYW5kYXRlXC9iYW5rLWVsaWdpYmlsaXR5LndyaXRlIGxtc1wvc2VydmljZS5yZWFkIGxlYWRcL2xvYW5hcHBsaWNhdGlvbi5yZWFkIHBhcnRuZXJcL21vY2tkYXRhLmNyZWF0ZSBjMndcL3NpZ24ud3JpdGUgYzJ3XC9yZWFkIGRvY3VtZW50XC91cGRhdGUgbG9hbmFwcGxpY2F0aW9uXC9yZXBheW1lbnQucmVhZCBwYXJ0bmVyXC9yZWFkIHBhcnRuZXJcL3JlZGlyZWN0LnJlYWQgbG1zXC9wcmVjbG9zdXJlLndyaXRlIiwiYXV0aF90aW1lIjoxNjU4NzUxMzg0LCJpc3MiOiJodHRwczpcL1wvY29nbml0by1pZHAuYXAtc291dGgtMS5hbWF6b25hd3MuY29tXC9hcC1zb3V0aC0xX0NxNlRwQTVGciIsImV4cCI6MTY1ODc1NDk4NCwiaWF0IjoxNjU4NzUxMzg0LCJ2ZXJzaW9uIjoyLCJqdGkiOiI3YmJhNjVkYS1iZjMzLTRkOTQtOTU4YS1kY2IyZjZlMTdkZTkiLCJjbGllbnRfaWQiOiI0bGxmODdhcmtkNHQyaTBrdWgxcWRpNTJycyJ9.Mq8inQprQHmZq8eTtTQHVKlMzDvLa5CLzTsHGJiWuTWEg77_pgBECP078H25Sl_-lH1Mdzdp1jN8ehyp3zZdKtZqH8loOpAimlNmSZHp6w3FHvJWk-XZjTSDS6zEiV-oSo92RLEooWsEZFDtQfZu0xE0rZzG5eJ4kMZlyOkB0q3eUVWjf68njE_HwYEAlsOMPD3TfXqkz2CAG6KOeZlBAIN9KOuQdj85aqa6xQTzWIV5KDzeNzgykiId6NqXY_--UzFv6xnjbALD759JVvds3wA4pXuF0Wz5m_H80N9NH5z9wzyZPFzYfZq3-sA-u9q6vT8DVsmKlsMnC2wF-glcpQ";
		String query2="update loan_finance_detail set monthly_total_sales='600000' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query2); 
		String name=ObjectReaders.readers.get_FullName();
		//EDEN RESIDENCY PRIVATE LIMITED
		String query3="update loan_applicant_detail set name='"+name+"' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query3); 
		String b_name="update loan_business_detail set business_name='"+name+"' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(b_name); 

		File file= new File("."+Filepath.BankStatementFilePath.toString());
		Response response = given().contentType("multipart/form-data").multiPart("file",file)
				.multiPart("document_category", "bank_statement")
				.multiPart("loan_code", loancode)
				.multiPart("document_type", "bank_statement_current_6")
				.multiPart("source","LOS")
				.header("Authorization", token).log().all()
				.when().post("https://console-staging.flexiloans.com/documentservice");
		String print= response.getBody().asPrettyString();
		System.out.println(print);


	}

}
