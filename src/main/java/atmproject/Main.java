package atmproject;

import atmproject.Exceptions.AmountLessThanZero;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class Main {

    private Scanner scanner;

    public Main(){
        scanner=new Scanner(System.in);
    }
    public static void main(String[] args) throws FileNotFoundException {

        displayInitialScreen();
        int userInputForInitialScreen=getUserInput();

        if(userInputForInitialScreen==1){
            Admin LoggedInAdmin= (Admin) login(userInputForInitialScreen);//case sensitive
            if(LoggedInAdmin!=null){
                displayAdminMenu();
                int userInputForAdminMenu=getUserInput();
                selectAdminOptions(userInputForAdminMenu,LoggedInAdmin);
            }
            else{
                System.out.println("Lutfen sifrenizi veya kullanici adinizi dogru girdiginizden emin olun.\n" +
                        "Daha Sonra tekrar deneyin.");
                System.exit(0);
            }
        }

        else if (userInputForInitialScreen==2){
            Customer LoggedInCustomer= (Customer) login(userInputForInitialScreen); //case sensitive
            if(LoggedInCustomer!=null){
                displayCustomerMenu();
                int userInputForCustomerMenu=getUserInput();
                selectCustomerOptions(userInputForCustomerMenu,LoggedInCustomer);
            }
            else {
                System.out.println("Lutfen sifrenizi veya kullanici adinizi dogru girdiginizden emin olun.\n" +
                        "Daha Sonra tekrar deneyin.");
                System.exit(0);
            }
        }

    }
    public static void  displayInitialScreen(){
        System.out.println("Nasil giris yapmak istersiniz?\n" +
                            "1-) Admin\n" +
                            "2-) Customer");
    }

    public static void displayAdminMenu(){
        System.out.println("ADMIN MENU\n" +
                "==============================================\n" +
                "1-)Musteri olustur\n" +
                "2-)Musteri Goruntule\n" +
                "3-)Musteri Sil\n" +
                "4-)Musteri duzenle\n" +
                "5-)Butun musterileri goruntule\n\n" +
                "Lutfen bir islem seciniz.\n");


    }

    public static void displayCustomerMenu(){

        System.out.println( "0-) Para görüntüle\n" +
                "1-) Para yatir\n" +
                "2-) Para cek\n" +
                "3-) Havale yap\n" +
                "4-) EFT yap\n" +
                "5-) Cikis yap");
        System.out.println("Ne yapmak istersiniz:");
    }

    public static void selectAdminOptions(int option,Admin admin){
        switch (option) {
            case 1:
                musteriOlustur(admin);
                break;
            case 2:
                System.out.println("Musteri sil ozelligi daha eklenmedi.");
                break;
            case 3:
                System.out.println("Musteri Bul ozelligi daha eklenmedi.");
                break;
            case 4:
                System.out.println("Musterileri listele ozelligi daha eklenmedi.");
                break;
            case 5:
                System.out.println("Bu ozellik daha eklenmedi.");
                break;
            default:
                System.out.println("Boyle bir secenek bulunmamaktadir.");
        }
    }

    public static void selectCustomerOptions(int option,Customer customer) throws FileNotFoundException {
        switch (option) {
            case 0:
                paraGoruntule(customer);
                break;
            case 1:
                paraYatir(customer);
                break;
            case 2:
                paraCek(customer);
                break;
            case 3:
                havaleYap(customer);
                break;
            case 4:
                EFTYap(customer);
                break;
            case 5:
                cikis();
                break;
            default:
                System.out.println("Boyle bir secenek bulunmamaktadir.");
        }
    }

    public static void musteriOlustur(Admin admin){
        Scanner scanner =new Scanner(System.in);
        System.out.println("MÜŞTERİ OLUŞTURMA EKRANI");
        System.out.println("====================================================================\n");
        System.out.print("Username: ");
        String username=scanner.next();
        System.out.print("Password: ");
        String password=scanner.next();
        System.out.print("İsim: ");
        String name=scanner.next();
        System.out.print("Soyisim: ");
        String surname=scanner.next();
        System.out.print("Dogum Tarihi: ");
        String DOB= scanner.next();
        System.out.print("Cep Telefonu Numarasi: ");
        String phoneNumber=scanner.next();
        System.out.print("Email Adresi: ");
        String email=scanner.next();
        System.out.print("Baslangic bakiyesi: ");
        double initialBalance=Double.parseDouble(scanner.next());
        admin.createCustomer(username,password,name,surname,DOB,phoneNumber,email,initialBalance);
    }
    //Case sensitive
    public static Person login(int DBType) throws FileNotFoundException {
        String DBname= DBType == 1 ? "AdminDB.csv" : "CustomerDB.csv";
        int attemp=3;
        Person result=null;
        while(attemp>0) {
            System.out.print("username: ");
            String username = newInput().trim();
            System.out.print("password: ");
            String password = newInput().trim();
            List<String> person= FileClass.fetchRecordFromCSVFile(DBname,username,password);
            if(person.size()!=0){
                if(DBType==1)
                    result= new Admin(person.get(0), person.get(1), person.get(2), person.get(3), person.get(4), person.get(5), person.get(6));
                else if (DBType==2)
                    result= new Customer(person.get(0),person.get(1), person.get(2), person.get(3), person.get(4), person.get(5), person.get(6), person.get(7),person.get(8),Double.parseDouble(person.get(9)));
                break;
            }
            attemp--;
            if(attemp!=0)
                System.out.println(attemp+" giris hakkin kaldi.");
            else
                System.out.println("3 kere kullanici adinizi veya sifrenizi yanlis girdiniz ve" +
                        "Hesabiniz bloke edildi." +
                        "Lutfen en yakin subeye gidiniz.");
        }

        return result;

    }
    public static int getUserInput(){
        int selectedOption=-1;
        boolean notSelected=true;

        while(notSelected){
        try{
            Scanner scanner=new Scanner(System.in);
            selectedOption=scanner.nextInt();
            notSelected=false;
        }
        catch (InputMismatchException e){
            System.out.println("Girisiniz bir rakam degildir.");
        }
        if((selectedOption<0 || selectedOption>6) || notSelected){
            notSelected=true;
            System.out.println("Luffen 0 ile 6 arasinda bir rakam seciniz: ");
        }
        }
        return selectedOption;
    }
    public static String newInput(){
        Scanner scanner=new Scanner(System.in);
        return scanner.next();
    }
    public static void paraGoruntule(Customer customer) throws FileNotFoundException {
        String filePath=Paths.CustomerTransactionsFolder_PATH+customer.getName()+"_"+customer.getSurname()+".csv";
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath));) {
            String[] values = null;

            String mostRecentBalance="";
            while ((values = csvReader.readNext()) != null) {
                mostRecentBalance=values[values.length-2]+" TL";
            }
            System.out.println("Bakiyeniz: "+mostRecentBalance);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //String transactionSummary=
       // FileClass.appendLineToFile(transactionSummary, customer.getFullName());

    }
    public static void paraYatir(Customer customer){
        System.out.println("Lutfen yatiracaginiz para miktarini giriniz: ");

        try {
            double amount = Double.parseDouble(newInput());
            if(amount<0)
                throw new AmountLessThanZero();
            customer.addDeposit(amount,customer);
        }
        catch(AmountLessThanZero e){
            System.out.println("Yatirilacak miktar 0 dan az olamaz. Lutfen Tekrar Deneyin.");

        }
        catch (InputMismatchException e){
            System.out.println("Lutfen rakam giriniz:");
        }
    }
    public static void paraCek(Customer customer){
        System.out.println("Lutfen cekeceginiz miktari giriniz: ");
        try {
            double amount=Double.parseDouble(newInput());
            if(amount<0)
                throw new AmountLessThanZero();
            customer.withdraw(amount);
        }
        catch (AmountLessThanZero e){
            System.out.println("Yatirilacak miktar 0 dan az olamaz. Lutfen Tekrar Deneyin.");
        }
        catch (NumberFormatException e){
            System.out.println("Lutfen rakam giriniz:");
        }
    }
    public static void havaleYap(Customer customer){
        System.out.println("Lutfen havale yapacaginiz hesabin IBAN numarasini giriniz: ");
        String IBAN=newInput();
        System.out.println("Lutfen havale edeceginiz para miktarini giriniz: ");
        try {
            double amount=Double.parseDouble(newInput());
            if(amount<0)
                throw new AmountLessThanZero();
            customer.transfer(amount,IBAN);
        }
        catch (AmountLessThanZero e){
            System.out.println("Yatirilacak miktar 0 dan az olamaz. Lutfen Tekrar Deneyin.");
        }
        catch (NumberFormatException e){
            System.out.println("Lutfen rakam giriniz:");
        }
    }
    public static void EFTYap(Customer customer){
        System.out.println("Lutfen havale yapacaginiz hesabin IBAN numarasini giriniz: ");
        String IBAN=newInput();
        System.out.println("Lutfen havale edeceginiz para miktarini giriniz: ");
        try {
            double amount=Double.parseDouble(newInput());
            if(amount<0)
                throw new AmountLessThanZero();
            customer.doEFT(amount,IBAN);
        }
        catch (AmountLessThanZero e){
            System.out.println("Yatirilacak miktar 0 dan az olamaz. Lutfen Tekrar Deneyin.");
        }
        catch (NumberFormatException e){
            System.out.println("Lutfen rakam giriniz:");
        }
    }
    public static void cikis(){
        System.out.println("Cikis yapiliyor...");
        try {
            Thread.sleep(1000);
            System.out.println("Logged Out.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
    public static String createTransactionLine(String transactionDesc, double amount,double balance){
        String line=String.format("%20s | %5.2f | %.2f | %s",transactionDesc,amount,balance,createdDate());

        return line;
    }
    public static String createdDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String StringFormatDate =formatter.format(date);

        return StringFormatDate;
    }


}
