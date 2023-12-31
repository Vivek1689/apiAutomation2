package generic;

import enums.RequestType;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.urlEncodingEnabled;

public class RestClient {

    public static Response callApi(String url, RequestType requestType, RequestSpecification reqSpec) {
        switch (requestType.getRequestType().toLowerCase()) {
            case "get":
                return  RestAssured.given()
                        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),new ErrorLoggingFilter())
                        .spec(reqSpec).when().get(url);
            case "post":
                return  RestAssured.given()
                        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),new ErrorLoggingFilter())
                        .spec(reqSpec).when().post(url);
            case "put":
                return  RestAssured.given()
                        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),new ErrorLoggingFilter())
                        .spec(reqSpec).when().put(url);
            case "patch":
                return  RestAssured.given()
                        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),new ErrorLoggingFilter())
                        .spec(reqSpec).when().patch(url);
            case "delete":
                return  RestAssured.given()
                        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(),new ErrorLoggingFilter())
                        .spec(reqSpec).when().delete(url);
            default:
                return null;
        }
    }

    public static RequestSpecification buildRequest (BaseRequest req, String basePath, String responseCode) {
        RequestSpecification reqSpec = new RequestSpecBuilder().build();
        reqSpec.relaxedHTTPSValidation().urlEncodingEnabled(urlEncodingEnabled);
        if(basePath!=null) reqSpec.baseUri(basePath);
        if(req.getBody() != null) reqSpec.body(req.getBody());
        if(!req.getHeaders().isEmpty()) reqSpec.headers(req.getHeaders());
        if(!req.getBasicAuth().isEmpty() && req.getBasicAuth().containsKey("username") && req.getBasicAuth().containsKey("password")) reqSpec.auth().preemptive().basic(req.getBasicAuth().get("username").toString(), req.getBasicAuth().get("password").toString());
        if(!req.getQueryParams().isEmpty()) reqSpec.queryParams(req.getQueryParams());
        if(!req.getPathParams().isEmpty()) reqSpec.pathParams(req.getPathParams());
        if(!req.getFormParams().isEmpty()) reqSpec.formParams(req.getFormParams());
        if(!req.getMultiPart().isEmpty()) {
            for (Map.Entry<String,Object> entry : req.getMultiPart().entrySet())
                reqSpec.multiPart(entry.getKey(), new File(entry.getValue().toString()));
        }
        return  reqSpec;
    }

}