package com.innovativecloudsolutions;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
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
    private static final String HUDSON_JOB_URL = ServiceConstants.HUDSON_BASE_URL + "/job/";
    //private static final String HUDSON_JOB_URL = "http://http403.local:8080/hudson/job/";
    private static final String HUDSON_BUILD_CMD = "/build";
    private static final String HUDSON_BUILD_CMD_DELAY = "?delay=";


    public static final String EXTRA_SENDER = "sender";
    public static final String EXTRA_APPLICATION_PENDING_INTENT = "app";
    public static final String REQUEST_UNREGISTRATION_INTENT = "com.google.android.c2dm.intent.UNREGISTER";
    public static final String REQUEST_REGISTRATION_INTENT = "com.google.android.c2dm.intent.REGISTER";
    public static final String LAST_REGISTRATION_CHANGE = "last_registration_change";
    public static final String BACKOFF = "backoff";
    public static final String GSF_PACKAGE = "com.innovativecloudsolutions";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.projects);

        buildRequests = new ArrayList<BuildRequest>();
        buildRequestsAdapter = new BuildRequestsAdapter(BuildRequestListActivity.this, R.layout.buildrequest_list, buildRequests);
        setListAdapter(buildRequestsAdapter);

        getProjects();
        buildRequestsAdapter.notifyDataSetChanged();

        //registerC2DM();
    }

    private void registerC2DM() {

        Intent registrationIntent = new Intent(REQUEST_REGISTRATION_INTENT);
        registrationIntent.setPackage(GSF_PACKAGE);
        registrationIntent.putExtra(EXTRA_APPLICATION_PENDING_INTENT,
                PendingIntent.getBroadcast(this, 0, new Intent(), 0));
        registrationIntent.putExtra(EXTRA_SENDER, "imran.iansari@gmail.com");

        startService(registrationIntent);

    }

    public void onListItemClick(ListView parent, View v, int position,
                                long id) {
        final BuildRequest selectedProject = (BuildRequest) buildRequestsList.get(position);
        Log.d("Selected : ", selectedProject.toString());
        final String buildMessage = "Building approved for " + selectedProject.getProjectName();

        final CharSequence[] items = {"Approve", "Reject", "Schedule"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Build " + selectedProject.getProjectName());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    Util.invokeWebService(HUDSON_JOB_URL + selectedProject.getProjectName() + HUDSON_BUILD_CMD);
                    Toast.makeText(getApplicationContext(), buildMessage, Toast.LENGTH_SHORT).show();
                }

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

        //Util.invokeWebService(HUDSON_JOB_URL + selectedProject.getProjectName() + HUDSON_BUILD_CMD);

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
        final Intent intent = new Intent(this, ConnectedDeployActivity.class);

        item = menu.add(0, 0, 0, "Reload");

        item.setOnMenuItemClickListener(new
                MenuItem.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                      startActivity(intent);

                        return true;
                    }
                });
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

    // Registration ID received via an Intent

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            handleRegistration(context, intent);
        }
    }

    public void handleRegistration(Context context, Intent intent) {
        String id = intent.getExtras().getString("registration_id");
        if ((intent.getExtras().getString("error") != null)) {
            Log.d("C2DM", "Registration failed.  Try again later, with backoff.");
        } else if (id != null) {
            // Send the registration ID to the appÕs server.
            // Be sure to do this in a separate thread.
            Log.d("C2DM reg id", id);
        }
    }
}