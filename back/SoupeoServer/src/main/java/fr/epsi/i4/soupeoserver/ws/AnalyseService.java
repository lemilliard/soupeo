package fr.epsi.i4.soupeoserver.ws;

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
	public String runAnalyse(@RequestParam("image") MultipartFile image) throws Exception {
		// image.getBytes() -> retourne l'outputStream
		return "OK";
	}
}
