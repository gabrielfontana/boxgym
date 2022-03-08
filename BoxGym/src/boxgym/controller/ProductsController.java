package boxgym.controller;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProductsController implements Initializable {

    @FXML
    private TextField searchBox;

    @FXML
    private TableView<?> productTableView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void addProduct(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/boxgym/view/ProductsAdd.fxml"));

            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Adicionando Produto");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            //initProductTableView();
            //productTableView.getSelectionModel().selectLast();
        } catch (IOException ex) {
            Logger.getLogger(SuppliersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void updateProduct(ActionEvent event) {

    }

    @FXML
    void deleteProduct(ActionEvent event) {

    }

    @FXML
    void exportExcel(ActionEvent event) {

    }

    @FXML
    void generatePdf(ActionEvent event) {

    }

}
