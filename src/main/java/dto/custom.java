package dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class custom {
@Id
int id;
String Name;
String Email;
String Password;
String gender;
long mobno;
LocalDate dob;
}
