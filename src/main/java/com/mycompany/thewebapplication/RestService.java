package com.mycompany.thewebapplication;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;

/**
 * @author Jev Prentice
 */
@Path("v1")
@Stateless
@PermitAll
public class RestService {

    private static final Logger LOGGER = Logger.getLogger(RestService.class.getName());

    private static class ClientFactory implements Supplier<Client> {

	private final String username;
	private final byte[] password;

	public ClientFactory(final String username, final byte[] password) {
	    this.username = Objects.requireNonNull(username, "username must not be null");
	    this.password = Objects.requireNonNull(password, "password must not be null");
	}

	@Override
	public Client get() {
	    return ClientBuilder.newBuilder()
		    .register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class)
		    .register(new LoggingFeature(LOGGER, Verbosity.PAYLOAD_ANY))
		    .register(HttpAuthenticationFeature.basic(username, password))
		    .build();
	}
    }

    // curl http://localhost:8080/TheWebApplication-1.0-SNAPSHOT/api/v1/simple -s | jq
    @Path("simple")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode getSimple() {
	return buildDummyJsonResponse();
    }

    // curl http://localhost:8080/TheWebApplication-1.0-SNAPSHOT/api/v1/url -s | jq
    @Path("url")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ObjectNode getConfiguredUrl() {

	final String url = System.getProperty("thewebapplication.url");
	final String username = System.getProperty("thewebapplication.username", "");
	final byte[] password = System.getProperty("thewebapplication.password", "").getBytes();

	final long beforeConnectionMillis = System.currentTimeMillis();

	Client client = null;
	Response response = null;
	try {
	    client = new ClientFactory(username, password).get();
	    response = client.target(url).request().get();
	    return buildJsonResponse(url, beforeConnectionMillis, response);
	} catch (final Exception ex) {
	    Logger.getLogger(RestService.class.getName()).log(Level.SEVERE, null, ex);
	    throw new RuntimeException(ex);
	} finally {
	    if (response != null) {
		response.close();
		LOGGER.log(Level.INFO, "Closed response for {0}", url);
	    }
	    if (client != null) {
		client.close();
		LOGGER.log(Level.INFO, "Closed client for {0}", url);
	    }
	}
    }

    private static ObjectNode buildDummyJsonResponse() {
	return JsonNodeFactory.instance.objectNode()
		.put("status", "OK - Dummy")
		.put("timestamp", Instant.now().toString())
		.put("url", "no-url");
    }

    private static ObjectNode buildJsonResponse(
	    final String url,
	    final long beforeConnectionMillis,
	    final Response response
    ) {
	final String responseString = response.readEntity(String.class);
	final long requestDurationMillis = (System.currentTimeMillis() - beforeConnectionMillis);
	final ObjectNode node = JsonNodeFactory.instance.objectNode()
		.put("status", response.getStatus())
		.put("timestamp", Instant.now().toString())
		.put("url", url)
		.put("requestDurationMillis", requestDurationMillis);
	LOGGER.log(Level.INFO, "{0} {1}", new Object[]{url, response.getStatus()});
	LOGGER.log(Level.FINE, responseString);
	return node;
    }
}
