package generic;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseResponse implements IResponse{
    private Response response;

    public void setResponse(Response res) {
        this.response = res;
    }

    @Override
    public Map<String, ?> getHeaders() {
        List<Header> headerList =  this.response.getHeaders().asList();
        Map<String, String> headers = new HashMap<>();
        for(Header header : headerList){
            headers.put(header.getName(),header.getValue());
        }
        return headers;
    }

    @Override
    public String getResponseAsString() {
        return this.response.asString();
    }

    @Override
    public int getStatusCode(){
        return this.response.statusCode();
    }

    @Override
    public void validateStatusCode(int statusCode) {
        Assert.assertEquals(getStatusCode(),statusCode,"Actual and Expected Status code do not match");
    }

    @Override
    public <T> T jsonPath(String path) {
        return this.response.jsonPath().get(path);
    }

    @Override
    public <T> T as(Class<T> cls) {
        return this.response.as(cls);
    }
}
