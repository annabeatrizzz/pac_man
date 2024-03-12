package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.model.fsm.States;

public class PausedStateUI extends BorderPane {
    GameManager model;
    private Label l;
    private Button btnPausa, btnSalvarJogo, btnExit;

    public PausedStateUI(GameManager model) {
        this.model = model;
        createViews();
        registerHandlers();
        update();
    }

    public void createViews() {
        this.setStyle("-fx-background-color: #27374D");

        l = new Label("PAUSED STATE");
        l.setTextFill(Color.WHITE);

        btnPausa = new Button();
        btnPausa.setText("UNPAUSE");
        btnPausa.setMinWidth(130);
        btnPausa.setTextFill(Color.INDIANRED);
        btnPausa.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: white;");

        btnSalvarJogo = new Button();
        btnSalvarJogo.setText("SALVAR PROGRESSO");
        btnSalvarJogo.setMinWidth(130);
        btnSalvarJogo.setTextFill(Color.INDIANRED);
        btnSalvarJogo.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: white;");

        btnExit = new Button();
        btnExit.setText("SAIR DO JOGO");
        btnExit.setMinWidth(130);
        btnExit.setTextFill(Color.INDIANRED);
        btnExit.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: white;");

        VBox vb = new VBox();
        vb.getChildren().addAll(l, btnPausa, btnSalvarJogo, btnExit);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);

        setCenter(vb);
    }

    public void registerHandlers() {
        model.addPropertyChangeListener(evt ->
                Platform.runLater(() -> {
                    update();
                }));

        btnPausa.setOnMouseClicked(actionEvent -> {
            model.pausa_jogo();
        });

        btnSalvarJogo.setOnMouseClicked(actionEvent -> {
            model.saveGame();
        });

        btnExit.setOnMouseClicked(actionEvent -> {
            setCenter(new ExitUI(model));
        });

        //efeitos sobre os botões
        btnPausa.setOnMouseEntered(e -> {
            btnPausa.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: indianred;");
        });
        btnPausa.setOnMouseExited(e -> {
            btnPausa.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: white;");
        });

        btnSalvarJogo.setOnMouseEntered(e -> {
            btnSalvarJogo.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: indianred;");
        });
        btnSalvarJogo.setOnMouseExited(e -> {
            btnSalvarJogo.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: white;");
        });

        btnExit.setOnMouseEntered(e -> {
            btnExit.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: indianred;");
        });
        btnExit.setOnMouseExited(e -> {
            btnExit.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: white;");
        });
    }

    public void update() {
        this.setVisible(model.getState() == States.EM_PAUSA);
    }
}