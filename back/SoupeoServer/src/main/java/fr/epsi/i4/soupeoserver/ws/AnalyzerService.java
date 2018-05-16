package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.analyzer.Analyzer;
import fr.epsi.i4.soupeoserver.analyzer.AnalyzerResult;
import fr.epsi.i4.soupeoserver.model.api.DataToAnalyse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thomas Kint
 */
@RestController
public class AnalyzerService {

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/analyse")
	public AnalyzerResult runAnalyzer(@RequestBody DataToAnalyse dataToAnalyse) {
		return Analyzer.analyze(dataToAnalyse.index, dataToAnalyse.image);
	}
}
