import java.util.ArrayList;

public class Course {

    public String Name;
    private ArrayList<Student> students = new ArrayList<Student>(); //Array list for Student
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>(); //Array list for Teacher


    public static void main(String args[]) {

        Course c = new Course("info 2550");

        Student stu1 = new Student("Aaron", "Johnson", "00000000");
        Student stu2 = new Student("Justin", "Ahlers", "00000001");
        Teacher t = new Teacher();



        c.add(stu1);
        c.add(stu2);
        c.add(t);
        c.remove(stu1);
        c.remove(stu2);
        c.remove(t);


        ArrayList<Student> stus = c.getStudents();
        for( Student elem : stus){
            System.out.println(elem);
        }
        ArrayList<Teacher> teach = c.getTeachers();
        for( Teacher elem : teach){
            System.out.println("FOO!");

        }


        //System.out.println("Student");
        //System.out.println("Teacher");
        //System.out.println("Time");
    }

    public Course(String name){
        this.Name = name;
    }
    public int compareTo (Course c){

        return Name.compareTo(c.Name);

    }
    public void add(Student s){         //Add Students when they check in
        students.add(s);
    }
    public void remove(Student s){      //Removes students when they check out
        students.remove(s);
    }
    public void add(Teacher t){         //Add Teachers when they check in
        teachers.add(t);
    }
    public void remove(Teacher t){     //Remove Teachers when they check out
        teachers.remove(t);
    }


    public ArrayList<Student> getStudents(){
        return students;
    }
    public ArrayList<Teacher> getTeachers() {
        return teachers;

    }



}