package boxgym.helper;

import javafx.scene.control.TextField;

public class TextValidationHelper {

    public static void onlyNumbers(TextField text) {
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) {
                return;
            }
            text.setText(newValue.replaceAll("[^\\d]", ""));
        });
    }

    public static void onlyLetters(TextField text) {
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("[a-zA-Z ]")) {
                return;
            }
            text.setText(newValue.replaceAll("[^a-zA-Z ]", ""));
        });
    }
}
