package testAPIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import static util.ExcelReader.*;
import static util.ExcelWriter.*;

public class TestGeoSuggest {

String fileName="";
String apiURL="";
String outputFile="";
    @Test
    public void testGeoSuggestAPI()
    {
        ReadExcelSheet(apiURL,fileName);
    }

    private void ReadExcelSheet(String apiURL,String fileName) {
        ArrayList<String> input=readFile(fileName);
        Response response;
        Integer actualResponseCode=0;
        writeExcel(outputFile);
        for(String row:input)
        {
            ArrayList<String> output=readFile(fileName);
            output.add(row);
            response=callAPI(apiURL,row);
            output.add(String.valueOf(response.getStatusCode()));
           actualResponseCode=response.getStatusCode();
            output.add(String.valueOf(response.getBody()));
            output.add(String.valueOf(((String) response.jsonPath().get("hotels")).length()>0));
            appendToExcel(outputFile,output);
        }
        verifyStatusCode(200,actualResponseCode);
        
    }

    private void verifyStatusCode(Integer expectedStatusCode,Integer actualStatusCode) {
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
    }

    private Response callAPI(String apiUrl, String input) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .param("query",input)
                .when().get(apiUrl).thenReturn();

    }

}
