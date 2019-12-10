package com.mycompany.thewebapplication;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Instant;

/**
 * @author Jev Prentice
 */
@Path("v1")
@Stateless
@PermitAll
public class RestService {

    // curl http://localhost:8080/TheWebApplication-1.0-SNAPSHOT/api/v1/test -s | jq
    @Path("test")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode getStatus() {
        final ObjectNode result = JsonNodeFactory.instance.objectNode();
        result.put("status", "OK");
        result.put("timestamp", Instant.now().toString());
        return result;
    }
}
