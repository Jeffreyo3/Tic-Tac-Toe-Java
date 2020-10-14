import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private char [][] gameBoard = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
    };


    public char[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

//    public int[] getIntArray() {
//        return intArray;
//    }

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
