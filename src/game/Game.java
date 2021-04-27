package game;

public class Game {
    public static int [] field = {
        0,0,0,
        0,0,0,
        0,0,0};

    public static void game() {
        boolean b = true, isCurrentX = false;
        while (b) {
            isCurrentX = !isCurrentX;
            DrawField.drawField();
            System.out.println("mark " + (isCurrentX ? "X" : "O"));

            int n = TakeData.getNumber();
            field[n] = isCurrentX ? 1 : 2;
            b = !CheckWinner.isGameOver(n);

            if (CheckWinner.isDraw()){
                System.out.println("Draw");
                return;
            }
        }
        DrawField.drawField();

        System.out.println();
        System.out.println((isCurrentX ? "X" : "O") + " won the game!");
    }
}
