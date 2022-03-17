package boxgym.controller;

import boxgym.helper.AlertHelper;
import boxgym.dao.UserDao;
import boxgym.helper.StageHelper;
import boxgym.model.User;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label registerLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonProperties();
    }

    @FXML
    void login(ActionEvent event) throws IOException {
        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            AlertHelper.customAlert("Login", "Não foi possível efetuar o login!", "Por favor, preencha todos os campos!", Alert.AlertType.WARNING);
        } else {
            String passwordSha256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(passwordTextField.getText());

            User user = new User(usernameTextField.getText(), passwordSha256);
            UserDao userDao = new UserDao();

            if (userDao.authenticate(user)) {
                loginButton.getScene().getWindow().hide();
                StageHelper sh = new StageHelper();
                sh.createMainScreenStage("/boxgym/view/MainScreen.fxml", "Tela Principal");
            } else {
                AlertHelper.customAlert("Login", "Não foi possível efetuar o login!", "Usuário e/ou senha inválido(s)!", Alert.AlertType.WARNING);
                usernameTextField.setText("");
                passwordTextField.setText("");
            }
        }
    }

    @FXML
    void register(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/boxgym/view/Register.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
        boxgym.Main.stage.setTitle("Cadastro");
    }

    private void buttonProperties() {
        loginButton.setDefaultButton(true);
        loginButton.setCursor(Cursor.HAND);
        registerLabel.setCursor(Cursor.HAND);
    }

}
