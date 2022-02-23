package boxgym.controller;

import static boxgym.Constant.*;
import boxgym.dao.SupplierDao;
import boxgym.model.Supplier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class SuppliersAddScreenController implements Initializable {
  
    @FXML
    private TitledPane infoTitledPane;
    
    @FXML
    private TitledPane addressTitledPane;

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
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        infoTitledPane.setCollapsible(false);
        addressTitledPane.setCollapsible(false);
        loadFederativeUnits();
    }

    private void loadFederativeUnits() {
        federativeUnitComboBox.setPromptText("Selecione");
        federativeUnitComboBox.setItems(FXCollections.observableArrayList(LIST_FEDERATIVE_UNITS));
    }

    @FXML
    void save(ActionEvent event) {
        Supplier supplier = new Supplier(companyRegistryTextField.getText(), corporateNameTextField.getText(),
                tradeNameTextField.getText(), emailTextField.getText(), phoneTextField.getText(),
                zipCodeTextField.getText(), addressTextField.getText(), addressComplementTextField.getText(),
                districtTextField.getText(), cityTextField.getText(), federativeUnitComboBox.getSelectionModel().getSelectedItem());

        SupplierDao supplierDao = new SupplierDao();

        supplierDao.add(supplier);
    }
    
    
    @FXML
    void clear(ActionEvent event) {
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
