package ssf.iss.day13class.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    @NotEmpty (message = "First Name is mandatory") //shows this message when its not empty
    @Size (min =3, max=30, message = "First name must be between 3 to 30 characters")
    private String firstName;

    @NotEmpty (message = "Last Name is mandatory")
    @Size (min =2, max=20, message = "Last name must be between 2 to 20 characters")
    private String lastName;

    @Email (message = "Invalid email format") // if not email, show this message
    @Size (max =30, message = "Email length exceeded 30 characters")
    @NotBlank (message = "email is a mandatory field")
    private String email;

    @Pattern (regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered") //number has to start with 8 or 9, rest of 7 numbers have to be btwn 0-9
    private String phoneNo;

    @Min (value = 1500, message = "Minimum salary starts from 1500")
    @Max (value = 500000, message = "Max salary cannot exceed 500000")
    private Integer salary;

    @DateTimeFormat (pattern = "yyyy-MM-dd")
    @Past (message = "Birthdate must be before today")
    private Date birthday;

    @Digits (fraction = 0, integer = 6, message = "Postal code format (6 digits)")
    @Min (value = 111111, message = "Starts from 111111")
    @Max (value = 999999, message = "Ends at 899999")
    private Integer postalCode;

    
}
