package boxgym.controller;

import boxgym.dao.SupplierDao;
import boxgym.helper.AlertHelper;
import boxgym.helper.LimitedTextField;
import boxgym.model.Supplier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class SuppliersUpdateController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TitledPane infoTitledPane;

    @FXML
    private TitledPane addressTitledPane;

    @FXML
    private Label companyRegistryLabel;

    @FXML
    private LimitedTextField corporateNameTextField;

    @FXML
    private LimitedTextField tradeNameTextField;

    @FXML
    private LimitedTextField emailTextField;

    @FXML
    private LimitedTextField phoneTextField;

    @FXML
    private LimitedTextField zipCodeTextField;

    @FXML
    private LimitedTextField addressTextField;

    @FXML
    private LimitedTextField addressComplementTextField;

    @FXML
    private LimitedTextField districtTextField;

    @FXML
    private LimitedTextField cityTextField;

    @FXML
    private ComboBox<String> federativeUnitComboBox;

    private Supplier loadSupplier;

    private boolean updated = false;

    public Supplier getLoadSupplier() {
        return loadSupplier;
    }

    public void setLoadSupplier(Supplier loadSupplier) {
        this.loadSupplier = loadSupplier;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpdated(false);
        infoTitledPane.setCollapsible(false);
        addressTitledPane.setCollapsible(false);
        loadFederativeUnitComboBox();
        suppliersInputRestrictions();

        Platform.runLater(() -> {
            initSupplier();
        });
    }

    private void initSupplier() {
        companyRegistryLabel.setText(loadSupplier.getCompanyRegistry());
        corporateNameTextField.setText(loadSupplier.getCorporateName());
        tradeNameTextField.setText(loadSupplier.getTradeName());
        emailTextField.setText(loadSupplier.getEmail());
        phoneTextField.setText(loadSupplier.getPhone());
        zipCodeTextField.setText(loadSupplier.getZipCode());
        addressTextField.setText(loadSupplier.getAddress());
        addressComplementTextField.setText(loadSupplier.getAddressComplement());
        districtTextField.setText(loadSupplier.getDistrict());
        cityTextField.setText(loadSupplier.getCity());
        federativeUnitComboBox.valueProperty().set(loadSupplier.getFederativeUnit());
    }

    private void loadFederativeUnitComboBox() {
        String[] federativeUnitsList = {
            "AC", "AL", "AP", "AM", "BA", "CE",
            "DF", "ES", "GO", "MA", "MT", "MS",
            "MG", "PA", "PB", "PR", "PE", "PI",
            "RJ", "RN", "RS", "RO", "RR", "SC",
            "SP", "SE", "TO"
        };
        federativeUnitComboBox.setPromptText("Selecione");
        federativeUnitComboBox.setItems(FXCollections.observableArrayList(federativeUnitsList));
    }

    private void suppliersInputRestrictions() {
        corporateNameTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 255);
        tradeNameTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 255);
        emailTextField.setValidationPattern("[A-Za-z0-9@._-]", 255);
        phoneTextField.setValidationPattern("[0-9]", 11, "Sem pontuação!");
        zipCodeTextField.setValidationPattern("[0-9]", 8, "Sem pontuação!");
        addressTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 255);
        addressComplementTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 255);
        districtTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF ]", 255);
        cityTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF ]", 255);
    }

    @FXML
    void save(ActionEvent event) {
        Supplier supplier = new Supplier(loadSupplier.getSupplierId(), corporateNameTextField.getText(), tradeNameTextField.getText(),
                emailTextField.getText(), phoneTextField.getText(), zipCodeTextField.getText(), addressTextField.getText(), addressComplementTextField.getText(),
                districtTextField.getText(), cityTextField.getText(), federativeUnitComboBox.getSelectionModel().getSelectedItem());

        SupplierDao supplierDao = new SupplierDao();
        supplierDao.update(supplier);
        setUpdated(true);
        AlertHelper.customAlert("", "O fornecedor foi editado com sucesso!", "", Alert.AlertType.INFORMATION);
        anchorPane.getScene().getWindow().hide();
    }

    @FXML
    void clear() {
        corporateNameTextField.setText("");
        tradeNameTextField.setText("");
        emailTextField.setText("");
        phoneTextField.setText("");
        zipCodeTextField.setText("");
        addressTextField.setText("");
        addressComplementTextField.setText("");
        districtTextField.setText("");
        cityTextField.setText("");
        federativeUnitComboBox.valueProperty().set(null);
    }
}
