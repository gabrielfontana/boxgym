package boxgym.dao;

import boxgym.jdbc.ConnectionFactory;
import boxgym.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplierDao {

    private Connection connection;

    public SupplierDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public boolean add(Supplier supplier) {
        String sql = "INSERT INTO supplier (companyRegistry, corporateName, tradeName, email, phone, zipCode, address, addressComplement, district, city, federativeUnit) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, supplier.getCompanyRegistry());
            stmt.setString(2, supplier.getCorporateName());
            stmt.setString(3, supplier.getTradeName());
            stmt.setString(4, supplier.getEmail());
            stmt.setString(5, supplier.getPhone());
            stmt.setString(6, supplier.getZipCode());
            stmt.setString(7, supplier.getAddress());
            stmt.setString(8, supplier.getAddressComplement());
            stmt.setString(9, supplier.getDistrict());
            stmt.setString(10, supplier.getCity());
            stmt.setString(11, supplier.getFederativeUnit());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);            
            return false;
        }
    }

}
