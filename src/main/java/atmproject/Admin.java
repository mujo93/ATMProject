package atmproject;

import com.opencsv.CSVWriter;
import lombok.*;

import javax.management.relation.Role;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        FileClass.appendRecordToCSVFile(customerDBPath,newCustomer);
        createCustomerTransactionFile(newCustomer);
        return newCustomer;
    }


    public static void createCustomerTransactionFile(Customer newCustomer) {
        String path=Paths.CustomerTransactionsFolder_PATH+newCustomer.getName()+"_"+newCustomer.getSurname()+".csv";

        try {
            File file = new File(path);

            // true if file does no exist and was created successfully.
            // false if file is already exists
            if (file.createNewFile()) {

            } else {
                System.out.println("File already exists.");
            }

            try (FileWriter writer = new FileWriter(file)) {
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

    public static void main(String[] args) throws IOException {
/*        String path="/Users/mucahitbayrak/IdeaProjects/airties-mujo93/src/main/java/" +
                "atmproject/Database/Transactions/Mucahit.csv";
        System.out.println(System.getProperty("user.home"));
        File file =new File(path);
        file.createNewFile();*/
    }





}
