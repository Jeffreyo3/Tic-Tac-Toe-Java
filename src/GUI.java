import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private static JTextField userText;
    private static JTextField passInput;
    private static JLabel success;


    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(10, 50, 80, 25);
        panel.add(passLabel);

        passInput = new JPasswordField(20);
        passInput.setBounds(100, 50, 165, 25);
        panel.add(passInput);

        JButton button = new JButton("Submit");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new GUI());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);

        GameBoard board = new GameBoard();
        char [][] layout = board.getGameBoard();
        System.out.println(board);
        System.out.println(layout[0].length);
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
