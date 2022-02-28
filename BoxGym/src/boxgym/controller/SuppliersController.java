package boxgym.controller;

import static boxgym.Constant.*;
import boxgym.dao.SupplierDao;
import boxgym.model.Supplier;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SuppliersController implements Initializable {

    @FXML
    private Button addSupplierButton;

    @FXML
    private TableView<Supplier> supplierTableView;

    @FXML
    private TableColumn<Supplier, Integer> supplierIdTableColumn;

    @FXML
    private TableColumn<Supplier, String> companyRegistryTableColumn;

    @FXML
    private TableColumn<Supplier, String> corporateNameTableColumn;

    @FXML
    private TableColumn<Supplier, String> tradeNameTableColumn;

    @FXML
    private TableColumn<Supplier, String> emailTableColumn;

    @FXML
    private TableColumn<Supplier, String> phoneTableColumn;

    @FXML
    private TableColumn<Supplier, String> zipCodeTableColumn;

    @FXML
    private TableColumn<Supplier, String> addressTableColumn;

    @FXML
    private TableColumn<Supplier, String> addressComplementTableColumn;

    @FXML
    private TableColumn<Supplier, String> districtTableColumn;

    @FXML
    private TableColumn<Supplier, String> cityTableColumn;

    @FXML
    private TableColumn<Supplier, String> federativeUnitTableColumn;

    @FXML
    private TableColumn<Supplier, String> createdAtTableColumn;

    @FXML
    private TableColumn<Supplier, String> updatedAtTableColumn;

    private List<Supplier> supplierList;
    private ObservableList<Supplier> supplierObservableList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSupplierTableView();
    }

    @FXML
    void addSupplier(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(SUPPLIERS_ADD_VIEW));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(SUPPLIERS_ADD_TITLE);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initSupplierTableView() {
        supplierIdTableColumn.setCellValueFactory(new PropertyValueFactory("supplierId"));
        companyRegistryTableColumn.setCellValueFactory(new PropertyValueFactory("companyRegistry"));
        corporateNameTableColumn.setCellValueFactory(new PropertyValueFactory("corporateName"));
        tradeNameTableColumn.setCellValueFactory(new PropertyValueFactory("tradeName"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory("email"));
        phoneTableColumn.setCellValueFactory(new PropertyValueFactory("phone"));
        zipCodeTableColumn.setCellValueFactory(new PropertyValueFactory("zipCode"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory("address"));
        addressComplementTableColumn.setCellValueFactory(new PropertyValueFactory("addressComplement"));
        districtTableColumn.setCellValueFactory(new PropertyValueFactory("district"));
        cityTableColumn.setCellValueFactory(new PropertyValueFactory("city"));
        federativeUnitTableColumn.setCellValueFactory(new PropertyValueFactory("federativeUnit"));
        createdAtTableColumn.setCellValueFactory(new PropertyValueFactory("createdAt"));
        updatedAtTableColumn.setCellValueFactory(new PropertyValueFactory("updatedAt"));
        supplierTableView.setItems(loadData());
    }

    private ObservableList<Supplier> loadData() {
        SupplierDao supplierDao = new SupplierDao();
        return FXCollections.observableArrayList(supplierDao.read());
    }

}
