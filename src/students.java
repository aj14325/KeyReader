import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.*;
public class students {
   String firstName;
   String lastName;
   String identification ;
   String inputClass;
   Date checkIn;
   Date checkOut;


   students(String firstName, String lastName, String identification, String inputClass, Date checkIn, Date checkOut){
       this.firstName = firstName;
       this.lastName = lastName;
       this.identification = identification;
       this.inputClass = inputClass;
       this.checkIn = checkIn;
       this.checkOut = checkOut;
   }

    public void setFirstName(String fName){
        this.firstName = fName;
    }

    public void setLastName(String lName){
        this.lastName = lName;
    }

    public void setInputClass(String className){
        this.inputClass = className;
    }

    public void setIdentification(String iD){
        this.identification = iD;
    }
    public void setCheckIn(String timeA){
        this.checkIn = timeA;
    }
    public void setCheckOut(String timeB){
        this.checkOut = timeB;
    }

}
