package boxgym;

import static boxgym.Constants.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(PATH_LOGIN_VIEW)); //Carrega FXML          
        Scene scene = new Scene(root); //Coloca o FXML em uma cena
        primaryStage.setResizable(false); //Não permitir a maximização da janela
        primaryStage.setTitle(TITLE_LOGIN); //Título da janela
        primaryStage.setScene(scene); //Coloca a cena em uma janela
        primaryStage.show(); //Abre a janela
    }

    public static void main(String[] args) {
        launch(args);
    }

}
