package com.innovativecloudsolutions.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: imranansari
 * Date: Jun 1, 2010
 * Time: 10:45:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class BuildRequests {
   private List<BuildRequest> buildRequests;

    public List<BuildRequest> getBuildRequests() {
        return buildRequests;
    }

    public void setBuildRequests(final List<BuildRequest> buildRequests) {
        this.buildRequests = buildRequests;
    }
}