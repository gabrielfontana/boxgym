package boxgym.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProductsAddController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ImageView productPhoto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void choosePhoto(MouseEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagem", "*.jpg", "*.png", "*.jpeg"));
        File file = chooser.showOpenDialog(new Stage());

        if (file != null) {
            productPhoto.setImage(new Image("file:///" + file.getAbsolutePath()));
        }
    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void clear(ActionEvent event) {

    }

}
