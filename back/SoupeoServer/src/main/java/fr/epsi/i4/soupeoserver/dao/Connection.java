package fr.epsi.i4.soupeoserver.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBDatabase;
import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.mongoDBUrl;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @author Thomas Kint
 */
public class Connection {

	private static MongoClient mongoClient;

	public static MongoClient getInstance() {
		if (mongoClient == null) {
			CodecRegistry pojoCodecRegistry = fromRegistries(
					MongoClient.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().automatic(true).build())
			);
			mongoClient = new MongoClient(mongoDBUrl, MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
		}
		return mongoClient;
	}

	public static MongoDatabase getDatabaseInstance() {
		return getInstance().getDatabase(mongoDBDatabase);
	}
}
