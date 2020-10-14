import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private char [][] gameBoard = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
    };
    private List<Integer> userChoices = new ArrayList<>();
    private List<Integer> cpuChoices = new ArrayList<>();


    public char[][] getGameBoard() {
        return gameBoard;
    }

    public String checkWinner() {
        List topRow = Arrays.asList(0, 1, 2);
        List midRow = Arrays.asList(3, 4, 5);
        List botRow = Arrays.asList(6, 7, 8);

        List topCol = Arrays.asList(0, 3, 6);
        List midCol = Arrays.asList(1, 4, 7);
        List botCol = Arrays.asList(2, 5, 8);

        List lTopRBot = Arrays.asList(0, 4, 8);
        List lBotLTop = Arrays.asList(6, 4, 2);

        List<List> winCond = new ArrayList<>();

        winCond.add(topRow);
        winCond.add(midRow);
        winCond.add(botRow);
        winCond.add(topCol);
        winCond.add(midCol);
        winCond.add(botCol);
        winCond.add(lTopRBot);
        winCond.add(lBotLTop);

        for(List l : winCond) {
            if(userChoices.containsAll(l)) {
                return "You WON!";
            } else if (cpuChoices.containsAll(l)) {
                return "CPU wins :(";
            } else if (userChoices.size() + cpuChoices.size() == 10 && !userChoices.containsAll(l) && !cpuChoices.containsAll(l)) {
                return "TIE";
            }
        }

        return "";
    }


    public List<Integer> getUserChoices() {
        return userChoices;
    }

    public void setUserChoices(List<Integer> userChoices) {
        this.userChoices = userChoices;
    }

    public void addUserChoice(Integer userChoice) {
        userChoices.add(userChoice);
    }

    public List<Integer> getCpuChoices() {
        return cpuChoices;
    }

    public void setCpuChoices(List<Integer> cpuChoices) {
        this.cpuChoices = cpuChoices;
    }

    public void addCpuChoice(Integer cpuChoice) {
        cpuChoices.add(cpuChoice);
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
                ", userChoices=" + userChoices +
                ", cpuChoices=" + cpuChoices +
                '}';
    }
}
