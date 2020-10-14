import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GameBoard {
    private char [][] gameBoard = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
    };
    private static HashMap componentMap = new HashMap<String, JButton>();
    private List<Integer> userChoices = new ArrayList<>();
    private List<Integer> cpuChoices = new ArrayList<>();

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public static void createComponentMap(JPanel panel) {
        Component[] buttons = panel.getComponents();
        for (Component button : buttons) {
            componentMap.put(button.getName(), button);
        }
    }

    private static Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        }
        else return null;
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

        boolean tie = false;
        for(List l : winCond) {
            if(userChoices.containsAll(l)) {
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
