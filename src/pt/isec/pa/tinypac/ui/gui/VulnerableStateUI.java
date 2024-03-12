package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.States;

public class VulnerableStateUI extends BorderPane {
    GameManager model;
    MazeUI mazeUI;
    Label l;

    public VulnerableStateUI(GameManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    public void createViews() {
        this.setStyle("-fx-background-color: #ff6262");
        this.setFocusTraversable(true);

        l = new Label("VULNERABLE STATE");
        l.setTextFill(Color.WHITE);
        setTop(l);

        mazeUI = new MazeUI(model);
        setCenter(mazeUI);

        setBottom(new InfoGame(model));
    }

    public void registerHandlers() {
        model.addPropertyChangeListener(evt ->
                Platform.runLater(() -> {
                    update();
                    mazeUI.imprime_maze(model); // Atualiza a exibição do tabuleiro
                    }));

        this.setOnKeyPressed(actionEvent ->{
            KeyCode keyCode = actionEvent.getCode();
            if(keyCode.isArrowKey()) {
                model.primeiro_movimento(keyCode);
            }
            //alteracao 1 - movimento PacMan por teclas
        });
    }

    public void update() {
        this.setVisible(model.getState() == States.VULNERAVEL);
    }
}