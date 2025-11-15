package javaPRJ;

import javax.swing.*;
import javaPRJ.controller.GameController;
import javaPRJ.model.GameModel;
import javaPRJ.view.GameView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameModel model = new GameModel();
            GameView view = new GameView();
            GameController controller = new GameController(model, view);

            view.setVisible(true);
        });
    }
}
