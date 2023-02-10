import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class StudentPage extends JFrame implements ActionListener {

    JPanel panel1 ;
    JPanel panel2;
    JPanel panel_first_name;
    JPanel panel_last_name;
    JPanel main1;
    JPanel main2;
    JLabel firstname_student;
    JLabel lastname_student;
    JTextField field_first_name;
    JTextField field_last_name;
    JButton button_find_student;
    JButton button_get_details;
    JButton test = new JButton("TEST");

    JButton back;

    JTextArea final_text;

    JList<String> course_list;
    DefaultListModel<String>default_course_list;
    JScrollPane scrollPane;
    public StudentPage(String first_name,String second_name){
        setSize(1000,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        main1 = new JPanel();
        main1.setLayout(new BorderLayout());
        main2 = new JPanel();
        back = new JButton("<-");
        back.addActionListener(this);
        main2.setLayout(new GridLayout(1,2));
        firstname_student = new JLabel("First Name Student");
        lastname_student = new JLabel("Last Name Student");
        field_first_name = new JTextField();
        field_last_name = new JTextField();
        field_first_name.setText(first_name);
        field_last_name.setText(second_name);
        field_first_name.setEditable(false);
        field_last_name.setEditable(false);
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel_first_name = new JPanel();
        panel_last_name = new JPanel();
        final_text = new JTextArea();
        button_find_student = new JButton("Find student");
        button_find_student.addActionListener(this);
        field_first_name.setPreferredSize(new Dimension(200,20));
        field_last_name.setPreferredSize(new Dimension(200,20));

        panel_first_name.setLayout(new GridLayout(1,2));
        panel1.add(panel_first_name);
        panel_first_name.add(firstname_student);
        panel_first_name.add(field_first_name);

        panel_last_name.setLayout(new GridLayout(1,2));
        panel1.add(panel_last_name);
        panel_last_name.add(lastname_student);
        panel_last_name.add(field_last_name);

        panel1.add(button_find_student);

        button_get_details = new JButton("More details");
        button_get_details.addActionListener(this);

        default_course_list = new DefaultListModel<>();
        course_list = new JList<>(default_course_list);
        scrollPane = new JScrollPane(course_list);
        scrollPane.setPreferredSize(new Dimension(400,40));

        panel1.add(scrollPane);
        panel2.setLayout(new BorderLayout());

        main1.add(back,BorderLayout.WEST);
        main2.add(panel1);
        main2.add(panel2);

        add(main1,BorderLayout.NORTH);
        add(main2,BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button_find_student){
            panel2.remove(final_text);
            panel1.remove(button_get_details);
            String first_name = field_first_name.getText();
            String last_name = field_last_name.getText();
            course_list.removeAll();
            default_course_list.removeAllElements();
            for(Course i : Catalog.getInstance().getCourses()){
                if(i.getAllStudents().contains(new Student(first_name,last_name))){
                    default_course_list.addElement(i.getName());
                }
            }
            if(default_course_list.getSize() == 0){
                default_course_list.addElement("Nu s-a gasit studentul");
            }
            else{
                panel1.add(button_get_details);
            }
            SwingUtilities.updateComponentTreeUI(this);
        }

        if(e.getSource() == button_get_details){
            panel2.remove(final_text);
            for(Course i : Catalog.getInstance().getCourses()){
                if(i.getName().equals(course_list.getSelectedValue())){
                    StringBuffer buffer = new StringBuffer();
                    StringBuffer modify = new StringBuffer();
                    buffer.append(i.getTeacher());
                    buffer.append("\n");
                    buffer.append("Asistenti:" + "\n");
                    for(Assistant j : i.getAssistents()){
                        buffer.append(" ->" +j);
                        buffer.append("\n");
                    }
                    Map<String,Group> map = new HashMap<>(i.getGroups());
                    for(Map.Entry<String,Group> set : map.entrySet()){
                        if(set.getValue().contains(new Student(field_first_name.getText(),field_last_name.getText()))){
                            buffer.append("Asistentul studentului: " + set.getValue().getAssistant() + "\n");
                            break;
                        }
                    }
                    if (i.getGrade( new Student(field_first_name.getText(),field_last_name.getText())) == null){
                        buffer.append("Momentan nu exista nicio nota" + "\n");
                        //buffer.append("Nota partial: " +i.getGrade( new Student(field_first_name.getText(),field_last_name.getText())).getExamScore() + "\n");
                    }
                    else{
                        buffer.append("Nota partial: " +i.getGrade( new Student(field_first_name.getText(),field_last_name.getText())).getpartialScore() + "\n");
                        buffer.append("Nota partial: " +i.getGrade( new Student(field_first_name.getText(),field_last_name.getText())).getExamScore() + "\n");
                    }

                    final_text.setText(buffer.toString());

                    SwingUtilities.updateComponentTreeUI(this);
                }
            }
            panel2.add(final_text,BorderLayout.CENTER);
        }
        if(e.getSource() == back){
            dispose();
            new MainFrame();
        }
    }
}
