package fr.epsi.i4.soupeoserver.ws;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thomas Kint
 */
@RestController
public class AnalyseService {

	private static final String basePath = "/analyse";

	@PostMapping(basePath)
	public void runAnalyse() {

	}
}
