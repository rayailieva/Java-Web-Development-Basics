package http;

import java.util.*;

public class HttpRequestImpl implements HttpRequest {

    private String method;
    private String requestUrl;
    private Map<String,String> headers;
    private Map<String, String> bodyParameters;
    private List<HttpCookie> cookies;
    private boolean isResource;

    public HttpRequestImpl(String request){
        this.init(request);
    }

    private void init(String request){
        this.setMethod(request.split("\\s+")[0]);
        this.setRequestUrl(request.split("\\s+")[1]);
        this.headers = new LinkedHashMap<>();
        this.bodyParameters = new LinkedHashMap<>();
        this.cookies = new LinkedList<>();

        String[] requestParams = request.split(System.lineSeparator());
        Arrays.stream(request.split(System.lineSeparator()))
                .skip(1)
                .filter(headerKvp -> headerKvp.contains(": "))
                .forEach(headerKvp -> {
                            String[] header = headerKvp.split(": ");
                            this.addHeader(header[0], header[1]);
                        }
                );

        if(!requestParams[requestParams.length - 1].isEmpty()){
            Arrays.stream(requestParams[requestParams.length - 1]
            .split("&"))
                    .forEach(bodyKvp -> {
                        String[] body = bodyKvp.split("=");
                        this.addBodyParameter(body[0], body[1]);
                    });
        }

        if(this.headers.containsKey("Cookie")){
           String cookie = this.headers.get("Cookie");
           Arrays.stream(cookie.split("; "))
                .forEach(cookieKvp -> {
                String[] cookieParams = cookie.split("=");
                HttpCookie httpCookie =
                        new HttpCookieImpl(cookieParams[0], cookieParams[1]);

                this.addCookie(httpCookie);
           });
        }
    }

    @Override
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public Map<String, String> getBodyParameters() {
        return this.bodyParameters;
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return this.cookies;
    }

    @Override
    public void addCookie(HttpCookie cookie) {
        this.cookies.add(cookie);
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return isResource;
    }
}
