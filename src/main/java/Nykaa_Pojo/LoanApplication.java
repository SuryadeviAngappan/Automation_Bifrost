package Nykaa_Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.ThreadLocalClass;



public class LoanApplication {


	/*declaring the variables*/
	private Integer amount;
	@JsonInclude(value = JsonInclude.Include.NON_DEFAULT)
	private String partner_code;
	private LoanBusiness LoanBusiness;

	/*creating contractor*/
	public LoanApplication(String TCID, String SheetName) {
		ExcelFileUtility excel=ThreadLocalClass.getexcelUtil();
		excel.initSheet(SheetName);
		this.amount = JavaUtility.convertToInt(excel.getExcelDataForSingleRow(TCID,"LA_amount"));
		this.partner_code=excel.getExcelDataForSingleRow(TCID,"LA_partner_code");
		this.LoanBusiness = new LoanBusiness(TCID,SheetName);
	}

	/*setter method is used to set the parameter value and getter method is used to get the parameter value*/
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getPartner_code() {
		return partner_code;
	}

	public void setPartner_code(String partner_code) {
		this.partner_code = partner_code;
	}


	public LoanBusiness getLoanBusiness() {
		return LoanBusiness;
	}

	public void setLoanBusiness(LoanBusiness loanBusiness) {
		LoanBusiness = loanBusiness;
	}
}
