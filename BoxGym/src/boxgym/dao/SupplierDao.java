package boxgym.dao;

import boxgym.jdbc.ConnectionFactory;
import boxgym.model.Supplier;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.DbUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
            //DbUtils.closeQuietly(conn);
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
            while (rs.next()) {
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
        String sql = "UPDATE supplier SET corporateName = ?, tradeName = ?, email = ?, phone = ?, zipCode = ?, address = ?, addressComplement = ?, district = ?, city = ?, federativeUnit = ? WHERE supplierId = ?;";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getCorporateName());
            ps.setString(2, supplier.getTradeName());
            ps.setString(3, supplier.getEmail());
            ps.setString(4, supplier.getPhone());
            ps.setString(5, supplier.getZipCode());
            ps.setString(6, supplier.getAddress());
            ps.setString(7, supplier.getAddressComplement());
            ps.setString(8, supplier.getDistrict());
            ps.setString(9, supplier.getCity());
            ps.setString(10, supplier.getFederativeUnit());
            ps.setInt(11, supplier.getSupplierId());
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

    public boolean exportToExcel() {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Fornecedores");
            HSSFRow header = sheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("CNPJ");
            header.createCell(2).setCellValue("Razão Social");
            header.createCell(3).setCellValue("Nome Fantasia");
            header.createCell(4).setCellValue("E-mail");
            header.createCell(5).setCellValue("Telefone");
            header.createCell(6).setCellValue("CEP");
            header.createCell(7).setCellValue("Endereço");
            header.createCell(8).setCellValue("Complemento");
            header.createCell(9).setCellValue("Bairro");
            header.createCell(10).setCellValue("Cidade");
            header.createCell(11).setCellValue("UF");
            header.createCell(12).setCellValue("Criação");
            header.createCell(13).setCellValue("Modificação");

            String sql = "SELECT * FROM supplier";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow(i);
                row.createCell(0).setCellValue(Integer.toString(rs.getInt("supplierId")));
                row.createCell(1).setCellValue(rs.getString("companyRegistry"));
                row.createCell(2).setCellValue(rs.getString("corporateName"));
                row.createCell(3).setCellValue(rs.getString("tradeName"));
                row.createCell(4).setCellValue(rs.getString("email"));
                row.createCell(5).setCellValue(rs.getString("phone"));
                row.createCell(6).setCellValue(rs.getString("zipCode"));
                row.createCell(7).setCellValue(rs.getString("address"));
                row.createCell(8).setCellValue(rs.getString("addressComplement"));
                row.createCell(9).setCellValue(rs.getString("district"));
                row.createCell(10).setCellValue(rs.getString("city"));
                row.createCell(11).setCellValue(rs.getString("federativeUnit"));
                row.createCell(12).setCellValue(rs.getString("createdAt"));
                row.createCell(13).setCellValue(rs.getString("updatedAt"));
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Public\\Fornecedores.xls");
            workbook.write(fileOut);
            fileOut.close();
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SupplierDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeQuietly(conn);
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(rs);
        }
        return false;
    }

}
