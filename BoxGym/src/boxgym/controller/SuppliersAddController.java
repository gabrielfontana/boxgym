package boxgym.controller;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class SuppliersAddController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField companyRegistryTextField;

    @FXML
    private TextField corporateNameTextField;

    @FXML
    private TextField tradeNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField addressComplementTextField;

    @FXML
    private TextField districtTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private ComboBox<String> federativeUnitComboBox;

    private boolean created = false;

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCreated(false);
        loadFederativeUnitComboBox();
        suppliersInputRestrictions();
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
//        companyRegistryTextField.setValidationPattern("[0-9]", 14, "Sem pontuação!");
//        corporateNameTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 255);
//        tradeNameTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 255);
//        emailTextField.setValidationPattern("[A-Za-z0-9@._-]", 255);
//        phoneTextField.setValidationPattern("[0-9]", 11, "Sem pontuação!");
//        zipCodeTextField.setValidationPattern("[0-9]", 8, "Sem pontuação!");
//        addressTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 255);
//        addressComplementTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 255);
//        districtTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF ]", 255);
//        cityTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF ]", 255);
    }

    @FXML
    void save(ActionEvent event) {
        Supplier supplier = new Supplier(companyRegistryTextField.getText(), corporateNameTextField.getText(), tradeNameTextField.getText(),
                emailTextField.getText(), phoneTextField.getText(), zipCodeTextField.getText(), addressTextField.getText(), addressComplementTextField.getText(),
                districtTextField.getText(), cityTextField.getText(), federativeUnitComboBox.getSelectionModel().getSelectedItem());

        SupplierDao supplierDao = new SupplierDao();

        TextValidationHelper validation = new TextValidationHelper();

        validation.handleEmptyField(companyRegistryTextField.getText(), "'CNPJ'\n");
        validation.handleEmptyField(corporateNameTextField.getText(), "'Razão Social'\n");
        validation.handleEmptyField(tradeNameTextField.getText(), "'Nome Fantasia'");

        if (!(validation.getEmptyCounter() == 0)) {
            AlertHelper.customAlert(Alert.AlertType.WARNING, "Não foi possível realizar o cadastro deste fornecedor!", validation.getMessage());
        } else if (!(CnpjValidator.isValid(companyRegistryTextField.getText()))) {
            AlertHelper.customAlert(Alert.AlertType.WARNING, "Não foi possível realizar o cadastro deste fornecedor!", "'CNPJ' inválido.");
        } else if (supplierDao.checkDuplicate(supplier)) {
            AlertHelper.customAlert(Alert.AlertType.WARNING, "Não foi possível realizar o cadastro deste fornecedor!", "Este CNPJ já está cadastrado.");
            companyRegistryTextField.setText("");
        } else if (!(phoneTextField.getText().length() == 0 || phoneTextField.getText().length() == 10 || phoneTextField.getText().length() == 11)) {
            AlertHelper.customAlert(Alert.AlertType.WARNING, "Não foi possível realizar o cadastro deste fornecedor!", "O formato do campo 'Telefone' está incorreto.");
        } else if (!(zipCodeTextField.getText().length() == 0 || zipCodeTextField.getText().length() == 8)) {
            AlertHelper.customAlert(Alert.AlertType.WARNING, "Não foi possível realizar o cadastro deste fornecedor!", "O campo 'CEP' deve conter 8 dígitos.");
        } else {
            supplierDao.create(supplier);
            setCreated(true);
            AlertHelper.customAlert(Alert.AlertType.INFORMATION, "O fornecedor foi cadastrado com sucesso!", "");
            anchorPane.getScene().getWindow().hide();
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
