package HttpParser2;

import HttpParser2.http.HttpRequest;
import HttpParser2.http.HttpRequestImpl;
import HttpParser2.http.HttpResponse;
import HttpParser2.http.HttpResponseImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    private static BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> validUrls = getValidUrls();
        String request = getRequest();

        HttpRequest httpRequest = new HttpRequestImpl(request);

        HttpResponse httpResponse = new HttpResponseImpl();


    }

    private static List<String> getValidUrls() throws IOException {

        return new ArrayList<>(Arrays.asList(bufferedReader
                .readLine()
                .split("\\s+")));

    }

    private static String getRequest() throws IOException {
        StringBuilder request = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null && line.length() > 0) {
            request.append(line).append(System.lineSeparator());
        }

        request.append(System.lineSeparator());

        if ((line = bufferedReader.readLine()) != null && line.length() > 0) {
            request.append(line);
        }

        return request.toString();
    }

    private static boolean urlIsValid(List<String> validUrls, HttpRequest httpRequest){
        return validUrls.contains(httpRequest.getRequestUrl());
    }
}
