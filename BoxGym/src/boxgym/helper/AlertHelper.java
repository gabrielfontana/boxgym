package boxgym.helper;

import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import jfxtras.styles.jmetro.FlatAlert;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class AlertHelper {

    private Optional<ButtonType> result;

    public Optional<ButtonType> getResult() {
        return result;
    }

    public void setResult(Optional<ButtonType> result) {
        this.result = result;
    }

    public static void customAlert(Alert.AlertType type, String headerText, String contentText) {
        FlatAlert alert = new FlatAlert(type);
        Scene scene = (Scene) alert.getDialogPane().getScene();
        JMetro jMetro = new JMetro(scene, Style.LIGHT);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void confirmationAlert(String headerText, String contentText) {
        FlatAlert alert = new FlatAlert(Alert.AlertType.CONFIRMATION);
        Scene scene = (Scene) alert.getDialogPane().getScene();
        JMetro jMetro = new JMetro(scene, Style.LIGHT);
        alert.setTitle(null);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

        Button yesButton = (Button) alert.getDialogPane().lookupButton(ButtonType.YES);
        yesButton.setDefaultButton(false);

        Button noButton = (Button) alert.getDialogPane().lookupButton(ButtonType.NO);
        noButton.setDefaultButton(true);

        result = alert.showAndWait();
    }
}
