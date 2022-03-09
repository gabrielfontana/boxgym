package boxgym.dao;

import boxgym.jdbc.ConnectionFactory;
import boxgym.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;

public class ProductDao {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public ProductDao() {
        this.conn = new ConnectionFactory().getConnection();
    }

    public boolean create(Product product) {
        String sql = "INSERT INTO product (image) VALUES (?);";

        try {
            ps = conn.prepareStatement(sql);
            ps.setBytes(1, product.getImage());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return false;
    }
    
    public List<Product> read() {
        List<Product> productsList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setImage(rs.getBytes("image"));
                p.setCreatedAt(rs.getString("createdAt"));
                p.setUpdatedAt(rs.getString("updatedAt"));
                productsList.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return productsList;
    }
}
