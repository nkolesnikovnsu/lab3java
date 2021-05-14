import controller.Controller;
import model.GameModel;
import view.GameView;

public class Start {
    public static void start() {
        GameView gameView = new GameView();
        GameModel gameModel = new GameModel();
        Controller gameController = new Controller();

        gameModel.registerView(gameView);
        gameController.setModel(gameModel);
        gameView.setActionListener(gameController);
    }
}
