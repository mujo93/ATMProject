package atmproject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

 public final class AtmUtility {

    public static String IdCreator(){
        return UUID.randomUUID().toString();
    }

    public static String createAccountNumber(){
        int max=9;
        int min=0;
        String numbers="0123456789";
        String accountNumber="";

        for (int i = 0; i < 9; i++) {
            int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
            accountNumber+=numbers.charAt(random_int);
        }

        return accountNumber;
    }

    public static String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String StringFormatDate =formatter.format(date);

        return StringFormatDate;
    }
    public static void sendMail(){
        //this.EmailAddress'in  arguman olarak kullanildigi bazi konfigurasyonlar
    }

}
