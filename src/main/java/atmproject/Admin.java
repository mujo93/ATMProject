package atmproject;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import lombok.*;

import javax.management.relation.Role;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static atmproject.colors.GREEN;

@AllArgsConstructor
@Getter
@Setter
public class Admin extends Person {
    public static final String SUCCES_MESSAGE = GREEN+"Isleminiz Basariya Gerceklestirildi";
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


    public void deleteCustomer(String username) throws IOException, CsvException {

        //1- load File into a map
       Map<String,Customer> customerMap= loadDataFromCustomerDBFileIntoMap("CustomerDB");
        //2- delete the row by username key in the map
        customerMap.remove(username);
        //3- refill the CustomerDB with the updated map
        loadDataFromCustomerMapIntoCustomerDBFile(customerMap);
        System.out.println(Customer.SUCCES_MESSAGE);
    }

    public void updateCustomer(String id){

    }

    public Customer GetCustomer(String username){
        return new Customer();
    }

    public static Map<String,Customer> loadDataFromCustomerDBFileIntoMap(String DBName) throws IOException, CsvException {
        String customerDB=Paths.DBFolder_PATH+DBName+".csv";
        HashMap<String,Customer> customerDBMap= new HashMap<>();
        FileReader reader=new FileReader(customerDB);
        CSVReader csvReader=new CSVReader(reader);
        csvReader.skip(1);
        String [] record=null;
        while((record = csvReader.readNext()) != null){
            // username is kept in second column of the row
            customerDBMap.put(record[1],new Customer(record[0],record[1],record[2],record[3],record[4],
                    record[5],record[6],record[7],record[8],Double.parseDouble(record[9])));
        }
        return customerDBMap;
    }

    public static void loadDataFromCustomerMapIntoCustomerDBFile(Map<String,Customer> customerMap) throws IOException {

        File file = new File(Paths.DBFolder_PATH+"CustomerDB.csv");
        try (FileWriter writer = new FileWriter(file)) {
            CSVWriter csvWriter=new CSVWriter(writer);
            String[] columns= new String[]{"Id","Username","Password", "Name","Surname",
                    "Phone Number","Email Address","Date Of Birth", "Account Number", "Initial Balance"};
            csvWriter.writeNext(columns);
            customerMap.forEach((u, c) -> {
                csvWriter.writeNext( new String[]{c.getId(),c.getUsername(),c.getPassword(),c.getName(),
                        c.getSurname(), c.getPhoneNumber(),c.getEmailAddress(),c.getDateOfBirth(),
                        c.getAccountNumber(),String.valueOf(c.getBalance())});
            });
        }
    }
}
