package fr.epsi.i4.soupeoserver.faceapi;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.net.URI;

/**
 * @author Thomas Kint
 */
public class FaceAPIClient {

	private static final String url = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect";

	private static final String token = "77e9f2e419cb4caab9b67ffcf7c1d5b8";

	private static final String faceAttributes = "emotion";

	private HttpClient httpClient;

	public static Object getResponse(byte[] image) {
		Object response = null;

		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
			URIBuilder builder = new URIBuilder(url);

			builder.setParameter("returnFaceLandmarks", "false");
			builder.setParameter("returnFaceAttributes", faceAttributes);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", token);

			ByteArrayEntity reqEntity = new ByteArrayEntity(image);
			request.setEntity(reqEntity);

			HttpResponse httpResponse = httpclient.execute(request);
			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) {
				String jsonString = EntityUtils.toString(entity).trim();
				Gson gson = new Gson();
				response = gson.fromJson(jsonString, Object.class);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return response;
	}
}
