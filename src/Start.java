import controller.Controller;
import model.GameModel;
import view.GameView;

public class Start {
    public static void start() {
        GameModel gameModel = new GameModel();
        GameView gameView = new GameView(gameModel);
        Controller gameController = new Controller();

        gameController.setModel(gameModel);
        gameView.setActionListener(gameController);
    }
}