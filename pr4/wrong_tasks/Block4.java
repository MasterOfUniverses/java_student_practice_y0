
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
//import java.util.AbstractCollection.*;
public class Block4{
    public static void main(String[] args) throws Exception{
        String fileWithInstructionsAdress = "input.txt";
        if(args.length==1){
            fileWithInstructionsAdress = args[0];
        }
        File instuctionsFile = new File(fileWithInstructionsAdress);
        Scanner instructionsScanner = new Scanner(instuctionsFile);
        int result = doingCycle(instructionsScanner);
        System.out.println("Works was ended: " + String.valueOf(result));
        return;
    }
    private static int doingCycle(Scanner instuctions) throws Exception{
        int res=0;
        while(instuctions.hasNextLine()){
            String newWork = instuctions.nextLine();
            int isOK = choseOneFunction(newWork);
            if(isOK>=0){
                res++;
            }else{
                System.out.println("After " + String.valueOf(res) + " good works we had some trouble with code " + String.valueOf(isOK));
            }
        }
        return res;
    }
    private static int choseOneFunction(String newWork) throws Exception{
        String[] args = newWork.split(" ");
        if(args.length<3){
            return -1;
        }
        int caseNumber = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        String outputFileName = args[2];
        File inputFile = new File(inputFileName);
        Scanner input = new Scanner(inputFile);
        PrintWriter output = new PrintWriter(outputFileName);
        int errorCode = 0;
        switch (caseNumber){
            case 1:
                errorCode=BessieTextProcessor(input,output);
                break;
            case 2:
                errorCode=bracketSplit(input,output);
                break;
            case 3:
                errorCode=changeCase(input,output);
                break;
            case 4:
                errorCode=overTime(input,output);
                break;
            case 5:
                errorCode=BMI(input,output);
                break;
            case 6:
                errorCode=bugger(input,output);
                break;
            case 7:
                errorCode= shortHandWorker(input,output);
                break;
            case 8:
                errorCode= doesRhymeWorker(input,output);
                break;
            case 9:
                errorCode= troubleWorker(input,output);
                break;
            case 10:
                errorCode= countUniqueBooksWorker(input,output);
                break;
            default:

                output.printf("no such work\n");
                output.close();
                return -2;
        }
        output.close();
        if(errorCode<0){
            return -3;
        }
        return 1;
    }
    private static int BessieTextProcessor (Scanner input, PrintWriter output){
        int wordsNumberInDoc = 0;
        int charsNumberInStr = 0;
        if(input.hasNextInt()){
            wordsNumberInDoc = input.nextInt();
        }else{
            return -1;
        }
        if(input.hasNextInt()){
            charsNumberInStr = input.nextInt();
        }else{
            return -1;
        }
        while(input.hasNextLine()){
            String docPart = input.nextLine();
            String[] wordsInThisPart = docPart.split(" ");
            String currStr="";
            int currStrLen=0;
            for(int wordNumber=0;(wordNumber<wordsNumberInDoc)&&(wordNumber<wordsInThisPart.length);wordNumber++){
                if(currStrLen + wordsInThisPart[wordNumber].length() <= charsNumberInStr){
                    if(currStrLen!=0){
                        currStr+=" ";
                    }
                    currStr += wordsInThisPart[wordNumber];
                    currStrLen += wordsInThisPart[wordNumber].length();
                }else{
                    output.println(currStr);
                    currStr="";
                    currStr = wordsInThisPart[wordNumber];
                    currStrLen = wordsInThisPart[wordNumber].length();
                }
            }
            output.println(currStr);
        }
        return 0;
    }
    private static int bracketSplit (Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String docPart = input.nextLine();
            String currCluster = "";
            int open_rng = 0;
            for(int index = 0; index <docPart.length(); index++){
                currCluster+=String.valueOf(docPart.charAt(index));
                if(docPart.charAt(index)=='('){
                    open_rng++;
                }
                if((docPart.charAt(index)==')')&&(open_rng>0)){
                    open_rng--;
                }
                if(open_rng==0){
                    output.println(currCluster);
                    currCluster="";
                }
            }
            if(open_rng==0){
                output.println(currCluster);
            }
        }
        return 0;
    }
    private static int changeCase (Scanner input, PrintWriter output){
        int mode = 0;
        if(input.hasNextInt()){
            mode = input.nextInt();
        }else{
            return -1;
        }
        while(input.hasNextLine()){
            String docPart = input.nextLine();
            if(mode==0){
                output.println(toCamelCase(fromSnakeCase(docPart)));
            }else if (mode==1){
                output.println(toSnakeCase(fromCamelCase(docPart)));
            }else if (mode==2){
                output.println(toSnakeCase(docPart.toLowerCase().split(" ")));
            }else if (mode==3){
                output.println(toCamelCase(docPart.toLowerCase().split(" ")));
            }else{
                return -1;
            }
        }
        return 0;
    }
    private static String[] fromCamelCase (String str){
        ArrayList<String> res = new ArrayList<String>();
        String currStr = "";
        for(int i=0;i<str.length();i++){
            if(!(((str.charAt(i)>='A')&&(str.charAt(i)<='Z')) || ((str.charAt(i)>='a')&&(str.charAt(i)<='z'))|| ((str.charAt(i)>='0')&&(str.charAt(i)<='9')))){
                continue;
            }
            if((str.charAt(i)>='A')&&(str.charAt(i)<='Z')){
                if(currStr.length()>0){
                    res.add(currStr.toLowerCase());
                }
                currStr="";
            }
            currStr+=String.valueOf(str.charAt(i));
        }
        res.add(currStr.toLowerCase());
        String[] result = new String[res.size()];
        return res.toArray(result);

    }
    private static String[] fromSnakeCase (String str){
        return str.split("_");
    }
    private static String toCamelCase (String[] words){
        String res="";
        for(int wordIndex=0;wordIndex<words.length;wordIndex++){
            for(int charIndex=0;charIndex<words[wordIndex].length();charIndex++){
                if((charIndex==0)&&(wordIndex!=0)){
                    res+=String.valueOf(words[wordIndex].charAt(charIndex)).toUpperCase();
                }else {
                    res += String.valueOf(words[wordIndex].charAt(charIndex)).toLowerCase();
                }
            }
        }
        return res;
    }
    private static String toSnakeCase (String[] words){
        if(words.length<1){
            return "";
        }
        String res=words[0];
        for(int wordIndex=1;wordIndex<words.length;wordIndex++){
            res+="_"+words[wordIndex];
        }
        return res;
    }
    private static int overTime (Scanner input, PrintWriter output){
        double dayStart = 9.0;
        double dayEnd = 17.0;
        double totalSum = 0.0;
        String errMessage = "0";
        while(input.hasNextLine()){
            double employeeEnter = -1.0;
            double employeeExit = -1.0;
            double salary = -1.0;
            double overTimeCoeff = -1.0;
            double currRes = 0.0;
            if(input.hasNextDouble()){
                employeeEnter = input.nextDouble();
            }else{
                output.println(errMessage);
                continue;
            }
            if(input.hasNextDouble()){
                employeeExit = input.nextDouble();
            }else{
                output.println(errMessage);
                continue;
            }
            if(input.hasNextDouble()){
                salary = input.nextDouble();
            }else{
                output.println(errMessage);
                continue;
            }
            if(input.hasNextDouble()){
                overTimeCoeff = input.nextDouble();
            }else{
                output.println(errMessage);
                continue;
            }
            if(employeeEnter > employeeExit){
                output.println(errMessage);
                continue;
            }
            if(dayStart>employeeExit){
                currRes = salary*overTimeCoeff*(employeeExit-employeeEnter);
            }
            else if(dayEnd<employeeEnter){
                currRes = salary*overTimeCoeff*(employeeExit-employeeEnter);
            }
            else if((dayEnd>=employeeExit)&&(dayStart<=employeeEnter)){
                currRes = salary*(employeeExit-employeeEnter);
            } else if (dayEnd>employeeExit) {
                currRes = salary*(overTimeCoeff*(dayStart-employeeEnter) + 1*(employeeExit-dayStart));
            } else if (dayStart<employeeEnter) {
                currRes = salary*(overTimeCoeff*(employeeExit-dayEnd)+1*(dayEnd-employeeEnter));
            }else{
                currRes = 0.0;
            }
            currRes = Math.round(currRes*100.0)/100.0;
            totalSum+=currRes;
            output.println(String.valueOf(currRes));


        }
        output.println();
        output.println(String.valueOf(totalSum));
        return 0;
    }
    private static int BMI (Scanner input, PrintWriter output){
        double metersToInches = 39.37;
        double kilogrammsToPounds = 2.205;
        String errMessage = "0";
        while(input.hasNextLine()){
            double height = -1.0;
            double weight = -1.0;
            double currBMI = 0.0;
            if(input.hasNextDouble()){
                double numberTmp = input.nextDouble();
                if(input.hasNext("kilos")){
                    weight = numberTmp;
                }else if(input.hasNext("pounds")){
                    weight = numberTmp/kilogrammsToPounds;
                }else if(input.hasNext("inches")){
                    height = numberTmp/metersToInches;
                }else if(input.hasNext("meters")){
                    height = numberTmp;
                }else{
                    output.println(errMessage);
                    continue;
                }
            }
            if(input.hasNextDouble()){
                double numberTmp = input.nextDouble();
                if(input.hasNext("kilos")){
                    weight = numberTmp;
                }else if(input.hasNext("pounds")){
                    weight = numberTmp/kilogrammsToPounds;
                }else if(input.hasNext("inches")){
                    height = numberTmp/metersToInches;
                }else if(input.hasNext("meters")){
                    height = numberTmp;
                }else{
                    output.println(errMessage);
                    continue;
                }
            }
            if((height<0.0)||(weight<0.0)){
                output.println(errMessage);
                continue;
            }
            currBMI = weight / (height*height);
            String BMICategory ="";
            if(currBMI<18.5){
                BMICategory = "Underweight";
            }else if(currBMI>25){
                BMICategory = "Overweight";
            }
            else{
                BMICategory = "Normal weight";
            }
            currBMI = Math.round(currBMI*10.0)/10.0;
            output.println(String.valueOf(currBMI) + " " + BMICategory);
        }
        return 0;
    }
    private static int bugger (Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            int currNumber = Integer.parseInt(input.nextLine());
            int count = 0;
            while(currNumber>9){
                count++;
                currNumber = digitMultiply(currNumber);
            }
            output.println(count);
        }
        return 0;
    }
    private static int digitMultiply(int number){
        int numberBegin = number;
        int result = 1;
        while(numberBegin>0){
            result*=numberBegin%10;
            numberBegin=numberBegin/10;
        }
        return result;
    }
    private static int shortHandWorker(Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String currStr = input.nextLine();
            output.println(toStarShorthand(currStr));
        }
        return 0;
    }
    private static String toStarShorthand(String str){
        if(str.length()<1){
            return "";
        }
        char lastChar = str.charAt(0);
        int count = 1;
        String result = "";
        for(int charIndex=1;charIndex<str.length();charIndex++){
            char currChar = str.charAt(charIndex);
            if(lastChar==currChar){
                count++;
            }else{
                if(count==1){
                    result+=String.valueOf(lastChar);
                }else{
                    result+= String.valueOf(lastChar)+"*"+String.valueOf(count);
                }
                lastChar=currChar;
                count=1;
            }
        }
        if(count==1){
            result+=String.valueOf(lastChar);
        }else{
            result+= String.valueOf(lastChar)+"*"+String.valueOf(count);
        }
        return result;
    }
    private static int doesRhymeWorker(Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String str1 = input.nextLine();
            if(! input.hasNextLine()){
                continue;
            }
            String str2 = input.nextLine();
            output.println(String.valueOf(doesRhyme(str1,str2)));
        }
        return 0;
    }
    private static boolean doesRhyme(String str1, String str2){
        char[] vovelsTemplate = {'a','e','i','o','u','y'};
        String[] strArray1 = str1.split(" ");
        String[] strArray2 = str2.split(" ");
        if((strArray1.length<1)&&(strArray2.length<1)){
            return true;
        }else if((strArray1.length<1)||(strArray2.length<1)){
            return false;
        }
        String lastWord1 = strArray1[strArray1.length-1].toLowerCase();
        String lastWord2 = strArray2[strArray2.length-1].toLowerCase();
        String vovels1 = "";
        String vovels2 = "";
        for(int i=0;i<lastWord1.length();i++){
            for(int j=0;j<vovelsTemplate.length;j++){
                if(lastWord1.charAt(i)==vovelsTemplate[j]){
                    vovels1+=String.valueOf(lastWord1.charAt(i));
                    continue;
                }
            }
        }
        for(int i=0;i<lastWord2.length();i++){
            for(int j=0;j<vovelsTemplate.length;j++){
                if(lastWord2.charAt(i)==vovelsTemplate[j]){
                    vovels2+=String.valueOf(lastWord2.charAt(i));
                    continue;
                }
            }
        }
        return vovels1.equals(vovels2);
    }
    private static int troubleWorker(Scanner input, PrintWriter output){
        while(input.hasNextLong()){
            long number1 = input.nextLong();
            long number2 = 0;
            if(!input.hasNextLong()){
                continue;
            }
            number2 = input.nextLong();
            output.println(String.valueOf(trouble(number1,number2)));
        }
        return 0;
    }
    private static boolean trouble(long num1,long num2){
        String str1 = String.valueOf(num1);
        String str2 = String.valueOf(num2);
        for(int len=1;(len<str1.length()/3) && (len<str2.length()/2);len++){
            for(int i=0;i<str1.length()-len;i++){
                String testStr = str1.substring(i,len+i);
                if((str1.contains(testStr+testStr+testStr))&&(str2.contains(testStr+testStr))){
                    return true;
                }
            }
        }
        return false;
    }
    private static int countUniqueBooksWorker (Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String[] inputArr = input.nextLine().toUpperCase().split(" ");
            if(inputArr.length<2){
                output.println();
                continue;
            }
            String data = inputArr[0];
            char border = inputArr[1].charAt(0);
            output.println(String.valueOf(countUniqueBooks(data,border)));
        }
        return 0;
    }
    private static int countUniqueBooks (String data, char border){
        HashSet<String> uniqueChars = new HashSet<String>();
        boolean isOpen = false;
        for(int i=0;i<data.length();i++){
            if(data.charAt(i)==border){
                isOpen = !isOpen;
            }else{
                if(isOpen){
                    uniqueChars.add(data.substring(i,i+1));
                }
            }
        }
        return uniqueChars.size();
    }
}