import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseworkGui {

    private JPanel panel1;
    private JPanel MainPage;
    private JTextField ProjectNameTextArea;
    private JTextField ProjectDeadlineTextArea;
    private JList MembersJList;
    private JList TeamLeaderJList;
    private JList CurrentTeamJList;

    // Project creation save button
    private JButton saveButton;


    public CourseworkGui() { // GUI code area

        saveButton.addActionListener(new ActionListener() { //Button to save inputs
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main (String [] args) {
        JFrame frame = new JFrame("Main GUI");
        frame.setContentPane(new CourseworkGui().MainPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
