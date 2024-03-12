package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.ui.gui.resources.ImageLoader;

public class Top5UI extends BorderPane {


    //ATTRIBUTES
    GameManager model;
    private Label titulo, top1, top2, top3, top4, top5;
    private Button btnMenu, btnExit;
    private ImageView gif_Pacman;


    //CONSTRUCTOR
    public Top5UI(GameManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }

    public void createViews() {

        //cria componentes
        titulo = new Label("TOP 5");
        titulo.setTextFill(Color.WHITE);
        titulo.setFont(new Font("Oliver", 25));

        top1 = new Label();
        top1.setTextAlignment(TextAlignment.JUSTIFY);
        top2 = new Label();
        top2.setTextAlignment(TextAlignment.JUSTIFY);
        top3 = new Label();
        top3.setTextAlignment(TextAlignment.JUSTIFY);
        top4 = new Label();
        top4.setTextAlignment(TextAlignment.JUSTIFY);
        top5 = new Label();
        top5.setTextAlignment(TextAlignment.JUSTIFY);

        gif_Pacman = new ImageView(ImageLoader.getImage("gifPacman.gif"));
        gif_Pacman.setFitHeight(200);
        gif_Pacman.setFitWidth(300);
        gif_Pacman.setFitWidth(this.getWidth());


        btnMenu = new Button("Voltar ao MENU");
        btnMenu.setMinWidth(100);
        btnMenu.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        btnExit = new Button("Sair do jogo");
        btnExit.setMinWidth(100);
        btnExit.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");

        //organiza a vista
        VBox vb1 = new VBox();
        vb1.setAlignment(Pos.CENTER);
        vb1.setBackground(Background.fill(Color.web("#27374D")));
        vb1.getChildren().setAll(titulo);

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(top1, top2, top3, top4, top5, gif_Pacman);

        VBox vb2 = new VBox();
        vb2.setSpacing(20);
        vb2.setAlignment(Pos.CENTER);
        vb2.getChildren().addAll(vb, gif_Pacman);

        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setAlignment(Pos.BOTTOM_CENTER);
        hb.getChildren().addAll(btnMenu, btnExit);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(vb1);
        borderPane.setCenter(vb2);
        borderPane.setBottom(hb);
        borderPane.setPadding(new Insets(30));

        this.setStyle("-fx-background-color: #DDE6ED");

        setCenter(borderPane);
    }

    public void registerHandlers() {
        model.addPropertyChangeListener(evt ->
                Platform.runLater(() -> {
                    update();
                }));

        btnMenu.setOnMouseClicked(actionEvent -> {
            setCenter(new MainMenuUI(model));
        });

        btnExit.setOnMouseClicked(actionEvent -> {
            setCenter(new ExitUI(model));
        });

        //efeitos sobre os botões
        btnMenu.setOnMouseEntered(e -> {
            btnMenu.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: #27374D;");
        });
        btnMenu.setOnMouseExited(e -> {
            btnMenu.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
        });

        btnExit.setOnMouseEntered(e -> {
            btnExit.setStyle("-fx-background-color: #9DB2BF; -fx-text-fill: #27374D;");
        });
        btnExit.setOnMouseExited(e -> {
            btnExit.setStyle("-fx-background-color: #27374D; -fx-text-fill: white;");
        });
    }

    public void update() {
        top1.setText("1. " + model.getTop(0).getNomeJogador() + " pontos: " + model.getTop(0).getPontuacao());
        top2.setText("2. " + model.getTop(1).getNomeJogador() + " pontos: " + model.getTop(1).getPontuacao());
        top3.setText("3. " + model.getTop(2).getNomeJogador() + " pontos: " + model.getTop(2).getPontuacao());
        top4.setText("4. " + model.getTop(3).getNomeJogador() + " pontos: " + model.getTop(3).getPontuacao());
        top5.setText("5. " + model.getTop(4).getNomeJogador() + " pontos: " + model.getTop(4).getPontuacao());
    }
}