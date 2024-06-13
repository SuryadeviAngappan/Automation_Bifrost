package GenericUtilities;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReportUtility {
	
	
	 public ExtentTest intialLogForTest(String testCaseName){
	        ExcelFileUtility excelUtil = ThreadLocalClass.getexcelUtil();
	      //  excelUtil.initSheet(ExcelFileUtility.INTIAL_TESTSCRIPT_REPORT_LOG_SHEET);
	        ExtentTest test = ThreadLocalClass.gettestlevel();
	        test.log(Status.INFO, "Description :- "+excelUtil.getExcelDataForSingleRow(testCaseName, "TC Description"));
	        test.log(Status.INFO, "TestcaseType :- "+excelUtil.getExcelDataForSingleRow(testCaseName,"TC Type"));
	        return test;
	    }

}
