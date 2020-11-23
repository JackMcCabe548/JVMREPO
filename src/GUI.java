import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    private JTabbedPane tabs;
    private JPanel panel1;
    private JTextField txtProjectName;
    private JTextField txtProjectStartDate;
    private JButton newTeamButton;
    private JComboBox savedTeams;
    private JList<Object> membersList;
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
    private JButton transferButton1;
    private JButton transferButton2;
    private JButton clearTeamButton;
    private JButton clearTasksButton;
    private JTextField txtTeamName;
    private JLabel teamNameLbl;
    private JLabel membersLbl;
    private JLabel teamLeaderLbl;
    private JLabel currentTeamLbl;
    private List<Project> projects;
    private static  ProjectHandler handler = null;
    private Project project;
    private DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
    private DefaultListModel<String> model2 = new DefaultListModel<String>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Project Management");
        frame.setContentPane(new GUI().panel1);
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
        handler.createFile();

    //TASKS: record duration of tasks(be able to record the successors of tasks
    }
    public GUI(){
        handler = new ProjectHandler();


        teamNameLbl.setVisible(false);
        txtTeamName.setVisible(false);
        membersLbl.setVisible(false);
        teamLeaderLbl.setVisible(false);
        currentTeamLbl.setVisible(false);
        teamLeaderList.setVisible(false);
        membersList.setVisible(false);
        currentTeam.setVisible(false);
        transferButton1.setVisible(false);
        clearTeamButton.setVisible(false);


        newTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamNameLbl.setVisible(true);
                txtTeamName.setVisible(true);
                membersLbl.setVisible(true);
                teamLeaderLbl.setVisible(true);
                currentTeamLbl.setVisible(true);
                teamLeaderList.setVisible(true);
                membersList.setVisible(true);
                currentTeam.setVisible(true);
                transferButton1.setVisible(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // to save the project to a file
                project = handler.createProject2(txtProjectName.getText(), txtTeamName.getText(), txtProjectStartDate.getText());
                // to add the project to the list
                //projects = handler.createProject(txtProjectName.getText(), txtTeamName.getText(), txtProjectStartDate.getText());
                handler.save(project);

            }
        });

        newTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = JOptionPane.showInputDialog("New task:");
                ((DefaultListModel)allTasksList.getModel()).addElement(message);
            }
        });

        transferButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTeam.setModel(defaultListModel);
                int[] selectedIndices = membersList.getSelectedIndices();
                for(int i=0; i < selectedIndices.length; i++) {
                    Object whatever = membersList.getModel().getElementAt(selectedIndices[i]);
                    ((DefaultListModel)currentTeam.getModel()).addElement(whatever);
                }
            }
        });

        transferButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTeam.setModel(model2);
                int[] selectedIndices = allTasksList.getSelectedIndices();
                for(int i=0; i < selectedIndices.length; i++) {
                    Object whatever = allTasksList.getModel().getElementAt(selectedIndices[i]);
                    ((DefaultListModel)completedTasksList.getModel()).addElement(whatever);
                }
            }
        });

        clearTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultListModel.clear();
                currentTeam.setModel(defaultListModel);
            }
        });

        clearTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model2.clear();
                completedTasksList.setModel(model2);
            }
        });

        savedProjects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Not needed functionality now - Mario
//                String s = (String)savedProjects.getSelectedItem();
//                completedTasksList.setModel(model2);
//                ((DefaultListModel)allTasksList.getModel()).addElement(s);
            }
        });
    }

}



