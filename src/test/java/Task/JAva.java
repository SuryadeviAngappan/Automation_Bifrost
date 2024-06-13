package Task;

import Nykaa_POM.form1;

public class JAva {

	
	public static void main(String[] args) {
		
	
	
	int[] s = {1,2,4,5,3,8,9,0,4,2};
	int count=0;
	
	for(int i =0; i< s.length-1;i++) 
	{
		
		if(s[i]==s[i+1]) 
		{
			count++;
		}
		
	}
	
	
	for(int j=0; j<count;j++) 
	{
		if(count==1) 
		{
			System.out.println(j);
		}
	}
	
	
	}
}
