import java.util.Arrays;

public class GameBoard {
    public char [][] gameBoard = {
            {' ', ' ', '|', ' ', ' ', '|', ' ', ' '},
            {'-', '-', '+', '-', '-', '+', '-', '-'},
            {' ', ' ', '|', ' ', ' ', '|', ' ', ' '},
            {'-', '-', '+', '-', '-', '+', '-', '-'},
            {' ', ' ', '|', ' ', ' ', '|', ' ', ' '},
    };


    public char[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(char[] row: gameBoard) {
            for(char c: row) {
                s.append(c);
            }
            s.append("\n");
        }
        return "GameBoard{" +
                "gameBoard=\n" + s +
                '}';
    }
}
