import java.util.ArrayList;
import java.util.HashMap;

public interface Strategy {
    public Student thebest(HashMap<Student, Grade> grades);
}
