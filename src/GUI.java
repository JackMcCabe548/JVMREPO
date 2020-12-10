import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class GUI {
    private JTabbedPane tabs;
    private JPanel panel1;
    private JButton newTeamButton;
    private JComboBox savedTeams;
    private JList<Object> membersList;
    private JList teamLeaderList;
    private JList currentTeam;
    private JButton nextButton;
    private JComboBox savedProjects;
    private JButton newTaskButton;
    private JList allTasksList;
    private JList completedTasksList;
    private JTextArea criticalPathTxtArea;
    private JPanel tab2;
    private JPanel tab1;
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
    private JButton saveAllButton;
    private JButton transferButton3;
    private JButton newProjectButton;
    private List<Project> projects;
    private static  ProjectHandler handler = null;
    private Project project;
    private CriticalPath criticalPath;
    private DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
    private DefaultListModel<String> defaultListModel2 = new DefaultListModel<String>();
    private DefaultListModel<String> defaultListModel3 = new DefaultListModel<String>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Project Management");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        handler.createFile();

    }
    public GUI(){
        handler = new ProjectHandler();

        criticalPathTxtArea.setText("Critical path's total duration (cost): 9");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabs.setSelectedIndex(1);
            }
        });

        newTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField1 = new JTextField();
                JTextField textField2 = new JTextField();
                JTextField textField3 = new JTextField();

                Object[] inputs = {"Name of task:", textField1, "Cost of task:", textField2, "Predecessor:", textField3};

                int inputDialog = JOptionPane.showConfirmDialog(panel1, inputs, "New Task",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (inputDialog == JOptionPane.OK_OPTION) {
                    String text = textField1.getText() + "\n" + " - " + textField2.getText() + " - " + textField3.getText();
                    ((DefaultListModel)allTasksList.getModel()).addElement(text);
                }
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
                currentTeam.setModel(defaultListModel2);
                int[] selectedIndices = allTasksList.getSelectedIndices();
                for (int selectedIndex : selectedIndices) {
                    Object whatever = allTasksList.getModel().getElementAt(selectedIndex);
                    ((DefaultListModel) completedTasksList.getModel()).addElement(whatever);
                }
            }
        });

        transferButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamLeaderList.setModel(defaultListModel3);
                int[] selectedIndices = currentTeam.getSelectedIndices();
                for(int i=0; i < selectedIndices.length; i++) {
                    Object whatever = currentTeam.getModel().getElementAt(selectedIndices[i]);
                    ((DefaultListModel)teamLeaderList.getModel()).addElement(whatever);
                }
            }
        });

        clearTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultListModel.clear();
                defaultListModel3.clear();
                currentTeam.setModel(defaultListModel);
                teamLeaderList.setModel(defaultListModel3);
            }
        });

        clearTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defaultListModel2.clear();
                completedTasksList.setModel(defaultListModel2);
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

        saveAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentTeam != null){
                    System.out.println("Team members: ");
                    for (int i = 0; i < currentTeam.getModel().getSize(); i++) {
                        System.out.println(currentTeam.getModel().getElementAt(i));
                    }
                }else {
                    System.out.println("Team members haven't been picked yet!");
                }

                handler.saveToConsole(project);
                handler.saveToFile(project);
                savedProjects.addItem(project);
                savedTeams.addItem(txtTeamName.getText());
            }
        });

        savedTeams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        newProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField1 = new JTextField();
                JTextField textField2 = new JTextField();

                JDateChooser dateChooser = new JDateChooser();
                dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 20));

                Object[] inputs = {"Name of project:", textField1, "Name of the team:", textField2,  "Starting date: ", dateChooser};

                int inputDialog = JOptionPane.showConfirmDialog(panel1, inputs, "New Project",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (inputDialog == JOptionPane.OK_OPTION) {
                    String s="";
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    s=sdf.format(((JDateChooser)inputs[5]).getDate());
                    String text = textField1.getText() + "\n" + " - " + s;
                    project = handler.createProject2(textField1.getText(), textField2.getText(), s);

                }
            }
        });
    }

}



