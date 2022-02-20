package boxgym.dao;

import boxgym.jdbc.ConnectionFactory;
import boxgym.model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDao {
    private final Connection connection;
    
    public ClientDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public boolean add(Client c) {
        String sql = "INSERT INTO client (cpf, name) VALUES (?, ?);";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, c.getCpf());
            stmt.setString(2, c.getName());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}