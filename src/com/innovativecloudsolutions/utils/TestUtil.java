package com.innovativecloudsolutions.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.innovativecloudsolutions.model.Projects;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Sep 26, 2010
 * Time: 5:50:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestUtil {

    public static void main(String[] args){
        TestUtil testUtil = new TestUtil();
        testUtil.testJson();
    }

    private void testJson() {
        String contractURI = "http://localhost:8080/hudson/api/json?tree=jobs[name]";
        String response = Util.invokeWebService(contractURI);

        System.out.println("response :"+ response);
        Type type = new TypeToken<Projects>() {
        }.getType();
        List projectList = new Gson().fromJson(response, type);
    }

    public TestUtil() {
    }
}
