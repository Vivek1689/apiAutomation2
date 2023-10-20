package generic;

import java.util.Map;

public interface IResponse {

    public Map<String, ?> getHeaders();
    public String getResponseAsString();
    public int getStatusCode();
    public void validateStatusCode(int statusCode);

    public default void validateSchema(String schema){
        return;
    }

    public <T extends Object> T jsonPath(String path);

    public <T> T as(Class<T> cls);
}
