import java.util.ArrayList;

public class Student extends User implements Comparable{
    private Parent mother;
    private Parent father;
    private ArrayList<String> notifications ;
    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        notifications = new ArrayList<>();
    }
    public Parent getFather() {
        return father;
    }
    public Parent getMother() {
        return mother;
    }
    public void setFather(Parent father) {
        this.father = father;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    @Override
    public int compareTo(Object o) {
        Student obj = (Student) o;
        return(obj.getFirstName().compareTo(getFirstName()));
    }

    public void add_notification(Notification notification){
        notifications.add(notification.toString());
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<String> notifications) {
        this.notifications = notifications;
    }

    @Override
    public boolean equals(Object obj) {
        Student o = (Student) obj;
        if(o.getFirstName().equals(getFirstName()) == true && o.getLastName().equals(getLastName()) == true)
            return true;
        return false;
    }
}
