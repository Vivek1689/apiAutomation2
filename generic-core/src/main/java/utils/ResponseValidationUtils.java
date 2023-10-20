package utils;

//import com.dpworld.framework.Constants;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.response.Response;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.skyscreamer.jsonassert.JSONAssert;
//import org.skyscreamer.jsonassert.JSONCompareMode;
//import org.testng.Assert;
//import java.util.ArrayList;
//import java.util.Iterator;

public class ResponseValidationUtils {

//    public static ObjectMapper mapper = new ObjectMapper();
//    public static boolean isResponseValidated = false;
//    public static JSONCompareMode jsonAssertStrictMode = JSONCompareMode.LENIENT;
//    public static boolean printResponseFields = false;
//    public static boolean validateExactMatchStringResponse = false;
//
//    public static boolean responseValidation(JSONObject testData, Response response) {
//        if (null != response) {
//            try {
//                Logger.message("response Status Code : " + response.getStatusCode());
//                Logger.message("response time(ms) : " + response.time());
//                String responseType = testData.get("responseType").toString();
//                String ignoreFields = testData.optString("ignoreFields");
//                String apiName = testData.optString("apiName");
//                if ((null == testData.opt("expectedOutput")) || (null == testData.opt("fieldsToBeVerified"))) {
//                    throw new Exception("Please mention the expectedOutput and fieldsToBeVerified fields in testData !");
//                }
//                JSONArray fieldsToBeVerified = testData.getJSONArray("fieldsToBeVerified");
//
//                Logger.message("response Type is : " + responseType);
//                Logger.message("Fields to be ignored : "+ignoreFields);
//                Logger.message("Fields to be verified : " + fieldsToBeVerified);
//
//                if (printResponseFields) {
//                    printFields(new JSONObject(response.getBody().asString()));
//                } else {
//                    if (response.getContentType().contains("json")) {
//                        if (responseType.equalsIgnoreCase("jsonArray")) {
//                            if (testData.getJSONArray("expectedOutput").length() == 0) {
//                                Assert.assertTrue(response.getStatusCode() == 200);
//                                isResponseValidated = true;
//                            } else {
//                                JSONArray responseAsJsonArray = new JSONArray(response.getBody().asString());
//                                isResponseValidated = validateJsonArrayResponse(testData.getJSONArray("expectedOutput"), responseAsJsonArray, ignoreFields);
//                            }
//                        } else if (responseType.equalsIgnoreCase("jsonObject")) {
//                            if(apiName.toLowerCase().contains("FieldValidation".toLowerCase()) || Constants.environment.equalsIgnoreCase("PROD")) {
//                                isResponseValidated = validateJsonObjectResponseFields(response, fieldsToBeVerified);
//                            } else {
//                                if (testData.getJSONObject("expectedOutput").length() != 0) {
//                                    isResponseValidated = validateJsonObjectResponse(testData.getJSONObject("expectedOutput"), new JSONObject(response.getBody().asString()), ignoreFields);
//                                } else {
//                                    Assert.assertTrue((response.getStatusCode() == 200 || response.getStatusCode() == 400));
//                                    isResponseValidated = true;
//                                }
//                            }
//                        } else {
//                            isResponseValidated = validateStringResponse(testData.get("expectedOutput").toString(), response);
//                        }
//                    } else if (response.asString().contains("fq")) { //Solr response validation
//                        isResponseValidated = validateJsonObjectResponse(testData.getJSONObject("expectedOutput"), new JSONObject(response.getBody().asString()), ignoreFields);
//                    } else {
//                        isResponseValidated = validateStringResponse(testData.get("expectedOutput").toString(), response);
//                    }
//                }
//            } catch (Exception e) {
//                isResponseValidated = validateStringResponse(testData.get("expectedOutput").toString(), response);
//                e.printStackTrace();
//            }
//        } else {
//            Logger.message("response is null !");
//            isResponseValidated = true;
//        }
//        return isResponseValidated;
//    }
////
//    public static boolean validateJsonArrayResponse(JSONArray expectedOutput, JSONArray response, String ignoreFields) {
//        boolean validateJsonArrayResponse = false;
//        try {
//            if(!ignoreFields.isEmpty()) {
//                Iterator<JsonNode> elements = mapper.readTree(ignoreFields).iterator();
//                while (elements.hasNext()) {
//                    String key = elements.next().asText();
//                    removeFields(expectedOutput, key);
//                    removeFields(response, key);
//                }
//            }
//            Assert.assertEquals(mapper.readTree(expectedOutput.toString()), mapper.readTree(response.toString()));
//            validateJsonArrayResponse = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            printResponse(expectedOutput.toString(), response.toString());
//            e.printStackTrace();
//            Logger.fail("Validation failed");
//        }
//        return validateJsonArrayResponse;
//    }
//
//    public static boolean validateJsonObjectResponse(JSONObject expectedOutput, JSONObject response, String ignoreFields) {
//        boolean validateJsonObjectResponse = false;
//        try {
//            if(!ignoreFields.isEmpty() || ignoreFields != null) {
//                Iterator<JsonNode> elements = mapper.readTree(ignoreFields).iterator();
//                while (elements.hasNext()) {
//                    String key = elements.next().asText();
//                    removeFields(expectedOutput, key.trim());
//                    removeFields(response, key.trim());
//                }
//            }
//            JSONAssert.assertEquals(expectedOutput,response,jsonAssertStrictMode);
//            validateJsonObjectResponse = true;
//        } catch (AssertionError e) {
//            e.printStackTrace();
//            printResponse(expectedOutput.toString(), response.toString());
//            Logger.fail("Validation failed");
//        } catch (Exception e) {
//            Logger.message("Jackson Exception: ");
//            e.printStackTrace();
//            Logger.fail("Validation failed");
//        }
//        return validateJsonObjectResponse;
//    }
//
//    public static boolean validateJsonObjectResponseFields(Response response, JSONArray fieldsToBeVerified) {
//        boolean validateJsonObjectResponseFields = false;
//        try {
//            if (!fieldsToBeVerified.isEmpty() || fieldsToBeVerified != null) {
//                String[] array = new String[fieldsToBeVerified.length()];
//                for (int i = 0; i < array.length; i++) {
//                    array[i] = (String)fieldsToBeVerified.get(i);
//                }
//                for (int i=0;i<fieldsToBeVerified.length();i++){
//                    if (!response.asString().contains(array[i].trim())){
//                        Logger.message("Field: "+array[i].trim()+" not found");
//                        Logger.fail("Field Validation Failed");
//                    }
//                }
//                validateJsonObjectResponseFields = true;
//            } else {
//                Logger.message("fieldsToBeVerified field is empty !");
//                Logger.fail("Validation failed");
//            }
//        } catch (AssertionError e) {
//            e.printStackTrace();
//            printResponse("", response.asString());
//            Logger.fail("Validation failed");
//        }
//        return validateJsonObjectResponseFields;
//    }
//
//    public static boolean validateStringResponse(String expectedOutput, Response response) {
//        boolean validateStringResponse = false;
//        try {
//            if (!validateExactMatchStringResponse && response.asString().contains(expectedOutput)) {
//                validateStringResponse = true;
//            } else if (validateExactMatchStringResponse && response.asString().equalsIgnoreCase(expectedOutput)) {
//                validateStringResponse = true;
//            } else {
//                printResponse(expectedOutput, response.asString());
//                Logger.fail("Validation failed");
//            }
//        } catch (Exception e) {
//            Logger.message("Exception is: " + e.getMessage());
//            Logger.fail("Exception occurred failed");
//        }
//        return validateStringResponse;
//    }
//
//    private static void removeFields(Object object, String field) throws JSONException {
//        try {
//            if (object instanceof JSONArray) {
//                JSONArray array = (JSONArray) object;
//                for (int i = 0; i < array.length(); ++i) {
//                    removeFields(array.get(i), field);
//                }
//            } else if (object instanceof JSONObject) {
//                JSONObject json = (JSONObject) object;
//                JSONArray names = json.names();
//                if (names == null) return;
//                for (int i = 0; i < names.length(); ++i) {
//                    String key = names.getString(i);
//                    if (key.equalsIgnoreCase(field)) {
//                        json.remove(key);
//                    } else {
//                        removeFields(json.get(key), field);
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            Logger.message(e.getMessage());
//        }catch (Exception e) {
//            Logger.message(e.getMessage());
//        }
//    }
//
//    public static void verifyFields(Object object, String field) throws JSONException {
//        if (object instanceof JSONArray) {
//            JSONArray array = (JSONArray) object;
//            for (int i = 0; i < array.length(); ++i) {
//                verifyFields(array.get(i), field);
//            }
//
//        } else if (object instanceof JSONObject) {
//            JSONObject json = (JSONObject) object;
//            JSONArray names = json.names();
//            if (names == null) return;
//            for (int i = 0; i < names.length(); ++i) {
//                String key = names.getString(i);
//                if (key.equalsIgnoreCase(field)) {
//                    Logger.message( field+" Verified");
//                } else {
//                    verifyFields(json.get(key), field);
//                }
//            }
//        }
//    }
//
//    private static void printResponse(String expectedResponse, String actualResponse) {
//        Logger.message("Expected response : " + expectedResponse);
//        Logger.message("Actual response : " + actualResponse);
//    }
//
//    public static void printFields(Object object) throws JSONException {
//        if (object instanceof JSONArray) {
//            JSONArray array = (JSONArray) object;
//            for (int i = 0; i < array.length(); ++i) {
//                printFields(array.get(i));
//            }
//        } else if (object instanceof JSONObject) {
//            JSONObject json = (JSONObject) object;
//            JSONArray names = json.names();
//            if (names == null) return;
//            for (int i = 0; i < names.length(); ++i) {
//                String key = names.getString(i);
//                Logger.message("\"" + key + "\",");
//                printFields(json.get(key));
//            }
//        }
//    }
//
//    private static void compareResponse(Object expected, Object actual) throws Exception {
//        if (expected instanceof JSONArray) {
//            JSONArray expectedArray = (JSONArray) expected;
//            JSONArray actualArray = (JSONArray) expected;
//            for (int i = 0; i < expectedArray.length(); ++i) compareResponse(expectedArray.get(i),actualArray.get(i));
//        } else if (expected instanceof JSONObject) {
//            JSONObject expectedJson = (JSONObject) expected;
//            JSONObject actualJson = (JSONObject) expected;
//            JSONAssert.assertEquals(expectedJson,actualJson, jsonAssertStrictMode);
//        }
//    }
//
//    public static boolean validateArrayListResponse(JSONObject expectedOutput, ArrayList<String> responseList, String ignoreFields, JSONArray fieldsToBeVerified) {
//        boolean validateArrayListResponse = false;
//        try {
//            for (String response : responseList) {
//                JSONObject responseObject = new JSONObject(response);
//                if(ignoreFields != null) {
//                    Iterator<JsonNode> elements = mapper.readTree(ignoreFields).iterator();
//                    while (elements.hasNext()) {
//                        String key = elements.next().asText();
//                        removeFields(expectedOutput, key.trim());
//                        removeFields(responseObject, key.trim());
//                    }
//                }
//                if (fieldsToBeVerified != null) {
//                    if (!fieldsToBeVerified.isEmpty()) {
//                        String[] array = new String[fieldsToBeVerified.length()];
//                        for (int i = 0; i < array.length; i++) {
//                            array[i] = (String)fieldsToBeVerified.get(i);
//                        }
//                        for (int i=0;i<fieldsToBeVerified.length();i++){
//                            if (!responseObject.toString().contains(array[i].trim())) {
//                                Logger.message("Field: "+array[i].trim()+" not found");
//                                Logger.fail("Field Validation Failed");
//                            }
//                        }
//                        validateArrayListResponse = true;
//                    } else {
//                        if ((mapper.readTree(expectedOutput.toString()).equals(mapper.readTree(responseObject.toString())))) {
//                            validateArrayListResponse = true;
//                            break;
//                        } else {
//                            printResponse(expectedOutput.toString(), responseObject.toString());
//                        }
//                    }
//                } else {
//                    if ((mapper.readTree(expectedOutput.toString()).equals(mapper.readTree(responseObject.toString())))) {
//                        validateArrayListResponse = true;
//                        break;
//                    } else {
//                        printResponse(expectedOutput.toString(), responseObject.toString());
//                    }
//                }
//            }
//        } catch (Exception e) {
//            Logger.message("Jackson Exception: ");
//            e.printStackTrace();
//            Logger.fail("Validation failed");
//        }
//        return validateArrayListResponse;
//    }
}