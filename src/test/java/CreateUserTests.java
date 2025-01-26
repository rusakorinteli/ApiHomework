import Utils.Auth;
import Utils.LombokData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends Auth {
    @Test
    public void testTwo(){
        LombokData userInfo = new LombokData();
        userInfo.setUserName("Rusa_korinteli555");
        userInfo.setPassword("Rusanna1234@");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userInfo)
                .when()
                .post("/Account/v1/user")
                .then()
                .log().all()
                .extract().response();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201, "Status code is 201");

        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("userID"), "Response contains userID");
    }

}
