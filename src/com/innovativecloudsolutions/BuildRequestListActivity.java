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
import com.innovativecloudsolutions.adapters.BuildRequestsAdapter;
import com.innovativecloudsolutions.model.BuildRequest;
import com.innovativecloudsolutions.utils.Util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class BuildRequestListActivity extends ListActivity {

    BuildRequestsAdapter buildRequestsAdapter;
    List buildRequestsList = null;
    String buildRequestURI = "http://connecteddeploy.appspot.com/buildrequest";

    ArrayList<BuildRequest> buildRequests = null;
    private static final String HUDSON_JOB_URL = "http://10.0.2.2:8080/hudson/job/";
    private static final String HUDSON_BUILD_CMD = "/build";
    private static final String HUDSON_BUILD_CMD_DELAY = "?delay=";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.projects);

        buildRequests = new ArrayList<BuildRequest>();
        buildRequestsAdapter = new BuildRequestsAdapter(BuildRequestListActivity.this, R.layout.buildrequest_list, buildRequests);
        setListAdapter(buildRequestsAdapter);

        getProjects();
        buildRequestsAdapter.notifyDataSetChanged();
    }

    public void onListItemClick(ListView parent, View v, int position,
                                long id) {
        BuildRequest selectedProject = (BuildRequest) buildRequestsList.get(position);
        Log.d("Selected : ", selectedProject.toString());
        Util.invokeWebService(HUDSON_JOB_URL + selectedProject.getProjectName() + HUDSON_BUILD_CMD);

        /*Intent contractDetailsIntent = new Intent(this, ProjectDetailActivity.class);
        contractDetailsIntent.putExtra("projectName", selectedProject.getName());

        startActivity(contractDetailsIntent);*/
    }

    private void getProjects() {
        String response = Util.invokeWebService(buildRequestURI);

        //System.out.println("responseDetails = " + json.toString());

        Log.d("response :", response);
        Type type = new TypeToken<List<BuildRequest>>() {
        }.getType();
        buildRequestsList = new Gson().fromJson(response, type);

        Log.d("response :", response);
        Log.d("json :", String.valueOf(buildRequestsList.size()));

        for (int i = 0; i < buildRequestsList.size(); i++) {
            buildRequests.add((BuildRequest) buildRequestsList.get(i));

        }

  /*      for (BuildRequest buildRequest : buildRequestsList.getBuildRequests()) {
            buildRequests.add(buildRequest);
        }*/
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