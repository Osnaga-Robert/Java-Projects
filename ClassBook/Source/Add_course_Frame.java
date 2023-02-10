import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Add_course_Frame extends JFrame implements ActionListener {

    JPanel panel1;
    JTextField field_route;
    JLabel label_route;
    JButton submit_button;
    JButton back;
    JCheckBox checkBox1;
    JCheckBox checkBox2;
    ButtonGroup buttonGroup;

    public Add_course_Frame(){
        setSize(800,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        label_route = new JLabel("Route of JSON file");
        field_route = new JTextField();
        field_route.setPreferredSize(new Dimension(600,30));
        panel1 = new JPanel();
        checkBox1 = new JCheckBox("PartialCourse");
        checkBox2 = new JCheckBox("FullCourse");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(checkBox1);
        buttonGroup.add(checkBox2);
        panel1.setLayout(new FlowLayout());
        panel1.add(label_route);
        panel1.add(field_route);
        panel1.add(checkBox1);
        panel1.add(checkBox2);
        submit_button = new JButton("Submit");
        submit_button.addActionListener(this);
        back = new JButton("<-");
        back.addActionListener(this);

        add(panel1, BorderLayout.CENTER);
        add(submit_button, BorderLayout.SOUTH);
        add(back,BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit_button){
            if((checkBox1.isSelected() || checkBox2.isSelected()) == true){
                JSONParser jsonParser = new JSONParser();
                FileReader reader;
                try {
                    reader = new FileReader(field_route.getText());
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"The file doesn't exist","File error",JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException(ex);
                }
                Object obj;
                try {
                    obj = jsonParser.parse(reader);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                JSONObject jo = (JSONObject) obj;
                String strategy = (String)jo.get("strategy");
                String name =(String)jo.get("name");
                for(Course i : Catalog.getInstance().getCourses()){
                    if(i.getName().equals(name)){
                        JOptionPane.showMessageDialog(null,"The course already exist!","Assistant",JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                }
                int point = Integer.parseInt((String)jo.get("points"));
                Teacher teacher = new Teacher((String)jo.get("teacher_first_name"),(String)jo.get("teacher_last_name"));
                Course course;
                if(checkBox1.isSelected())
                    course = new PartialCourse.PartialCourseBuilder().name(name).credit_points(point).teacher(teacher).strategy(strategy).build();
                else
                    course = new FullCourse.FullCourseBuilder().name(name).credit_points(point).teacher(teacher).strategy(strategy).build();
                JSONArray array = (JSONArray) jo.get("Assistents");
                for(int i = 0 ; i < array.size() ; i++){
                    JSONObject group = (JSONObject) array.get(i);
                    course.addGroup((String)group.get("ID"),new Assistant((String)group.get("Assitant_first_name"),(String)group.get("Assistant_second_name")));
                    int ok = 0;
                    for(Assistant j : course.getAssistents()){
                        if(j.equals(new Assistant((String)group.get("Assitant_first_name"),(String)group.get("Assistant_second_name"))) == true){
                            ok = 1;
                        }
                    }
                    if(ok == 0)
                        course.getAssistents().add(new Assistant((String)group.get("Assitant_first_name"),(String)group.get("Assistant_second_name")));
                }
                array = (JSONArray) jo.get("Students");
                for(int i = 0 ; i < array.size() ; i++) {
                    JSONObject studentjson = (JSONObject) array.get(i);
                    Student student = new Student((String)studentjson.get("Student_first_name"),(String)studentjson.get("Student_last_name"));
                    student.setFather(new Parent((String)studentjson.get("Student_father_first_name"),(String)studentjson.get("Student_father_last_name")));
                    student.setMother(new Parent((String)studentjson.get("Student_mother_first_name"),(String)studentjson.get("Student_mother_last_name")));
                    course.addStudent((String)studentjson.get("ID"),student);
                }
                Catalog.getInstance().addCourse(course);
            }
            else{
                JOptionPane.showMessageDialog(null,"Select a type","Course",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource() == back){
            dispose();
            new MainFrame();
        }
    }

}
