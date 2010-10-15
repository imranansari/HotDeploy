package com.innovativecloudsolutions.adapters;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Jun 1, 2010
 * Time: 10:40:32 PM
 * To change this template use File | Settings | File Templates.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.innovativecloudsolutions.R;
import com.innovativecloudsolutions.model.BuildRequest;

import java.util.List;

public class BuildRequestsAdapter extends ArrayAdapter<BuildRequest> {

    int resource;

    public BuildRequestsAdapter(Context context, int resource, List<BuildRequest> items) {
        super(context, resource, items);
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout buildRequestView;
        BuildRequest buildRequest = getItem(position);

        if (convertView == null) {
            buildRequestView = new LinearLayout(getContext());
            LayoutInflater layoutInflater;
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater.inflate(resource, buildRequestView, true);
        } else {
            buildRequestView = (LinearLayout) convertView;
        }

        TextView buildRequestProjectName = (TextView) buildRequestView.findViewById(R.id.projectName);

        buildRequestProjectName.setText(buildRequest.getProjectName());
        return buildRequestView;
    }

}
