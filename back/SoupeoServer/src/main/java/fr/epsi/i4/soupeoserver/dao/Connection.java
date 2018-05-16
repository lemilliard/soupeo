package fr.epsi.i4.soupeoserver.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBDatabase;
import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBPort;
import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBUrl;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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
