package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.analyzer.Analyzer;
import fr.epsi.i4.soupeoserver.analyzer.AnalyzerResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Thomas Kint
 */
@RestController
public class AnalyzerService {

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/analyse/{id}")
	public AnalyzerResult runAnalyzer(@PathVariable("id") int id, @RequestParam("image") MultipartFile image) throws Exception {
		return Analyzer.analyze(id, image.getBytes());
	}
}
