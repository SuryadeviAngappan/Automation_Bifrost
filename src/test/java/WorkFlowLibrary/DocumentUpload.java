package WorkFlowLibrary;
import java.io.File;



public class DocumentUpload {
	
	
	   public String documentWorkFlow(String filePath) 
	 
	    {

		 File file= new File(System.getProperty("user.dir")+filePath);
		 String path=file.getAbsolutePath();
		 return path;
		 
	    }

}
