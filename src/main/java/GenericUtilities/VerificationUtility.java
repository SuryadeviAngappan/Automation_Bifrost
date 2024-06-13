package GenericUtilities;

import java.text.DecimalFormat;
import java.util.Map;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class VerificationUtility {

	 /**
     * This method is used to verify multiple parameter in responses
     * @param response
     * @param JSONPATH###Expdata
     */

    public void verifyResponseBody( Response response, String... act_expDatas){
        //int count=0;
        ExtentTest test = ThreadLocalClass.gettestlevel();
        for (String act_exp:act_expDatas ) {
            String[] dataSet = act_exp.split(Commanparameter.KEY_VALUE_SEPERATOR);
            String jsonPath = dataSet[0];
            String expData = dataSet[1];
            String[] ar = jsonPath.split("\\.");
            String actualData=ThreadLocalClass.getrestAPIUtil().getjasondata(response, jsonPath);
            try{
                Double doubleActData = Double.parseDouble(actualData);
                Double doubleExpData =Double.parseDouble(expData);
                DecimalFormat format = new DecimalFormat("0.##");
                actualData=format.format(doubleActData);
                expData=format.format(doubleExpData);
            }
            catch (Exception e){

            }
            try {
                Assert.assertEquals(actualData, expData, ar[ar.length - 1] + " is not matching in response");
                test.log(Status.PASS, "Verified that '" + ar[ar.length - 1] + "' is correct in response.");
            }catch (Error e){
                test.log(Status.FAIL, "Verified that '" + ar[ar.length - 1] + "' is  incorrect in response."+
                        " <br> Actual data is : <b>"+actualData+"</b> <br> " +
                        "Expected data is : <b>"+expData);
                //count++;
            }
        }
		/*if (count>=1){
			Assert.fail("Response validation Failed");
		}*/

    }
    
    /**
     * @Description: This method is used to verify response status Line as "PreconditionFailed" and add the response in extent report
     * @param res
     */
    public  void verifyResponseHeaders(Response res, String expCode) {
        ExtentTest testlevel = ThreadLocalClass.gettestlevel();
        String[] statusCodeWithMsg = expCode.split(Commanparameter.KEY_VALUE_SEPERATOR);
        int expStatusCode = Integer.parseInt(statusCodeWithMsg[0]);
        String expResponseMessage = statusCodeWithMsg[1];
        ValidatableResponse ValidateResponse = res.then();
        testlevel.log(Status.INFO,"Status code is :"+res.getStatusCode());
        testlevel.log(Status.INFO,"Response Message is :"+ res.getStatusLine());
        testlevel.log(Status.INFO,"Content Type is " +res.contentType());
try {
    testlevel.log(Status.INFO, "<b>Response Body:-</b>"+System.lineSeparator()+"<pre>"+ res.getBody().prettyPrint()+ "</pre>");
}catch (Exception e){

}
        Assert.assertEquals(ValidateResponse.extract().statusCode(),expStatusCode,"Response showing different Status Code");
        Assert.assertTrue(ValidateResponse.extract().statusLine().contains(expResponseMessage),"Response showing different Status message");
        testlevel.log(Status.PASS,"Verified the Response Code as "+expStatusCode+" & Response Message as "+expResponseMessage);
    }

  
}
