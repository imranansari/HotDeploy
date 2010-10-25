import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.innovativecloudsolutions.model.Projects;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

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

    public static void main(String[] args) {
        TestUtil testUtil = new TestUtil();
        testUtil.testEC2Json();
    }

    private void testEC2Json() {
        String contractURI = "http://ec2-174-129-104-185.compute-1.amazonaws.com/api/json";
        String response = Util.invokeWebService(contractURI);
        response = response.substring(response.indexOf("\"jobs\":["), response.indexOf(",\"overallLoad"));
        response = "{"+ response + "}";
        System.out.println("response = " + response);
             Projects projectList = null;


        Type type = new TypeToken<Projects>() {
        }.getType();
        projectList = new Gson().fromJson(response, type);
        System.out.println("projectList.getJobs().size() = " + projectList.getJobs().size());


    }

    public TestUtil() {
    }
}
