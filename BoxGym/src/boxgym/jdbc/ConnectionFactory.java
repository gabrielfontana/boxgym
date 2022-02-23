package boxgym.jdbc;

import static boxgym.Constant.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {        
        try {
            String url = CONNECTION_URL;
            String userName = CONNECTION_USERNAME;
            String userPassword = CONNECTION_USERPASSWORD;
            
            return DriverManager.getConnection(url, userName, userPassword);
        } catch (SQLException ex) {
            System.out.println("Não foi possível abrir a conexão!");
            throw new RuntimeException(ex);            
        }
    }
}
