package atmproject;

import atmproject.Exceptions.AmountLessThanZero;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends Person{

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String SUCCES_MESSAGE = GREEN+"Isleminiz Basariya Gerceklestirildi";

    private double Balance;
    private String AccountNumber;

    public Customer(String username,String password,
                    String name, String surname, String PhoneNumber,
                    String emailAddress, String dateOfBirth,double initialBalance) {

        this.Id=AtmUtility.IdCreator();
        this.Username=username;
        this.Password=password;
        this.Name=name;
        this.Surname=surname;
        this.DateOfBirth=dateOfBirth;
        this.PhoneNumber=PhoneNumber;
        this.EmailAddress=emailAddress;
        this.AccountNumber=AtmUtility.createAccountNumber();
        this.Balance=initialBalance;
    }

    public Customer(String Id,String username,String password,
                    String name, String surname, String phoneNumber,
                    String emailAddress, String dateOfBirth, String accountNumber,double balance)
    {
        this.Id=Id;
        this.Username=username;
        this.Password=password;
        this.Name=name;
        this.Surname=surname;
        this.PhoneNumber=phoneNumber;
        this.EmailAddress=emailAddress;
        this.DateOfBirth=dateOfBirth;
        this.AccountNumber=accountNumber;
        this.Balance=balance;
    }

    public String getFullName(){
        return this.Name+"_"+this.Surname;
    }

    public void displayBalance(){
        double balance = this.getBalance();
        System.out.println(balance);
    }

    public void  addDeposit (double amount, Customer customer){
        try{
            if(amount<0)
                throw new AmountLessThanZero();
            double balance=findBalance(customer)+amount;
            this.setBalance(balance);
            String action="adding deposits.";
            appendRecordToTransactionFile(customer,action,amount);
            System.out.println(SUCCES_MESSAGE);
            System.out.println("Mevcut bakiyeniz: "+this.getBalance());
        }
        catch (AmountLessThanZero e){
            System.out.println(e.getMessage());
        }
    }

    public void withdraw(double amount,Customer customer){
        double balance=findBalance(customer);
        if(amount<balance){
            this.setBalance(balance-amount);
            String action="withdrawing deposits.";
            appendRecordToTransactionFile(customer,action,amount);
            System.out.println(SUCCES_MESSAGE);
            System.out.println(RED+"Mevcut bakiyeniz: "+this.getBalance());
        }
        else
            System.out.println(RED+"Bu işlem için yetersiz bakiyeniz var.");
    }

    public void transfer(Customer customer,double amount, String IBAN){
        double balance=findBalance(customer);
        if(amount<balance){
            this.setBalance(balance-amount);
            String action="withdrawing deposits.";
            appendRecordToTransactionFile(customer,action,amount);
            System.out.println(SUCCES_MESSAGE);
            System.out.println(YELLOW+amount+"TL "+IBAN+" no`lu hesaba gonderilmistir.");
            System.out.println(RED+"Mevcut bakiyeniz: "+this.getBalance());
        }
        else
            System.out.println(RED+"Bu işlem için yetersiz bakiyeniz var.");
    }

    public void doEFT(Customer customer,double amount, String IBAN){
        double balance=findBalance(customer);
        if(amount<balance){
            this.setBalance(balance-amount);
            String action="withdrawing deposits.";
            appendRecordToTransactionFile(customer,action,amount);
            System.out.println(SUCCES_MESSAGE);
            System.out.println(YELLOW+amount+"TL "+IBAN+" no`lu hesaba gonderilmistir.");
            System.out.println(RED+"Mevcut bakiyeniz: "+this.getBalance());
        }
        else
            System.out.println(RED+"Bu işlem için yetersiz bakiyeniz var.");

    }

    public Double findBalance(Customer customer){
        String mostRecentBalance="";
        String filePath=Paths.CustomerTransactionsFolder_PATH+customer.getName()+"_"+customer.getSurname()+".csv";
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                mostRecentBalance=values[values.length-2];
            }
            System.out.println("Bakiyeniz: "+mostRecentBalance);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Double.parseDouble(mostRecentBalance);
    }

    public void appendRecordToTransactionFile(Customer customer,String action,double amount){
        String path=Paths.CustomerTransactionsFolder_PATH+customer.getFullName()+".csv";
            try (FileWriter writer = new FileWriter(path,true)) {
                String[] transaction= new String[]{customer.getFullName(), action,
                        String.valueOf(amount), String.valueOf(customer.getBalance()),
                        AtmUtility.getCurrentDate()};
                CSVWriter csvWriter = new CSVWriter(writer);
                csvWriter.writeNext(transaction);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
    }


}

