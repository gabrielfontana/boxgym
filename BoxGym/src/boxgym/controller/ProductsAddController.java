package boxgym.controller;

import static boxgym.Constant.*;
import boxgym.dao.ProductDao;
import boxgym.dao.SupplierDao;
import boxgym.helper.AlertHelper;
import boxgym.model.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;

public class ProductsAddController implements Initializable {

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
    private Spinner<Integer> amountSpinner;

    @FXML
    private Spinner<Integer> minimumStockSpinner;

    @FXML
    private TextField costPriceTextField;

    @FXML
    private TextField sellingPriceTextField;

    @FXML
    private ComboBox<String> supplierNameComboBox;

    private String imagePath;
    private FileInputStream fis;
    private byte[] imageBytes;
    private boolean created = false;

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    SupplierDao dao = new SupplierDao();
    LinkedHashMap<Integer, String> map = dao.readId();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCreated(false);
        initSpinner();
        loadSupplierNameComboBox();
        setDefaultImage();
    }

    private void initSpinner() {
        SpinnerValueFactory<Integer> amountValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000);
        amountValueFactory.setValue(0);
        amountSpinner.setValueFactory(amountValueFactory);

        SpinnerValueFactory<Integer> minimumStockValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100000);
        minimumStockValueFactory.setValue(0);
        minimumStockSpinner.setValueFactory(minimumStockValueFactory);
    }

    private void loadSupplierNameComboBox() {
        ObservableList<String> obsList = FXCollections.observableArrayList();
        for (String s : map.values()) {
            obsList.add(s);
        }
        supplierNameComboBox.setPromptText(COMBO_BOX_PROMPT_TEXT);
        supplierNameComboBox.setItems(obsList);
    }

    private int getKeyFromComboBox() {
        int fkSupplier = 0;
        for (Entry<Integer, String> entry : map.entrySet()) {
            if (supplierNameComboBox.getSelectionModel().getSelectedItem().equals(entry.getValue())) {
                fkSupplier = entry.getKey();
                break;
            }
        }
        return fkSupplier;
    }

    private void setDefaultImage() {
        imagePath = "src/boxgym/img/default-no-image.png";
        convertImageToBytes(imagePath);
    }

    @FXML
    void chooseImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagem", "*.jpg", "*.png", "*.jpeg"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            imagePath = file.getAbsolutePath();
            productImage.setImage(new Image("file:///" + imagePath));
            convertImageToBytes(imagePath);
        }
    }

    @FXML
    void save(ActionEvent event) {
        Product product = new Product(nameTextField.getText(), categoryTextField.getText(), descriptionTextArea.getText(), amountSpinner.getValue(), minimumStockSpinner.getValue(),
                new BigDecimal(costPriceTextField.getText()), new BigDecimal(sellingPriceTextField.getText()), imageBytes, getKeyFromComboBox());
        ProductDao productDao = new ProductDao();
        if (productDao.create(product)) {
            setCreated(true);
            AlertHelper.customAlert("", "O produto foi cadastrado com sucesso!", "", Alert.AlertType.INFORMATION);
            anchorPane.getScene().getWindow().hide();
        } else {
            System.out.println("false");
        }
    }

    @FXML
    void clear(ActionEvent event) {

    }

    private void convertImageToBytes(String path) {
        try {
            //max_allowed_packet=32M (default -> max_allowed_packet=1M)
            fis = new FileInputStream(new File(path));
            imageBytes = IOUtils.toByteArray(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductsAddController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductsAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
