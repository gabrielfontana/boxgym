package boxgym.controller;

import boxgym.model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProductsUpdateController implements Initializable {

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
    
    private Product loadProduct;
    
    private boolean updated = false;

    public Product getLoadProduct() {
        return loadProduct;
    }

    public void setLoadProduct(Product loadProduct) {
        this.loadProduct = loadProduct;
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
        Platform.runLater(() -> {
            initProduct();
        });
    }
    
    private void initProduct() {
        nameTextField.setText(loadProduct.getName());
        categoryTextField.setText(loadProduct.getCategory());
        descriptionTextArea.setText(loadProduct.getDescription());
        amountTextField.setText(String.valueOf(loadProduct.getAmount()));
        minimumStockTextField.setText(String.valueOf(loadProduct.getMinimumStock()));
        costPriceTextField.setText(String.valueOf(loadProduct.getCostPrice()));
        sellingPriceTextField.setText(String.valueOf(loadProduct.getSellingPrice()));
    }

    @FXML
    private void chooseImage(MouseEvent event) {
    }

    @FXML
    private void save(ActionEvent event) {
    }

    @FXML
    private void clear(ActionEvent event) {
    }
    
}
