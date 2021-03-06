import java.lang.reflect.Array;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.*;
import java.util.*;

import static java.time.temporal.ChronoUnit.*;

public class Student {
    private String firstName;
    private String lastName;
    private String identification;
    private ArrayList<Course> courses;
    private ArrayList<LocalDateTime> log = new ArrayList<LocalDateTime>();

    Student(String firstName, String lastName, String identification) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public void setCourses(ArrayList<Course> course) {
        this.courses = course;
    }

    public void setIdentification(String iD) {
        this.identification = iD;
    }

    public boolean setCheckIn() {

        //TODO Add logic to see if the user just checked in or needs checked out.
        //if something goes wrong return false
        log.add(LocalDateTime.now());
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now();
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        log.add(todayMidnight);
        return true;
    }

    public boolean setCheckOut() {

        //TODO Add logic to see if the user checkout makes sense.
        //if something goes wrong return false
        log.remove(log.size() - 1);
        log.add(LocalDateTime.now());
        return true;
    }

    public LocalDateTime getLastCheckinTime() {
        return log.get(log.size() - 2);
    }

    public LocalDateTime getLastCheckoutTime() {
        return log.get(log.size() - 1);
    }

    public ArrayList<LocalDateTime> getLog() {
        return log;
    }

    public void setLog(ArrayList<LocalDateTime> Log) {
        this.log = log;
    }

    public String toString() {
        return firstName + " " + lastName + " " + identification;
    }

    public double getAverageTime() {

        long difference_In_Time = 0;
        long checkins = 0;
        for (int i = 0; i < this.log.size(); i += 2) {

            LocalDateTime d1 = log.get(i + 1);
            LocalDateTime d2 = log.get(i);

            if (d1.isEqual(LocalDateTime.of(d2.toLocalDate(), LocalTime.MIDNIGHT))) {
                continue;
            }
            checkins++;
            difference_In_Time += ChronoUnit.MILLIS.between(d1, d2);
        }

        return (double)difference_In_Time/checkins;

    }

    public ArrayList<Double> getAverageTimeByDay() {

        List<Long> difference_In_Time =  new ArrayList<Long>();
        for(int i = 0; i < 7; i++)
            difference_In_Time.add(0L);
        List<Long> checkins =new ArrayList<Long>();
        for(int i = 0; i < 7; i++)
            difference_In_Time.add(0L);
        for (int i = 0; i < this.log.size(); i += 2) {

            LocalDateTime d1 = log.get(i + 1);
            LocalDateTime d2 = log.get(i);

            if (d1.isEqual(LocalDateTime.of(d2.toLocalDate(), LocalTime.MIDNIGHT))) {
                continue;
            }
            checkins.set(DayOfWeek.from(d2).ordinal(),checkins.get(DayOfWeek.from(d2).ordinal()) + 1);
            difference_In_Time.set(DayOfWeek.from(d2).ordinal(), difference_In_Time.get(DayOfWeek.from(d2).ordinal()) +MILLIS.between(d1,d2));
        }

        ArrayList<Double> answer = new ArrayList<Double>();
        for(int i = 0; i < 7; i++)
            answer.add((double)difference_In_Time.get(i) / checkins.get(i));
        return answer;


    }


}
