import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class GUI {

    private static JPanel gui;
    private static JFrame frame;
    private static JPanel tttBoard;
    private static GameBoard board;
    private static HashMap componentMap;

    public static void main(String[] args) {

        board = new GameBoard();
        makeGameBoard();
        createComponentMap();
        System.out.println(getComponentByName("4"));

    }

    private static void createComponentMap() {
        componentMap = new HashMap<String, JButton>();
        Component[] buttons = tttBoard.getComponents();
        for(int i=0; i<buttons.length; i++) {
            componentMap.put(buttons[i].getName(), buttons[i]);
        }
    }

    private static Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        }
        else return null;
    }

    public static void makeGameBoard() {
        gui = new JPanel(new BorderLayout(3, 3));
        frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gui);
        frame.setTitle("Tic-Tac-Toe by jeffreyo3");

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

                System.out.println(count);
                count++;
            }
        }
        frame.setVisible(true);
    }

    private static ActionListener selectMove(JButton b) {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String curr = b.getText();
                if (!curr.contains("X") && !curr.contains("O")) {
                    b.setText("X");
                    System.out.println("hi");
                }

            }
        };
        return action;
    }
}
