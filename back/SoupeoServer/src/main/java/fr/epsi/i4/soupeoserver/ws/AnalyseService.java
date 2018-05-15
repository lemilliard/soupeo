package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.webcam.FaceAPIClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Thomas Kint
 */
@RestController
public class AnalyseService {

	private static final String basePath = "/analyse";

	@CrossOrigin(origins = "*")
	@PostMapping(value = basePath)
	public Object runAnalyse(@RequestParam("image") MultipartFile image) throws Exception {
		// Récupération des données sur les émotions
		Object faceAPIResponse = FaceAPIClient.getResponse(image.getBytes());


		return faceAPIResponse;
	}
}
