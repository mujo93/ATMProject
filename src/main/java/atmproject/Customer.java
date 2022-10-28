package atmproject;

import atmproject.Exceptions.AmountLessThanZero;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends Person{
    public static final String SUCCES_MESSAGE = colors.GREEN+"Isleminiz Basariya Gerceklestirildi";
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

    public void  addDeposit (double amount, Customer customer){
        try{
            if(amount<0)
                throw new AmountLessThanZero();
            double balance=findBalance(customer)+amount;
            this.setBalance(balance);
            String action="adding deposits.";
            appendRecordToTransactionFile(customer,action,amount);
            System.out.println(SUCCES_MESSAGE);
            System.out.println(colors.GREEN+"Mevcut bakiyeniz: "+this.getBalance());
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
            System.out.println(colors.RED+"Mevcut bakiyeniz: "+this.getBalance());
        }
        else
            System.out.println(colors.RED+"Bu işlem için yetersiz bakiyeniz var.");
    }

    public void transfer(Customer customer,double amount, String IBAN){
        double balance=findBalance(customer);
        if(amount<balance){
            this.setBalance(balance-amount);
            String action= colors.GREEN+"transferred deposits to"+IBAN;
            appendRecordToTransactionFile(customer,action,amount);
            System.out.println(SUCCES_MESSAGE);
            System.out.println(colors.YELLOW+amount+"TL "+IBAN+" no`lu hesaba gonderilmistir.");
            System.out.println(colors.GREEN+"Mevcut bakiyeniz: "+this.getBalance());
        }
        else
            System.out.println(colors.RED+"Bu işlem için yetersiz bakiyeniz var.");
    }

    public void doEFT(Customer customer,double amount, String IBAN){
        double balance=findBalance(customer);
        if(amount<balance){
            this.setBalance(balance-amount);
            String action="withdrawing deposits.";
            appendRecordToTransactionFile(customer,action,amount);
            System.out.println(SUCCES_MESSAGE);
            System.out.println(colors.YELLOW+amount+"TL "+IBAN+" no`lu hesaba gonderilmistir.");
            System.out.println(colors.RED+"Mevcut bakiyeniz: "+this.getBalance());
        }
        else
            System.out.println(colors.RED+"Bu işlem için yetersiz bakiyeniz var.");

    }

    public Double findBalance(Customer customer){
        String mostRecentBalance="";
        String filePath=Paths.CustomerTransactionsFolder_PATH+customer.getName()+"_"+customer.getSurname()+".csv";
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                mostRecentBalance=values[values.length-2];
            }
            System.out.println(colors.GREEN+"Bakiyeniz: "+mostRecentBalance);
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

    public String toString(){
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",this.Id,this.Username,
                this.Password,this.Name,this.Surname,this.PhoneNumber,this.EmailAddress,
                this.DateOfBirth,this.AccountNumber,String.valueOf(this.Balance));
    }


}

