package fr.epsi.i4.soupeoserver.dao;

import fr.epsi.i4.soupeoserver.model.morphia.UserSession;

import java.util.List;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.connection;

public class UserSessionDAO {

//	private static final MongoCollection<UserSession> collection = Connection.getDatabaseInstance().getCollection("user_session", UserSession.class);
	//private static final Mongo mongo = new MongoClient();
	//private static Morphia morphia = new Morphia();
	//private static final Datastore datastore = morphia.createDatastore();

	public static UserSession getUserById() {
		List<UserSession> userSessions = connection.getDatastore().find(UserSession.class).asList();

		for (UserSession userSession : userSessions) {
			System.out.println(userSession);
		}

		return null;
	}

	public static List<UserSession> getAllUsers() {
		List<UserSession> usersSession = null;

		try {

		} catch (Exception e) {

		}

		return usersSession;
	}
}
