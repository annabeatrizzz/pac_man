import pt.isec.pa.tinypac.model.data.GameManager;
import pt.isec.pa.tinypac.ui.gui.JavaFxMain;
import pt.isec.pa.tinypac.ui.text.MenuText;

import javafx.application.Application;

public class Main {

    public static GameManager model = null;

    public static void main(String[] args) {
        MenuText.menu();
        model = new GameManager();
        Application.launch(JavaFxMain.class, args);
    }
}