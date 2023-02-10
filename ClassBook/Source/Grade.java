import java.util.ArrayList;
import java.util.Iterator;

public class Grade implements Comparable,Cloneable,Subject{
    private Double partialScore,examScore;
    private Student student;
    private String course;

    public Grade(Double partialScore, Double examScore,Student student,String course){
        this.partialScore = partialScore;
        this.examScore = examScore;
        this.student = student;
        this.course = course;
        if(Catalog.getInstance().observers.contains(new Notification(student.getFather(),student.getMother(),examScore,course,partialScore)) == false)
            addObserver(new Notification(student.getFather(),student.getMother(),examScore,course,partialScore));
    }

    public Grade(Grade grade){
        partialScore = grade.getpartialScore();
        examScore = grade.getExamScore();
        student = grade.getStudent();
        course = grade.getCourse();
        if(Catalog.getInstance().observers.contains(new Notification(student.getFather(),student.getMother(),examScore,course,partialScore)) == false)
            addObserver(new Notification(student.getFather(),student.getMother(),examScore,course,partialScore));
    }

    public Double getExamScore() {
        return examScore;
    }

    public Double getpartialScore() {
        return partialScore;
    }

    public String getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
        //notifyObservers(this);
    }

    public void setpartialScore(Double partialScore) {
        this.partialScore = partialScore;
        //notifyObservers(this);
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getTotal(){
        if(partialScore == null)
            return examScore;
        if(examScore == null)
            return partialScore;
        return partialScore + examScore;
    }

    @Override
    public int compareTo(Object o) {
        Grade obj = (Grade)o;
        double d1 = getTotal().doubleValue();
        double d2 = obj.getTotal().doubleValue();
        if(d1 - d2 < 0)
            return -1;
        else if(d1 -d2 > 0)
            return 1;
        else
            return 0;
    }

    public Object clone() throws CloneNotSupportedException{
        Grade e = new Grade(getpartialScore(),getExamScore(),getStudent(),getCourse());
        return e;
    }

    public String toString(){
        return "Studentul " + student + " are la materia " + course + "\n" +
                "->partial " + partialScore + "\n" +
                "->examen " + examScore + "\n" +
                "->total " + getTotal() + "\n";
    }


    @Override
    public void addObserver(Observer observer) {
        Catalog.getInstance().observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        Catalog.getInstance().observers.remove(Catalog.getInstance().observers.indexOf(observer));
    }

    @Override
    public void notifyObservers(Grade grade) {

    }
}
