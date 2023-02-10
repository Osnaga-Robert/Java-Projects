import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserFactory userfactory = new UserFactory();
        for(int k = 1 ; k <= 3 ; k++){
            JSONParser jsonParser = new JSONParser();
            FileReader reader;
            try {
                reader = new FileReader("course" + k +".json");
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Object obj;
            try {
                obj = jsonParser.parse(reader);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            JSONObject jo = (JSONObject) obj;

            String name =(String)jo.get("name");
            String strategy = (String)jo.get("strategy");
            int point = Integer.parseInt((String)jo.get("points"));
            //Teacher teacher = new Teacher((String)jo.get("teacher_first_name"),(String)jo.get("teacher_second_name"));
            Teacher teacher = (Teacher) userfactory.getUser("Teacher", (String)jo.get("teacher_first_name"), (String)jo.get("teacher_second_name"));
            Course course;
            if(k == 3)
                course = new FullCourse.FullCourseBuilder().name(name).credit_points(point).teacher(teacher).strategy(strategy).build();
            else
                course = new PartialCourse.PartialCourseBuilder().name(name).credit_points(point).teacher(teacher).strategy(strategy).build();
            JSONArray array = (JSONArray) jo.get("Assistents");
            for(int i = 0 ; i < array.size() ; i++){
                JSONObject group = (JSONObject) array.get(i);
                course.addGroup((String)group.get("ID"), (Assistant) userfactory.getUser("Assistant",(String)group.get("Assistant_first_name"), (String)group.get("Assistant_second_name")));// new Assistant((String)group.get("Assistant_first_name"),(String)group.get("Assistant_second_name")));
                int ok = 0;
                for(Assistant j : course.getAssistents()){
                    if(j.equals((Assistant) userfactory.getUser("Assistant",(String)group.get("Assistant_first_name"), (String)group.get("Assistant_second_name"))) == true){
                        ok = 1;
                    }
                }
                if(ok == 0)
                    course.getAssistents().add((Assistant) userfactory.getUser("Assistant",(String)group.get("Assistant_first_name"), (String)group.get("Assistant_second_name")));
            }
            array = (JSONArray) jo.get("Students");
            for(int i = 0 ; i < array.size() ; i++) {
                JSONObject studentjson = (JSONObject) array.get(i);
                Student student = (Student) userfactory.getUser("Student", (String)studentjson.get("Student_first_name"),(String)studentjson.get("Student_last_name"));// new Student((String)studentjson.get("Student_first_name"),(String)studentjson.get("Student_last_name"));
                student.setFather((Parent) userfactory.getUser("Parent",(String)studentjson.get("Student_father_first_name"),(String)studentjson.get("Student_father_last_name"))); //new Parent((String)studentjson.get("Student_father_first_name"),(String)studentjson.get("Student_father_last_name")));
                student.setMother((Parent) userfactory.getUser("Parent",(String)studentjson.get("Student_mother_first_name"),(String)studentjson.get("Student_mother_last_name")));//new Parent((String)studentjson.get("Student_mother_first_name"),(String)studentjson.get("Student_mother_last_name")));
                course.addStudent((String)studentjson.get("ID"),student);
            }
            array = (JSONArray) jo.get("Grades");
            if(array != null){
                for(int i = 0 ; i < array.size() ; i++){
                    List<Grade> list = new ArrayList<Grade>();
                    JSONObject grade= (JSONObject) array.get(i);
                    course.addGrade(new Grade(Double.parseDouble((String) grade.get("Partial_score")),Double.parseDouble((String) grade.get("Exam_score")),course.getAllStudents().get(course.getAllStudents().indexOf(new Student((String)grade.get("Student_first_name"),(String)grade.get("Student_last_name")))),course.getName()));
                }
            }
            array = (JSONArray) jo.get("Grades_Assistant");
            for(int i = 0 ; i < array.size() ; i++){
                List<Assistant> list = new ArrayList<Assistant>(course.getAssistents());
                JSONObject grade= (JSONObject) array.get(i);
                Catalog.getInstance().scoreVisitor.add_grade_assistant(list.get(list.indexOf(new Assistant((String)grade.get("Assistant_first_name"),(String)grade.get("Assistant_second_name")))),course.getAllStudents().get(course.getAllStudents().indexOf(new Student((String)grade.get("Student_first_name"),(String)grade.get("Student_last_name")))),name,Double.parseDouble((String)grade.get("Grade")));
            }
            array = (JSONArray) jo.get("Grades_teacher");
            for(int i = 0 ; i < array.size() ; i++){
                JSONObject grade= (JSONObject) array.get(i);
                Catalog.getInstance().scoreVisitor.add_grade_teacher(teacher,course.getAllStudents().get(course.getAllStudents().indexOf(new Student((String)grade.get("Student_first_name"),(String)grade.get("Student_last_name")))),name,Double.parseDouble((String)grade.get("Grade")));
            }
            Catalog.getInstance().addCourse(course);
        }

        System.out.println("----Exmeplu functionalitate----");
        System.out.println("----Ne vom folosi de cursul 3 deoarece avem mai multe note deja existente pe care nu trebuie sa le confirmam inca----");

        System.out.println();

        System.out.println("----Vom prezenta ce se afla in catalogul cursului 3----");
        System.out.println(Catalog.getInstance().getCourses().get(2));

        System.out.println();

        System.out.println("---Vom afisa toti studentii");
        System.out.println(Catalog.getInstance().getCourses().get(2).getAllStudents());

        System.out.println();

        System.out.println("---Vom afisa toate notele studentilor");
        System.out.println(Catalog.getInstance().getCourses().get(2).getGrades());

        System.out.println();

        System.out.println("---Vom afisa toti studentii care au promovat");
        System.out.println(Catalog.getInstance().getCourses().get(2).getGraduatedStudents());

        System.out.println();

        System.out.println("---Vom afisa studentul top");
        System.out.println(Catalog.getInstance().getCourses().get(2).getBestStudent());

        System.out.println();

        System.out.println("---Salvam notele pe care le avem deja");
        Catalog.getInstance().getCourses().get(2).makeBackup();

        System.out.println();
        System.out.println("---Profesorul si asistentii vor valida niste note");

        System.out.println();
        System.out.println("In cazul profesorului");
        System.out.println();
        Catalog.getInstance().scoreVisitor.visit(Catalog.getInstance().getCourses().get(2).getTeacher());

        System.out.println();
        System.out.println("In cazul asistentului 4");
        System.out.println();
        //System.out.println(Catalog.getInstance().scoreVisitor.partialScores.get(new Assistant("Assistant_first_name4","Assistant_second_name4")));
        Catalog.getInstance().scoreVisitor.visit(new Assistant("Assistant_first_name4","Assistant_second_name4"));
        //System.out.println(Catalog.getInstance().scoreVisitor.partialScores);
        System.out.println();
        System.out.println("In cazul asistentului 5");
        System.out.println();
        Catalog.getInstance().scoreVisitor.visit(new Assistant("Assistant_first_name5","Assistant_second_name5"));

        System.out.println("---Vom afisa toate notele studentilor");
        System.out.println(Catalog.getInstance().getCourses().get(2).getGrades());

        System.out.println();

        System.out.println("---Vom afisa toti studentii care au promovat");
        System.out.println(Catalog.getInstance().getCourses().get(2).getGraduatedStudents());

        System.out.println();

        System.out.println("---Vom afisa studentul top");
        System.out.println(Catalog.getInstance().getCourses().get(2).getBestStudent());

        System.out.println();

        System.out.println("---Vom reveni la notele anterioare");
        Catalog.getInstance().getCourses().get(2).undo();

        System.out.println();

        System.out.println("---Vom afisa toate notele studentilor");
        System.out.println(Catalog.getInstance().getCourses().get(2).getGrades());

        System.out.println();

        System.out.println("---Vom afisa toti studentii care au promovat");
        System.out.println(Catalog.getInstance().getCourses().get(2).getGraduatedStudents());

        System.out.println();

        System.out.println("---Vom afisa studentul top");
        System.out.println(Catalog.getInstance().getCourses().get(2).getBestStudent());

        System.out.println();

        new MainFrame();
    }
}