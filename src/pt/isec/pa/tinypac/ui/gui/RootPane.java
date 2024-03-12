package pt.isec.pa.tinypac.ui.gui;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.tinypac.model.data.GameManager;

public class RootPane extends BorderPane {

    //ATTRIBUTES
    GameManager model;
    StackPane stackpane;
    BeginStateUI beginStateUI;
    InGameStateUI inGameStateUI;
    PausedStateUI pausedStateUI;
    VulnerableStateUI vulnerableStateUI;
    EndStateUI endStateUI;
    MainMenuUI mainMenuUI;



    //CONSTRUCTOR
    public RootPane(GameManager model) {
        this.model = model;

        createViews();
        registerHandlers();
        update();
    }


    //METHODS
    public void createViews() {
        beginStateUI = new BeginStateUI(model);
        inGameStateUI = new InGameStateUI(model);
        pausedStateUI = new PausedStateUI(model);
        vulnerableStateUI = new VulnerableStateUI(model);
        endStateUI = new EndStateUI(model);
        mainMenuUI = new MainMenuUI(model);

        stackpane = new StackPane(mainMenuUI , beginStateUI, inGameStateUI, pausedStateUI, vulnerableStateUI, endStateUI);


        beginStateUI.setVisible(false);
        inGameStateUI.setVisible(false);
        pausedStateUI.setVisible(false);
        vulnerableStateUI.setVisible(false);
        endStateUI.setVisible(false);

        setCenter(stackpane);
    }

    public void registerHandlers() {
        model.addPropertyChangeListener(evt -> { update(); });
    }

    public void update() {}

}