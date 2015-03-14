/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thewebapplication;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.mycompany.db.entities.Doctor;
import java.util.logging.Level;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

/**
 *
 * @author Tranquility
 */
public class MyHTTPClient {

    private static final String URL = "http://localhost:8080/TheWebApplication/rs/getDoctor";

    private Client getWebResourceClient() {
        return ClientBuilder.newBuilder()
                .register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class)
                //                .register(HttpAuthenticationFeature.basic(getProperty(IVR_HELIUM_USER_TOKEN), getProperty(IVR_HELIUM_USER_SECRET)))
                .build();
    }

    public static void main(String[] args) {

        MyHTTPClient c = new MyHTTPClient();
//        Doctor doctor = new Doctor("MID123", "John", "Doe");

        try {

            WebTarget target = c.getWebResourceClient().target(URL); //TODO DB
            Response response;

            //target.queryParam("doctor", "{\"id\":null,\"mediId\":\"MID123\",\"name\":\"John\",\"surname\":\"Doe\"}");
            response = target.request(MediaType.APPLICATION_JSON).get();

            String responseString = response.readEntity(String.class);
            int responseCode = response.getStatus();

            System.out.println(responseCode);
            System.out.println(responseString);

            //if (responseCode >= 399) {  }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
