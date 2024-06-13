package Nykaa_Pojo;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.ThreadLocalClass;

public class LoanBusiness {



	/*declaring the variables*/
	private String business_name;
	private String pincode;
	private String legal_status;

	/*creating contractor*/
	public LoanBusiness(String TCID, String SheetName) {
		ExcelFileUtility excel=ThreadLocalClass.getexcelUtil();
		excel.initSheet(SheetName);
		this.business_name = excel.getExcelDataForSingleRow(TCID,"LB_business_name");
		this.pincode =excel.getExcelDataForSingleRow(TCID,"LB_pincode");
		this.legal_status = excel.getExcelDataForSingleRow(TCID,"LB_legal_status");
	}
	/*setter method is used to set the parameter value and getter method is used to get the parameter value*/
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public void setLegal_status(String legal_status) {
		this.legal_status = legal_status;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public String getPincode() {
		return pincode;
	}

	public String getLegal_status() {
		return legal_status;
	}
}
