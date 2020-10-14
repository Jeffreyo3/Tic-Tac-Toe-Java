import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class GameBoard {
    private final char [][] gameBoard = new char[][]{
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
    };

    private static final HashMap<String, JButton> componentMap = new HashMap<>();

    private final List<Integer> userChoices = new ArrayList<>();
    private final List<Integer> cpuChoices = new ArrayList<>();

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public static void createComponentMap(JPanel panel) {
        Component[] buttons = panel.getComponents();
        for (Component button : buttons) {
            componentMap.put(button.getName(), (JButton)button);
        }
    }

    private static Component getComponentByName(String name) {
        return componentMap.getOrDefault(name, null);
    }

    public String checkWinner() {
        List<Integer> topRow = Arrays.asList(0, 1, 2);
        List<Integer> midRow = Arrays.asList(3, 4, 5);
        List<Integer> botRow = Arrays.asList(6, 7, 8);

        List<Integer> topCol = Arrays.asList(0, 3, 6);
        List<Integer> midCol = Arrays.asList(1, 4, 7);
        List<Integer> botCol = Arrays.asList(2, 5, 8);

        List<Integer> lTopRBot = Arrays.asList(0, 4, 8);
        List<Integer> lBotLTop = Arrays.asList(6, 4, 2);

        List<List<Integer>> winCond = new ArrayList<>();

        winCond.add(topRow);
        winCond.add(midRow);
        winCond.add(botRow);
        winCond.add(topCol);
        winCond.add(midCol);
        winCond.add(botCol);
        winCond.add(lTopRBot);
        winCond.add(lBotLTop);

        boolean tie = false;
        for(List<Integer> l : winCond)
            if (userChoices.containsAll(l)) {
                return "You WON!";
            } else if (cpuChoices.containsAll(l)) {
                return "CPU wins :(";
            } else if (userChoices.size() + cpuChoices.size() == 9) {
                tie = true;
            }

        if(tie) {
            return "TIE!";
        } else {
            return "";
        }
    }


    public void addUserChoice(Integer userChoice) {
        userChoices.add(userChoice);
    }

    public void addCpuChoice(Integer cpuChoice) {
        cpuChoices.add(cpuChoice);
    }

    public boolean checkSelected (String bTxt) {
        return (!bTxt.contains("X") && !bTxt.contains("O"));
    }

    public static void computerTurn(GameBoard board) {
        Random rNum = new Random();
        String cpuChoice = Integer.toString(rNum.nextInt(8));
        Component findButton = getComponentByName(cpuChoice);
        if (findButton == null) {
            throw new AssertionError();
        }
        String text = ((JButton)findButton).getText();

        while (!board.checkSelected(text)) {
            cpuChoice = Integer.toString(rNum.nextInt(8));
            findButton = getComponentByName(cpuChoice);
            assert findButton != null;
            text = ((JButton)findButton).getText();
        }
        ((JButton)findButton).setText("O");
        findButton.setForeground(Color.ORANGE);
        board.addCpuChoice(Integer.parseInt(cpuChoice));
        String result = board.checkWinner();
        if (result.length() > 0) {
            JOptionPane.showMessageDialog(null, result);
            System.out.println(result);
        }
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
