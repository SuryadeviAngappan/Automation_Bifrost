package GenericUtilities;


public class Commanparameter {



	   public static  final  String KEY_VALUE_SEPERATOR ="###";
	    public static final String LEAD_CREATION_ID="TC_LeadCreationOther";
	    public static final String UNIFIEDLEAD_CREATION_ID="TC_UnifiedLeadCreation";
	    public static final String CLIENT_CODE="client_code";
	    public static final String LOAN_CODE="loan_code";
	    public static final String LEAD_CODE="lead_code";
	    public static final String PARTNER_CODE="code";
	    public static final String DOC_CODE="docCode";
	    public static final String ENV="qa";
	    public static final String AUTHORIZATION="Authorization";
	    public static final String MOBILE_NO="mobile_no";
	    public static final String PINCODE_NO="pincode_no";
	    public static final String CONSIGNEE_CODE="consignee_code";
	    public static final String FILE="file";
	    public static final String DOCUMENT_CATEGORY="document_category"; 
	    public static final String DOCUMENT_TYPE="document_type";


	    public static String getKeyValueForDataBase(String key, String value){
	        String[] ar = key.split("\\.");
	        return  ar[ar.length-1] + Commanparameter.KEY_VALUE_SEPERATOR + value;
	    }
	    public static String getKeyValue(String key, String value){
	        return  key + Commanparameter.KEY_VALUE_SEPERATOR + value;
	    }
	
}
