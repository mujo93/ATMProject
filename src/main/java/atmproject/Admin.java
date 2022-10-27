package atmproject;

import com.opencsv.CSVWriter;
import lombok.*;

import javax.management.relation.Role;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@AllArgsConstructor
@Getter
@Setter
public class Admin extends Person {
    public Admin(String username,String password,
                 String name, String surname, String PhoneNumber,
                 String emailAddress, String dateOfBirth) {

        this.Id=AtmUtility.IdCreator();
        this.Username=username;
        this.Password=password;
        this.Name=name;
        this.Surname=surname;
        this.DateOfBirth=dateOfBirth;
        this.PhoneNumber=PhoneNumber;
        this.EmailAddress=emailAddress;
    }

    //Note: The validations have not been implemented since the deadline for project review is soon. Therefore,
    //validations will be implemented in the next scrum.
    public Customer createCustomer(String username,String password, String name, String surname,
                                          String dateOfBirth, String phoneNumber, String email,
                                          double initialBalance ){

        Customer newCustomer= new Customer(username,password,name,surname,dateOfBirth,
                                           phoneNumber,email,initialBalance);
        String customerDBPath=Paths.DBFolder_PATH+"CustomerDB.csv";
        saveCustomer(customerDBPath,newCustomer);
        createCustomerTransactionFile(newCustomer);

        return newCustomer;
    }


    public static void saveCustomer(String filePath,Customer nCus){
        try {
            File file = new File(filePath);
            try (FileWriter writer = new FileWriter(file,true)) {

                String[] customerRecord= new String[]{nCus.getId(), nCus.getUsername(),nCus.getPassword(),nCus.getName(),
                        nCus.getSurname(), nCus.getPhoneNumber(),nCus.getEmailAddress(),nCus.getDateOfBirth(),
                        nCus.getAccountNumber(), String.valueOf(nCus.getBalance())};

                CSVWriter csvWriter = new CSVWriter(writer);
                csvWriter.writeNext(customerRecord);
            }
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void createCustomerTransactionFile(Customer newCustomer) {
        String path=Paths.CustomerTransactionsFolder_PATH+newCustomer.getName()+"_"+newCustomer.getSurname()+".csv";
        try {
            File file = new File(path);

            // true if file does no exist and was created successfully.
            // false if file is already exists
            if (file.createNewFile()) {
            } else {
                System.out.println(colors.CYAN+"File already exists.");
            }
            try (FileWriter writer = new FileWriter(file,true)) {

                String[] firstTransaction= new String[]{newCustomer.getName()+" "+ newCustomer.getSurname(), "The bank account has been opened","0",
                        String.format(String.valueOf(newCustomer.getBalance())),AtmUtility.getCurrentDate()};
                CSVWriter csvWriter = new CSVWriter(writer);
                String[] columns=new String[]{"Full Name","Transaction Description", "Amount", "Balance","Date"};
                csvWriter.writeNext(columns);
                csvWriter.writeNext(firstTransaction);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteCustomer(String id){
        //for

    }

    public void updateCustomer(String id){

    }

    public Customer GetCustomer(String id){
        Customer customer= (Customer) Customer.builder().build();
        return customer;
    }

}
