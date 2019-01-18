package main.java.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {

    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        List<String> validUrls = getValidUrls();
        List<String> request = getRequest();

        String method = getMethod(request.get(0));
        String url = getUrl(request.get(0));

        Map<String, String> headers = getHeaders(request);
        Map<String, String> bodyParameters = getBodyParams(request);
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static List<String> getRequest() throws IOException {
        List<String> request = new ArrayList<>();

        String line = null;

        while ((line = reader.readLine()) != null && line.length() > 0){
                request.add(line);
        }

        request.add(System.lineSeparator());
        if((line = reader.readLine()) != null && line.length() > 0){
            request.add(line);
        }
        return request;
    }

    private static String getMethod(String line){
        return line.split("\\s+")[0];
    }

    private static String getUrl(String line){
        return line.split("\\s+")[1];
    }

    private static Map<String, String> getHeaders(List<String> request){
        Map<String, String> headers = new LinkedHashMap<>();

        request.stream()
                .skip(1)
                .filter(h -> h.contains(": "))
                .map(h -> h.split(": "))
                .forEach(headerKvp -> {
                    headers.put(headerKvp[0], headerKvp[1]);
                });

        return headers;
    }

    private static Map<String, String> getBodyParams(List<String> request){
        Map<String, String> bodyParameters = new LinkedHashMap<>();

        if(request.get(request.size() - 1).equals("")){
            return bodyParameters;
        }

        Arrays.stream(request.get(request.size() - 1)
                .split("&"))
                .map(bp -> bp.split("="))
                .forEach(bpKvp -> {
                    bodyParameters.put(bpKvp[0], bpKvp[1]);
                });

        return bodyParameters;
    }
}
