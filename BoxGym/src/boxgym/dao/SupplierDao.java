package boxgym.dao;

import boxgym.jdbc.ConnectionFactory;
import boxgym.model.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

public class SupplierDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public SupplierDao() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public boolean checkDuplicate(Supplier supplier) {
        try {
            String cnpj = supplier.getCompanyRegistry();
            String sql = "SELECT companyRegistry FROM supplier WHERE companyRegistry = '" + cnpj + "'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return (cnpj.equals(rs.getString("companyRegistry")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return false;
    }

    public boolean create(Supplier supplier) {
        String sql = "INSERT INTO supplier (companyRegistry, corporateName, tradeName, email, phone, zipCode, address, addressComplement, district, city, federativeUnit) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getCompanyRegistry());
            ps.setString(2, supplier.getCorporateName());
            ps.setString(3, supplier.getTradeName());
            ps.setString(4, supplier.getEmail());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getZipCode());
            ps.setString(7, supplier.getAddress());
            ps.setString(8, supplier.getAddressComplement());
            ps.setString(9, supplier.getDistrict());
            ps.setString(10, supplier.getCity());
            ps.setString(11, supplier.getFederativeUnit());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return false;
    }
    
    public List<Supplier> read() {
        List<Supplier> suppliersList = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        try {            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                Supplier s = new Supplier();
                s.setSupplierId(rs.getInt("supplierId"));
                s.setCompanyRegistry(rs.getString("companyRegistry"));
                s.setCorporateName(rs.getString("corporateName"));
                s.setTradeName(rs.getString("tradeName"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                s.setZipCode(rs.getString("zipCode"));
                s.setAddress(rs.getString("address"));
                s.setAddressComplement(rs.getString("addressComplement"));
                s.setDistrict(rs.getString("district"));
                s.setCity(rs.getString("city"));
                s.setFederativeUnit(rs.getString("federativeUnit"));
                s.setCreatedAt(rs.getString("createdAt"));
                s.setUpdatedAt(rs.getString("updatedAt"));
                suppliersList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return suppliersList;
    }

    public boolean update(Supplier supplier) {
        String sql = "UPDATE supplier SET companyRegistry = ?, corporateName = ?, tradeName = ?, email = ?, phone = ?, "
                + "zipCode = ?, address = ?, addressComplement = ?, district = ?, city = ?, federativeUnit = ? WHERE supplierId = ?;";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getCompanyRegistry());
            ps.setString(2, supplier.getCorporateName());
            ps.setString(3, supplier.getTradeName());
            ps.setString(4, supplier.getEmail());
            ps.setString(5, supplier.getPhone());
            ps.setString(6, supplier.getZipCode());
            ps.setString(7, supplier.getAddress());
            ps.setString(8, supplier.getAddressComplement());
            ps.setString(9, supplier.getDistrict());
            ps.setString(10, supplier.getCity());
            ps.setString(11, supplier.getFederativeUnit());
            ps.setInt(12, supplier.getSupplierId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return false;
    }
    
    public boolean delete(Supplier supplier) {
        String sql = "DELETE FROM supplier WHERE supplierId = ?;";

        try {
            ps = conn.prepareStatement(sql);            
            ps.setInt(1, supplier.getSupplierId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return false;
    }

}
