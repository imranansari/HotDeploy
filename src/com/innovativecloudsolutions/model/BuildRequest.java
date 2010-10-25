package com.innovativecloudsolutions.model;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Jun 1, 2010
 * Time: 10:45:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildRequest {
    private String projectName;
    private String date;
    private String requestedBy;


    public BuildRequest() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(final String requestedBy) {
        this.requestedBy = requestedBy;
    }

    @Override
    public String toString() {
        return "BuildRequest{" +
                "projectName='" + projectName + '\'' +
                '}';
    }
}
