import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    private JTabbedPane tabs;
    private JPanel panel1;
    private JTextField txtProjectName;
    private JTextField txtProjectDeadline;
    private JButton newTeamButton;
    private JComboBox savedTeams;
    private JList membersList;
    private JList teamLeaderList;
    private JList currentTeam;
    private JButton saveButton;
    private JComboBox savedProjects;
    private JButton newTaskButton;
    private JList allTasksList;
    private JList completedTasksList;
    private JTextArea criticalPathTxtArea;
    private JPanel tab2;
    private JPanel tab1;
    private JLabel projectNameLabel;
    private JLabel projectDeadlineLabel;
    private JLabel newProjectLabel;
    private JLabel trackProgressLabel;
    private List<Project> projects;
    private ProjectHandler handler;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Project Management");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public GUI(){

        newTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // to do tomorrow
                //projects = handler.createProject(txtProjectName.getText(), txtProjectDeadline.getText(), )
            }
        });
        newTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
