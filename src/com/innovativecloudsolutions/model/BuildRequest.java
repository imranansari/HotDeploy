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


    public BuildRequest() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "BuildRequest{" +
                "projectName='" + projectName + '\'' +
                '}';
    }
}
