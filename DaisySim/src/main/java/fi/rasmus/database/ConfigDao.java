package fi.rasmus.database;

/**
 * * This class handles the initial conditions of the simulation.
 */
import java.sql.*;
import java.util.*;

public interface ConfigDao<T, K> {

    /**
     * Reads initial data from database.
     * @param key   Key of initial conditions data
     * @return  Data-object
     * @throws SQLException Thrown exception 
     */
    T read(K key) throws SQLException;

}
