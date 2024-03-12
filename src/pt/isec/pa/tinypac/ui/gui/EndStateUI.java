package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.States;

public class EndStateUI extends BorderPane {

    GameManager model;
    Label l, resultado;
    RegisterPlayerUI registerPlayerUI = null;
    private Button btnNextLevel;

    public EndStateUI(GameManager model) {
        this.model = model;

        //if(!model.isTOP5atualizado()) {
            registerPlayerUI = new RegisterPlayerUI(model);
        //}

        createViews();
        registerHandlers();
        update();
    }

    public void createViews() {
        this.setStyle("-fx-background-color: #27374D");

        l = new Label("END STATE");
        l.setTextFill(Color.WHITE);
        l.setAlignment(Pos.CENTER);

        resultado = new Label();
        resultado.setTextFill(Color.WHITE);
        resultado.setAlignment(Pos.CENTER);

        btnNextLevel = new Button();
        btnNextLevel.setText("PRÓXIMO NÍVEL");
        btnNextLevel.setMinWidth(130);
        btnNextLevel.setTextFill(Color.WHITE);
        btnNextLevel.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: white;");

        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.getChildren().addAll(l, btnNextLevel);
        hb.setAlignment(Pos.CENTER);

        resultado.setAlignment(Pos.CENTER);

        this.setTop(hb);
        this.setBottom(resultado);
        this.setCenter(registerPlayerUI);
    }

    public void registerHandlers() {
        model.addPropertyChangeListener(evt ->
                Platform.runLater(() -> {
                    update();
                }));

        btnNextLevel.setOnMouseClicked(actionEvent -> {
            //model.passarDeNivel();
        });
    }

    public void update() {
        this.setVisible(model.getState() == States.FIM);

        resultado.setText(model.msgFinal());
    }
}