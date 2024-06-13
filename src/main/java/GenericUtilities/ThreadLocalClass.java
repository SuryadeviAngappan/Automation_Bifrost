package GenericUtilities;


import com.aventstack.extentreports.ExtentTest;

import PropertyFileConfig.PropertyFileReaders;



public class ThreadLocalClass 
{


	public static ThreadLocal<ExtentTest> test=new ThreadLocal<>();
	public  static ThreadLocal<ExtentTest> testlevel=new ThreadLocal<>();
	public static  ThreadLocal<ExtentTest> classlevel=new ThreadLocal<>();
	public static  ThreadLocal<ExcelFileUtility> excelUtil=new ThreadLocal<>();
	public static  ThreadLocal<RestAPIUtil> restAPIUtil=new ThreadLocal<>();
	public static  ThreadLocal<DataBaseUtility>  databaseutil=new ThreadLocal<>();

	public static  ThreadLocal<PropertyFileReaders>  propfile=new ThreadLocal<>();

	public static  ThreadLocal<String> token=new ThreadLocal<>();

	public static ExtentTest gettest() { return test.get(); }



	public static void settest(ExtentTest stest) { test.set(stest);

	}
	public static PropertyFileReaders getpropfile() { return propfile.get(); }

	public static void setpropfile(PropertyFileReaders setpropfile) { propfile.set(setpropfile);

	}

	public static ExcelFileUtility getexcelUtil() {
		return excelUtil.get(); }

	public static void setexcelUtil(ExcelFileUtility sexcelUtil) { excelUtil.set(sexcelUtil);

	}
	public static RestAPIUtil getrestAPIUtil() { return restAPIUtil.get(); }



	public static void setrestAPIUtil(RestAPIUtil srestAPIUtil) { restAPIUtil.set(srestAPIUtil);

	}

	public static DataBaseUtility getdatabaseutil() { return databaseutil.get(); }



	public static void setDatabaseutil(DataBaseUtility sdatabaseutil) { databaseutil.set(sdatabaseutil);

	}


	public static String gettoken() { return token.get(); }



	public static void settoken(String stoken) { token.set(stoken);

	}



	public static ExtentTest   gettestlevel()
	{
		return testlevel.get();
	}


	public static void settestlevel(ExtentTest stestlevel)
	{
		testlevel.set(stestlevel);

	}

	public static ExtentTest   getclasslevel()
	{
		return classlevel.get();
	}


	public  static void setclasslevel(ExtentTest sclasslevel)
	{
		classlevel.set(sclasslevel);
	}
	


    	
}
