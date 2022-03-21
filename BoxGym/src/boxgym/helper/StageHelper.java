package boxgym.helper;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class StageHelper {
            
    public void createMainScreenStage(String path, String title) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        JMetro jMetro = new JMetro(root, Style.LIGHT);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public static void createAddOrUpdateStage(String title, Parent root) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    
    public static void openLoginStageAfterLogout(String title, Parent root) {
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
