package Document_Upload;


import static  io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import GenericUtilities.Commanparameter;
import PropertyFileConfig.ObjectReaders;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class demo {

	
	    @Test
		public  void UploadPDF( ) throws Throwable 
	{
	
		 String path=ObjectReaders.readers.BankStatement();
		 File filepath = new File(path);
		 String apiURI="https://console-staging.flexiloans.com/documentservice";
		 String loancode="64991e968nh6n";
		 String document_Category="bank_statement";
		 String document_Type="bank_statement_current_6";
		 String file="file";
		
			Response response= given().contentType("multipart/form-data").multiPart(Commanparameter.FILE,filepath)
	                .multiPart(Commanparameter.DOCUMENT_CATEGORY, document_Category)
	                .multiPart(Commanparameter.LOAN_CODE, loancode)
	                .multiPart(Commanparameter.DOCUMENT_TYPE, document_Type)
	                .multiPart(Commanparameter.FILE,file)
	                .header("Authorization", "Basic dWJqajZyNDZ0Z2U0OHQ5MDBnZnBpNnNxZDoxMWV2NmE5NHZyMW10bXFxdDNpbGJzcGxsazBxbzhxc3E4N29qMjF0NGxzcm11MjU2YjJ1").log().all()
			        .when()
			        .post(apiURI);
			  
		String body=response.getBody().asPrettyString();
		int statuscode=response.getStatusCode();
		
		System.out.println("body   ="+body);
		System.out.println("statuscode   ="+statuscode);
		
		        
		
	}
	
	
}

