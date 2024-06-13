package WorkFlowLibrary;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.ThreadLocalClass;
import io.restassured.response.Response;
import Nykaa_Pojo.*;


public class LeadWorkFlowLib {
	
	public Response createLead(String TCID,String SheetName){

        /*creating object of excel util by using thread local class */
        ExcelFileUtility excel = ThreadLocalClass.getexcelUtil();

        /*creating the object of pojo class*/
        CreateLead_Nykaa crateLead=new CreateLead_Nykaa(TCID,SheetName);

        String endpoint="/unified/lead";
        /*sending the http request components*/
        Response res = ThreadLocalClass.getrestAPIUtil().postRequest(crateLead, ThreadLocalClass.gettoken(), endpoint, true);

        return res;
    }


	
}
