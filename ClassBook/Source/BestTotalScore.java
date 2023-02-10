import java.util.HashMap;

public class BestTotalScore implements Strategy{
    @Override
    public Student thebest(HashMap<Student, Grade> grades) {
        Double best = -1.3;
        Student output = null;
        for (HashMap.Entry<Student, Grade> set : grades.entrySet()) {
            if(set.getValue().getTotal() > best){
                output = set.getKey();
                best = set.getValue().getTotal();
            }
        }
        return output;
    }
}
