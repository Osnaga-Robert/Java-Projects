import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartialCourse extends Course {


    public PartialCourse(PartialCourseBuilder builder){
        super(builder);
    }
    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> output = new ArrayList<>();
        HashMap<Student,Grade> students = new HashMap<>(gettAllStudentGrades());
        for(Map.Entry<Student,Grade> set : students.entrySet()){
            if(set.getValue().getTotal() >=5)
                output.add(set.getKey());
        }
        return output;
    }

    public static class PartialCourseBuilder extends CourseBuilder{

        @Override
        public Course build() {
            return new PartialCourse(this);
        }
    }
}
