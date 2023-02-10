import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class Teacher_Frame extends JFrame implements ActionListener {

    JPanel top_panel;
    JPanel center_panel;
    JPanel panel1;
    JPanel panel2;
    JPanel panel_first_name;
    JPanel panel_last_name;
    JButton back;
    JButton button_courses;
    JButton validate;

    JTextField field_first_name;
    JTextField field_last_name;
    JTextArea description;
    JLabel label_first_name;
    JLabel label_last_name;
    JLabel firstname_student;
    JLabel lastname_student;

    JList<String> course_list;
    DefaultListModel<String>default_course_list;
    JScrollPane scrollPane1;
    JScrollPane scrollPane2;

    String aux = "" ;

    public Teacher_Frame(String first_name,String last_name){
        setSize(1000,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        top_panel = new JPanel();
        top_panel.setLayout(new BorderLayout());

        back = new JButton("<-");
        back.addActionListener(this);
        top_panel.add(back,BorderLayout.WEST);

        label_first_name = new JLabel("First name");
        label_last_name = new JLabel("Last name");

        field_first_name = new JTextField();
        field_last_name = new JTextField();
        field_first_name.setText(first_name);
        field_last_name.setText(last_name);
        field_first_name.setEditable(false);
        field_last_name.setEditable(false);

        center_panel = new JPanel();
        center_panel.setLayout(new GridLayout(1,2));

        firstname_student = new JLabel("First Name Teacher");
        lastname_student = new JLabel("Last Name Teacher");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel_first_name = new JPanel();
        panel_last_name = new JPanel();
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

        button_courses = new JButton("Get courses");
        panel1.add(button_courses);
        button_courses.addActionListener(this);

        validate = new JButton("Validate");
        validate.addActionListener(this);
        validate.setPreferredSize(new Dimension(300,30));

        default_course_list = new DefaultListModel<>();
        course_list = new JList<>(default_course_list);
        scrollPane1 = new JScrollPane(course_list);
        scrollPane1.setPreferredSize(new Dimension(400,40));
        panel1.add(scrollPane1);

        StringBuffer buffer = new StringBuffer();

        Set<Teacher> teachers = Catalog.getInstance().scoreVisitor.examScores.keySet();
        for(Teacher i : teachers){
            if(i.equals(new Teacher(first_name, last_name))){
                aux = " " + aux;
                aux = aux +  Catalog.getInstance().scoreVisitor.examScores.get(i).toString().replace(",","").replace("[","").replace("]","");
            }
        }

        description = new JTextArea(aux);
        description.setEditable(false);
        scrollPane2 = new JScrollPane(description);
        panel2.add(scrollPane2);

        center_panel.add(panel1);
        center_panel.add(panel2);

        add(top_panel,BorderLayout.NORTH);
        add(center_panel,BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            dispose();
            new MainFrame();
        }
        if(e.getSource() == button_courses){
            default_course_list.removeAllElements();
            for(Course i :Catalog.getInstance().getCourses()){
                if(i.getTeacher().equals(new Teacher(field_first_name.getText(),field_last_name.getText()))){
                    default_course_list.addElement(i.getName());
                }
            }
            if(default_course_list.getSize() == 0){
                default_course_list.addElement("Nu s-a gasit profesorul");
            }
            else{
                panel1.add(validate);
            }
            SwingUtilities.updateComponentTreeUI(this);
        }
        if(e.getSource() == validate){
            if (description.getText().equals("") == false){
                for(Course i : Catalog.getInstance().getCourses()){
                    if(i.getTeacher().equals(new Teacher(field_first_name.getText(),field_last_name.getText()))){
                        Catalog.getInstance().scoreVisitor.visit(i.getTeacher());
                        Catalog.getInstance().scoreVisitor.examScores.remove(i.getTeacher());
                    }
                }
            }
            description.setText("");
            SwingUtilities.updateComponentTreeUI(this);
        }
    }
}
