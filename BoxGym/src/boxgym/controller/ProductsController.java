package boxgym.controller;

import boxgym.dao.ProductDao;
import boxgym.helper.StageHelper;
import boxgym.model.Product;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

public class ProductsController implements Initializable {

    @FXML
    private TextField searchBox;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIdTableColumn;

    @FXML
    private ImageView productImageView;

    @FXML
    private Label productIdLabel;

    private Product selected;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resetDetails();
        initProductTableView();
        tableViewListeners();
    }

    @FXML
    void addProduct(ActionEvent event) {
        try {
            StageHelper sh = new StageHelper();
            sh.openAddStage("/boxgym/view/ProductsAdd.fxml", "Adicionando Produto");
            initProductTableView();
            productTableView.getSelectionModel().selectLast();
        } catch (IOException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void updateProduct(ActionEvent event) {

    }

    @FXML
    void deleteProduct(ActionEvent event) {

    }

    private void resetDetails() {
        if (selected == null) {
            productImageView.setImage(new Image("/boxgym/img/default-no-image.png"));
            productIdLabel.setText("");
        }
    }

    private void showDetails() {        
        try {
            if (selected != null) {
                byte[] imgByteArray = selected.getImage();
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imgByteArray);
                BufferedImage bufferedImg = ImageIO.read(inputStream);
                productImageView.setImage(SwingFXUtils.toFXImage(bufferedImg, null));
                productIdLabel.setText(String.valueOf(selected.getProductId()));
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initProductTableView() {
        productIdTableColumn.setCellValueFactory(new PropertyValueFactory("productId"));
        productTableView.setItems(loadData());
    }

    private ObservableList<Product> loadData() {
        ProductDao productDao = new ProductDao();
        return FXCollections.observableArrayList(productDao.readImage());
    }

    private void tableViewListeners() {
        productTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selected = (Product) newValue;
                showDetails();
            }
        });

        productTableView.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            final TableHeaderRow header = (TableHeaderRow) productTableView.lookup("TableHeaderRow");
            header.reorderingProperty().addListener((o, oldVal, newVal) -> header.setReordering(false));
        });
    }

    @FXML
    void exportExcel(ActionEvent event) {

    }

    @FXML
    void generatePdf(ActionEvent event) {

    }

}
