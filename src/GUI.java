import javax.swing.*;

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
    private JButton saveTeamButton;
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Project Management");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public GUI(){

    }


}
