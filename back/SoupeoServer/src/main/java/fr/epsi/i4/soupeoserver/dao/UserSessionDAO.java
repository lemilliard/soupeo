package fr.epsi.i4.soupeoserver.dao;

import fr.epsi.i4.soupeoserver.model.morphia.UserSession;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.connection;

public class UserSessionDAO {

	public static UserSession getUserSessionByIp(String ip) {
		return connection.getDatastore().find(UserSession.class).field("id_terminal").equal(ip).get();
	}
}
