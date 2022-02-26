package boxgym.controller;

import static boxgym.Constant.*;
import boxgym.dao.SupplierDao;
import boxgym.helper.AlertHelper;
import boxgym.helper.CnpjValidator;
import boxgym.helper.LimitedTextField;
import boxgym.helper.TextValidationHelper;
import boxgym.model.Supplier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;

public class SuppliersAddScreenController implements Initializable {

    @FXML
    private TitledPane infoTitledPane;

    @FXML
    private TitledPane addressTitledPane;

    @FXML
    private LimitedTextField companyRegistryTextField;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoTitledPane.setCollapsible(false);
        addressTitledPane.setCollapsible(false);
        loadFederativeUnitComboBox();
        suppliersInputRestrictions();
    }

    private void loadFederativeUnitComboBox() {
        federativeUnitComboBox.setPromptText(COMBO_BOX_PROMPT_TEXT);
        federativeUnitComboBox.setItems(FXCollections.observableArrayList(FEDERATIVE_UNITS_LIST));
    }

    private void suppliersInputRestrictions() {
        companyRegistryTextField.setValidationPattern(POSITIVE_INTEGERS_NUMBERS_REGEX, CNPJ_MAX_LENGTH, TOOLTIP_TEXT);
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
        String companyRegistry = companyRegistryTextField.getText();
        String corporateName = corporateNameTextField.getText();
        String tradeName = tradeNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String zipCode = zipCodeTextField.getText();
        String address = addressTextField.getText();
        String addressComplement = addressComplementTextField.getText();
        String district = districtTextField.getText();
        String city = cityTextField.getText();
        String federativeUnit = federativeUnitComboBox.getSelectionModel().getSelectedItem();

        TextValidationHelper validation = new TextValidationHelper();
        AlertHelper alert = new AlertHelper();

        Supplier supplier = new Supplier(companyRegistry, corporateName, tradeName, email, phone, zipCode, address, addressComplement, district, city, federativeUnit);
        SupplierDao supplierDaoCheck = new SupplierDao();
        
        validation.handleEmptyField(companyRegistry, "'CNPJ'\n");
        validation.handleEmptyField(corporateName, "'Razão Social'\n");
        validation.handleEmptyField(tradeName, "'Nome Fantasia'");

        if (!(validation.getEmptyCounter() == 0)) {
            alert.warningAlert("Atenção", "Não foi possível realizar o cadastro deste fornecedor!", validation.getMessage());
        } else if (!(CnpjValidator.isValid(companyRegistry))) {
            alert.warningAlert("Atenção", "Não foi possível realizar o cadastro deste fornecedor!", "'CNPJ' inválido.");            
        } else if (supplierDaoCheck.checkDuplicate(supplier)) {
            alert.warningAlert("Atenção", "Não foi possível realizar o cadastro deste fornecedor!", "Este CNPJ já está cadastrado.");
            companyRegistryTextField.setText("");
        } else if (!(phone.length() == 0 || phone.length() == 10 || phone.length() == 11)) {
            alert.warningAlert("Atenção", "Não foi possível realizar o cadastro deste fornecedor!", "O formato do campo 'Telefone' está incorreto.");
        } else if (!(zipCode.length() == 0 || zipCode.length() == 8)) {
            alert.warningAlert("Atenção", "Não foi possível realizar o cadastro deste fornecedor!", "O campo 'CEP' deve conter 8 dígitos.");
        } else {
            SupplierDao supplierDaoInsert = new SupplierDao();
            supplierDaoInsert.create(supplier);
            alert.confirmationAlert("Informação", "O fornecedor foi cadastrado com sucesso", "");
            clear();
        }
    }

    @FXML
    void clear() {
        companyRegistryTextField.setText("");
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
