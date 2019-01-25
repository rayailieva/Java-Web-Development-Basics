package HttpParser2.http;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class HttpResponseImpl implements HttpResponse {

    private final HashMap<String, String> headers;
    private byte[] content;
    private HttpStatus httpStatus;

    public HttpResponseImpl() {
        this.headers = new LinkedHashMap<>();
        this.content = new byte[0];
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public HttpStatus getStatusCode() {
        return this.httpStatus;
    }

    @Override
    public void setStatusCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public byte[] getBytes() {
        StringBuilder response = new StringBuilder()
                .append("HTTP/1.1")
                .append(" ")
                .append(httpStatus.getCode())
                .append(" ")
                .append(httpStatus.getName())
                .append(" ");
        headers.forEach((key, value) -> response
                .append(key)
                .append(": ")
                .append(value)
                .append("\r\n"));
        response.append("\r\n")
                .append(new String(content, StandardCharsets.UTF_8));

        return response.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public void addHeader(String header, String value) {
        headers.put(header, value);
    }
}
