package fr.epsi.i4.soupeoserver.dao;

import com.mongodb.MongoClient;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBPort;
import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBUrl;

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
}
