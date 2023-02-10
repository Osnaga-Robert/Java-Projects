import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Parents_Frame extends JFrame implements ActionListener {

    JPanel north_panel;
    JLabel parent_name;
    JTextArea notifications_area;
    JLabel error;
    JScrollPane scrollPane;
    JButton back;


    public Parents_Frame(String first_name, String last_name){
        setSize(1000,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        StringBuffer buffer = new StringBuffer();
        int ok = 0;

        parent_name = new JLabel("Bine ati venit " + first_name + " " + last_name,SwingConstants.CENTER);
        for(Course i : Catalog.getInstance().getCourses()){
            for(Student j : i.getAllStudents()){
                if((j.getFather() != null && j.getFather().equals(new Parent(first_name,last_name))) || (j.getMother() != null && j.getMother().equals(new Parent(first_name,last_name)))){
                    ok = 1;
                    for(String k : j.getNotifications()){
                        buffer.append(k);
                        buffer.append("\n");
                    }
                    break;
                }
                if(ok == 1)
                    break;
            }
            if(ok == 1)
                break;
        }

        north_panel = new JPanel();
        north_panel.setLayout(new BorderLayout());

        back = new JButton("<-");
        back.addActionListener(this);

        notifications_area = new JTextArea(buffer.toString());
        notifications_area.setEditable(false);

        if(ok==0){
            error = new JLabel("Parintele nu exista");
            add(error,BorderLayout.CENTER);
        }
        else if (buffer.length() == 0){
            error = new JLabel("Nu exista notificari");
            add(error, BorderLayout.CENTER);
        }
        else{
            scrollPane = new JScrollPane(notifications_area);
            add(scrollPane, BorderLayout.CENTER);
        }

        north_panel.add(parent_name, BorderLayout.CENTER);
        north_panel.add(back,BorderLayout.WEST);

        add(north_panel, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            dispose();
            new MainFrame();
        }
    }
}
