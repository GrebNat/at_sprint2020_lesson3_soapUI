package countries.low_level;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

public class GetCountriesByCodeTest {

    @SneakyThrows
    private Properties getProperties() {
        Properties props = new Properties();
        String propFileName = "test.properties";
        props.load(getClass().getClassLoader().getResourceAsStream(propFileName));
        return props;
    }

    @BeforeMethod
    public void setup() {
    }

    @AfterMethod
    public void teardown() {
    }

    @Test(description = "Get country by one code")
//----------------------simple fail test without headers
    void getCountryByOneCodeFail() {
        RestAssured
                .post("https://restcountries-v1.p.rapidapi.com/alpha/?codes=co;nor")
                .then().statusCode(401).and().body(is(""));
    }

    @Test(description = "Get country by one code")
//---------------------simple pass tests with headers
    void getCountryByOneCodePass() {
        RestAssured
                .given()
                .header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                .header("X-RapidAPI-Key", getProperties().get("X-RapidAPI-Key"))
                .when()
                .get("https://restcountries-v1.p.rapidapi.com/alpha/?codes=co;nor")
                .then()
                .statusCode(200);
    }

    @Test(description = "Get country by one code")
//simple pass test with  Request Specification and hamcrest
    void getCountryByOneCodeWithConfig() {
        RequestSpecification requestSpecification = given()
                .header("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                .header("X-RapidAPI-Key", getProperties().get("X-RapidAPI-Key"));

        requestSpecification
                .param("codes", "co;nor")
                .get("https://restcountries-v1.p.rapidapi.com/alpha/")
                .then()
                .statusCode(200)
                .body("name[0]", Matchers.notNullValue())
                .body("name[1]", is("Norway"))
                .body("name", is(asList("Colombia", "Norway")))
                .body("altSpellings[0]", hasItem("CO"))
                .body("translations[0].de", is("Kolumbien"))
                .body("name", not("Russia"));
    }

    @Test(description = "Get country by one code")
//test will pass
    void getCountryByOneCodeWitiConfigLog() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", getProperties().get("X-RapidAPI-Key").toString())
                .setBaseUri("https://restcountries-v1.p.rapidapi.com/")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        given(requestSpecification)
                .param("codes", "co;nor")
                .get("alpha/")
                .then()
                .statusCode(400).and()
                .body("name[0]", is("Colombia"))
                .body("name[1]", is("Norway"))
                .body("name", is(asList("Colombia", "Norway")))
                .body("altSpellings[0]", hasItem("CO"))
                .body("name", not("Russia"));
    }
}
