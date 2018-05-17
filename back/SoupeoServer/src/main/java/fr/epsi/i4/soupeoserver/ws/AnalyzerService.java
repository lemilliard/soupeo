package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.analyzer.Analyzer;
import fr.epsi.i4.soupeoserver.analyzer.AnalyzerResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Thomas Kint
 */
@RestController
public class AnalyzerService {

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/analyse")
	public AnalyzerResult runAnalyzer(@RequestParam("image") MultipartFile image, @RequestParam int index) throws IOException {
		return Analyzer.analyze(index, image.getBytes());
	}
}
