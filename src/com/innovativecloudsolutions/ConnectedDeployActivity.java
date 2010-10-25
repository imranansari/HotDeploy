package com.innovativecloudsolutions;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Oct 20, 2010
 * Time: 9:47:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectedDeployActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    Resources res = getResources(); // Resource object to get Drawables
    TabHost tabHost = getTabHost();  // The activity TabHost
    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
    Intent intent;  // Reusable Intent for each tab

    // Create an Intent to launch an Activity for the tab (to be reused)
    intent = new Intent().setClass(this, BuildRequestListActivity.class);

    // Initialize a TabSpec for each tab and add it to the TabHost
    spec = tabHost.newTabSpec("requests").setIndicator(null,
                      res.getDrawable(R.drawable.onebit_21))
                  .setContent(intent);
    tabHost.addTab(spec);

    // Do the same for the other tabs
    intent = new Intent().setClass(this, ProjectListActivity.class);
    spec = tabHost.newTabSpec("projects").setIndicator(null,
                      res.getDrawable(R.drawable.onebit_20))
                  .setContent(intent);
    tabHost.addTab(spec);


    tabHost.setCurrentTab(0);
    }
}
