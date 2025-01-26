import Utils.Auth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static ApiEndpoints.BookStoreEndpoint.GetBooks;
import static DataObject.StatCodes.OK200;

public class BooksApiTest extends Auth {
    @Test
    public void getBooks() {
        Response response = RestAssured
                .given()
                .when()
                .get(GetBooks)
                .then()
                .extract().response();

        SoftAssert softAssert = new SoftAssert();

        int statusCode = response.getStatusCode();
        String autName = response.getBody().jsonPath().getString("books[1].author");
        String publisherName = response.getBody().jsonPath().getString("books[1].publisher");

        softAssert.assertEquals(statusCode, OK200.getValue());
        softAssert.assertEquals(autName, "Addy Osmani");
        softAssert.assertEquals(publisherName, "O'Reilly Media");
        softAssert.assertAll();
    }

}
