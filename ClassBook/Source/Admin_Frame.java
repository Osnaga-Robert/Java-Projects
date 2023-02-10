import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin_Frame extends JFrame implements ActionListener {

    JPanel panel;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel north_panel;
    JList<String> courses;
    JTextArea info_panel2;
    JTextArea info_panel3;
    JButton confirm_button = new JButton();
    JButton back_button = new JButton();
    JButton add_grade ;
    JButton add_assistant;
    JButton add_student;
    JButton see_students;

    JTextField area_first_name;
    JTextField area_last_name;
    JTextField field_first_name;
    JTextField field_last_name;
    JTextField field_partial_score;
    JTextField field_exam_score;

    JTextField field_first_name_father;
    JTextField field_last_name_father;
    JTextField field_first_name_mother;
    JTextField field_last_name_mother;
    JTextField field_group;

    int index = 0;

    public Admin_Frame(){
        setSize(1400,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        north_panel = new JPanel();
        north_panel.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(1,3));
        panel1.setBackground(new Color(255, 253, 221));
        panel2.setBackground(new Color(255,253,208));
        panel3.setBackground(new Color(255, 248, 174));
        panel4.setBackground(Color.GREEN);

        DefaultListModel<String> buffer1 = new DefaultListModel<>();
        for(Course i : Catalog.getInstance().getCourses()){
            buffer1.addElement(i.getName());
        }
        courses =  new JList<>(buffer1);
        courses.setPreferredSize(new Dimension(200,200));
        confirm_button = new JButton("Confirm the course");
        confirm_button.addActionListener(this);
        back_button = new JButton("<-");
        back_button.addActionListener(this);
        add_assistant = new JButton("Add assistant");
        add_assistant.addActionListener(this);
        add_grade = new JButton("Add grade");
        add_grade.addActionListener(this);
        add_student = new JButton("Add student");
        add_student.addActionListener(this);
        see_students = new JButton("See students");
        see_students.addActionListener(this);

        info_panel2 = new JTextArea();
        info_panel2.setEditable(false);

        panel1.setLayout(new BorderLayout());
        panel1.add(courses, BorderLayout.CENTER);
        panel1.add(confirm_button, BorderLayout.SOUTH);

        info_panel3 = new JTextArea();
        info_panel3.setEditable(false);

        panel3.setLayout(new BorderLayout());
        panel4.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER);
        add(north_panel, BorderLayout.NORTH);
        north_panel.add(back_button,BorderLayout.WEST);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm_button){
            for(Course i : Catalog.getInstance().getCourses()){
                if(i.getName().equals(courses.getSelectedValue())){
                    index = Catalog.getInstance().getCourses().indexOf(i);
                    StringBuffer buffer1 = new StringBuffer();
                    int no_assistants = i.getAssistents().size();
                    int no_students = i.getAllStudents().size();
                    int no_students_w_grade = i.getGrades().size();
                    int no_students_wo_grade = no_students - no_students_w_grade;
                    Student student_by_partial_score = i.getBestStudent();
                    buffer1.append("Profesorul titular: " + i.getTeacher() + " \n");
                    buffer1.append("Numarul asistentilor: " + no_assistants + " asistenti\n");
                    buffer1.append("Numarul studentilor: " + no_students + " studenti\n");
                    buffer1.append("Numarul studentilor cu nota: " + no_students_w_grade + " studenti\n");
                    buffer1.append("Numarul studntilor fara nota: " + no_students_wo_grade + " studenti\n");
                    buffer1.append("Studentul top este\n   ->" + student_by_partial_score + "\n");
                    info_panel2.setText(buffer1.toString());

                    StringBuffer buffer2 = new StringBuffer();
                    buffer2.append("Asistentii inscrisi la curs sunt:\n");
                    for(Assistant j : i.getAssistents()){
                        buffer2.append("   ->" + j + "\n");
                    }
                    info_panel3.setText(buffer2.toString());

                }
            }
            panel3.add(info_panel3, BorderLayout.CENTER);
            panel2.add(info_panel2,BorderLayout.CENTER);
            panel2.add(see_students,BorderLayout.SOUTH);
            panel4.add(add_grade,BorderLayout.EAST);
            panel4.add(add_assistant,BorderLayout.WEST);
            panel4.add(add_student, BorderLayout.CENTER);
            panel3.add(panel4, BorderLayout.SOUTH);
            SwingUtilities.updateComponentTreeUI(this);
        }
        if(e.getSource() == add_assistant){
            int ok = 0;
            int response = JOptionPane.showConfirmDialog(null,getPanelAssistant(),"Add asistant",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(response == JOptionPane.OK_OPTION){
                if(area_first_name != null && area_last_name != null){
                    if(check() == false){
                        Catalog.getInstance().getCourses().get(index).getAssistents().add(new Assistant(area_first_name.getText(),area_last_name.getText()));
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"The assistant already exist","Assistant",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            SwingUtilities.updateComponentTreeUI(this);
        }
        if(e.getSource() == add_grade){
            int response = JOptionPane.showConfirmDialog(null,getPanelGrade(),"Add grade",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(response == JOptionPane.OK_OPTION){
                if(field_first_name != null && field_last_name != null){
                    int ver = check1();
                    if(ver != -1 && check2() == false){
                        Double d1 = Double.parseDouble(field_partial_score.getText());
                        Double d2 = Double.parseDouble(field_exam_score.getText());
                        Grade grade = new Grade(d1,d2,Catalog.getInstance().getCourses().get(index).getAllStudents().get(ver),Catalog.getInstance().getCourses().get(index).getName());
                        Catalog.getInstance().getCourses().get(index).addGrade(grade);
                    }
                    else if(ver == -1){
                        JOptionPane.showMessageDialog(null,"The student does not exist","Assistant",JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"The student have a grade","Assistant",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
        if(e.getSource() == add_student){
            int response = JOptionPane.showConfirmDialog(null,getPanelStudent(),"Add grade",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
            if(response == JOptionPane.OK_OPTION){
                 for(Student i : Catalog.getInstance().getCourses().get(index).getAllStudents()){
                     if(i.equals(new Student(field_first_name.getText(),field_last_name.getText())) == true){
                         JOptionPane.showMessageDialog(null,"The student already exist!","Student",JOptionPane.WARNING_MESSAGE);
                         return;
                     }
                 }
                 boolean ok = false;
                 for(String i : Catalog.getInstance().getCourses().get(index).getGroups().keySet()){
                     if(i.equals(field_group.getText())){
                         ok = true;
                     }
                 }
                 if(ok == false){
                     JOptionPane.showMessageDialog(null,"The group doesn't exist","GroupID",JOptionPane.WARNING_MESSAGE);
                     return;
                 }
                 Student student = new Student(field_first_name.getText(), field_last_name.getText());
                 student.setFather(new Parent(field_first_name_father.getText(),field_last_name_father.getText()));
                 student.setMother(new Parent(field_first_name_mother.getText(),field_last_name_mother.getText()));
                 Catalog.getInstance().getCourses().get(index).addStudent(field_group.getText(),student);

            }
        }
        if(e.getSource() == see_students){
            String aux = Catalog.getInstance().getCourses().get(index).getGrades().toString();
            aux = aux.replace('{',' ').replace('[',' ').replace('=',' ').replace(']',' ').replace('}',' ').replace(',',' ');
            JOptionPane.showMessageDialog(null,aux,"Studets",JOptionPane.PLAIN_MESSAGE);
        }
        if(e.getSource() == back_button){
            dispose();
            new MainFrame();
        }
    }

    public JPanel getPanelAssistant(){
        JPanel output = new JPanel();
        JPanel panel11 = new JPanel();
        JPanel panel21 = new JPanel();
        panel11.setLayout(new FlowLayout());
        panel21.setLayout(new FlowLayout());
        output.setLayout(new GridLayout(2,1));
        JLabel field_first_name = new JLabel("First Name");
        JLabel field_last_name = new JLabel("Last Name");
        area_first_name = new JTextField();
        area_last_name = new JTextField();
        area_first_name.setPreferredSize(new Dimension(200,30));
        area_last_name.setPreferredSize(new Dimension(200,30));
        panel11.add(field_first_name);
        panel11.add(area_first_name);
        panel21.add(field_last_name);
        panel21.add(area_last_name);
        output.add(panel11);
        output.add(panel21);
        return output;
    }

    public JPanel getPanelGrade(){
        JPanel panel = new JPanel();
        JPanel panel31 = new JPanel();
        JPanel panel32 = new JPanel();
        JPanel panel33 = new JPanel();
        JPanel panel34 = new JPanel();

        JLabel first_name = new JLabel("Student First Name");

        panel.setLayout(new GridLayout(4,1));
        panel31.setLayout(new FlowLayout());
        panel32.setLayout(new FlowLayout());
        panel33.setLayout(new FlowLayout());

        field_first_name = new JTextField();
        field_last_name = new JTextField();
        field_exam_score = new JTextField();
        field_partial_score = new JTextField();

        field_first_name.setPreferredSize(new Dimension(200,30));
        field_last_name.setPreferredSize(new Dimension(200,30));
        field_exam_score.setPreferredSize(new Dimension(200,30));
        field_partial_score.setPreferredSize(new Dimension(200,30));

        JLabel label_first_name = new JLabel("First name student");
        JLabel label_last_name = new JLabel("Last name student");
        JLabel label_partial_score = new JLabel("Partial score");
        JLabel label_exam_score = new JLabel("Exam score");

        panel31.add(label_first_name);
        panel32.add(label_last_name);
        panel33.add(label_partial_score);
        panel34.add(label_exam_score);

        panel31.add(field_first_name);
        panel32.add(field_last_name);
        panel33.add(field_partial_score);
        panel34.add(field_exam_score);

        panel.add(panel31);
        panel.add(panel32);
        panel.add(panel33);
        panel.add(panel34);

        return panel;

    }

    public JPanel getPanelStudent(){
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();

        JLabel first_name = new JLabel("Student First Name");

        panel.setLayout(new GridLayout(7,1));
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());
        panel6.setLayout(new FlowLayout());
        panel7.setLayout(new FlowLayout());

        field_first_name = new JTextField();
        field_last_name = new JTextField();
        field_first_name_father = new JTextField();
        field_last_name_father = new JTextField();
        field_first_name_mother = new JTextField();
        field_last_name_mother = new JTextField();
        field_group = new JTextField();

        field_first_name.setPreferredSize(new Dimension(200,30));
        field_last_name.setPreferredSize(new Dimension(200,30));
        field_first_name_father.setPreferredSize(new Dimension(200,30));
        field_last_name_father.setPreferredSize(new Dimension(200,30));
        field_first_name_mother.setPreferredSize(new Dimension(200,30));
        field_last_name_mother.setPreferredSize(new Dimension(200,30));
        field_group.setPreferredSize(new Dimension(200,30));

        JLabel label_first_name = new JLabel("First name student");
        JLabel label_last_name = new JLabel("Last name student");
        JLabel label_first_name_father = new JLabel("First name father");
        JLabel label_last_name_father = new JLabel("Last name father");
        JLabel label_first_name_mother = new JLabel("First name mother");
        JLabel label_last_name_mother = new JLabel("Last name mother");
        JLabel label_group = new JLabel("Group");

        panel1.add(label_first_name);
        panel2.add(label_last_name);
        panel3.add(label_first_name_father);
        panel4.add(label_last_name_father);
        panel5.add(label_first_name_mother);
        panel6.add(label_last_name_mother);
        panel7.add(label_group);

        panel1.add(field_first_name);
        panel2.add(field_last_name);
        panel3.add(field_first_name_father);
        panel4.add(field_last_name_father);
        panel5.add(field_first_name_mother);
        panel6.add(field_last_name_mother);
        panel7.add(field_group);

        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);
        panel.add(panel7);

        return panel;
    }

    public boolean check(){
        for(Assistant i : Catalog.getInstance().getCourses().get(index).getAssistents()){
            if(i.equals(new Assistant(area_first_name.getText(),area_last_name.getText()))){
                return true;
            }
        }
        return false;
    }

    public int check1(){
        for(Student i : Catalog.getInstance().getCourses().get(index).getAllStudents()){
            if(i.equals(new Student(field_first_name.getText(),field_last_name.getText()))){
                return Catalog.getInstance().getCourses().get(index).getAllStudents().indexOf(i);
            }
        }
        return -1;
    }

    public boolean check2(){
        for(Grade i : Catalog.getInstance().getCourses().get(index).getGrades()){
            if(i.getStudent().getFirstName().equals(field_first_name.getText())  == true && i.getStudent().getLastName().equals(field_last_name.getText()) == true)
                return true;
        }
        return false;
    }
}


