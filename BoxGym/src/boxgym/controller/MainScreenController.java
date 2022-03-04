package boxgym.controller;

import static boxgym.Constant.*;
import boxgym.helper.AlertHelper;
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
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane contentArea;

    AlertHelper alert = new AlertHelper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            changeContentArea(HOME_VIEW);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void changeContentArea(String path) throws IOException {
        AnchorPane anchorPane = (AnchorPane) FXMLLoader.load(getClass().getResource(path));
        contentArea.getChildren().setAll(anchorPane);

        /*Parent fxml = FXMLLoader.load(getClass().getResource(path));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);*/
    }

    @FXML
    void handleHome(ActionEvent event) throws IOException {
        changeContentArea(HOME_VIEW);
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        alert.confirmationAlert("", "Tem certeza que deseja sair do sistema?", "");

        if (alert.getResult().get() == ButtonType.YES) {
            borderPane.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(LOGIN_VIEW));
            Scene scene = new Scene(root);
            Stage s1 = new Stage();
            s1.setResizable(false);
            s1.setTitle(LOGIN_TITLE);
            s1.setScene(scene);
            s1.show();
        }
    }

    @FXML
    void handlePlans(ActionEvent event) throws IOException {
        changeContentArea(PLANS_VIEW);
    }

    @FXML
    void handleClients(ActionEvent event) throws IOException {
        changeContentArea(CLIENTS_VIEW);
    }

    @FXML
    void handleSuppliers(ActionEvent event) throws IOException {
        changeContentArea(SUPPLIERS_VIEW);
    }

    @FXML
    void handleEmployees(ActionEvent event) throws IOException {
        changeContentArea(EMPLOYEES_VIEW);
    }

    @FXML
    void handleBillsToPay(ActionEvent event) throws IOException {
        changeContentArea(BILLSTOPAY_VIEW);
    }

    @FXML
    void handleBillsToReceive(ActionEvent event) throws IOException {
        changeContentArea(BILLSTORECEIVE_VIEW);
    }

    @FXML
    void handleBanks(ActionEvent event) throws IOException {
        changeContentArea(BANKS_VIEW);
    }

    @FXML
    void handleSales(ActionEvent event) throws IOException {
        changeContentArea(SALES_VIEW);
    }

    @FXML
    void handleProducts(ActionEvent event) throws IOException {
        changeContentArea(PRODUCTS_VIEW);
    }

    @FXML
    void handleStock(ActionEvent event) throws IOException {
        changeContentArea(STOCK_VIEW);
    }

    @FXML
    void handleFiles(ActionEvent event) throws IOException {
        changeContentArea(FILES_VIEW);
    }

    @FXML
    void handleExercises(ActionEvent event) throws IOException {
        changeContentArea(EXERCISES_VIEW);
    }

    @FXML
    void handleMeasurements(ActionEvent event) throws IOException {
        changeContentArea(MEASUREMENTS_VIEW);
    }

    @FXML
    void handleAbout(ActionEvent event) throws IOException {
        changeContentArea(ABOUT_VIEW);
    }

}
