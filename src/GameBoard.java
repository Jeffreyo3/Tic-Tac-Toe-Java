import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
//    private char [][] gameBoard = {
//            {' ', '|', ' ', '|', ' '},
//            {'-', '+', '-', '+', '-'},
//            {' ', '|', ' ', '|', ' '},
//            {'-', '+', '-', '+', '-'},
//            {' ', '|', ' ', '|', ' '},
//    };
    /*
    spaces at:
    [0][0], [0][2], [0][4]
    [2][0], [2][2], [2][4]
    [4][0], [4][2], [4][4]

    0, 2, 4, 10, 12, 14, 20, 22, 24
    */
//    private int[] intArray = new int[]{0, 2, 4, 10, 12, 14, 20, 22, 24};

    private char [][] gameBoard = {
            {' ', ' ', ' '},
            {' ', 'O', ' '},
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
