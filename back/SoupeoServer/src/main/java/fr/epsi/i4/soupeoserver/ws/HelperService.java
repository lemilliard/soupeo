package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.assistant.AssistantVirtuel;
import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.dao.UserSessionDAO;
import fr.epsi.i4.soupeoserver.model.api.HelpDemand;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import fr.epsi.i4.soupeoserver.utils.WebUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelperService {

	private static final String basePath = "/help";

	@PostMapping(basePath)
	public Object help(@RequestBody HelpDemand demand) {
		UserSession userSession = UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
		userSession.getParcours().get(demand.index).setHelp_used(true);
		MainDAO.save(userSession);

		return AssistantVirtuel.resultatAide(demand.message);
	}
}
