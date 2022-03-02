package boxgym.controller;

import static boxgym.Constant.*;
import boxgym.controller.SuppliersUpdateScreenController;
import boxgym.dao.SupplierDao;
import boxgym.helper.AlertHelper;
import boxgym.model.Supplier;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SuppliersController implements Initializable {

    @FXML
    private TableView<Supplier> supplierTableView;

    @FXML
    private TableColumn<Supplier, Integer> supplierIdTableColumn;

    @FXML
    private TableColumn<Supplier, String> companyRegistryTableColumn;

    @FXML
    private TableColumn<Supplier, String> tradeNameTableColumn;

    @FXML
    private TableColumn<Supplier, String> emailTableColumn;

    @FXML
    private TableColumn<Supplier, String> phoneTableColumn;

    @FXML
    private Label supplierIdLabel;

    @FXML
    private Label companyRegistryLabel;

    @FXML
    private Label corporateNameLabel;

    @FXML
    private Label tradeNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label zipCodeLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label addressComplementLabel;

    @FXML
    private Label districtLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label federativeUnitLabel;

    @FXML
    private Label createdAtLabel;

    @FXML
    private Label updateAtLabel;

    private Supplier selected;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSupplierTableView();
        tableViewListeners();
    }

    @FXML
    void addSupplier(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(SUPPLIERS_ADD_VIEW));
            
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(SUPPLIERS_ADD_TITLE);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            
            initSupplierTableView();
        } catch (IOException ex) {
            Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void updateSupplier(ActionEvent event) {
        AlertHelper alert = new AlertHelper();

        if (selected == null) {
            alert.warningAlert("", "Selecione um fornecedor para editar.", "");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/boxgym/view/SuppliersUpdateScreen.fxml"));
                
                //Instancia o SuppliersUpdateScreenController para que o initialize possa utilizar o parâmetro
                SuppliersUpdateScreenController controller = new SuppliersUpdateScreenController();
                controller.setLoadSupplier(selected);                                
                loader.setController(controller);
                
                Parent root = (Parent) loader.load();
                
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("Editando Fornecedor");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                
                initSupplierTableView();
            } catch (IOException ex) {
                Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void deleteSupplier(ActionEvent event) {
        SupplierDao supplierDao = new SupplierDao();
        AlertHelper alert = new AlertHelper();

        if (selected == null) {
            alert.warningAlert("", "Selecione um fornecedor para excluir.", "");
        } else {
            supplierDao.delete(selected);
            alert.confirmationAlert("", "O fornecedor '" + selected.getCorporateName() + "' foi excluído com sucesso!", "");
            supplierTableView.setItems(loadData());
        }
    }

    private void showDetails() {
        if (selected == null) {
            supplierIdLabel.setText("");
            companyRegistryLabel.setText("");
            corporateNameLabel.setText("");
            tradeNameLabel.setText("");
            emailLabel.setText("");
            phoneLabel.setText("");
            zipCodeLabel.setText("");
            addressLabel.setText("");
            addressComplementLabel.setText("");
            districtLabel.setText("");
            cityLabel.setText("");
            federativeUnitLabel.setText("");
            createdAtLabel.setText("");
            updateAtLabel.setText("");
        } else {
            supplierIdLabel.setText(String.valueOf(selected.getSupplierId()));
            companyRegistryLabel.setText(selected.getCompanyRegistry());
            corporateNameLabel.setText(selected.getCorporateName());
            tradeNameLabel.setText(selected.getTradeName());
            emailLabel.setText(selected.getEmail());
            phoneLabel.setText(selected.getPhone());
            zipCodeLabel.setText(selected.getZipCode());
            addressLabel.setText(selected.getAddress());
            addressComplementLabel.setText(selected.getAddressComplement());
            districtLabel.setText(selected.getDistrict());
            cityLabel.setText(selected.getCity());
            federativeUnitLabel.setText(selected.getFederativeUnit());
            createdAtLabel.setText(selected.getCreatedAt());
            updateAtLabel.setText(selected.getUpdatedAt());
        }
    }

    private void initSupplierTableView() {
        supplierIdTableColumn.setCellValueFactory(new PropertyValueFactory("supplierId"));
        companyRegistryTableColumn.setCellValueFactory(new PropertyValueFactory("companyRegistry"));
        tradeNameTableColumn.setCellValueFactory(new PropertyValueFactory("tradeName"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory("email"));
        phoneTableColumn.setCellValueFactory(new PropertyValueFactory("phone"));
        supplierTableView.setItems(loadData());
    }

    private ObservableList<Supplier> loadData() {
        SupplierDao supplierDao = new SupplierDao();
        return FXCollections.observableArrayList(supplierDao.read());
    }

    private void tableViewListeners() {
        supplierTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (Supplier) newValue;
                showDetails();
            }
        });

        supplierTableView.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) supplierTableView.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
    }

}
