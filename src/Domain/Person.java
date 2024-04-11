package Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    private String lastName;
    private String firstName;
    private String middleName;


    private Date dateOfBirth;
    private long phoneNumber;
    private char gender;


    public Person(String lastName, String firstName, String middleName, Date dateOfBirth, long phoneNumber, char gender) throws ParseException {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }


@Override
public String toString() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    String formattedDate = dateFormat.format(dateOfBirth);

    return "<" + lastName + ">" +
            "<" + firstName + ">" +
            "<" + middleName + ">" +
            "<" + formattedDate + ">" +
            "<" + phoneNumber + ">" +
            "<" + gender + ">" ;
}
}