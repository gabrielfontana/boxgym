package boxgym.controller;

import static boxgym.Constant.*;
import boxgym.dao.UserDao;
import boxgym.helper.AlertHelper;
import boxgym.model.User;
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
import javafx.scene.control.Alert;
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
    private TextField usernameTextField;

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
    public void backToLogin() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/boxgym/view/Login.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
        boxgym.Main.stage.setTitle(LOGIN_TITLE);
    }

    @FXML
    public void register(ActionEvent event) throws IOException {
        if(usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || confirmPasswordTextField.getText().isEmpty()) {
            AlertHelper.customAlert("Cadastro", "Não foi possível efetuar o cadastro!", "Por favor, preencha todos os campos!", Alert.AlertType.WARNING);
        } else if(!passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            AlertHelper.customAlert("Cadastro", "Não foi possível efetuar o cadastro!", "As senhas não coincidem!", Alert.AlertType.WARNING);
        } else{            
            String passwordSha256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(passwordTextField.getText());
            String confirmPasswordSha256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(confirmPasswordTextField.getText());
            
            User user = new User(usernameTextField.getText(), passwordSha256, confirmPasswordSha256);
            
            UserDao userDao = new UserDao();
            userDao.create(user);
            AlertHelper.customAlert("Cadastro", "O cadastro foi realizado com sucesso!", "", Alert.AlertType.INFORMATION);
            backToLogin();
        }
    }

    private void buttonProperties() {
        registerButton.setDefaultButton(true);
        registerButton.setCursor(Cursor.HAND);
        backArrow.setCursor(Cursor.HAND);
    }
}
