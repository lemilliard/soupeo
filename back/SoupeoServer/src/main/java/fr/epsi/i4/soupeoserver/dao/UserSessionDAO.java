package fr.epsi.i4.soupeoserver.dao;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import fr.epsi.i4.soupeoserver.model.UserSession;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

public class UserSessionDAO {

    private static final MongoCollection collection = Connection.getDatabaseInstance().getCollection("user_session");
    //private static final Mongo mongo = new MongoClient();
    //private static Morphia morphia = new Morphia();
    //private static final Datastore datastore = morphia.createDatastore();

    public static UserSession getUserById() {

        FindIterable iterable = collection.find();

        MongoCursor cursor = iterable.iterator();

        Object o;
        while (cursor.hasNext()) {
            o = cursor.next();
            System.out.println(o);
        }
        return null;
    }

    public static List<UserSession> getAllUsers() {
        List<UserSession> usersSession = null;

        try {

        }
        catch (Exception e) {

        }

        return usersSession;
    }
}
