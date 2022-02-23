package boxgym.controller;

import boxgym.AlertUtil;
import static boxgym.Constant.*;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private JFXButton loginButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginButton.setDefaultButton(true);
    }
           
    private void checkLogin() throws IOException {
        AlertUtil alert = new AlertUtil();
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(passwordTextField.getText());

        if (userTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {            
            alert.warningAlert(WARNING_ALERT_TITLE, WARNING_ALERT_HEADER, WARNING_ALERT_CONTENT_EMPTY);            
        } else if (userTextField.getText().equals(DEFAULT_LOGIN_USERNAME) && sha256hex.equals(DEFAULT_LOGIN_PASSWORD)) {
            loginButton.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(PATH_MAINSCREEN_VIEW));
            Scene scene = new Scene(root);
            Stage s1 = new Stage();
            s1.setResizable(false);
            s1.setTitle(TITLE_MAINSCREEN);
            s1.setScene(scene);
            s1.show();
        } else {
            alert.warningAlert(WARNING_ALERT_TITLE, WARNING_ALERT_HEADER, WARNING_ALERT_CONTENT_WRONG);
            userTextField.setText("");
            passwordTextField.setText("");
        }
    }

    @FXML
    void login() throws IOException {
        checkLogin();
    }

}
