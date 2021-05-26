package model;

public interface GameModelObserver {
    void update(int row, int column, char symbol, String message);
    void winner(int row, int column, char symbol, String message);
    void resetGame();
}
