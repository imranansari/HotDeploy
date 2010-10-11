package com.innovativecloudsolutions.model;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Jun 1, 2010
 * Time: 10:45:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Project {
    private String name;
    private String url;


    public Project() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
