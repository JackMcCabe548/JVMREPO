import javax.swing.*;

public class SecondPage extends JFrame{
    private JPanel panel1;
    private JRadioButton teamsRadioButton;
    private JRadioButton projectManegementRadioButton;
    private JButton GOButton;
    private JComboBox comboBox1;
    private JPanel MainPagePanel;
    private JButton ProjectPicker;
    private JList list1;
    private JList list2;
    private JList list3;

    public static void main (String [] args) {
        JFrame frame = new JFrame("Main GUI");
        frame.setContentPane(new SecondPage().MainPagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
