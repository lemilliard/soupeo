package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.dao.UserSessionDAO;
import fr.epsi.i4.soupeoserver.model.api.NewParcours;
import fr.epsi.i4.soupeoserver.model.morphia.Parcours;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import fr.epsi.i4.soupeoserver.utils.DateUtils;
import fr.epsi.i4.soupeoserver.utils.WebUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thomas Kint
 */
@RestController
public class ParcoursService {

	private static final String basePath = "/parcours";

	@PostMapping(basePath + "/open")
	public int open(@RequestBody NewParcours newParcours) {
		UserSession userSession = getCurrentSession();
		Parcours parcours = new Parcours();
		parcours.setUrl(newParcours.url);
		parcours.setStart(DateUtils.getCurrentDate());
		userSession.getParcours().add(parcours);
		MainDAO.save(userSession);

		return userSession.getParcours().size() - 1;
	}

	private UserSession getCurrentSession() {
		return UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
	}

}
