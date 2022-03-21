package boxgym.controller;

import boxgym.dao.UserDao;
import boxgym.helper.AlertHelper;
import boxgym.helper.LimitedTextField;
import boxgym.model.User;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

public class RegisterController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private MaterialDesignIconView backArrow;

    @FXML
    private LimitedTextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private Button registerButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonProperties();
        registerInputRestrictions();
    }

    @FXML
    public void backToLogin() throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/boxgym/view/Login.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
        boxgym.Main.stage.setTitle("Login");
    }

    public void registerInputRestrictions() {
        usernameTextField.setValidationPattern("[a-zA-Z\\u00C0-\\u00FF0-9 ._-]", 32);
    }

    @FXML
    public void register(ActionEvent event) throws IOException {
        UserDao userDao = new UserDao();

        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || confirmPasswordTextField.getText().isEmpty()) {
            AlertHelper.customAlert("Cadastro", "Não foi possível efetuar o cadastro!", "Por favor, preencha todos os campos!", Alert.AlertType.WARNING);
        } else if (!passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
            AlertHelper.customAlert("Cadastro", "Não foi possível efetuar o cadastro!", "As senhas não coincidem!", Alert.AlertType.WARNING);
        } else if (userDao.checkDuplicate(usernameTextField.getText())) {
            usernameTextField.setText("");
            AlertHelper.customAlert("Cadastro", "Não foi possível efetuar o cadastro!", "Nome de usuário já cadastrado!", Alert.AlertType.WARNING);
        } else {
            String passwordSha256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(passwordTextField.getText());
            String confirmPasswordSha256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(confirmPasswordTextField.getText());

            User user = new User(usernameTextField.getText(), passwordSha256, confirmPasswordSha256);

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
