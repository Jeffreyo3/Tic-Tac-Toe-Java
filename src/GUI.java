import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private static JTextField userText;
    private static JTextField passInput;
    private static JLabel success;
    private JPanel tttBoard;


    public static void main(String[] args) {
        JPanel gui = new JPanel(new BorderLayout(3, 3));
        JFrame frame = new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gui);

        JPanel tttBoard = new JPanel(new GridLayout(0,3));
        tttBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(tttBoard);

        GameBoard board = new GameBoard();
        char [][] layout = board.getGameBoard();
        System.out.println(layout.length);
        System.out.println(layout[0].length);

        // TODO render data to screen
        int count = 0;
        for (char[] rows : layout) {
            for (char c : rows) {
                JButton b = new JButton();
                b.setBackground(Color.BLACK);
                b.setText(Character.toString(c));
                b.setForeground(Color.WHITE);
                b.setFont(new Font("Arial", Font.PLAIN, 40));
                b.setFocusPainted(false);
                b.setFocusable(false);

                b.setName(Integer.toString(count));

//                for (int e : board.getIntArray()) {
//                    if (e == count) {
//                        b.setName(Integer.toString(num));
//                        b.setFocusable(true);
//                        b.setSize(4, 4);
//                    }
//                }

                tttBoard.add(b);

                System.out.println(count);
                count++;
            }
        }

        frame.setVisible(true);
    }

    public void makeGameBoard() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passInput.getText();

        if(user.equals("Jeff") & password.equals("1234")) {
            success.setText("YOU DID IT");
        } else {
            success.setText("ERROR!!");
        }
    }
}
