package boxgym.controller;

import static boxgym.Constant.*;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegisterController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private MaterialDesignIconView backArrow;

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private JFXButton registerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonProperties();
    }

    @FXML
    void backToLogin(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/boxgym/view/Login.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
        boxgym.Main.stage.setTitle(LOGIN_TITLE);
    }

    @FXML
    void register(ActionEvent event) {

    }

    private void buttonProperties() {
        registerButton.setDefaultButton(true);
        registerButton.setCursor(Cursor.HAND);
        backArrow.setCursor(Cursor.HAND);
    }
}
