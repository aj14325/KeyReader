import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;
public class Student {
    String firstName;
    String lastName;
    String identification ;
    ArrayList<Course> Courses;
    ArrayList<LocalDateTime> log;


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
    public boolean setCheckIn(LocalDateTime time){

        //TODO Add logic to see if the user just checked in or needs checked out.
        //if something goes wrong return false
        log.add(time);
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        log.add(todayMidnight);
        return true;
    }
    public boolean setCheckOut(LocalDateTime time){

        //TODO Add logic to see if the user checkout makes sense.
        //if something goes wrong return false
        log.remove(log.size()-1);
        log.add(time);
        return true;
    }

    public String toString(){
        return firstName + " " + lastName + " " + identification;
    }
}
