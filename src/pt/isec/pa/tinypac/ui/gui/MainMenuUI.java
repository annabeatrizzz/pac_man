package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pt.isec.pa.tinypac.model.data.GameManager;
import javafx.scene.image.ImageView;
import pt.isec.pa.tinypac.ui.gui.resources.ImageLoader;

public class MainMenuUI extends BorderPane {

    //ATTRIBUTES
    GameManager model;
    Button btnStart, btnTop5, btnExit;
    Label titulo, uni, curso, uc, tp;
    ImageView logo_isec;


    //CONSTRUCTOR
    public MainMenuUI(GameManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }


    //METHODS
    public void createViews() {

        //cria componentes
        titulo = new Label("MENU");
        titulo.setFont(new Font("Oliver", 25));
        titulo.setTextFill(Color.web("#27374D"));

        btnStart = new Button("JOGAR");
        btnStart.setMinWidth(130);
        btnStart.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        btnTop5 = new Button("TOP 5");
        btnTop5.setMinWidth(130);
        btnTop5.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        btnExit = new Button("SAIR");
        btnExit.setMinWidth(130);
        btnExit.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        uni = new Label("DEIS-ISEC-IPC");
        uni.setTextFill(Color.web("#27374D"));

        curso = new Label("LEI-CE");
        curso.setTextFill(Color.web("#27374D"));

        uc = new Label("Programação Avançada - 2022-2023");
        uc.setTextFill(Color.web("#27374D"));

        tp = new Label("Trabalho Acadêmico feito por Anna Beatriz Yabe - 2021132515");
        tp.setTextFill(Color.web("#27374D"));

        logo_isec = new ImageView(ImageLoader.getImage("ISEC.png"));
        logo_isec.setFitHeight(50);
        logo_isec.setFitWidth(100);

        //organiza a vista
        this.setStyle("-fx-background-color: #9DB2BF");

        VBox vb_logo_isec = new VBox();
        vb_logo_isec.setAlignment(Pos.CENTER);
        vb_logo_isec.getChildren().add(logo_isec);

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(20));
        vb.getChildren().addAll(titulo, btnStart, btnTop5, btnExit);

        VBox vb2 = new VBox();
        vb2.setSpacing(10);
        vb2.setAlignment(Pos.CENTER);
        vb2.getChildren().addAll(uni, curso, uc, tp);

        VBox vb_final = new VBox();
        vb_final.setSpacing(30);
        vb_final.setAlignment(Pos.CENTER);
        vb_final.getChildren().addAll(vb_logo_isec, vb, vb2);

        setCenter(vb_final);

    }

    public void registerHandlers() {
        btnStart.setOnMouseClicked(actionEvent -> {
            if(model.isTemJogoSalvo())
                setCenter(new ResumeUI(model));
            else
                setCenter(new BeginStateUI(model));
        });

        btnTop5.setOnMouseClicked(actionEvent -> {
            setCenter(new Top5UI(model));
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

        btnTop5.setOnMouseEntered(e -> {
            btnTop5.setStyle("-fx-background-color: #DDE6ED; -fx-text-fill: #27374D;");
        });
        btnTop5.setOnMouseExited(e -> {
            btnTop5.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
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