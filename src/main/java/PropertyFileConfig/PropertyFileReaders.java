package PropertyFileConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import GenericUtilities.Filepath;


public  class PropertyFileReaders implements ConfigReaders
{

     String filepath;
    // Properties prop;
     
     
     //@Override
 	public void OpenPropertyfile() throws IOException 
 	
 	{
 		
 		filepath=Filepath.QAPropertiesPath;
 		FileInputStream file = new FileInputStream(filepath);
 		Properties prop = new Properties();
 		prop.load(file);
 		
 	}
     
     //@Override
 	public String getAuthorization() throws Throwable {
    	 filepath=Filepath.QAPropertiesPath;
  		FileInputStream file = new FileInputStream(filepath);
  		Properties prop = new Properties();
 		return prop.getProperty("Auth");
 	}
     
     
    
   //  @Override
 	public String getAuthToken(String auth) throws Throwable {
    	 filepath=Filepath.QAPropertiesPath;
  		FileInputStream file = new FileInputStream(filepath);
  		Properties prop = new Properties();
 		return prop.getProperty(auth);
 	}
     
    // @Override
     public String getQuery(String key) throws Throwable 
     {
    	 
    	filepath=Filepath.QAPropertiesPath;
  		FileInputStream file = new FileInputStream(filepath);
  		Properties prop = new Properties();
  		prop.load(file);
  		return prop.getProperty(key);
     }
     
     
     
     public String getEnvPropertiesData(String env,String key) {
 		try {
 			FileInputStream file = new FileInputStream(env);
 			Properties properties = new Properties();
 			properties.load(file);
 			return properties.getProperty(key);
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return "No Such Key in property file: " + key;
 	}
     
     
     /**
  	 * @Description: This method return the value associated with key in property file and all the key value are defined under folder Test data
  	 * with file name DbQuery.properties
  	 * @param key
  	 * @return value
  	 */
    
  	public static String getPropertiesData(String key) {

  		try {
  			FileInputStream file = new FileInputStream(Filepath.DBQueryfilePath);
  			Properties properties = new Properties();
  			properties.load(file);
  			return properties.getProperty(key);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  		return "No Such Key in property file: " + key;
  	}

     
     
    // @Override
     public String getDBUSERNAME() throws Throwable 
     {
    	 filepath=Filepath.QAPropertiesPath;
  		FileInputStream file = new FileInputStream(filepath);
  		Properties prop = new Properties();
  		prop.load(file);
		return prop.getProperty("QA_DBUSERNAME");
	}
     
    // @Override
     public String getDBPASSWORD() throws Throwable  
    {
    	 filepath=Filepath.QAPropertiesPath;
   		FileInputStream file = new FileInputStream(filepath);
   		Properties prop = new Properties();
   		prop.load(file);
		return prop.getProperty("QA_DBPASSWORD");
	}
     
    // @Override
     public String getQA_DBURL() throws Throwable 
     {
    	 filepath=Filepath.QAPropertiesPath;
   		FileInputStream file = new FileInputStream(filepath);
   		Properties prop = new Properties();
   		prop.load(file);
		return prop.getProperty("QA_DBURL");
	}
     
     //@Override
     public String getBrowser() throws Throwable 
    {
    	 filepath=Filepath.QAPropertiesPath;
    		FileInputStream file = new FileInputStream(filepath);
    		Properties prop = new Properties();
    		prop.load(file);
		return prop.getProperty("browser");
	}
     
   //  @Override
	public String getNLP_1() throws Throwable 
    {
    	 filepath=Filepath.QAPropertiesPath;
 		FileInputStream file = new FileInputStream(filepath);
 		Properties prop = new Properties();
 		prop.load(file);
		return prop.getProperty("nlp-1");
	}
     
   //  @Override
	public String getNLP_3() throws Throwable 
	{
    	 filepath=Filepath.QAPropertiesPath;
 		FileInputStream file = new FileInputStream(filepath);
 		Properties prop = new Properties();
 		prop.load(file);
		return prop.getProperty("nlp-3");
	}
    
    // @Override
	public String getNLP_5() throws Throwable 
    {
    	 filepath=Filepath.QAPropertiesPath;
 		FileInputStream file = new FileInputStream(filepath);
 		Properties prop = new Properties();
 		prop.load(file);
		return prop.getProperty("nlp-5");
	}
    
   //  @Override
	public String getNLP_6() throws Throwable 
    {
    	 filepath=Filepath.QAPropertiesPath;
  		FileInputStream file = new FileInputStream(filepath);
  		Properties prop = new Properties();
  		prop.load(file);
		return prop.getProperty("nlp-6");
	}
    
   //  @Override
	public String Offers() throws Throwable 
    {
    	 filepath=Filepath.QAPropertiesPath;
  		FileInputStream file = new FileInputStream(filepath);
  		Properties prop = new Properties();
  		prop.load(file);
		return prop.getProperty("Flow_Offers_URL");
	}
    
   //  @Override
	public String Amazon()throws Throwable 
    {
    	 filepath=Filepath.QAPropertiesPath;
  		FileInputStream file = new FileInputStream(filepath);
  		Properties prop = new Properties();
  		prop.load(file);
		return prop.getProperty("AmazonFlow");
	}
    
  //   @Override
	public String Flipkart() throws Throwable 
	{
    	 filepath=Filepath.QAPropertiesPath;
  		FileInputStream file = new FileInputStream(filepath);
  		Properties prop = new Properties();
  		prop.load(file);
		return prop.getProperty("FlipkartFlow");
	}

  //   @Override
 	public String BankStatement() throws Throwable 
 	{
     	 filepath=Filepath.QAPropertiesPath;
   		FileInputStream file = new FileInputStream(filepath);
   		Properties prop = new Properties();
   		prop.load(file);
 		return prop.getProperty("BankStetement");
 	}
     
     
  //   @Override
  	public String UploadDocument() throws Throwable 
  	{
      	 filepath=Filepath.QAPropertiesPath;
    		FileInputStream file = new FileInputStream(filepath);
    		Properties prop = new Properties();
    		prop.load(file);
  		return prop.getProperty("UploadDocument");
  	}
     
     
   //  @Override
   	public String Prod_DB_URL() throws Throwable 
   	{
       	 filepath=Filepath.QAPropertiesPath;
     		FileInputStream file = new FileInputStream(filepath);
     		Properties prop = new Properties();
     		prop.load(file);
   		return prop.getProperty("QA_Prod_DBURL");
   	}
     
     
    // @Override
    	public String Prod_DB_Username() throws Throwable 
    	{
        	 filepath=Filepath.QAPropertiesPath;
      		FileInputStream file = new FileInputStream(filepath);
      		Properties prop = new Properties();
      		prop.load(file);
    		return prop.getProperty("QA_Prod_DBUSERNAME");
    	}
     
     
     
    // @Override
    	public String Prod_DB_Password() throws Throwable 
    	{
        	 filepath=Filepath.QAPropertiesPath;
      		FileInputStream file = new FileInputStream(filepath);
      		Properties prop = new Properties();
      		prop.load(file);
    		return prop.getProperty("QA_prod_DBPASSWORD");
    	}
     
     
   

		//@Override
		public String get_FullName() throws Throwable {
			 filepath=Filepath.QAPropertiesPath;
	       		FileInputStream file = new FileInputStream(filepath);
	       		Properties prop = new Properties();
	       		prop.load(file);
	       		return prop.getProperty("fullName");
		}

		//@Override
		public String getPinelabs() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("pinelabs");
		}

		//@Override
		public String getMyntra() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("myntra");
		}

		//@Override
		public String getMSSwipe() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("MSSwipe");
		}

		//@Override
		public String getPaisaBazar() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("paisaBazar");
		}

		//@Override
		public String getYatra() throws Throwable {
		
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Yatra");
		}

		//@Override
		public String getXiaomi() throws Throwable {
		
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Xiaomi");
		}

		//@Override
		public String getMeesho() throws Throwable {
			

			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("meesho");
			
		}

		//@Override
		public String getswiggy() throws Throwable {
		

			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("swiggy");
		}

		//@Override
		public String getBajajF() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Bajaj_Finserv");

		}

		//@Override
		public String getZomato() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("zomato");
			
		}

		//@Override
		public String getTruecaller() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("truecaller");
		}

		//@Override
		public String getTally() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("tally");
			
		}

		//@Override
		public String getEasyPay() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Easypay");
			
		}

		@Override
		public String getFinanceBuddha() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("financeBuddha");
		}

		@Override
		public String getFinePedia() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("finePedia");
		}

		@Override
		public String getJumboTailEcom() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("JumboTail_Ecom");		}

		@Override
		public String getDunzo() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Dunzo");
		}

		@Override
		public String getClearTax() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("ClearTax");
		}

		@Override
		public String getAirpay() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Airpay");
		}

		@Override
		public String getEntero() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Entero");
		}

		@Override
		public String getFineway() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Fineway");
		}

		@Override
		public String getRetailio() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("retailio");
		}

		@Override
		public String getFino() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("fino");
		}

		@Override
		public String getVakil_search() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Vakil_search");
		}

		@Override
		public String getBank_Open() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Bank_Open");
		}

		@Override
		public String getMarg_ERP() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Marg_ERP");
		}

		@Override
		public String getDefault() throws Throwable {
			
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("DefaultURL");
		}

		@Override
		public String getRazor_pay_Authorization() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("RAZORPAY_AUTO_LENDING_Authorization");
			
		}

		@Override
		public String getFlow_ahl() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("flow_ahl");
		}

		@Override
		public String getInvoice_Uat() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("invoice_flow");
			
		}

		@Override
		public String getDev_DB_URL() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("QA_Dev_DBURL");
			
			
		}

		@Override
		public String getDev_QA_Username() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("QA_Dev_DBUSERNAME");
			
		}

		@Override
		public String getDev_QA_Password() throws Throwable {
			
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("QA_Dev_DBPASSWORD");
			
		}

		@Override
		public String getNykaa_Token() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Nykaa_BNPL");
		}

		@Override
		public String getFlow_AHL_app() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("flow_AHL_app");		}

		@Override
		public String getPatym() throws Throwable {
			filepath=Filepath.QAPropertiesPath;
       		FileInputStream file = new FileInputStream(filepath);
       		Properties prop = new Properties();
       		prop.load(file);
       		return prop.getProperty("Paytm");
		}
		
		
		}
		
   
	
	
	
	

	
	
	

