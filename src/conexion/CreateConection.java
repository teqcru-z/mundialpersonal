package conexion;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CreateConection {
    static Properties config = new Properties();
    String hostname, port, database, username, password;

    public CreateConection() {
        String path = "C:\\Users\\tequi\\OneDrive\\Documentos\\NetBeansProjects\\ProyectoMundial\\src\\conexion\\db_properties.properties";
        try {
            config.load(Files.newInputStream(Paths.get(path)));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        loadProperties();
    }

    public void loadProperties() {
        this.hostname = config.getProperty("hostname");
        this.port = config.getProperty("port");
        this.database = config.getProperty("database");
        this.username = config.getProperty("username");
        this.password = config.getProperty("password");
    }

    public Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + database;
        
        System.out.println("Conectado exitosamente");
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
