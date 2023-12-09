package ssf.iss.d13workshop.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @NotEmpty (message = "Name is mandatory")
    @Size (min=3, max=64, message="Name must be between 3 and 64 characters")
    private String name;

    @NotEmpty (message = "Email is mandatory")
    @Email (message = "Invalid email format")
    @Size (max=30, message="email exceeded 30 characters")
    private String email;

    @NotEmpty (message = "Phone Number is mandatory")
    @Pattern (regexp = "(8|9)[0-9]{7}", message="Invalid phone number entered")
    private String phoneNo;
    
    @DateTimeFormat (pattern="yyyy-MM-dd")
    @Past (message = "Date of birth must be before than today")
    private LocalDate DoB;

    private String id;

    public String validAge(){
        LocalDate now = LocalDate.now();
        if ((now.getYear() - DoB.getYear()) < 10){
            return "Age cannot be less than 10!";
        }

        if ((now.getYear() - DoB.getYear()) > 100){
            return "Age cannot be more than 100!";
        }

        return null;
    }



}
