package atmproject;

import atmproject.DBModels.AdminModel;
import com.opencsv.*;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public final class FileClass {
    public static List<String> fetchRecordFromCSVFile(String DB, String username, String password){
        List<String> record=new ArrayList<>();
        File file=getFile(Paths.DBFolder_PATH+DB);
        try {
            CSVReader csvReader = new CSVReader(new FileReader(file));
            //skip first line since it is header
            csvReader.skip(1);
            String[] nextRecord;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
               List<String> nextRecordlist = Arrays.asList(nextRecord);
                System.out.println(nextRecord[1]);
                System.out.println(nextRecord[2]);
                if(nextRecord[1].matches(username))
                    if(nextRecord[2].matches(password))
                        record.addAll(nextRecordlist);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }
    public static File getFile(String path){
        File file = new File(path);
        return file;
    }

    private static FileWriter getFileWriter(File file) throws IOException {
        FileWriter fileWriter=new FileWriter(file);

        return fileWriter;
    }


}




