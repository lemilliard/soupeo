package fr.epsi.i4.soupeoserver.dao;

import org.bson.types.ObjectId;

import java.util.List;

import static fr.epsi.i4.soupeoserver.SoupeoServerApplication.connection;

/**
 * @author Thomas Kint
 */
public class MainDAO {

	public static <T> List<T> findAll(Class<T> clazz) {
		return connection.getDatastore().find(clazz).asList();
	}

	public static <T> T findById(Class<T> clazz, ObjectId id) {
		return connection.getDatastore().get(clazz, id);
	}

	public static <T> T save(T entity) {
		connection.getDatastore().save(entity);
		return entity;
	}

	public static <T> boolean delete(T entity) {
		if (connection.getDatastore().delete(entity).getN() > 0) {
			return true;
		}
		return false;
	}
}
