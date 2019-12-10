package com.mycompany.thewebapplication;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.time.Instant;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jev Prentice
 */
@Path("v1")
@Stateless
@PermitAll
public class RestService {

    private static final Logger LOGGER = Logger.getLogger(RestService.class.getName());

    private Client getWebResourceClient() {
        return ClientBuilder.newBuilder()
                .register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class)
                .build();
    }

    // curl http://localhost:8080/TheWebApplication-1.0-SNAPSHOT/api/v1/simple -s | jq
    @Path("simple")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode getSimple() {
        final ObjectNode result = JsonNodeFactory.instance.objectNode();
        result.put("status", "OK");
        result.put("timestamp", Instant.now().toString());
        return result;
    }

    // curl http://localhost:8080/TheWebApplication-1.0-SNAPSHOT/api/v1/google -s | jq
    @Path("google")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode getGoogle() {
        final long beforeConnectionMillis = System.currentTimeMillis();
        final String url = "http://www.google.com";
        final WebTarget target = getWebResourceClient().target(url);
        final Response response = target.request().get();
        final long requestDurationMillis = (System.currentTimeMillis() - beforeConnectionMillis);

        final int responseCode = response.getStatus();

        LOGGER.log(Level.INFO, "{0}: {1}", new Object[]{responseCode, url});
        final String responseString = response.readEntity(String.class);
        Objects.requireNonNull(responseString);

        final ObjectNode result = JsonNodeFactory.instance.objectNode();
        result.put("status", responseCode);
        result.put("timestamp", Instant.now().toString());
        result.put("url", url);
        result.put("requestDurationMillis", requestDurationMillis);
        //result.put("responseString", responseString);

        return result;
    }
}
