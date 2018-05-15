package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.model.Session;
import fr.epsi.i4.soupeoserver.model.Terminal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Thomas Kint
 */
@RestController
public class SessionService {

	private static final String basePath = "/session";

	@GetMapping(basePath + "/{id}")
	public Session getSession(@PathVariable("id") int id) {
		// Instanciation
		Session session = null;

		// Récupération de la session en base


		// Retour de la session
		return session;
	}

	@PostMapping(basePath)
	public Session startSession(@RequestBody Terminal terminal) {
		// Création de la session
		Session session = new Session(terminal);

		// Persistence en base


		// Retour de la session
		return session;
	}

	@DeleteMapping(basePath + "/{id}")
	public boolean endSession(@PathVariable("id") int id) {
		// Modification en base


		// Retour du résultat
		return true;
	}
}
