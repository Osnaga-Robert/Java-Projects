import java.util.ArrayList;
import java.util.HashMap;

public class BestPartialScore implements Strategy{
    @Override
    public Student thebest(HashMap<Student, Grade> grades) {
        Double best = -1.3;
        Student output = null;
        for (HashMap.Entry<Student, Grade> set : grades.entrySet()) {
            if(set.getValue().getpartialScore() != null && set.getValue().getpartialScore() > best){
                output = set.getKey();
                best = set.getValue().getpartialScore();
            }
        }
        return output;
    }
}
