package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.States;

public class BeginStateUI extends BorderPane {
    private GameManager model;
    private char [][] maze;
    private Label teste;

    public BeginStateUI(GameManager model) {
        this.model = model;
        maze = model.getGameMaze();
        this.setFocusTraversable(true);

        createViews();
        registerHandlers();
        update();
    }

    public void createViews() {
        //organiza a vista

        teste = new Label("BEGIN STATE");

        VBox vb = new VBox();
        vb.getChildren().addAll(teste);
        vb.setAlignment(Pos.CENTER);

        setTop(vb);
        setCenter(new MazeUI(model));
        setBottom(new InfoGame(model));
    }

    public void registerHandlers() {
        model.addPropertyChangeListener(evt ->
                Platform.runLater(() -> {
                    update();
                }));

        this.setOnKeyPressed(actionEvent ->{
            KeyCode keyCode = actionEvent.getCode();
            String keyText = keyCode.getName();
            teste.setText("Key pressed: " + keyText);
            teste.getParent().requestLayout();
            if(keyCode.isArrowKey())
                model.primeiro_movimento(keyCode);
        });
    }

    public void update() {
        this.setVisible(model.getState() == States.INICIO);
    }
}