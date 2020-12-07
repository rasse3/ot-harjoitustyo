package fi.rasmus.database;

import java.sql.*;
import java.util.*;

/**
 * Creates save-files and loads save-files of a ongoing simulation.
 *
 * @author Rasmus
 * @param <T>
 * @param <K>
 */
public interface SaveLoadDao<T, K> {

    /**
     * Saves data to the database.
     *
     * @param object Object to be saved
     * @throws SQLException ecception thrown
     */
    void create(T object) throws SQLException;

    /**
     * Loads data from the database.
     *
     * @param key Key of the data to be loaded
     * @return Data-object
     * @throws SQLException Thrown exception
     */
    T read(K key) throws SQLException;

    /**
     * Updates data in a savefile.
     *
     * @param object Data to be handled
     * @return Object retutrned
     * @throws SQLException Thrown exception
     */
    T update(T object) throws SQLException;

    /**
     * Deletes saved data.
     *
     * @param key Key of data to be deleted
     * @throws SQLException Thrown exception
     */
    void delete(K key) throws SQLException;

    /**
     * Lists data on the database.
     *
     * @return List object returned
     * @throws SQLException Therown exception
     */
    List<T> list() throws SQLException;
}
