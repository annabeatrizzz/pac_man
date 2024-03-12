package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageLoader;

public class ResumeUI extends BorderPane {

    //ATTRIBUTES
    GameManager model;
    Button btnStart, btnResumeGame, btnMenu, btnExit;


    //CONSTRUCTOR
    public ResumeUI(GameManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }


    //METHODS
    public void createViews() {

        //cria componentes
        btnStart = new Button("Iniciar novo jogo");
        btnStart.setMinWidth(130);
        btnStart.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        btnResumeGame = new Button("Retomar o jogo salvo");
        btnResumeGame.setMinWidth(130);
        btnResumeGame.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        btnMenu = new Button("MENU");
        btnMenu.setMinWidth(130);
        btnMenu.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        btnExit = new Button("SAIR");
        btnExit.setMinWidth(130);
        btnExit.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        //organiza a vista
        this.setStyle("-fx-background-color: #9DB2BF");

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(20));
        vb.getChildren().addAll(btnStart, btnResumeGame, btnMenu, btnExit);

        setCenter(vb);

    }

    public void registerHandlers() {
        btnStart.setOnMouseClicked(actionEvent -> {
            setCenter(new BeginStateUI(model));
            model.deletaJogoSalvo();
        });

        btnResumeGame.setOnMouseClicked(actionEvent -> {
            model.loadGame();
            model.deletaJogoSalvo();
        });

        btnMenu.setOnMouseClicked(actionEvent -> {
            setCenter(new MainMenuUI(model));
        });

        btnExit.setOnMouseClicked(actionEvent -> {
            setCenter(new ExitUI(model));
        });

        //efeitos sobre os botões
        btnStart.setOnMouseEntered(e -> {
            btnStart.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        });
        btnStart.setOnMouseExited(e -> {
            btnStart.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
        });

        btnResumeGame.setOnMouseEntered(e -> {
            btnResumeGame.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        });
        btnResumeGame.setOnMouseExited(e -> {
            btnResumeGame.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
        });

        btnMenu.setOnMouseEntered(e -> {
            btnMenu.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        });
        btnMenu.setOnMouseExited(e -> {
            btnMenu.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
        });

        btnExit.setOnMouseEntered(e -> {
            btnExit.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        });
        btnExit.setOnMouseExited(e -> {
            btnExit.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
        });
    }

    public void update() {
    }
}