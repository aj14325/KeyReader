import java.util.ArrayList;
import java.io.*;
import java.io.File.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class FileIO {

    String path = "temp.txt";

    public static ArrayList<Student> getStudents(String path) {
        return null;
    }

    public static ArrayList<Teacher> getTeachers(String path) {
        return null;
    }

    public static ArrayList<Course> getClasses(String path) {
        return null;
    }

    public static void main(String args[]) {
        //Gets date and time
        Student s = new Student("name","lastname","ID");
        Course c = new Course("math");
        ArrayList<Course> ac = new ArrayList<Course>();
        ArrayList<Student> as = new ArrayList<Student>();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();

        ac.add(c);
        s.setCourses(ac);
        as.add(s);

        s = new Student("aaron","lastname","ID");
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCheckIn();
        s.setCheckOut();
        s.setCourses(ac);
        as.add(s);
        as.add(s);
        printRawData(as,null,null,"temp.txt");
    }

    public static void printRawData(ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses, String path) {

        try {

            FileWriter fo = new FileWriter(path);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            for (int i = 0; i < students.size(); i++) {
                Student s = students.get(i);
                fo.write(s.getFirstName() + ", " + s.getLastName() + " ," + s.getIdentification() + " ," + s.getCourses().get(0).toString() + "\n");
                ArrayList<LocalDateTime> log =  s.getLog();
                for (int j = 0; j < log.size(); j++) {
                    fo.write(dtf.format(log.get(j)) +"\n");
                }
            }
            fo.close();

        } catch (Exception e) {

        }
    }

    public static void printReports(ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses, String path) {

        File f = new File(path);

        int i = 1;
        while(f.exists()){
            path = path + "_" + i++;
            f = new File(path);
        }

        ArrayList<String> list = new ArrayList<String>();

        for( i = 0; i < students.size(); i++){
            Student s = students.get(i);
            list.add(s.getLastName() + "," + s.getFirstName() + ", " + s.getAverageTime());
        }

        try{
            FileWriter fo = new FileWriter(path);
            for (i = 0; i < list.size(); i++) {
                fo.write(list.get(i));
            }
        }catch(Exception e){

        }

    }


}
