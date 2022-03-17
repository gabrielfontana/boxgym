package boxgym.controller;

import static boxgym.Constant.COMBO_BOX_PROMPT_TEXT;
import boxgym.dao.SupplierDao;
import boxgym.helper.ImageHelper;
import boxgym.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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

    SupplierDao dao = new SupplierDao();
    LinkedHashMap<Integer, String> map = dao.readId();
    ImageHelper ih = new ImageHelper();
    
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
        loadSupplierNameComboBox();
        Platform.runLater(() -> {
            initProduct();
        });
    }
    
    private void loadSupplierNameComboBox() {
        ObservableList<String> obsList = FXCollections.observableArrayList();
        for (String s : map.values()) {
            obsList.add(s);
        }
        fkSupplierComboBox.setPromptText(COMBO_BOX_PROMPT_TEXT);
        fkSupplierComboBox.setItems(obsList);
    }
    
    private String getValueFromComboBox() {
        String fkSupplier = null;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (loadProduct.getFkSupplier() == entry.getKey()) {
                fkSupplier = entry.getValue();
                break;
            }
        }
        return fkSupplier;
    }

    private void initProduct() {
        nameTextField.setText(loadProduct.getName());
        categoryTextField.setText(loadProduct.getCategory());
        descriptionTextArea.setText(loadProduct.getDescription());
        amountTextField.setText(String.valueOf(loadProduct.getAmount()));
        minimumStockTextField.setText(String.valueOf(loadProduct.getMinimumStock()));
        costPriceTextField.setText(String.valueOf(loadProduct.getCostPrice()));
        sellingPriceTextField.setText(String.valueOf(loadProduct.getSellingPrice()));
        fkSupplierComboBox.valueProperty().set(getValueFromComboBox());
        try{
            productImage.setImage(SwingFXUtils.toFXImage(ImageHelper.convertBytesToImage(loadProduct), null));
        } catch (IOException ex) {
            Logger.getLogger(ProductsUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void chooseImage(MouseEvent event) {
        ih.chooser(productImage);
    }

    @FXML
    void save(ActionEvent event) {
        
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
