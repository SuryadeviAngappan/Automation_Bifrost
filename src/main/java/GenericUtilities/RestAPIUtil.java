package GenericUtilities;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.Map;
import io.restassured.http.ContentType;
import io.restassured.response.Response;




public class RestAPIUtil {

	public  Response postRequest(Object obj, String token, String Endpoint,boolean _isReport, Map<String, String>... pathParamsAndHeaders)
	{
		ExtentTest testlevel = ThreadLocalClass.gettestlevel();
		RequestSpecification requestSpec = given().auth().oauth2(token);
		if(pathParamsAndHeaders.length>=1){
			if (pathParamsAndHeaders[0].size()>=1)requestSpec.pathParams(pathParamsAndHeaders[0]);
		}
		if(pathParamsAndHeaders.length>=2)	{
			if (pathParamsAndHeaders[1].size()>=1) requestSpec.headers(pathParamsAndHeaders[1]);
		}
		requestSpec.contentType(ContentType.JSON).body(obj).log().all();
		try {

		if (_isReport) {
			//testlevel.log(Status.INFO,"Request Body : "+ new ObjectMapper().writeValueAsString(obj));
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(obj));
			String prettyJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
			testlevel.log(Status.INFO, "<b>Request Body:-</b>" + System.lineSeparator() + "<pre>" + prettyJsonString + "</pre>");
		}

		}catch (Exception e){		}
	Response res=requestSpec.when().post(Endpoint);
		//res.then().log().all();
		return res;
	}
	
	/**
	 * @Description: This method is used to extract the response data based on JSON path
	 * @param res
	 * @param path
	 * @return
	 */
	public String  getjasondata(Response res, String path)

	{
		String data=null;
		try {
			data = res.jsonPath().get(path).toString();
		}catch (NullPointerException E){
			//return null;
		}
		return data;
	}
	

}
