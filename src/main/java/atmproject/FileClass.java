package atmproject;

import com.opencsv.*;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public final class FileClass {



    public static void appendLineToFile(String text, String fullname) throws IOException {

        File file=getFile(FilePath.MY_PATH+fullname+".txt");
        FileWriter fileWriter=getFileWriter(file);
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        PrintWriter printWriter=new PrintWriter(bufferedWriter);
        printWriter.println(text);
         System.out.println("Transaction has been saved.");
    }

    public static <T> void appendRecordToCSVFile(String filePath,T object){
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // Create an array which contains the object fields of an object
            Field[] objectFields = object.getClass().getFields();
            ArrayList<String>headFieldsArrayList=new ArrayList<>();
            Arrays.stream(objectFields).forEach((field -> headFieldsArrayList.add(field.getName())));
           // String[] objectFieldsArray= (String[]) headFieldsArrayList.toArray();

            // add data to csv

            List<String> rowList=getObjectProperties(headFieldsArrayList,object);
            String[] rowArray = new String[rowList.size()];
            rowList.toArray(rowArray);
            writer.writeNext(rowArray);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static List<String> fetchRecordFromCSVFile(String DB, String username, String password){
        List<String> record=new ArrayList<>();
        File file=getFile(Paths.DBFolder_PATH+DB);
        try {
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // filereader as parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
               List<String> nextRecordlist = Arrays.asList(nextRecord);
                if(nextRecordlist.contains(username))
                    if(nextRecordlist.contains(password)){
                        record.addAll(nextRecordlist);
                    }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }
    private static <T> List<String> getObjectProperties(ArrayList<String> objectFields, T object){

        ArrayList<String> arrayList= new ArrayList<>();
        objectFields.forEach(field-> {
            try { arrayList.add(object.getClass().getField(field).toString());
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });
        return arrayList;
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



