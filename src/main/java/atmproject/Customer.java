package atmproject;

import atmproject.Exceptions.AmountLessThanZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
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
        return this.Name+" "+this.Surname;
    }

    public void displayBalance(){
        double balance = this.getBalance();
        System.out.println(balance);
    }

    public void  addDeposit (double amount){
        try{
            if(amount<0)
                throw new AmountLessThanZero();
            double balance=this.getBalance()+amount;
            this.setBalance(balance);
            System.out.println("Isleminiz basariyla gerceklestirildi.");
            System.out.println("Mevcut bakiyeniz: "+this.getBalance());
        }
        catch (AmountLessThanZero e){
            System.out.println(e.getMessage());
        }
    }

    public void withdraw(double amount){
        if(amount<this.getBalance()){
            double balance=this.getBalance()-amount;
            this.setBalance(balance);
            System.out.println("Mevcut bakiyeniz: "+this.getBalance());
        }
        else
            System.out.println("Bu işlem için yetersiz bakiyeniz var.");
    }

    public void transfer(double amount, String IBAN){

        if(amount<this.getBalance()){
            double balance=this.getBalance()-amount;
            this.setBalance(balance);
            System.out.println("Mevcut bakiyeniz: "+this.getBalance());
            AtmUtility.sendMail();
        }
        else
            System.out.println("Bu işlem için yetersiz bakiyeniz var. Lutfen yeterli bakiyeye ulastiginizda tekrar deneyin.");

    }

    public void doEFT(double amount, String IBAN){

        if(amount<this.getBalance()){
            double balance=this.getBalance()-amount;
            this.setBalance(balance);
            System.out.println("Mevcut bakiyeniz: "+this.getBalance());
            AtmUtility.sendMail();
        }
        else
            System.out.println("Bu işlem için yetersiz bakiyeniz var. Lutfen yeterli bakiyeye ulastiginizda tekrar deneyin.");

    }
}

