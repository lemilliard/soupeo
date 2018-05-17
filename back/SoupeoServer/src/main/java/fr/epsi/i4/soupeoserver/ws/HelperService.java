package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.assistant.AssistantVirtuel;
import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.dao.UserSessionDAO;
import fr.epsi.i4.soupeoserver.model.api.HelpDemand;
import fr.epsi.i4.soupeoserver.model.morphia.PopupContent;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import fr.epsi.i4.soupeoserver.utils.WebUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelperService {

	@PostMapping("/help")
	public PopupContent help(@RequestBody HelpDemand demand) {
		UserSession userSession = UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
		userSession.getParcours().get(demand.index).setHelp_used(true);
		MainDAO.save(userSession);

		PopupContent popupContent = AssistantVirtuel.resultatAide(demand.message);
		if (popupContent != null) {
			System.out.println("HelpDemand: " + demand.message + " -> " + popupContent.getId_popup() + " - " + popupContent.getUrl());
		} else {
			System.out.println("HelpDemand: " + demand.message + " -> null");
		}

		return popupContent;
	}
}
