package countries.low_level;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

public class GetCountriesByCodeTestWithParams {
	private final String DUMMY_CODES = "usa;nor";
	private final String FIRST_COUNTY = "USA";
	private final String FIRST_COUNTY_ALT_NAME = "CO";
	private final String SECOND_COUNTY = "Norway";
	private final String INCORRECT_COUNTY = "Russia";

	private final String DOMAIN = "https://restcountries-v1.p.rapidapi.com/";
	private final String COUNTRY_BY_CODE_ENDPOINT = DOMAIN + "alpha/";

	private RequestSpecification REQUEST_SPECIFICATION;

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
				.param("codes", DUMMY_CODES)
				.get(COUNTRY_BY_CODE_ENDPOINT)
				.then()
				.statusCode(HttpStatus.SC_OK).and()
				.body("name[0]", is(FIRST_COUNTY))
				.body("name[1]", is(SECOND_COUNTY))
				.body("name", is(asList(FIRST_COUNTY, SECOND_COUNTY)))
				.body("altSpellings[0]", hasItem(FIRST_COUNTY_ALT_NAME))
				.body("name", not(INCORRECT_COUNTY))
				.header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
	}
}