package com.innovativecloudsolutions;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.innovativecloudsolutions.adapters.ProjectsAdapter;
import com.innovativecloudsolutions.model.Project;
import com.innovativecloudsolutions.model.Projects;
import com.innovativecloudsolutions.utils.Util;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class ProjectListActivity extends ListActivity {

    ProjectsAdapter projectsAdapter;
    Projects projectList = null;
    //String contractURI = "http://10.0.2.2:8888/test";
    String contractURI = "http://10.0.2.2:8080/hudson/api/json?tree=jobs[name]";


    ArrayList<Project> Projects = null;
    private static final String HUDSON_JOB_URL = "http://10.0.2.2:8080/hudson/job/";
    private static final String HUDSON_BUILD_CMD = "/build";
    private static final String HUDSON_BUILD_CMD_DELAY = "?delay=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projects);

        Projects = new ArrayList<Project>();
        projectsAdapter = new ProjectsAdapter(ProjectListActivity.this, R.layout.projects_list, Projects);
        setListAdapter(projectsAdapter);

        getProjects();
        projectsAdapter.notifyDataSetChanged();
    }

    public void onListItemClick(ListView parent, View v, int position,
                                long id) {
        Project selectedProject = projectList.getJobs().get(position);
        Log.d("Selected : ", selectedProject.toString());
        Util.invokeWebService(HUDSON_JOB_URL + selectedProject.getName() +HUDSON_BUILD_CMD);

        /*Intent contractDetailsIntent = new Intent(this, ProjectDetailActivity.class);
        contractDetailsIntent.putExtra("projectName", selectedProject.getName());

        startActivity(contractDetailsIntent);*/
    }

    private void getProjects() {
        String response = Util.invokeWebService(contractURI);
        JSONObject json = null;
        try {
            json = new JSONObject(response);
            String result = json.getString("responseDetails");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //System.out.println("responseDetails = " + json.toString());

        Log.d("response :", response);
        Type type = new TypeToken<Projects>() {
        }.getType();
        projectList = new Gson().fromJson(response, type);

        Log.d("response :", response);
        Log.d("json :", String.valueOf(projectList.getJobs().size()));

        for (Project contract : projectList.getJobs()) {
            Projects.add(contract);
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item;
        menu.removeItem(0);
        menu.removeItem(1);

        item = menu.add(0, 0, 0, "Search Project");
        item = menu.add(0, 1, 0, "Dashboard");
        return true;
    }


    /**
     * Handle the menu item selections
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                onSearchRequested();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSearchRequested() {
        Log.d("search", "search invoked");

        Bundle appDataBundle = null;
        final String queryAppDataString = "event Name";
        if (queryAppDataString != null) {
            appDataBundle = new Bundle();
            appDataBundle.putString("adminSystem", queryAppDataString);
        }
        //final String queryPrefill = "some term";

        // Now call the Activity member function that invokes the Search Manager UI.
        startSearch(null, false, appDataBundle, false);

        // Returning true indicates that we did launch the search, instead of blocking it.
        return true;
    }
}