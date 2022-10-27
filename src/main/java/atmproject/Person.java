package atmproject;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Person {

    @Id
    @NotEmpty(message = "Id must not be empty")
    protected String Id;

    @NotEmpty(message = "username must not be empty")
    @UniqueElements(message = "username must be unique")
    protected String Username;

    protected String Password;

    @NotEmpty(message="name must not be empty.")
    protected String Name;

    @NotEmpty(message="surname must not be empty.")
    protected String Surname;

    @NotEmpty(message="Date of Birth must not be empty.")
    protected String DateOfBirth;


    @NotEmpty(message="phone number must not be empty.")
    protected String PhoneNumber;


    @NotEmpty(message="email address must not be empty.")
    protected String EmailAddress;

}
