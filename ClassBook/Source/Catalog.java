import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Catalog{

    ArrayList<Observer> observers = new ArrayList<>();
    ScoreVisitor scoreVisitor = new ScoreVisitor();

    private List<Course> courses ;

    private static final Catalog instance = new Catalog();
    private Catalog(){
        courses = new ArrayList<Course>();
    }
    public static Catalog getInstance() {
        return instance;
    }
    public void addCourse(Course course){
        courses.add(course);
    }
    public void removeCourse(Course course){
        courses.remove(courses.indexOf(course));
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String toString(){
        return courses.toString();
    }

}
