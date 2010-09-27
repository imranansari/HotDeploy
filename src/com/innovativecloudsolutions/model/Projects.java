package com.innovativecloudsolutions.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Jun 1, 2010
 * Time: 10:45:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Projects {
   private List<Project> jobs;

    /**
     * 
     * @return
     */
    public List<Project> getJobs() {
        return jobs;
    }

    public void setJobs(final List<Project> contracts) {
        this.jobs = contracts;
    }
}