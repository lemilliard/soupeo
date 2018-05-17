package fr.epsi.i4.soupeoserver.arduinoapi;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.net.URI;

/**
 * @author Thomas Kint
 */
public class ArduinoAPIClient {

	private static final String url = "http://192.168.5.2";

	private static final String alertPath = "/alert";

	public static String alertArduino() {
		String response = null;

		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
			URIBuilder builder = new URIBuilder(url + alertPath);

			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);

			request.setHeader("Content-Type", "application/json");

			HttpResponse httpResponse = httpclient.execute(request);
			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) {
				String jsonString = EntityUtils.toString(entity).trim();
				Gson gson = new Gson();
				response = gson.fromJson(jsonString, String.class);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return response;
	}
}
