package boxgym;

import javafx.scene.control.Alert;

public class AlertUtil {
    public void warningAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
