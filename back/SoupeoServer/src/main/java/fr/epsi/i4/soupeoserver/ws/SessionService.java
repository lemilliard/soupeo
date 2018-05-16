package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.model.Session;
import fr.epsi.i4.soupeoserver.model.Terminal;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class SessionService {

	private static final String basePath = "/session";

	@GetMapping(basePath)
	public List<UserSession> getAllSession() {
		// Récupération de la session en base
		return MainDAO.findAll(UserSession.class);
	}

	@GetMapping(basePath + "/{id}")
	public UserSession getSession(@PathVariable("id") ObjectId id) {
		// Récupération de la session en base
		return MainDAO.findById(UserSession.class, id);
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
