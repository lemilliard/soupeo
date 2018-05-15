package fr.epsi.i4.soupeoserver.webcam;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * @author Thomas Kint
 */
public class APIClient {

	private static final String token = "";

	private static final String url = "";

	private Client client;

	private WebTarget webTarget;

	public APIClient() {
		client = ClientBuilder.newClient();
	}

	public Object getResponse() {
		webTarget = client.target(url);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		return invocationBuilder.get(Object.class);
	}

}
