package atmproject;

import atmproject.Exceptions.AmountLessThanZero;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class Main {

    private Scanner scanner;

    public Main(){
        scanner=new Scanner(System.in);
    }
    public static void main(String[] args) throws IOException, CsvException {

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
                System.out.println(colors.RED+"Lutfen sifrenizi veya kullanici adinizi dogru girdiginizden emin olun.\n" +
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
                System.out.println(colors.RED+"Lutfen sifrenizi veya kullanici adinizi dogru girdiginizden emin olun.\n" +
                        "Daha Sonra tekrar deneyin.");
                System.exit(0);
            }
        }

    }
    public static void  displayInitialScreen(){
        System.out.println(colors.PURPLE+"Nasil giris yapmak istersiniz?\n" +
                           colors.CYAN+ "1-) Admin\n" +
                            colors.CYAN+"2-) Customer");
    }

    public static void displayAdminMenu(){
        System.out.println(colors.CYAN+"ADMIN MENU\n" +
                "==============================================\n" +
                "1-)Musteri olustur\n" +
                "2-)Musteri Goruntule\n" +
                "3-)Musteri Sil\n" +
                "4-)Musteri duzenle\n" +
                "5-)Butun musterileri goruntule\n" +
                "6-)Cikis\n" +
                "Lutfen bir islem seciniz.\n");


    }

    public static void displayCustomerMenu(){

        System.out.println( colors.CYAN+"0-) Para görüntüle\n" +
                "1-) Para yatir\n" +
                "2-) Para cek\n" +
                "3-) Havale yap\n" +
                "4-) EFT yap\n" +
                "5-) Cikis yap");
        System.out.println(colors.YELLOW+"Ne yapmak istersiniz:");
    }

    public static void selectAdminOptions(int option,Admin admin) throws IOException, CsvException {
        switch (option) {
            case 1:
                musteriOlustur(admin);
                break;
            case 2:
                musteriGoruntule(admin);
                break;
            case 3:
                musteriSil(admin);
                break;
            case 4:
                System.out.println(colors.RED+"Musterileri listele ozelligi daha eklenmedi.");
                break;
            case 5:
                System.out.println(colors.RED+"Bu ozellik daha eklenmedi.");
                break;
            case 6:
                cikis();
                break;
            default:
                System.out.println(colors.RED+"Boyle bir secenek bulunmamaktadir.");
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
                System.out.println(colors.RED+"Boyle bir secenek bulunmamaktadir.");
        }
    }

    public static void musteriOlustur(Admin admin){
        Scanner scanner =new Scanner(System.in);
        System.out.println(colors.BLUE+"MÜŞTERİ OLUŞTURMA EKRANI");
        System.out.println(colors.BLUE+"====================================================================\n");
        System.out.print(colors.PURPLE+"Username: ");
        String username=scanner.next();
        System.out.print(colors.PURPLE+"Password: ");
        String password=scanner.next();
        System.out.print(colors.PURPLE+"İsim: ");
        String name=scanner.next();
        System.out.print(colors.PURPLE+"Soyisim: ");
        String surname=scanner.next();
        System.out.print(colors.PURPLE+"Dogum Tarihi: ");
        String DOB= scanner.next();
        System.out.print(colors.PURPLE+"Cep Telefonu Numarasi: ");
        String phoneNumber=scanner.next();
        System.out.print(colors.PURPLE+"Email Adresi: ");
        String email=scanner.next();
        System.out.print(colors.PURPLE+"Baslangic bakiyesi: ");
        double initialBalance=Double.parseDouble(scanner.next());
        scanner.close();
        admin.createCustomer(username,password,name,surname,DOB,phoneNumber,email,initialBalance);
    }
    //Case sensitive
    public static void musteriSil(Admin admin) throws IOException, CsvException {
        Scanner scanner =new Scanner(System.in);
        System.out.println(colors.BLUE+"Lutfen sileceginiz musterinin kullanici adini giriniz: ");
        String username= scanner.next();
        admin.deleteCustomer(username.trim());
        scanner.close();
    }

    public static void musteriGoruntule(Admin admin) throws IOException, CsvException {
        Scanner scanner =new Scanner(System.in);
        System.out.println(colors.BLUE+"Lutfen goruntuluyeceginiz musterinin kullanici adini giriniz: ");
        String username= scanner.next().trim();
        scanner.close();
        Customer customer=admin.getCustomer(username);
        if(customer.getId().length()==0)
            System.out.println(colors.RED+"Aradiginiz musterinin kaydi bulunamadi");
        else {
            System.out.println(colors.RED+"Id Username\t\t\t Password\t Name\t Surname\t Data Of Birth\t Phone Number\t Email Address\t Account Number\t Initial Balance ");
            System.out.println(colors.BLUE+customer.toString());
        }
    }

    public static Person login(int DBType) throws IOException {
        String DBname= DBType == 1 ? "AdminDB.csv" : "CustomerDB.csv";
        int attemp=3;
        Person result=null;
        while(attemp>0) {
            System.out.print(colors.PURPLE+"username: ");
            String username = newInput().trim();
            System.out.print(colors.PURPLE+"password: ");
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
                System.out.println(colors.RED+attemp+" giris hakkin kaldi.");
            else
                System.out.println(colors.RED+"3 kere kullanici adinizi veya sifrenizi yanlis girdiniz ve" +
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
            System.out.println(colors.RED+"Girisiniz bir rakam degildir.");
        }
        if((selectedOption<0 || selectedOption>6) || notSelected){
            notSelected=true;
            System.out.println(colors.RED+"Luffen 0 ile 6 arasinda bir rakam seciniz: ");
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
            System.out.println(colors.GREEN+"Bakiyeniz: "+mostRecentBalance);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //String transactionSummary=
       // FileClass.appendLineToFile(transactionSummary, customer.getFullName());

    }
    public static void paraYatir(Customer customer){
        System.out.println(colors.PURPLE+"Lutfen yatiracaginiz para miktarini giriniz: ");

        try {
            double amount = Double.parseDouble(newInput());
            if(amount<0)
                throw new AmountLessThanZero();
            customer.addDeposit(amount,customer);
        }
        catch(AmountLessThanZero e){
            System.out.println(colors.RED+"Yatirilacak miktar 0 dan az olamaz. Lutfen Tekrar Deneyin.");

        }
        catch (InputMismatchException e){
            System.out.println(colors.RED+"Lutfen rakam giriniz:");
        }
    }
    public static void paraCek(Customer customer){
        System.out.println(colors.PURPLE+"Lutfen cekeceginiz miktari giriniz: ");
        try {
            double amount=Double.parseDouble(newInput());
            if(amount<0)
                throw new AmountLessThanZero();
            customer.withdraw(amount,customer);
        }
        catch (AmountLessThanZero e){
            System.out.println(colors.RED+"Yatirilacak miktar 0 dan az olamaz. Lutfen Tekrar Deneyin.");
        }
        catch (NumberFormatException e){
            System.out.println(colors.RED+"Lutfen rakam giriniz:");
        }
    }
    public static void havaleYap(Customer customer){
        System.out.println(colors.PURPLE+"Lutfen havale yapacaginiz hesabin IBAN numarasini giriniz: ");
        String IBAN=newInput();
        System.out.println(colors.PURPLE+"Lutfen havale edeceginiz para miktarini giriniz: ");
        try {
            double amount=Double.parseDouble(newInput());
            if(amount<0)
                throw new AmountLessThanZero();
            customer.transfer(customer,amount,IBAN);
        }
        catch (AmountLessThanZero e){
            System.out.println(colors.RED+"Yatirilacak miktar 0 dan az olamaz. Lutfen Tekrar Deneyin.");
        }
        catch (NumberFormatException e){
            System.out.println(colors.RED+"Lutfen rakam giriniz:");
        }
    }
    public static void EFTYap(Customer customer){
        System.out.println(colors.PURPLE+"Lutfen havale yapacaginiz hesabin IBAN numarasini giriniz: ");
        String IBAN=newInput();
        System.out.println(colors.PURPLE+"Lutfen havale edeceginiz para miktarini giriniz: ");
        try {
            double amount=Double.parseDouble(newInput());
            if(amount<0)
                throw new AmountLessThanZero();
            customer.doEFT(customer,amount,IBAN);
        }
        catch (AmountLessThanZero e){
            System.out.println(colors.RED+"Yatirilacak miktar 0 dan az olamaz. Lutfen Tekrar Deneyin.");
        }
        catch (NumberFormatException e){
            System.out.println(colors.RED+"Lutfen rakam giriniz:");
        }
    }
    public static void cikis(){
        System.out.println(colors.BLUE_BACKGROUND+"Cikis yapiliyor...");
        try {
            Thread.sleep(1000);
            System.out.println("Logged Out.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}
