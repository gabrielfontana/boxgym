package boxgym.controller;

import boxgym.dao.ProductDao;
import boxgym.dao.SupplierDao;
import boxgym.helper.AlertHelper;
import boxgym.helper.ImageHelper;
import boxgym.model.Product;
import java.math.BigDecimal;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProductsAddController implements Initializable {

    SupplierDao dao = new SupplierDao();
    LinkedHashMap<Integer, String> map = dao.readId();
    ImageHelper ih = new ImageHelper();

    private boolean created = false;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView productImage;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextField amountTextField;

    @FXML
    private TextField minimumStockTextField;

    @FXML
    private TextField costPriceTextField;

    @FXML
    private TextField sellingPriceTextField;

    @FXML
    private ComboBox<String> fkSupplierComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCreated(false);
        loadSupplierNameComboBox();
        ih.loadDefaultImage(productImage);
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    private void loadSupplierNameComboBox() {
        ObservableList<String> obsList = FXCollections.observableArrayList();
        for (String s : map.values()) {
            obsList.add(s);
        }
        fkSupplierComboBox.setPromptText("Selecione");
        fkSupplierComboBox.setItems(obsList);
    }

    private int getKeyFromComboBox() {
        int fkSupplier = 0;
        for (Entry<Integer, String> entry : map.entrySet()) {
            if (fkSupplierComboBox.getSelectionModel().getSelectedItem().equals(entry.getValue())) {
                fkSupplier = entry.getKey();
                break;
            }
        }
        return fkSupplier;
    }

    @FXML
    void chooseImage(MouseEvent event) {
        ih.chooser(productImage);
    }

    @FXML
    void save(ActionEvent event) {
        if (fkSupplierComboBox.getItems().size() <= 0) {
            System.out.println("Cadastre um fornecedor");
        } else if (fkSupplierComboBox.getSelectionModel().getSelectedItem() == null) {
            System.out.println("Escolha um fornecedor");
        } else {
            Product product = new Product(nameTextField.getText(), categoryTextField.getText(), descriptionTextArea.getText(), Integer.valueOf(amountTextField.getText()),
                    Integer.parseInt(minimumStockTextField.getText()), new BigDecimal(costPriceTextField.getText()), new BigDecimal(sellingPriceTextField.getText()),
                    ih.getImageBytes(), getKeyFromComboBox());
            ProductDao productDao = new ProductDao();
            productDao.create(product);
            setCreated(true);
            AlertHelper.customAlert("", "O produto foi cadastrado com sucesso!", "", Alert.AlertType.INFORMATION);
            anchorPane.getScene().getWindow().hide();
        }
    }

    @FXML
    void clear(ActionEvent event) {
        nameTextField.setText("");
        categoryTextField.setText("");
        descriptionTextArea.setText("");
        amountTextField.setText("");
        minimumStockTextField.setText("");
        costPriceTextField.setText("");
        sellingPriceTextField.setText("");
        fkSupplierComboBox.valueProperty().set(null);
        ih.loadDefaultImage(productImage);
    }

}
