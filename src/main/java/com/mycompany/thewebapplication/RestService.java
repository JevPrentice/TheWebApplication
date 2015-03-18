package com.mycompany.thewebapplication;

import com.mycompany.db.PersistentBean;
import com.mycompany.db.entities.DoctorImpl;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    //{"medID":"MID123","name":"John","surname":"Doe","id":null}
    @Path("/createDoctor")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(DoctorImpl doctor) {

        System.out.println("entering createDoctor - Doctor=" + doctor);

        if (doctor == null) {
            System.out.println("1, doctor == null");
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else if (doctor != null) {
            System.out.println("2, doctor == null");
        } else {
            System.out.println("3, doctor != null && !(doctor == null) ");
        }

        System.out.println("doctor: " + doctor);

//        Doctor response = bean.createDoctor(doctor);
//        return Response.ok(response.getId().toString()).build();
        System.out.println("ending createDoctor - Doctor=" + doctor);
        return Response.ok().build();

    }

    @Path("/getDoctor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DoctorImpl getDoctors() {

        return new DoctorImpl("MID123", "John", "Doe");

    }

}
