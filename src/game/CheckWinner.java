package game;

import static game.Game.field;

public class CheckWinner {
    static boolean isGameOver(int n){
        int row = n - n % 3;
        if (field[row]== field[row+1] && field[row]== field[row+2]) return true;

        int column = n % 3;
        if (field[column]== field[column + 3])
            if (field[column]== field[column + 6]) return true;

        if (n % 2 != 0) return false;

        if (n % 4 == 0){
            if (field[0] == field[4] && field[0] == field[8]) return true;
            if (n != 4) return false;
        }
        return field[2] == field[4] && field[2] == field[6];
    }

    public static boolean isDraw() {
        for (int n : field) if (n==0) return false;
        return true;
    }
}
