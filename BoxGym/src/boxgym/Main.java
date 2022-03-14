package boxgym;

import static boxgym.Constant.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/boxgym/view/FirstScreen.fxml")); //Carrega FXML          
        Scene scene = new Scene(root); //Coloca o FXML em uma cena
        Main.stage = stage;
        stage.setResizable(false); //Não permitir a maximização da janela
        stage.setTitle(LOGIN_TITLE); //Título da janela
        stage.setScene(scene); //Coloca a cena em uma janela
        stage.show(); //Abre a janela
    }

    public static void main(String[] args) {
        launch(args);
    }

}
