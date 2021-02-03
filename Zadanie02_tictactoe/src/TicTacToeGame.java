import java.util.ArrayList;

public class TicTacToeGame implements TicTacToeInterface {
    private final ArrayList<Integer> board = new ArrayList<>();

    private int widthBord = 0;
    private int lastPlayer = 0;

    private enum STATE {
        NONE,
        PLAYER_O,
        PLAYER_X
    }

    public void printBoard() {
        for (int i = 0; i < this.widthBord; i++) {
            for (int j = 0; j < this.widthBord; j++) {
                System.out.print(board.get(i + j * this.widthBord));
            }
            System.out.print("\n");
        }
    }

    private void fillBoard() {
        for (int i = 0; i < this.widthBord * this.widthBord; i++) {
            board.add(0);
        }
    }

    private void addToBoard(int newSize) {
        ArrayList<Integer> boardClone = (ArrayList<Integer>) board.clone();

        int fieldToAdd = newSize * newSize - this.widthBord * this.widthBord;
        for (int i = 0; i < fieldToAdd; i++) {
            board.add(0);
        }

        for (int i = 0; i < this.board.size(); i++) {
            this.board.set(i, 0);
        }

        for (int i = 0; i < this.widthBord; i++) {
            for (int j = 0; j < this.widthBord; j++) {
                board.set(i + j * newSize, boardClone.get(i + j * this.widthBord));
            }
        }

        this.widthBord = newSize;
    }

    @Override
    public void setSize(int newSize) {
        if (this.widthBord == 0) {
            this.widthBord = newSize;
            this.fillBoard();
        } else if (newSize > this.widthBord) {
            this.addToBoard(newSize);
        }
    }

    private boolean correctField(int index, int move) {
        return this.widthBord != 0
                && this.lastPlayer != move
                && index <= this.widthBord * this.widthBord
                && this.board.get(index) == 0
                && index >= 0;
    }

    @Override
    public void setO(int col, int row) {
        int index = row + col * this.widthBord;
        if (!correctField(index, STATE.PLAYER_O.ordinal())) {
            return;
        }

        this.lastPlayer = STATE.PLAYER_O.ordinal();
        this.board.set(index, STATE.PLAYER_O.ordinal());
    }

    @Override
    public void setX(int col, int row) {
        if (row < 0 || col < 0) {
            return;
        }

        int index = row + col * this.widthBord;
        if (!correctField(index, STATE.PLAYER_X.ordinal())) {
            return;
        }

        this.lastPlayer = STATE.PLAYER_X.ordinal();
        this.board.set(index, STATE.PLAYER_X.ordinal());
    }

    @Override
    public String get(int col, int row) {
        if (this.widthBord == 0) {
            return "";
        }

        if (row < 0 || col < 0) {
            return "";
        }

        int index = row + col * this.widthBord;

        if (index > this.widthBord * this.widthBord) {
            return "";
        }

        if (this.board.get(index) == STATE.PLAYER_O.ordinal()) {
            return "O";
        }

        if (this.board.get(index) == STATE.PLAYER_X.ordinal()) {
            return "X";
        }

        return "";
    }
}
