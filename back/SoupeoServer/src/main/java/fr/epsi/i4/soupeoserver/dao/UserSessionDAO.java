package fr.epsi.i4.soupeoserver.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import fr.epsi.i4.soupeoserver.model.UserSession;
import org.bson.Document;

import java.util.List;

public class UserSessionDAO {

	private static final MongoCollection<UserSession> collection = Connection.getDatabaseInstance().getCollection("user_session", UserSession.class);
	//private static final Mongo mongo = new MongoClient();
	//private static Morphia morphia = new Morphia();
	//private static final Datastore datastore = morphia.createDatastore();

	public static UserSession getUserById() {

		FindIterable<UserSession> iterable = collection.find();

		MongoCursor<UserSession> cursor = iterable.iterator();

		UserSession userSession;
		while (cursor.hasNext()) {
			userSession = cursor.next();
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
