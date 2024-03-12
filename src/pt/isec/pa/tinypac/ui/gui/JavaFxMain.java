package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.data.GameManager;

public class JavaFxMain extends Application {


    //ATTRIBUTES
    GameManager model;


    //CONSTRUCTOR
    public JavaFxMain() {
        model = new GameManager();
    }


    //METHODS
    @Override
    public void start(Stage stage) throws Exception {
        newStageForTesting(stage, "PacMan");
        newStageForTesting(new Stage(), "PacMan Clone");
    }

    private void newStageForTesting(Stage stage, String title) {
        //cria a cena com o componente de base
        RootPane rootPane = new RootPane(model);

        Scene scene = new Scene(rootPane, 500, 600);
        stage.setTitle(title);
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }
}