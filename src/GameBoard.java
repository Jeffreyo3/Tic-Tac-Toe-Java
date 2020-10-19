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
    List<List<Integer>> winCond = new ArrayList<>();



    public GameBoard() {
        List<Integer> topRow = Arrays.asList(0, 1, 2);
        List<Integer> midRow = Arrays.asList(3, 4, 5);
        List<Integer> botRow = Arrays.asList(6, 7, 8);

        List<Integer> topCol = Arrays.asList(0, 3, 6);
        List<Integer> midCol = Arrays.asList(1, 4, 7);
        List<Integer> botCol = Arrays.asList(2, 5, 8);

        List<Integer> lTopRBot = Arrays.asList(0, 4, 8);
        List<Integer> lBotLTop = Arrays.asList(6, 4, 2);

        this.winCond.add(topRow);
        this.winCond.add(midRow);
        this.winCond.add(botRow);
        this.winCond.add(topCol);
        this.winCond.add(midCol);
        this.winCond.add(botCol);
        this.winCond.add(lTopRBot);
        this.winCond.add(lBotLTop);
    }

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
        boolean tie = false;
        for(List<Integer> l : winCond) {
            if (userChoices.containsAll(l)) {
                return "You WON!";
            } else if (cpuChoices.containsAll(l)) {
                return "CPU wins :(";
            } else if (userChoices.size() + cpuChoices.size() == 9) {
                tie = true;
            }
        }

        if(tie) {
            return "TIE!";
        } else {
            return "";
        }
    }
    public int cpuDefense(GameBoard board) {
        System.out.println("===========");
        for(List<Integer> l: board.winCond) {
            System.out.println("CURRENT LIST: " + l);
            int count = 0;
            int missing = 0;
            for(Integer i:l){
                if(board.userChoices.contains(i)) {
                    System.out.println("USER " + board.userChoices + "| ITEM " + i);
                    count++;
                } else if (!board.cpuChoices.contains(i)) {
                    System.out.println("CPU " + board.cpuChoices + "| ITEM " + i + "| COUNT " + count);
                    missing = i;
                }
            }
            if(count == 2) {
                if (!board.userChoices.contains(missing) && !board.cpuChoices.contains(missing)) {
                    return missing;
                }
            }
        }
        return -1;
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
        String cpuChoice = Integer.toString(board.cpuDefense(board));
        Component findButton;

        if (cpuChoice.equals("-1")) {
            Random rNum = new Random();
            cpuChoice = Integer.toString(rNum.nextInt(8));
            findButton = getComponentByName(cpuChoice);
            if (findButton == null) {
                throw new AssertionError();
            }
            String text = ((JButton) findButton).getText();

            while (!board.checkSelected(text)) {
                cpuChoice = Integer.toString(rNum.nextInt(8));
                findButton = getComponentByName(cpuChoice);
                assert findButton != null;
                text = ((JButton) findButton).getText();
            }
        } else {
            findButton = getComponentByName(cpuChoice);
        }
        setButtonText(board, cpuChoice, findButton);
    }

    private static void setButtonText(GameBoard board, String cpuChoice, Component findButton) {
        ((JButton) findButton).setText("O");
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
