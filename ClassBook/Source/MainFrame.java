import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    JButton student_button;
    JButton parent_button;
    JButton teacher_button;
    JButton assistant_button;
    JButton add_course_button;

    JButton admin;

    JPanel panel1;
    JPanel panel2;

    JPanel panel3;

    JPanel panel_first_name;
    JPanel panel_second_name;

    JLabel text;
    JLabel first_name;
    JLabel last_name;

    JTextField field_first_name;
    JTextField field_last_name;

    public MainFrame(){
        setSize(1000,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(1,2));

        student_button = new JButton("Student");
        parent_button = new JButton("Parent");
        teacher_button = new JButton("Teacher");
        assistant_button = new JButton("Assistant");
        admin = new JButton("Admin settings");
        add_course_button = new JButton("Add course");
        add_course_button.addActionListener(this);
        student_button.addActionListener(this);
        admin.addActionListener(this);
        parent_button.addActionListener(this);
        teacher_button.addActionListener(this);
        assistant_button.addActionListener(this);

        text = new JLabel("Introduceti date pentru a va conecta");
        text.setHorizontalAlignment(JLabel.CENTER);
        first_name = new JLabel("Your first name:");
        first_name.setHorizontalAlignment(JLabel.CENTER);
        last_name = new JLabel("Your last name:");
        last_name.setHorizontalAlignment(JLabel.CENTER);

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,1));

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4,1));

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,2));
        panel3.add(admin);
        panel3.add(add_course_button);

        field_first_name = new JTextField();
        field_first_name.setPreferredSize(new Dimension(200,30));
        panel_first_name = new JPanel();
        panel_first_name.setLayout(new FlowLayout());
        panel_first_name.add(first_name);
        panel_first_name.add(field_first_name);

        field_last_name = new JTextField();
        field_last_name.setPreferredSize(new Dimension(200,30));
        panel_second_name = new JPanel();
        panel_second_name.setLayout(new FlowLayout());
        panel_second_name.add(last_name);
        panel_second_name.add(field_last_name);



        panel2.add(text);
        panel2.add(panel_first_name);
        panel2.add(panel_second_name);
        panel2.add(panel3);

        panel1.add(student_button);
        panel1.add(parent_button);
        panel1.add(teacher_button);
        panel1.add(assistant_button);

        add(panel1);
        add(panel2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == student_button){
            dispose();
            new StudentPage(field_first_name.getText(),field_last_name.getText());
        }
        if(e.getSource() == teacher_button){
            dispose();
            new Teacher_Frame(field_first_name.getText(),field_last_name.getText());
        }
        if(e.getSource() == assistant_button){
            dispose();
            new Assistant_Frame(field_first_name.getText(),field_last_name.getText());
        }
        if(e.getSource() == parent_button){
            dispose();
            new Parents_Frame(field_first_name.getText(),field_last_name.getText());
        }
        if(e.getSource() == admin){
            dispose();
            new Admin_Frame();
        }
        if(e.getSource() == add_course_button){
            dispose();
            new Add_course_Frame();
        }
    }
}
