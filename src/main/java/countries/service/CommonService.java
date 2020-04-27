package countries.service;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class CommonService {
    private RequestSpecification REQUEST_SPECIFICATION;

    @SneakyThrows
    private Properties getProperties() {
        Properties props = new Properties();
        String propFileName = "test.properties";
        props.load(getClass().getClassLoader().getResourceAsStream(propFileName));
        return props;
    }

    public CommonService() {
    	RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .setBaseUri("https://restcountries-v1.p.rapidapi.com/")
                .addHeader("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", getProperties().get("X-RapidAPI-Key").toString())
                .addFilter(new RequestLoggingFilter())
                //.addFilter(new ResponseLoggingFilter())
				.build();
    }

    public Response getNoParams(String uri) {
		given(REQUEST_SPECIFICATION).get(uri).then().statusCode(300);
        return given(REQUEST_SPECIFICATION).get(uri);
    }

    public Response getWithParams(String uri, Map<String, Object> params) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);

        for (Map.Entry<String, Object> param : params.entrySet())
            specification.param(param.getKey(), param.getValue());

        return specification.get(uri);
    }
}
