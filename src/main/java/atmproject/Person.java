package atmproject;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Person {

    @Id
    protected String Id;

    @NotBlank(message = "username must not be empty")
    @UniqueElements(message = "username must be unique")
    protected String Username;

    protected String Password;

    @NotBlank(message="name must not be empty.")
    protected String Name;

    @NotBlank(message="surname must not be empty.")
    protected String Surname;

    @NotBlank(message="Date of Birth must not be empty.")
    protected String DateOfBirth;


    @NotBlank(message="phone number must not be empty.")
    protected String PhoneNumber;


    @NotBlank(message="email address must not be empty.")
    protected String EmailAddress;

}
