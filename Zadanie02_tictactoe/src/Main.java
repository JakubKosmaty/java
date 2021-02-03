public class Main {
    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame = new TicTacToeGame();

        ticTacToeGame.setO(2,2);
        ticTacToeGame.setSize(0);
        ticTacToeGame.setSize(3);
        ticTacToeGame.setSize(0);
        ticTacToeGame.setO(-1,0);
        ticTacToeGame.setX(1,1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ticTacToeGame.setX(j, i);
                ticTacToeGame.setO(j, i);
            }
        }

        ticTacToeGame.setSize(4);
        ticTacToeGame.setSize(10);
        ticTacToeGame.printBoard();

        System.out.println(ticTacToeGame.get(1, 1));
        System.out.println(ticTacToeGame.get(3, 9));
    }
}
