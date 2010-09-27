package com.innovativecloudsolutions.utils;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Jun 6, 2010
 * Time: 6:05:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Util {

     public static String invokeWebService(final String uri) {
        HttpClient client = new DefaultHttpClient();
        URI url = null;
        String response = null;
        try {
            url = new URI(uri);
            HttpGet getMethod = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();

            response = client.execute(getMethod, responseHandler);
        } catch (URISyntaxException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClientProtocolException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return response;
    }
}
