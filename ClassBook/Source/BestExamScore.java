import java.util.HashMap;

public class BestExamScore implements Strategy{
    @Override
    public Student thebest(HashMap<Student, Grade> grades) {
        Double best = -1.3;
        Student output = null;
        for (HashMap.Entry<Student, Grade> set : grades.entrySet()) {
            if(set.getValue().getExamScore() != null && set.getValue().getExamScore() > best ){
                output = set.getKey();
                best = set.getValue().getExamScore();
            }
        }
        return output;
    }
}
