package fr.epsi.i4.soupeoserver.ws;

import fr.epsi.i4.soupeoserver.assistant.AssistantVirtuel;
import fr.epsi.i4.soupeoserver.dao.MainDAO;
import fr.epsi.i4.soupeoserver.dao.UserSessionDAO;
import fr.epsi.i4.soupeoserver.model.morphia.Parcours;
import fr.epsi.i4.soupeoserver.model.morphia.UserSession;
import fr.epsi.i4.soupeoserver.utils.WebUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class HelperService {

    private static final String basePath = "/help";

    @PostMapping(basePath)
    public Object help(@RequestBody String message, @RequestBody Integer index) {

        UserSession userSession = getCurrentSession();
        userSession.getParcours().get(index).setHelp_used(true);
        MainDAO.save(userSession);

        return AssistantVirtuel.resultatAide(message);
    }

    private UserSession getCurrentSession() {
        return UserSessionDAO.getCurrentSession(WebUtils.getClientIp());
    }
}
