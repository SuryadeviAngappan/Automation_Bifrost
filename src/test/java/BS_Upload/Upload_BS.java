package BS_Upload;

import static io.restassured.RestAssured.given;

import java.io.File;

import GenericUtilities.Commanparameter;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.Filepath;
import WorkFlowLibrary.DocumentUpload;
import io.restassured.response.Response;

public class Upload_BS {

	
	static DocumentUpload upload = new DocumentUpload();
	public static void BS_Upload(String loancode) throws InterruptedException 
	{
		File filepath = new File(upload.documentWorkFlow(Filepath.BankStatementFilePath));
		String apiURI="https://console-staging.flexiloans.com/documentservice";  
		String document_Category="bank_statement";
		String document_Type="bank_statement_current_6";
		String query2="update loan_finance_detail set monthly_total_sales='600000' where loan_code='"+loancode+"'";
		DataBaseUtility.executeUpdateQuery(query2);
		Response response= given().contentType("multipart/form-data")
				.multiPart(Commanparameter.FILE,filepath)
				.multiPart(Commanparameter.DOCUMENT_CATEGORY, document_Category)
				.multiPart(Commanparameter.LOAN_CODE, loancode)
				.multiPart(Commanparameter.DOCUMENT_TYPE, document_Type)
				.header("Authorization", "Basic dWJqajZyNDZ0Z2U0OHQ5MDBnZnBpNnNxZDoxMWV2NmE5NHZyMW10bXFxdDNpbGJzcGxsazBxbzhxc3E4N29qMjF0NGxzcm11MjU2YjJ1").log().all()
				.when()
				.post(apiURI);

		String body=response.getBody().asPrettyString();
		int statuscode=response.getStatusCode();
		System.out.println(body);

		Thread.sleep(8000);
	}
	
	
}
