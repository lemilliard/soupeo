package fr.epsi.i4.soupeoserver.webcam;

import javax.ws.rs.client.*;

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

}
