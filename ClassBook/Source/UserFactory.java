public class UserFactory {
    public User getUser(String usertype,String firstname, String lastname){
        if(usertype.equals("Parent"))
            return new Parent(firstname, lastname);
        if(usertype.equals("Student"))
            return new Student(firstname, lastname);
        if(usertype.equals("Teacher"))
            return new Teacher(firstname, lastname);
        if(usertype.equals("Assistant"))
            return new Assistant(firstname, lastname);
        return null;
    }
}
