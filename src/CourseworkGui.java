import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
    private JComboBox comboBox1;
    private JButton setUpANewTeamButton;
    private JRadioButton teamsRadioButton;
    private JButton GOButton;
    private JRadioButton projectManagementRadioButton;


    public CourseworkGui() { // GUI code area


        GOButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ButtonGroup BG = new ButtonGroup();
                BG.add(projectManagementRadioButton);
                BG.add(teamsRadioButton);
                BG.clearSelection();

            }
        });// end of button brackets


    }

    public static void main (String [] args) {
        JFrame frame = new JFrame("Main GUI");
        frame.setContentPane(new CourseworkGui().MainPage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
