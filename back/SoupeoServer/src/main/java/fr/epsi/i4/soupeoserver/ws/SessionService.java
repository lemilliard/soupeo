package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.dao.UserSessionDAO;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import fr.epsi.i4.soupeoserver.utils.WebUtils;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
		System.out.println("GetAllSession");
		return MainDAO.findAll(UserSession.class);
	}

	@GetMapping(basePath + "/{id}")
	public UserSession getSession(@PathVariable("id") ObjectId id) {
		System.out.println("GetSession: " + id.toHexString());
		// Récupération de la session en base
		return MainDAO.findById(UserSession.class, id);
	}

	@GetMapping(basePath + "/start")
	public String startSession() {
		UserSession userSession = getCurrentSession();
		if (userSession != null) {
			userSession.end();
			MainDAO.save(userSession);
		}
		ObjectId id = MainDAO.save(new UserSession(WebUtils.getClientIp())).get_id();
		System.out.println("StartSession: " + id.toHexString());
		return id.toHexString();
	}

	@GetMapping("/test")
	public String getIp() {
		System.out.println("Test " + WebUtils.getClientIp());
		return WebUtils.getClientIp();
	}

	private UserSession getCurrentSession() {
		return UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
	}
}
