package boxgym.controller;

import static boxgym.Constant.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SuppliersController implements Initializable {

    @FXML
    private Button addSupplierButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void addSupplier(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(PATH_SUPPLIERS_ADD_VIEW));            
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(TITLE_SUPPLIERS_ADD);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
