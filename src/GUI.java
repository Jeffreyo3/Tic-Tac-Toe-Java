import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

    private static JPanel tttBoard;
    private static GameBoard board;


    public static void main(String[] args) {

        board = new GameBoard();
        makeGameBoard();
        GameBoard.createComponentMap(tttBoard);

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



    private static ActionListener selectMove(JButton b) {
        return e -> {
            if (board.checkSelected(b.getText())) {
                b.setText("X");
                b.setForeground(Color.CYAN);
                board.addUserChoice(Integer.parseInt(b.getName()));
                String result = board.checkWinner();
                if (result.length() > 0) {
                    System.out.println(result);
                } else {
                    GameBoard.computerTurn(board);
                }
            }
        };
    }
}
