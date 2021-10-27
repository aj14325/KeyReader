import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;
public class Student {
    String firstName;
    String lastName;
    String identification ;
    ArrayList<Course> Courses;
    Date checkIn;
    Date checkOut;


    Student(String firstName, String lastName, String identification){
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
    }

    public void setFirstName(String fName){
        this.firstName = fName;
    }

    public void setLastName(String lName){
        this.lastName = lName;
    }

    public void setInputClass(ArrayList<Course> className){
        this.Courses = className;
    }

    public void setIdentification(String iD){
        this.identification = iD;
    }
    public void setCheckIn(Date time){
        this.checkIn = time;
    }
    public void setCheckOut(Date time){
        this.checkOut = time;
    }

    public String toString(){
        return firstName + " " + lastName + " " + identification;
    }
}
