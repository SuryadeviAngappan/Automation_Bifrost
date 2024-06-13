package Nykaa_Pojo;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.ThreadLocalClass;

public class CreateLead_Nykaa {
	
	 /*declaring the variables*/
    private String first_name;
    private String last_name;
    private String mobile_no;
    private LoanApplication loanApplication;

    /*creating contractor to pass the parameter value*/
    public CreateLead_Nykaa(String first_name, String last_name, String mobile_no, String email, LoanApplication loanApplication) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_no = mobile_no;
        this.loanApplication = loanApplication;
    }

    /**
     * @Description: this method is used to get the data from excel for each parameter.
     * @param TCID : pass Testcase id.
     * @param sheetName : pass Sheet Name.
     */
    public CreateLead_Nykaa(String TCID,String sheetName) {
        ExcelFileUtility excel=ThreadLocalClass.getexcelUtil();
        excel.initSheet(sheetName);
        this.first_name = excel.getExcelDataForSingleRow(TCID,"first_name");
        this.last_name = excel.getExcelDataForSingleRow(TCID,"last_name");
        this.mobile_no = excel.getExcelDataForSingleRow(TCID,"mobile_no");
        this.loanApplication = new LoanApplication(TCID,sheetName);
    }

    /*setter method is used to set the parameter value and getter method is used to get the parameter value*/
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }


    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }
}
