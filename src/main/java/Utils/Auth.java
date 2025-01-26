package Utils;
import DataObject.HeadersConfig;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
public class Auth {
        public HeadersConfig headConfig;
        @BeforeMethod
        public void setBaseURL() {
            RestAssured.baseURI = "https://bookstore.toolsqa.com/";
            headConfig = new HeadersConfig();
        }
}
