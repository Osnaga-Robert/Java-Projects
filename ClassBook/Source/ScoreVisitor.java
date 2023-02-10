import java.util.*;

public class ScoreVisitor implements Visitor,Subject{

    HashMap<Teacher,List<Tuple>> examScores;
    HashMap<Assistant,List<Tuple>> partialScores;

    public ScoreVisitor(){
        examScores = new HashMap<>();
        partialScores = new HashMap<>();
    }


    public void add_grade_teacher(Teacher teacher,Student student, String name, Double grade){
        List<Tuple> list = examScores.get(teacher);
        Tuple<Student,String,Double> tuple = new Tuple<>();
        tuple.add(student,name,grade);
        if(list == null){
            list = new ArrayList<Tuple>();
            list.add(tuple);
            examScores.put(teacher,list);
        }
        else{
            //System.out.println("Am fost aici");
            if(!list.contains(tuple))
                list.add(tuple);
        }
    }

    public void add_grade_assistant(Assistant assistant,Student student, String name, Double grade){
        List<Tuple> list = partialScores.get(assistant);
        Tuple<Student,String,Double> tuple = new Tuple<>();
        tuple.add(student,name,grade);
        if(list == null){
            list = new ArrayList<Tuple>();
            list.add(tuple);
            partialScores.put(assistant,list);
        }
        else{
            if(!list.contains(tuple))
                list.add(tuple);
        }
    }

    @Override
    public void visit(Assistant assistant) {
        for(Assistant i : partialScores.keySet()){
            if(i.equals(assistant)){
                for(Tuple j : partialScores.get(i)){
                    for(Course k : Catalog.getInstance().getCourses()){
                        int ok = 0;
                        for(Assistant l : k.getAssistents())
                            if(l.equals(assistant))
                                ok = 1;
                        if(j.val2.equals(k.getName()) && ok == 1){
                            if(k.getGrade(j.val1) != null){
                                k.getGrade(j.val1).setpartialScore(j.val3);
                                notifyObservers(k.getGrade(k.getAllStudents().get(k.getAllStudents().indexOf(j.val1))));
                            }
                            else{
                                k.addGrade(new Grade(j.val3,null,k.getAllStudents().get(k.getAllStudents().indexOf(j.val1)),j.val2));
                            }

                        }
                    }
                }
            }
        }
    }

    @Override
    public void visit(Teacher teacher) {
        for(Teacher i : examScores.keySet()){
            if(i.equals(teacher)){
                for(Tuple j : examScores.get(i)){
                    for(Course k : Catalog.getInstance().getCourses()){
                        if(j.val2.equals(k.getName())){
                            if(k.getGrade(j.val1) != null){
                                k.getGrade(j.val1).setExamScore(j.val3);
                                notifyObservers(k.getGrade(k.getAllStudents().get(k.getAllStudents().indexOf(j.val1))));
                            }
                            else
                                k.addGrade(new Grade(null,j.val3,k.getAllStudents().get(k.getAllStudents().indexOf(j.val1)),j.val2));
                        }
                    }
                }
            }
        }
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(Grade grade) {
        Notification notification = new Notification(grade.getStudent().getFather(),grade.getStudent().getMother(),grade.getExamScore(),grade.getCourse(),grade.getpartialScore());
         if(Catalog.getInstance().observers.contains(notification)){
            Catalog.getInstance().observers.get(Catalog.getInstance().observers.indexOf(notification)).update(notification);

        }
    }

    private class Tuple<T1 extends Student,T2 extends String,T3 extends Double>{
        private T1 val1;
        private T2 val2;
        private T3 val3;
        public void add(T1 val1,T2 val2,T3 val3){
            this.val1 = val1;
            this.val2 = val2;
            this.val3 = val3;
        }

        public String toString(){
            return "Studentul " + val1 + " are note " + val3 + " la cursul " + val2 + "\n";
        }

    }
}
