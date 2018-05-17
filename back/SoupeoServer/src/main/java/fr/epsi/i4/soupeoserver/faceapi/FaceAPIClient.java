package fr.epsi.i4.soupeoserver.faceapi;

import com.google.gson.Gson;
import fr.epsi.i4.soupeoserver.model.apiModel.Verification;
import javafx.scene.shape.VertexFormat;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

/**
 * @author Thomas Kint
 */
public class FaceAPIClient {

	private static final String url = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect";

	private static final String urlVerification = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/verify";

	private static final String token = "77e9f2e419cb4caab9b67ffcf7c1d5b8";

	private static final String faceAttributes = "emotion";

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

	public static Verification getVerificationResponse(String currentId, String previousId) {
		HttpClient httpclient = HttpClients.createDefault();

		try
		{
			URIBuilder builder = new URIBuilder(urlVerification);


			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", token);


			// Request body
			StringEntity reqEntity = new StringEntity("{\n" +
					"    \"faceId1\":\""+currentId+"\",\n" +
					"    \"faceId2\":\""+previousId+"\",\n" +
					"}");
			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				Verification res;
				System.out.println(EntityUtils.toString(entity));
				String jsonString = EntityUtils.toString(entity).trim();
				Gson gson = new Gson();
				res = gson.fromJson(jsonString, Verification.class);
				return res;
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return null;
	}
}
