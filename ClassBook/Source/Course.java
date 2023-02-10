import java.util.*;

public abstract class Course {
    private String name;
    private Teacher teacher;
    private HashSet<Assistant> assistents;
    private ArrayList<Grade> grades;
    private Hashtable<String,Group> groups;
    private int credit_points;
    private Snapshot backup;
    private String strategy;

    public Course(CourseBuilder builder){
        this.name = builder.name;
        this.teacher = builder.teacher;
        this.credit_points = builder.credit_points;
        this.strategy = builder.strategy;
        assistents = new HashSet<>();
        grades = new ArrayList<>();
        groups = new Hashtable<>();
        backup = new Snapshot();
    }

    public void addAssistant(String ID, Assistant assistant){
        assistents.add(assistant);
        groups.put(ID,new Group(ID,assistant));
    }

    public void addStudent(String ID, Student student){
        if(getAllStudents().contains(student) == true)
            return;
        if(groups.containsKey(ID) == true)
            groups.get(ID).add(student);
    }

    public void addGroup(Group group){
        int ok = 0;
        for(Assistant assistant : assistents){
            if(assistant.getFirstName().equals(group.getAssistant().getFirstName()) && assistant.getLastName().equals(group.getAssistant().getLastName())){
                groups.put(group.getID(),group);
            }
        }
    }

    public void addGroup(String ID, Assistant assistant){
        groups.put(ID,new Group(ID,assistant));
    }

    public void addGroup(String ID, Assistant assist, Comparator<Student> comp){
        groups.put(ID,new Group(ID,assist,comp));
    }

    public Grade getGrade(Student student){
        for(Grade i : grades){
            if(i.getStudent().equals(student) == true)
                return i;
        }
        return null;
    }

    public void addGrade(Grade grade){
        Student ok = null;
        for(Group i : groups.values()){
            for(Student j : i){
                if((grade.getStudent().equals(j)) == true){
                    ok = j;
                    break;
                }
            }
        }
        int ok1 = 0;
        if(ok != null){
            for(Grade i : grades){
               if(i.getStudent().equals(ok)){
                   i.setpartialScore(grade.getpartialScore());
                   i.setExamScore(grade.getExamScore());
                   ok1 = 1;
                   break;
               }
            }
        }
        if(ok1 == 0){
            grades.add(grade);
        }
        Catalog.getInstance().scoreVisitor.notifyObservers(grade);
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> students = new ArrayList<>();
        for(Group i : groups.values()){
            for(Student j : i){
                students.add(j);
            }
        }
        return students;
    }

    public HashMap<Student, Grade> gettAllStudentGrades(){
        HashMap<Student,Grade> studentsgrades = new HashMap<>();
        for(Grade i : grades){
            studentsgrades.put(i.getStudent(),i);
        }
        return studentsgrades;
    }

    public abstract ArrayList<Student> getGraduatedStudents();

    public HashSet<Assistant> getAssistents() {
        return assistents;
    }

    public Hashtable<String, Group> getGroups() {
        return groups;
    }

    public int getCredit_points() {
        return credit_points;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setAssistents(HashSet<Assistant> assistents) {
        this.assistents = assistents;
    }

    public void setCredit_points(int credit_points) {
        this.credit_points = credit_points;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public void setGroups(Hashtable<String, Group> groups) {
        this.groups = groups;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public static abstract class CourseBuilder{
        private String name;
        private Teacher teacher;
        private int credit_points;

        private String strategy;

        public CourseBuilder name (String name){
            this.name = name;
            return this;
        }

        public CourseBuilder teacher(Teacher teacher){
            this.teacher = teacher;
            return this;
        }

        public CourseBuilder credit_points(int credit_points){
            this.credit_points = credit_points;
            return this;
        }

        public CourseBuilder strategy(String strategy){
            this.strategy = strategy;
            return this;
        }

        public abstract Course build();
    }

    public Student getBestStudent(){
        if(strategy.equals("Partial"))
            return new BestPartialScore().thebest(gettAllStudentGrades());
        else if(strategy.equals("Exam"))
            return new BestExamScore().thebest(gettAllStudentGrades());
        return new BestTotalScore().thebest(gettAllStudentGrades());
    }

    private class Snapshot{
        private ArrayList<Grade> grades;

        public Snapshot(){
            grades = new ArrayList<>();
        }

        public ArrayList<Grade> getGrades() {
            return grades;
        }

        public void setGrades(ArrayList<Grade> grades) {
            this.grades.removeAll(this.grades);
            this.grades.addAll(grades);
        }
        public String toString(){
            return grades.toString();
        }
    }

    public void makeBackup(){
        backup.getGrades().removeAll(backup.getGrades());
        backup.setGrades(grades);
    }

    public void undo(){
        grades.removeAll(grades);
        grades.addAll(backup.getGrades());
    }

    public ArrayList<Grade> getBackup() {
        return this.backup.getGrades();
    }


    public String toString(){
        return "Numele cursului: " + name + "\n" +
                "Titularul cursului: " + teacher + "\n" +
                "Puncte credit: " + credit_points + "\n" +
                "Asistentii: " + assistents + "\n" +
                "Grupe: " + groups + "\n" +
                "Note: " + grades + "\n";
    }

}
