package model;

import java.util.ArrayList;

public class GameModel {
    public enum ModelState {
        CURRENTLY_PLAYING,
        DRAW,
        WIN
    }

    private ArrayList<GameModelObserver> observers = new ArrayList<>();
    private int playerId;
    private int movesCount;
    private final char[][] board;
    private String message;
    ModelState currentState;

    public GameModel() {
        this.board = new char[3][3];
        this.movesCount = 9;
        this.playerId = 1;
        this.setCurrentState(ModelState.CURRENTLY_PLAYING);
    }

    public void addObserver(GameModelObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(int row, int column, char symbol, String message) {
        for(GameModelObserver observer: observers) {
            observer.update(row, column, symbol, message);
            if(getCurrentState() == ModelState.WIN) {
                observer.winner(row, column, symbol, message);
            }
        }
    }

    public void reset() {
        for(GameModelObserver observer: observers) {
            this.setCurrentState(ModelState.CURRENTLY_PLAYING);
            observer.resetGame();
        }
    }

    public void setCurrentState(ModelState currentState) {
        this.currentState = currentState;
    }

    public ModelState getCurrentState() {
        return currentState;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMovesCount() {
        return movesCount;
    }

    public void setMovesCount(int movesCount) {
        this.movesCount = movesCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void PlayMove(int x, int y) {

        if(getMovesCount() > 0){
            if(playerId % 2 != 0)
                board[x][y] = 'X';
            else
                board[x][y] = 'O';

            setMovesCount(--movesCount);

            if(isWinner(x, y)) {
                setMessage("Player " + playerId + " is Winner!");
                this.setCurrentState(ModelState.WIN);
                notifyObservers(x, y, board[x][y], getMessage());
            }
            else if(getMovesCount()==0) {
                setMessage("This is a draw!");
                this.setCurrentState(ModelState.DRAW);
                notifyObservers(x, y, board[x][y], getMessage());
            }
            else {
                if(playerId % 2 != 0) {
                    setPlayerId(2);
                    setMessage("'O':  Player " +getPlayerId());
                }
                else {
                    setPlayerId(1);
                    setMessage("'X':  Player " +getPlayerId());

                }
                notifyObservers(x, y, board[x][y], getMessage());
            }

        }

    }

    public boolean isWinner(int x, int y) {
        int countRow = 0, countCol = 0, countLDiag = 0, countRDiag = 0;
        char symbol;
        if(getPlayerId() % 2 != 0)
            symbol = 'X';
        else
            symbol = 'O';

        for(int i = 0; i < board.length; i++) {
            if(board[x][i] == symbol)
                countRow++;
            if(board[i][y] == symbol)
                countCol++;
            if(board[i][i] == symbol)
                countRDiag++;
            if(board[board.length-1-i][i] == symbol)
                countLDiag++;
        }

        return (countCol == board.length || countRow == board.length || countLDiag == board.length || countRDiag == board.length);
    }

    public void ResetModel() {
        movesCount = 9;
        setPlayerId(1);
        setMessage("");
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                board[i][j] = '\0';
            }
        }
        reset();
    }
}