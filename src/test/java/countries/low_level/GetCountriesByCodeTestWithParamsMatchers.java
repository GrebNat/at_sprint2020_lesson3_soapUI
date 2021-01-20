package countries.low_level;

import countries.dto.CountryDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Condition;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.not;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetCountriesByCodeTestWithParamsMatchers {
    private final String CODES = "usa;nor";
    private final String SECOND_COUNTY = "United States";
    private final String SECOND_COUNTY_ALT_NAME = "USA";
    private final String FIRST_COUNTY = "Norway";
    private final String INCORRECT_COUNTY = "Russia";

    private final String DOMAIN = "https://restcountries-v1.p.rapidapi.com/";
    private final String COUNTRY_BY_CODE_ENDPOINT = DOMAIN + "alpha/";

    private RequestSpecification REQUEST_SPECIFICATION;

    private Matchers matchers;

    @SneakyThrows
    private Properties getProperties() {
        Properties props = new Properties();
        String propFileName = "test.properties";
        props.load(getClass().getClassLoader().getResourceAsStream(propFileName));
        return props;
    }

    @BeforeMethod
    public void setup() {
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .addHeader("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", getProperties().get("X-RapidAPI-Key").toString())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter()).build();
    }

    @AfterMethod
    public void teardown() {
    }

    @Test(description = "Get country by one code")
    void getCountryByOneCodeWitiConfigLogPretty() {

        given(REQUEST_SPECIFICATION)
                .param("codes", CODES)
                .get(COUNTRY_BY_CODE_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK).and()
                .body("name[0]", is(SECOND_COUNTY),
                        "name[1]", is(FIRST_COUNTY),
                        "name", is(asList(SECOND_COUNTY, FIRST_COUNTY)),
                        "altSpellings[0]", hasItem(SECOND_COUNTY_ALT_NAME),
                        "name", Matchers.not(INCORRECT_COUNTY))
                .header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }
}