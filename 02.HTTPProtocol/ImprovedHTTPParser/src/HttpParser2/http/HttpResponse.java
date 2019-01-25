package HttpParser2.http;

import java.util.HashMap;

public interface HttpResponse {

    HashMap<String, String> getHeaders();

    HttpStatus getStatusCode();

    void setStatusCode(HttpStatus httpStatus);

    byte[] getContent();

    void setContent(byte[] content);

    byte[] getBytes();

    void addHeader(String header, String value);
}
