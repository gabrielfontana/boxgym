package boxgym.controller;

import boxgym.dao.ProductDao;
import boxgym.model.Product;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    private String imagePath;
    private byte[] imageBytes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void chooseImage(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagem", "*.jpg", "*.png", "*.jpeg"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null) {
            imagePath = file.getAbsolutePath();
            productImage.setImage(new Image("file:///" + imagePath));
            
            try {
                //max_allowed_packet=32M
                FileInputStream fis = new FileInputStream(new File(imagePath));
                imageBytes = IOUtils.toByteArray(fis);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ProductsAddController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ProductsAddController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void save(ActionEvent event) {
        Product product = new Product(imageBytes);
        ProductDao productDao = new ProductDao();
        if (productDao.create(product)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    @FXML
    void clear(ActionEvent event) {

    }

}
