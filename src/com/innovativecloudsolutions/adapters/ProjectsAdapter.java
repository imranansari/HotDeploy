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
import com.innovativecloudsolutions.model.Project;

import java.util.List;

public class ProjectsAdapter extends ArrayAdapter<Project> {

    int resource;

    public ProjectsAdapter(Context context, int resource, List<Project> items) {
        super(context, resource, items);
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout projectView;
        Project project = getItem(position);

        if (convertView == null) {
            projectView = new LinearLayout(getContext());
            LayoutInflater layoutInflater;
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layoutInflater.inflate(resource, projectView, true);
        } else {
            projectView = (LinearLayout) convertView;
        }

        TextView contractName = (TextView) projectView.findViewById(R.id.projectName);
        //TextView contractNumber = (TextView) projectView.findViewById(R.id.projectUrl);

        contractName.setText(project.getName());
        //contractNumber.setText(project.getUrl());

        return projectView;
    }

}
