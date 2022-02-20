package boxgym.controller;

import static boxgym.Constants.*;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {
     
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private AnchorPane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            changeContentArea(PATH_HOME_VIEW);
        } catch (IOException ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void changeContentArea (String path) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource(path));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    
    @FXML
    void handleHome(ActionEvent event) throws IOException {
        changeContentArea(PATH_HOME_VIEW);
    }

    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        borderPane.getScene().getWindow().hide();        
        Parent root = FXMLLoader.load(getClass().getResource(PATH_LOGIN_VIEW));
        Scene scene = new Scene(root);
        Stage s1 = new Stage();
        s1.setResizable(false);
        s1.setTitle(TITLE_LOGIN);
        s1.setScene(scene);
        s1.show();
    }
    
    @FXML
    void handlePlans(ActionEvent event) throws IOException {
        changeContentArea(PATH_PLANS_VIEW);
    }
    
    @FXML
    void handleClients(ActionEvent event) throws IOException {
        changeContentArea(PATH_CLIENTS_VIEW);
    }
    
    @FXML
    void handleSuppliers(ActionEvent event) throws IOException {
        changeContentArea(PATH_SUPPLIERS_VIEW);
    }
    
    @FXML
    void handleEmployees(ActionEvent event) throws IOException {
        changeContentArea(PATH_EMPLOYEES_VIEW);
    }
    
    @FXML
    void handleBillsToPay(ActionEvent event) throws IOException {
        changeContentArea(PATH_BILLSTOPAY_VIEW);
    }
    
    @FXML
    void handleBillsToReceive(ActionEvent event) throws IOException {
        changeContentArea(PATH_BILLSTORECEIVE_VIEW);
    }
    
    @FXML
    void handleBanks(ActionEvent event) throws IOException {
        changeContentArea(PATH_BANKS_VIEW);
    }
    
    @FXML
    void handleSales(ActionEvent event) throws IOException {
        changeContentArea(PATH_SALES_VIEW);
    }
    
    @FXML
    void handleProducts(ActionEvent event) throws IOException {
        changeContentArea(PATH_PRODUCTS_VIEW);
    }
    
    @FXML
    void handleStock(ActionEvent event) throws IOException {
        changeContentArea(PATH_STOCK_VIEW);
    }
    
    @FXML
    void handleFiles(ActionEvent event) throws IOException {
        changeContentArea(PATH_FILES_VIEW);
    }
    
    @FXML
    void handleExercises(ActionEvent event) throws IOException {
        changeContentArea(PATH_EXERCISES_VIEW);
    }
    
    @FXML
    void handleMeasurements(ActionEvent event) throws IOException {
        changeContentArea(PATH_MEASUREMENTS_VIEW);
    }
    
    @FXML
    void handleAbout(ActionEvent event) throws IOException {
        changeContentArea(PATH_ABOUT_VIEW);
    }
    
}
