package stepdefs;

import enums.RequestType;
import generic.BaseRequest;
import generic.RestClient;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import mapper.LoginMapper;
import model.LoginModel;
import utils.assertion.AssertionUtil;

public class MyStepdefs {

    @When("user hits login api using {string} and {string}")
    public void user_hits_login_api_using_and(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
        LoginModel loginModel=LoginMapper.map(dataTable);
        BaseRequest req=new BaseRequest(RequestType.GET,"url");
        Response res=RestClient.callApi("url",RequestType.GET,RestClient.buildRequest(req,"baseURI","200"));
        AssertionUtil.assertTrue(res.getStatusCode()==200,"status code is as per expectation","status code is wrong");
    }


}
