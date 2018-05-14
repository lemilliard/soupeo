package fr.epsi.i4.soupeoserver.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.*;

/**
 * @author Thomas Kint
 */
public class Connection {

	private static MongoClient mongoClient;

	public static MongoClient getInstance() {
		if (mongoClient == null) {
			mongoClient = new MongoClient(mongoDBUrl, mongoDBPort);
		}
		return mongoClient;
	}

	public static MongoDatabase getDatabaseInstance() {
		return getInstance().getDatabase(mongoDBDatabase);
	}
}
