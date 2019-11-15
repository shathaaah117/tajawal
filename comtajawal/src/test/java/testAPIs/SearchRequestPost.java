package testAPIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static util.ExcelReader.readFile;
import static util.ExcelWriter.appendToExcel;
import static util.ExcelWriter.writeExcel;

public class SearchRequestPost {
    String fileName="";
    String apiURL="";
    String outputFile="";
    @Test
    public void testGeoSuggestAPI() throws JSONException {
        ReadExcelSheet(apiURL,fileName);
    }

    private void ReadExcelSheet(String apiURL,String fileName) throws JSONException {
        ArrayList<String> input=readFile(fileName);
        Response response;
        Integer actualResponseCode=0;
        writeExcel(outputFile);
        for(String row:input)
        {
            ArrayList<String> output=readFile(fileName);
            JSONArray requestBody=setBody();
            output.add(String.valueOf(requestBody));
            response=callAPI(apiURL,requestBody);
            output.add(String.valueOf(response.getStatusCode()));
            actualResponseCode=response.getStatusCode();
            output.add(String.valueOf(response.getBody()));
            appendToExcel(outputFile,output);
        }
        verifyStatusCode(200,actualResponseCode);

    }
    public JSONArray setBody() throws JSONException {
        JSONArray mainList = new JSONArray();

try {



    JSONObject datesList = new JSONObject();
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    datesList.put("checkin", simpleDateFormat.format(new Date()));
    datesList.put("checkout", simpleDateFormat.format(new Date()));

    mainList.put(datesList);


    JSONObject destination = new JSONObject();
    destination.put("destination", "paris");
    mainList.put(destination);



    JSONArray room=new JSONArray();
    JSONArray guestRoom=new JSONArray();

    JSONObject type=new JSONObject();
    type.put("type",generatingRandomAlphabeticString());

    guestRoom.put(type);
    room.put(guestRoom);
    mainList.put(room);





    JSONArray guest=new JSONArray();

    JSONObject typeGuest=new JSONObject();
    type.put("type",generatingRandomAlphabeticString());

    guest.put(typeGuest);
    mainList.put(guest);


    JSONObject placeID=new JSONObject();
    placeID.put("placeId",generatingRandomAlphanumaricString());
    mainList.put(placeID);


}catch (JSONException e) {
    e.printStackTrace();
}

        return mainList;
    }

    private void verifyStatusCode(Integer expectedStatusCode,Integer actualStatusCode) {
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
    }

    private Response callAPI(String apiUrl, JSONArray input) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .when().body(input).post(apiUrl).thenReturn();

    }
    public String generatingRandomAlphabeticString() {
        String generatedString = RandomStringUtils.randomAlphabetic(3);

        return generatedString;
    }

    public String generatingRandomAlphanumaricString() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);

        return generatedString;
    }
}
