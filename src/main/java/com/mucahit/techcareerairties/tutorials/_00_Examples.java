package com.mucahit.techcareerairties.tutorials;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class _00_Examples {

    public static void main(String[] args) {
        //HesapMakinesi();
        //isNegative();
        //findOutGrade();
        //String[] charArr= new String[]{"j","a","v","a"};
        //reverseOrderOfAnArray(charArr,charArr.length);
        //System.out.println(findFibonacciNumber(9));
        //findQuantityOfRepeatingNumbers();
        //findTypeOfCharacter();

    }




        // hesap makinesi 4 işlemli
        public static  void HesapMakinesi(){

        // initialize the current result
        double currentValue=0;
        boolean onFirstTime=true;
        //char operation='a';

        while(true) {

            // 1- get input
            int input1 = ValidateIntegerInput();

            // 2- Identify the operation
            char operation = getOperationType().charAt(0);

            // 3- Do the operation and return the result.
            if (operation == '+') {
                currentValue = addUp(input1, currentValue,onFirstTime);
                showResult(currentValue);

            } else if (operation == '-') {
                currentValue = subtract(input1, currentValue,onFirstTime);
                showResult(currentValue);
            } else if (operation == '*') {
                currentValue = multiply(input1, currentValue,onFirstTime);
                showResult(currentValue);
            } else if (operation == '/') {
                currentValue = divide(input1, currentValue,onFirstTime);
                showResult(currentValue);
            } else {
                System.out.println("Invalid operation");
            }
            // each operation starts with a different initialized differently.
            // this variable used for indication whether it is first operation or not.
            onFirstTime=false;
            }
        }
        public static int ValidateIntegerInput(){
        System.out.println("Please enter an integer number:");
        Scanner userEntry =getInput();
        while (!userEntry.hasNextInt()){
            System.out.println("This is not an integer. Please enter an integer value!");
            userEntry.next();
        }
        int input =userEntry.nextInt();

        return input;
    }
        public static String getOperationType(){
        System.out.println("Please select and operation + , - , * , /");
        Scanner scanner =new Scanner(System.in);
        String regex= "[+,\\-,*,/]";
        String operation= scanner.next();
        while (!operation.matches(regex) && operation.length()!=1){
            System.out.println("Lütfen doğru bir işlem seçiniz: ");
            operation=scanner.next();
        }
        return operation;
    }
        public static double addUp(int number1, double currentVal, boolean isFirstOperation){

        double result = number1+currentVal;

        return result;

    }
        public static double subtract(int number1, double currentVal, boolean isFirstOperation){

        double result =isFirstOperation ? number1 :currentVal-number1;

        return result;

    }
        public static double multiply(int number1, double currentVal, boolean isFirstOperation){

        double result = isFirstOperation ? number1 *1 : currentVal*number1 ;

        return result;

    }
        public static double divide(int number1, double currentVal, boolean isFirstOperation){
        if (number1==0){
            System.out.println("Input cannot be 0!");
            return currentVal;
        }
        double result = isFirstOperation ? number1/1 :  currentVal/ number1;
        return result;
    }
        public static void showResult(double result){
            System.out.println(result);
    }
        public static Scanner getInput(){
        Scanner scanner =new Scanner(System.in);
        return scanner;
    }



        // kullanıcıdan aldığım bir sayıyı negatif mi pozitif mi hesaplayana lagoritma
        public static String isNegative(){

        // variable for exit the loop and stop the program.
        char quit = 'y';
        String message="Program has been terminated.";
        //Create a scanner for getting input from a user
        Scanner userInput=getInputFromUser();
        while(quit!='q'){
            // try-catch to avoid inappropriate user inputs
            try{
                System.out.println("Please enter a number:");
                double number=Double.parseDouble(userInput.next());

                if(number>0)
                    System.out.println("Positive");
                else if (number<0)
                    System.out.println("Negative");
                else if (number==0)
                    System.out.println("Zero");

                //allow user to exit the application
                System.out.println("Please enter 'q' to exit the program. For continue please press 'y'");
                quit = userInput.next().charAt(0);
            }
            catch (NumberFormatException e){
                System.out.println("Please enter a valid input!");
            }
        }
        return message;
    }


       // kullanıcıdan aldığım bir yapınını harf mi, sayı mı , özel karakttermi ? (Character.isLetter, Character.isDigit)

        //Vize Final
        // Vize Final Ödevi
        // kullanıcıdan alınan vize ve final notuna göre geçme(ortalama) Algoritması
        // eğer kullanıcı vize veya final 0(sıfır) bir değer girerse sistemden atsın
        // ekranda "Sifir disiplin hatasi Sistemden cikiliyor
        // bunun haricinde sürekli vize final sorsun
        // Not Harfleri ==> AA BA BB FF
        // not ortalaması: ortalama<50 altında ise kaldı FF
        // not ortalaması: ortalama==50 Geçti
        // not ortalaması: 55<=x<=69 BB
        // not ortalaması: 70<=x<=84 BA
        // not ortalaması: 85<=x<=100 AA
        // Vize:40% Final:60%
        // Dikkat: Consolda virgül kullanmalısın.
        // while sonsuz döngü   ==> while(true) {}
        // for sonsuz döngü     ==> for(;;) {}
        public static Scanner getInputFromUser(){
            Scanner scanner =new Scanner(System.in);
            return scanner;
        }

        public static void findOutGrade(){

        while(true){

            System.out.println("Please enter a visa score: ");
            String visa = getInputFromUser().next();

            // improvements!!: type control should be made before using Double method below inside if()
            // otherwise it ends up in run time error.
            if(Double.parseDouble(visa)==0){
                System.out.println("Sifir disiplin hatasi Sistemden cikiliyor...");
                break;
            }


            System.out.println("Please enter a final score: ");
            String finalScore = getInputFromUser().next();
            if(Double.parseDouble(finalScore)==0){
                System.out.println("Sifir disiplin hatasi Sistemden cikiliyor...");
                break;
            }

            boolean isVisaValid=isInputValidBetweenAGivenRange(visa,100,0);
            boolean isFinalValid=isInputValidBetweenAGivenRange(finalScore,100,0);

            double average=0;

            if(isVisaValid && isFinalValid){
                //Calculate average
                average = Double.parseDouble(visa)*0.4 +  Double.parseDouble(finalScore)*0.6;
                // determine the grade
                String grade= findGrade(85, 70, 50, average);
                //Display the Grade
                System.out.println("GRADE: "+grade);
            }
            else{
                System.out.println("Invalid entry. Please try again.");
            }
        }
    }

        public static String findGrade(int limitForAA, int limitForBA,int limitForPass, double average ){
        String grade="not specified yet.";
        if(average>=limitForBA)
            grade="AA";
        else if (average<limitForAA && average>=limitForBA)
            grade="BA";
        else if (average<limitForBA && average>=limitForPass)
            grade="BB";
        else if(average<limitForPass && average>=0)
            grade="FF";
        else{
            System.out.println("Grade cannot be figured out based on given inputs.");
        }

        return grade;
    }

        public static boolean isInputANumber(String input){

        boolean result=true;
        try
        {
            Double.parseDouble(input);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Please enter an acceptable input.");
            result=false;
        }
        finally {
            return result;
        }
    }

        public static boolean isNumberBetweenAGivenRange(String input, double upperLimit, double lowerLimit){
        double doubledInput= Double.parseDouble(input);
        boolean result= true;
        if (doubledInput>= upperLimit || doubledInput<=lowerLimit){
            System.out.print("Input is not between given range.\nPlease enter an input between the range.");
            result=false;
        }
        return result;
    }

        public static boolean isInputValidBetweenAGivenRange(String input ,int upperLimit, int lowerLimit){
        return isInputANumber(input) && isNumberBetweenAGivenRange(input,upperLimit,lowerLimit);
    }




        // String tersten yazdıran algoritma java => avaj
        public static void reverseOrderOfAnArray(String arr[], int arrLength){

            String[] b = new String[arrLength];
            int j = arrLength;
            for (int i = 0; i < arrLength; i++) {
                b[j - 1] = arr[i];
                j = j - 1;
            }
            // printing the reversed array
            System.out.println("Reversed array is: \n");
            for (int k = 0; k < arrLength; k++) {
                System.out.print(b[k]);
            }
        }



        // Kullanıcı tarafında belirtilen sayıya kadar fibonacci sayısını bulan algoritma ?
        public static int findFibonacciNumber(int number){
            if (number <= 1)
                return number;
            return findFibonacciNumber(number - 1) + findFibonacciNumber(number - 2);
        }



        //Dizi
        //Bir dizide 1-10 arasında rastgele sayılar oluştursun ve bu sayılardan tekrar eden sayılardan kaç tane olduğunu
        // ve tekrarsız veriler algoritma yazalım ?
        public static void findQuantityOfRepeatingNumbers(){

        int [] randomlyComposedArr= new int[10];

        for (int i = 0; i <10 ; i++) {
            randomlyComposedArr[i]=(int)(Math.random()*(10-1+1)+1);
        }
        for (int element:randomlyComposedArr) {
            System.out.println(element);
        }
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int element : randomlyComposedArr) {
            if (map.get(element) == null) {
                map.put(element, 1);
            } else {
                map.put(element, map.get(element) + 1);
            }
        }

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();

        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (entry.getValue() > 1) {
                System.out.println("Duplicate Element : " + entry.getKey() + " - found " + entry.getValue() + " times.");
            }
        }
    }


        // kullanıcıdan aldığım bir yapınını harf mi, sayı mı , özel karakttermi ? (Character.isLetter, Character.isDigit)
        public static void findTypeOfCharacter(){
            char exit= 'y';
            while(exit=='y') {
                //Assumption: Every character is special character except letters and digits.
                System.out.println("Please enter a character: ");
                char input = getInputFromUser().next().charAt(0);
                if (Character.isLetter(input))
                    System.out.println("This is a letter.");
                else if (Character.isDigit(input))
                    System.out.println("This is a digit.");
                else
                    System.out.println("This is a special character.");

                System.out.println("Please press 'y' to continue. To exit press any key on keyboard.");
                exit=getInputFromUser().next().charAt(0);
            }
        }



}
