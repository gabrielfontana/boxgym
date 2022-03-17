package boxgym.controller;

import boxgym.dao.SupplierDao;
import boxgym.helper.AlertHelper;
import boxgym.helper.StageHelper;
import boxgym.model.Supplier;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class SuppliersController implements Initializable {

    @FXML
    private TextField searchBox;

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
    private Label updatedAtLabel;

    private Supplier selected;

    AlertHelper alert = new AlertHelper();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resetDetails();
        initSupplierTableView();
        tableViewListeners();
        searchBox.setOnKeyTyped((KeyEvent e) -> search());
    }

    @FXML
    void addSupplier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boxgym/view/SuppliersAdd.fxml"));
            Parent root = (Parent) loader.load();

            SuppliersAddController controller = loader.getController();

            StageHelper sh = new StageHelper();
            sh.createStage("Adicionando Fornecedor", root);

            if (controller.isCreated()) {
                initSupplierTableView();
                supplierTableView.getSelectionModel().selectLast();
            }
        } catch (IOException ex) {
            Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void updateSupplier(ActionEvent event) {
        if (selected == null) {
            AlertHelper.customAlert("", "Selecione um fornecedor para editar.", "", Alert.AlertType.WARNING);
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/boxgym/view/SuppliersUpdate.fxml"));
                Parent root = (Parent) loader.load();

                SuppliersUpdateController controller = loader.getController();
                controller.setLoadSupplier(selected);

                StageHelper sh = new StageHelper();
                sh.createStage("Editando Fornecedor", root);

                if (controller.isUpdated()) {
                    initSupplierTableView();
                }
            } catch (IOException ex) {
                Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void deleteSupplier(ActionEvent event) {
        SupplierDao supplierDao = new SupplierDao();

        if (selected == null) {
            AlertHelper.customAlert("", "Selecione um fornecedor para excluir!", "", Alert.AlertType.WARNING);
        } else {
            alert.confirmationAlert("Aviso", "Tem certeza que deseja excluir o fornecedor '" + selected.getTradeName() + "'?", "Esta ação é irreversível!");
            if (alert.getResult().get() == ButtonType.YES) {
                supplierDao.delete(selected);
                supplierTableView.setItems(loadData());
                resetDetails();
                AlertHelper.customAlert("", "O fornecedor foi excluído com sucesso!", "", Alert.AlertType.INFORMATION);
            }
        }
    }

    private void resetDetails() {
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
            updatedAtLabel.setText("");
        }
    }

    private void showDetails() {
        if (selected != null) {
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
            updatedAtLabel.setText(selected.getUpdatedAt());
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

    private boolean searchFindsSupplier(Supplier supplier, String searchText) {
        String supplierId = String.valueOf(supplier.getSupplierId()).toLowerCase();
        String companyRegistry = String.valueOf(supplier.getCompanyRegistry()).toLowerCase();
        String corporateName = String.valueOf(supplier.getCorporateName()).toLowerCase();
        String tradeName = String.valueOf(supplier.getTradeName()).toLowerCase();
        String email = String.valueOf(supplier.getEmail()).toLowerCase();
        String phone = String.valueOf(supplier.getPhone()).toLowerCase();
        String zipCode = String.valueOf(supplier.getZipCode()).toLowerCase();
        String address = String.valueOf(supplier.getAddress()).toLowerCase();
        String addressComplement = String.valueOf(supplier.getAddressComplement()).toLowerCase();
        String district = String.valueOf(supplier.getDistrict()).toLowerCase();
        String city = String.valueOf(supplier.getCity()).toLowerCase();
        String federativeUnit = String.valueOf(supplier.getFederativeUnit()).toLowerCase();
        String createdAt = String.valueOf(supplier.getCreatedAt()).toLowerCase();
        String updatedAt = String.valueOf(supplier.getUpdatedAt()).toLowerCase();

        return (supplierId.contains(searchText)) || (companyRegistry.contains(searchText))
                || (corporateName.contains(searchText)) || (tradeName.contains(searchText))
                || (email.contains(searchText)) || (phone.contains(searchText))
                || (zipCode.contains(searchText)) || (address.contains(searchText))
                || (addressComplement.contains(searchText)) || (district.contains(searchText))
                || (city.contains(searchText)) || (federativeUnit.contains(searchText))
                || (createdAt.contains(searchText)) || (updatedAt.contains(searchText));
    }

    private void search() {
        FilteredList<Supplier> filteredData = new FilteredList<>(loadData(), p -> true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(supplier -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                return searchFindsSupplier(supplier, newValue.toLowerCase());
            });
        });

        SortedList<Supplier> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(supplierTableView.comparatorProperty());
        supplierTableView.setItems(sortedData);
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

    @FXML
    void exportExcel(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("Pasta de Trabalho do Excel", "*.xlsx"));
        File file = chooser.showSaveDialog(new Stage());

        SupplierDao supplierDao = new SupplierDao();

        if (file != null) {
            supplierDao.createExcelFile(file.getAbsolutePath());
        }
    }

    @FXML
    void generatePdf(ActionEvent event) {

    }

}
