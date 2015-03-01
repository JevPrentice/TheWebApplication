/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thewebapplication;

import com.mycompany.db.PersistentBean;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jevprentice
 */
@Path("")
@ApplicationScoped
public class RestService {

    @Inject
    PersistentBean bean;

    @Path("/test")
    @GET
    public String getTest() {
        return "its online!";
    }

    @Path("/populateConfigs")
    @GET
//    @Produces(MediaType.APPLICATION_JSON)
    public String populateConfigs() {
        bean.populateDefaultConfigValues();
         return "done";
    }
    
    @GET
    @Path("/props")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> getProps() {
        return bean.getProperties();
    }

}
