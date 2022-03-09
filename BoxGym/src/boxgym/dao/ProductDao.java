package boxgym.dao;

import boxgym.jdbc.ConnectionFactory;
import boxgym.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
