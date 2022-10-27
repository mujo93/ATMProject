package atmproject.DBModels;

import com.opencsv.bean.CsvBindByPosition;
import org.springframework.data.annotation.Id;

public class CustomerModel {
    protected String Id;

    protected String Username;

    protected String Password;

    protected String Name;

    protected String Surname;

    protected String DateOfBirth;


    protected String PhoneNumber;

    protected String EmailAddress;
}
