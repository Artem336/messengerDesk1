package main.java.messenger.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler extends Configs{
    Connection dbconnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString="jdbc:postgresql://"+dbHost+":"
                +dbPort+"/"+dbName;
        Class.forName("org.postgresql.Driver");
        dbconnection= DriverManager.getConnection(connectionString,dbUser,dbPass);
        return dbconnection;
    }

    //запис и считывание

}
