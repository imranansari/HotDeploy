package com.innovativecloudsolutions;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Sep 27, 2010
 * Time: 9:14:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProjectDetailActivity extends Activity {

    private static final String ACTIVITY = "ContractDetailtActivity";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.contract_details);

        String projectName = getIntent().getExtras().getString("projectName");
        ((TextView)(findViewById(R.id.contractDetailsNumber))).setText(projectName);

        String contractName = getIntent().getExtras().getString("contractName");
        ((TextView)(findViewById(R.id.contractDetailsName))).setText(contractName);

         String lob = getIntent().getExtras().getString("lob");
        ((TextView)(findViewById(R.id.contractDetailsLob))).setText(lob);*/
    }
}
