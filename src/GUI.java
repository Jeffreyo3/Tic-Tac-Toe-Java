import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class GUI {

    private static JPanel tttBoard;
    private static GameBoard board;
    private static HashMap componentMap;

    public static void main(String[] args) {

        board = new GameBoard();
        makeGameBoard();
        createComponentMap();

    }

    private static void computerTurn() {
        Random rNum = new Random();
        String cpuChoice = Integer.toString(rNum.nextInt(8));
        Component findButton = getComponentByName(cpuChoice);
        if (findButton == null) {
            throw new AssertionError();
        }
        String text = ((JButton)findButton).getText();

        while (!checkSelected(text)) {
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

    private static void createComponentMap() {
        componentMap = new HashMap<String, JButton>();
        Component[] buttons = tttBoard.getComponents();
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

    public static void makeGameBoard() {
        JPanel gui = new JPanel(new BorderLayout(3, 3));
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gui);
        frame.setTitle("Tic-Tac-Toe by Jeffrey-O");

        tttBoard = new JPanel(new GridLayout(0,3));
        tttBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(tttBoard);

        int count = 0;
        for (char[] rows : board.getGameBoard()) {
            for (char c : rows) {
                JButton b = new JButton();
                b.setBackground(Color.BLACK);
                b.setText(Character.toString(c));
                b.setForeground(Color.WHITE);
                b.setFont(new Font("Arial", Font.PLAIN, 40));
                b.setFocusPainted(false);
                b.setFocusable(false);
                b.setDefaultCapable(false);
                b.setName(Integer.toString(count));
                b.addActionListener(selectMove(b));
                tttBoard.add(b);

                count++;
            }
        }
        frame.setVisible(true);
    }

    private static boolean checkSelected (String bTxt) {
        return (!bTxt.contains("X") && !bTxt.contains("O"));
    }

    private static ActionListener selectMove(JButton b) {
        return e -> {
            if (checkSelected(b.getText())) {
                b.setText("X");
                b.setForeground(Color.CYAN);
                board.addUserChoice(Integer.parseInt(b.getName()));
                String result = board.checkWinner();
                if (result.length() > 0) {
                    System.out.println(result);
                } else {
                    computerTurn();
                }
            }
        };
    }
}
