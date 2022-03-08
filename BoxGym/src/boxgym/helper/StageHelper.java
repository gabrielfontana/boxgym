package boxgym.helper;

import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageHelper {

    public void openAddStage(String path, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
//        stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> b, Boolean oldValue, Boolean newValue) {
//                if (newValue == false) {
//                    stage.close();
//                }
//            }
//        });
        stage.showAndWait();
    }
}