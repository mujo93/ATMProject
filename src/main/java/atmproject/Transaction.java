package atmproject;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private String FullName;
    private String TransactionDesc;
    private double amount;
    private double CustomerBalance;
    private String Date;


}
