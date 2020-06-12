package utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientUtils {

    public static String sendRequest() {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme(PropertyUtils.getPropertyData(PropertyUtils.URL_SCHEME))
                    .setHost(PropertyUtils.getPropertyData(PropertyUtils.URL_HOST))
                    .setPort(Integer.parseInt(PropertyUtils.getPropertyData(PropertyUtils.URL_PORT)))
                    .setPath(String.format(PropertyUtils.getPropertyData(PropertyUtils.URL_PATH), PropertyUtils.getPropertyData(PropertyUtils.URL_RUN_ID)))
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpGet getRequest = new HttpGet(uri);

        String encoding = null;
        try {
            encoding = DatatypeConverter.printBase64Binary(String.join(":", PropertyUtils.getPropertyData(PropertyUtils.USER_LOGIN),
                    PropertyUtils.getPropertyData(PropertyUtils.USER_PASSWORD)).getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getRequest.setHeader("Authorization", "Basic " + encoding);

        //read response
        HttpResponse response = null;
        try {
            response = httpClient.execute(getRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String responseResult = null;
        try {
            responseResult = convertStreamToString(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpClient.close();

        return responseResult;
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
