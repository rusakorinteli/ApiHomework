import DataObject.ValidationMessages;
import Utils.Auth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static ApiEndpoints.AccountPageEndpoint.PostUser;
import static DataObject.StatCodes.BadReq400;
import static DataObject.StatCodes.Created201;
import static DataObject.UserInfo.*;


public class AccApiTest extends Auth {
    @Test
    public void UserRightPass() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName1);
        requestBody.put("password", password);

        Response response = RestAssured
                .given()
                .headers(headConfig.getHeaders())
                .body(requestBody.toString())
                .when()
                .post(PostUser)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCode, Created201.getValue(), "Wrong success code");
        softAssert.assertTrue(responseBody.contains("userID"), "The response body need to contain user ID");
        softAssert.assertAll();
    }

    @Test
    public void UserWrongPass() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName2);
        requestBody.put("password", WrongPassword);

        Response response = RestAssured
                .given()
                .headers(headConfig.getHeaders())
                .body(requestBody.toString())
                .when()
                .post(PostUser)
                .then()
                .extract().response();

        int statusCodes = response.getStatusCode();
        String actualMsg = response.jsonPath().getString("message");
        String expectedMsg = ValidationMessages.MessageForPassword;

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(statusCodes, BadReq400.getValue(), "Wrong error code");
        softAssert.assertEquals(actualMsg, expectedMsg, "Wrong error message");
        softAssert.assertAll();
    }

}
