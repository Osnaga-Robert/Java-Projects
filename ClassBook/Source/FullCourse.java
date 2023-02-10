import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullCourse extends Course{

    public FullCourse(FullCourseBuilder builder) {
        super(builder);
    }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> output = new ArrayList<>();
        HashMap<Student,Grade> students = new HashMap<>(gettAllStudentGrades());
        for(Map.Entry<Student,Grade> set : students.entrySet()){
            if(set.getValue().getpartialScore() >=3 && set.getValue().getExamScore() >= 2)
                output.add(set.getKey());
        }
        return output;
    }

    public static class FullCourseBuilder extends CourseBuilder{

        @Override
        public Course build() {
            return new FullCourse(this);
        }
    }

}
