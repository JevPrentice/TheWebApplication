package com.mycompany.thewebapplication;

import com.mycompany.db.PersistentBean;
import com.mycompany.db.entities.Doctor;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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

    @Path("/createDoctor")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public String createDoctor(Doctor doctor) {

        System.out.println(doctor);

        Doctor response = bean.createDoctor(doctor);
        return response.getId().toString();

    }

    @Path("/getDoctor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor getDoctors() {

        return new Doctor("MID123", "John", "Doe");

    }

}
