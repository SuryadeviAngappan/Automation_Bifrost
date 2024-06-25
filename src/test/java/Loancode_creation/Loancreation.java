package Loancode_creation;

import java.sql.ResultSet;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.util.Assert;
import com.github.dockerjava.transport.DockerHttpClient.Response;

import JsonPath.DynamicResponse;
import WorkFlowLibrary.LeadWorkFlowLib;
import genaricUtil.Baseclass;
import genaricUtil.CommonParameter;
import genaricUtil.Databaseutil;
import genaricUtil.ExcelUtil;
import genaricUtil.ResponseMessage;
import genaricUtil.ThreadLocalclass;


@Listeners(Listner.ListnerClass.class)
public class Loancreation  extends Baseclass {
	public String loanCode;
    public String leadCode;
    public LeadWorkFlowLib reusableMethod = new LeadWorkFlowLib();
    Databaseutil dbutil = new Databaseutil();
    SoftAssert soft_Assert = new SoftAssert();
    
    /**
     * @Description Validate LoanCode is created successfully from Response
     */
    
    @Test
    public String TC_01_Loan_CreationTest() {
        Response response = (Response) reusableMethod.createLead(TCID, ExcelUtil.REQUEST_BODY_DATA_SHEET_2);
        ThreadLocalclass.getexcelUtil().initSheet(ExcelUtil.REQUEST_BODY_DATA_SHEET_2);
        /*Verification of response header*/
        verificationUtil.verifyResponseHeaders(response, ResponseMessage.$200);
        /*capture & validate the leadCode & loan code from response*/
        loanCode = ThreadLocalclass.getrestAPIUtil().getjasondata(response, DynamicResponse.LOANCODE_LEAD);
        ThreadLocalclass.gettestlevel().log(Status.INFO, "Capturing the loan code from the response");
//        soft_Assert.assertNotNull("leadCode is not present", restAPIUtil.getjasondata(response, "data.leadCode"));
//        ThreadLocalclass.gettestlevel().log(Status.PASS, "Verifying leadcode is generating. Lead code is " + restAPIUtil.getjasondata(response, "data.leadCode"));
        soft_Assert.assertNotNull("loanCode is not present", restAPIUtil.getjasondata(response, "data.loanCode"));
        ThreadLocalclass.gettestlevel().log(Status.PASS, "Verifying loancode is generating. Loan code is " + restAPIUtil.getjasondata(response, "data.loanCode"));
 		System.out.println("LoanCode = "+loanCode);
 		return loanCode;

        
    }
}
