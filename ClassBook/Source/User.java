public class User {
    private String firstName, lastName;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString(){
        return firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean equals(Object obj){
        User o = (User) obj;
        if(o.getFirstName().equals(firstName) && o.getLastName().equals(lastName))
            return true;
        return false;
    }

}
