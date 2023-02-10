public class Notification implements Observer{
    private Parent father_name;

    private Parent mother_name;

    private Double final_grade;

    private Double partial_grade;

    private String course;


    public Notification(Parent father_name, Parent mother_name,Double final_grade,String course, Double partial_grade){
        this.father_name = father_name;
        this.mother_name = mother_name;
        this.final_grade = final_grade;
        this.partial_grade = partial_grade;
        this.course = course;
    }

    public String toString() {
        if (partial_grade == null)
            return "Domnule " + father_name + " si doamna " + mother_name + " va anuntam ca fiul dumneavoiastra a luat nota " + (final_grade) + " la " + course + "\n";
        if (final_grade == null)
            return "Domnule " + father_name + " si doamna " + mother_name + " va anuntam ca fiul dumneavoiastra a luat nota " + (partial_grade) + " la " + course + "\n";
        return "Domnule " + father_name + " si doamna " + mother_name + " va anuntam ca fiul dumneavoiastra a luat nota " + (final_grade + partial_grade) + " la " + course + "\n";
    }
    @Override
    public void update(Notification notification) {
        System.out.println(notification);
        for(Course i : Catalog.getInstance().getCourses()){
            for(Student j : i.getAllStudents()){
                if( (j.getMother() != null && mother_name != null && j.getMother().equals(mother_name)) || ( j.getFather() != null && father_name != null && j.getFather().equals(father_name))){
                    j.add_notification(notification);
                    return;
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        Notification o = (Notification) obj;
        if( (father_name != null && o.father_name != null && father_name.equals(o.father_name)) || ( mother_name != null && o.mother_name != null && mother_name.equals(o.mother_name) ))
            return true;
        return false;
    }
}
