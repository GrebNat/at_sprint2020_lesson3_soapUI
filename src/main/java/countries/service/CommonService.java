package countries.service;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;

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
               //.setBaseUri(ServiceProps.getBaseUrl())

                // .setBaseUri(getProperties().getProperty("baseurl"))
                .addHeader("X-RapidAPI-Host", "restcountries-v1.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", getProperties().get("X-RapidAPI-Key").toString())
                .addFilter(new RequestLoggingFilter())
                //.addFilter(new ResponseLoggingFilter())
                .build();
    }

    public Response getNoParams(String uri) {
        Response response = given(REQUEST_SPECIFICATION).get(uri);
        response.then()
                .statusCode(Matchers.lessThan(300))
                .statusCode(Matchers.greaterThanOrEqualTo(200));
        return response;
    }

    public Response getWithParams(String uri, Map<String, Object> params) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);

        for (Map.Entry<String, Object> param : params.entrySet())
            specification.param(param.getKey(), param.getValue());

        return specification.get(uri);
    }
}
