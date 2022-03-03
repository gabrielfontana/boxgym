package boxgym.controller;

import static boxgym.Constant.*;
import boxgym.dao.SupplierDao;
import boxgym.helper.LimitedTextField;
import boxgym.model.Supplier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    public Supplier getLoadSupplier() {
        return loadSupplier;
    }

    public void setLoadSupplier(Supplier loadSupplier) {
        this.loadSupplier = loadSupplier;
    }
                
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
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
        federativeUnitComboBox.setPromptText(COMBO_BOX_PROMPT_TEXT);
        federativeUnitComboBox.setItems(FXCollections.observableArrayList(FEDERATIVE_UNITS_LIST));
    }

    private void suppliersInputRestrictions() {       
        corporateNameTextField.setValidationPattern(STANDARD_REGEX, STANDARD_MAX_LENGTH);
        tradeNameTextField.setValidationPattern(STANDARD_REGEX, STANDARD_MAX_LENGTH);
        emailTextField.setValidationPattern(EMAIL_REGEX, STANDARD_MAX_LENGTH);
        phoneTextField.setValidationPattern(POSITIVE_INTEGERS_NUMBERS_REGEX, PHONE_MAX_LENGTH, TOOLTIP_TEXT);
        zipCodeTextField.setValidationPattern(POSITIVE_INTEGERS_NUMBERS_REGEX, CEP_MAX_LENGTH, TOOLTIP_TEXT);
        addressTextField.setValidationPattern(STANDARD_REGEX, STANDARD_MAX_LENGTH);
        addressComplementTextField.setValidationPattern(STANDARD_REGEX, STANDARD_MAX_LENGTH);
        districtTextField.setValidationPattern(LETTERS_REGEX, STANDARD_MAX_LENGTH);
        cityTextField.setValidationPattern(LETTERS_REGEX, STANDARD_MAX_LENGTH);
    }

    @FXML
    void save(ActionEvent event) {
        Supplier supplier = new Supplier(loadSupplier.getSupplierId(), corporateNameTextField.getText(), tradeNameTextField.getText(),
                emailTextField.getText(), phoneTextField.getText(), zipCodeTextField.getText(), addressTextField.getText(), addressComplementTextField.getText(),
                districtTextField.getText(), cityTextField.getText(), federativeUnitComboBox.getSelectionModel().getSelectedItem());

        SupplierDao supplierDao = new SupplierDao();        
        supplierDao.update(supplier);        
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
