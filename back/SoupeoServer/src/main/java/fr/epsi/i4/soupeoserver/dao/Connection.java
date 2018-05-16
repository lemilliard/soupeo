package fr.epsi.i4.soupeoserver.dao;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBPort;
import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBUrl;

/**
 * @author Thomas Kint
 */
public class Connection {

	private static MongoClient mongoClient;

	private final Morphia morphia = new Morphia();

	private final Datastore datastore;

	public Connection() {
		mongoClient = new MongoClient(mongoDBUrl, mongoDBPort);
		morphia.mapPackage("fr.epsi.i4.soupeoserver.model.morphia");
		datastore = morphia.createDatastore(mongoClient, "soupeo");
		datastore.ensureIndexes();
	}

	public Datastore getDatastore() {
		return this.datastore;
	}
}
