
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Hashtable;
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
                errorCode=nonRepeatableWorker(input,output);
                break;
            case 2:
                errorCode=generateBracketsWorker(input,output);
                break;
            case 3:
                errorCode=binarySystemWorker(input,output);
                break;
            case 4:
                errorCode=alphabeticRowWorker(input,output);
                break;
            case 5:
                errorCode=shortHandWorker(input,output);
                break;
            case 6:
                errorCode= convertToNumWorker(input,output);
                break;
            case 7:
                errorCode = uniqueSubstringWorker (input,output);
                break;
            case 8:
                errorCode= shortestWayWorker(input,output);
                break;
            case 9:
                errorCode = numericOrderWorker(input,output);
                break;
            case 10:
                errorCode = switchNumsWorker(input,output);
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
    private static int nonRepeatableWorker(Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String currLine = input.nextLine();
            String res = nonRepeatable(currLine);
            output.println(res);
        }
        return 0;
    }
    private static String nonRepeatable(String str){
        if(str.length()<=1){
            return str;
        }
        String firstChar = str.substring(0,1);
        String withoutFirstChar = "";
        for(int charIndex=1;charIndex<str.length();charIndex++) {
            if (str.substring(charIndex, charIndex + 1).equals(firstChar)) {
                continue;
            } else {
                withoutFirstChar += str.substring(charIndex, charIndex + 1);
            }
        }
        return firstChar+nonRepeatable(withoutFirstChar);
    }
    private static int generateBracketsWorker(Scanner input, PrintWriter output){
        while(input.hasNextInt()){
            int currNumber = input.nextInt();
            String[] res = generateBrackets(currNumber);
            for(String cluster: res) {
                output.print(cluster);
                output.print(" ");
            }
            output.println();
        }
        return 0;
    }
    private static String[] generateBrackets(int n){
        if(n<1){
            return new String[]{""};
        }else if(n==1){
            return new String[]{"()"};
        }
        Set<String> allVarsForCurrNumber= new TreeSet<String>();
        for(int innerIndex=0;innerIndex<n;innerIndex++){
            String[] innerVars = generateBrackets(innerIndex);
            String[] nextClusterVars = generateBrackets(n-1-innerIndex);
            for(String inner:innerVars){
                for(String nextCluster:nextClusterVars){
                    String oneVar = "("+inner+")"+nextCluster;
                    allVarsForCurrNumber.add(oneVar);
                }
            }
        }
        String[] res = new String[allVarsForCurrNumber.size()];
        return allVarsForCurrNumber.toArray(res);
    }
    private static int binarySystemWorker(Scanner input, PrintWriter output){
        while(input.hasNextInt()){
            int currNumber = input.nextInt();
            String[] res = binarySystem(currNumber);
            for(String cluster: res) {
                output.print(cluster);
                output.print(" ");
            }
            output.println();
        }
        return 0;

    }
    private static String[] binarySystem(int n){
        if(n<1){
            return new String[]{""};
        }
        Set<String> allVarsForCurrNumber= new TreeSet<String>();
        allVarsForCurrNumber.addAll(binarySystemAutomat(n,false));
        allVarsForCurrNumber.addAll(binarySystemAutomat(n,true));
        String[] res = new String[allVarsForCurrNumber.size()];
        return allVarsForCurrNumber.toArray(res);
    }
    private static Set<String> binarySystemAutomat (int n,boolean isFirstNull){
        if(n==1){
            Set<String> res= new TreeSet<String>();
            if(isFirstNull){
                res.add("0");
            }else{
                res.add("1");
            }
            return res;
        }
        if(isFirstNull){
            Set<String> res= new TreeSet<String>();
            Set<String> resOld = binarySystemAutomat(n-1,false);
            for(String str: resOld){
                res.add("0"+str);
            }
            return res;
        }else{
            Set<String> res= new TreeSet<String>();
            Set<String> resOld = binarySystemAutomat(n-1,false);
            for(String str: resOld){
                res.add("1"+str);
            }
            resOld = binarySystemAutomat(n-1,true);
            for(String str: resOld){
                res.add("1"+str);
            }
            return res;
        }
    }
    private static int alphabeticRowWorker(Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String currLine = input.nextLine();
            String res = alphabeticRow(currLine);
            output.println(res);
        }
        return 0;

    }
    private static String alphabeticRow(String str){
        if(str.length()<1){
            return "";
        }
        //str=str.toLowerCase();
        String currAlphabeticRow = str.substring(0,1);
        String currMaxRow = currAlphabeticRow;
        char lastChar = currAlphabeticRow.charAt(0);
        int flag=0;
        for(int charIndex=1;charIndex<str.length();charIndex++){
            char currChar = str.charAt(charIndex);
            if(flag==0){
                if(currChar == lastChar+1){
                    currAlphabeticRow+=str.substring(charIndex,charIndex+1);
                    flag=1;
                }else if(currChar == lastChar-1){
                    currAlphabeticRow+=str.substring(charIndex,charIndex+1);
                    flag=-1;
                }else{
                    if(currAlphabeticRow.length()>currMaxRow.length()){
                        currMaxRow=currAlphabeticRow;
                    }
                    currAlphabeticRow=str.substring(charIndex,charIndex+1);
                    flag=0;
                }
            }else{
                if(currChar==lastChar+flag){
                    currAlphabeticRow+=str.substring(charIndex,charIndex+1);
                }else{
                    if(currAlphabeticRow.length()>currMaxRow.length()){
                        currMaxRow=currAlphabeticRow;
                    }
                    currAlphabeticRow=str.substring(charIndex,charIndex+1);
                    flag=0;
                }
            }
            lastChar=currChar;
        }
        if(currAlphabeticRow.length()>currMaxRow.length()){
            currMaxRow=currAlphabeticRow;
        }
        return currMaxRow; //currMaxRow.length();
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
        String result="";
        Set<String> stringClusters= new TreeSet<String>(new Comparator<String>(){
            public int compare(String str1,String str2){
                if((str1.length()==str2.length())&&(str1.length()>0)){
                    return str1.substring(0,1).compareTo(str2.substring(0,1));
                }else{
                    return Integer.valueOf(str1.length()).compareTo(Integer.valueOf(str2.length()));
                }
            }
        });
        String currCluster ="";
        String lastChar = str.substring(0,1);
        currCluster+=lastChar;
        for(int charIndex =1 ; charIndex<str.length();charIndex++){
            String currChar = str.substring(charIndex,charIndex+1);
            if(lastChar.equals(currChar)){
                currCluster+=currChar;
            }else{
                stringClusters.add(currCluster);
                lastChar=currChar;
                currCluster=lastChar;
            }
        }
        stringClusters.add(currCluster);
        for(String cluster:stringClusters){
            if(cluster.length()>0){
                result+=cluster.substring(0,1)+String.valueOf(cluster.length());
            }
        }
        return result;
    }
    private static int convertToNumWorker(Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String currLine = input.nextLine();
            int res = convertToNum(currLine);
            output.println(res);
        }
        return 0;
    }
    private static Hashtable<String,Integer> decoder_from_0_to_99;
    private static boolean isDecoderInitialized = false;
    private static void decoderInit(){
        if(isDecoderInitialized){
            return;
        }
        decoder_from_0_to_99 = new Hashtable<String, Integer>();
        decoder_from_0_to_99.put("and",0);
        decoder_from_0_to_99.put("zero",0);
        decoder_from_0_to_99.put("one",1);
        decoder_from_0_to_99.put("two",2);
        decoder_from_0_to_99.put("three",3);
        decoder_from_0_to_99.put("four",4);
        decoder_from_0_to_99.put("five",5);
        decoder_from_0_to_99.put("six",6);
        decoder_from_0_to_99.put("seven",7);
        decoder_from_0_to_99.put("eight",8);
        decoder_from_0_to_99.put("nine",9);
        decoder_from_0_to_99.put("ten",10);
        decoder_from_0_to_99.put("eleven",11);
        decoder_from_0_to_99.put("twelve",12);
        decoder_from_0_to_99.put("thirteen",13);
        decoder_from_0_to_99.put("fourteen",14);
        decoder_from_0_to_99.put("fifteen",15);
        decoder_from_0_to_99.put("sixteen",16);
        decoder_from_0_to_99.put("seventeen",17);
        decoder_from_0_to_99.put("eighteen",18);
        decoder_from_0_to_99.put("nineteen",19);
        decoder_from_0_to_99.put("twenty",20);
        decoder_from_0_to_99.put("thirty",30);
        decoder_from_0_to_99.put("fourty",40);
        decoder_from_0_to_99.put("fifty",50);
        decoder_from_0_to_99.put("sixty",60);
        decoder_from_0_to_99.put("seventy",70);
        decoder_from_0_to_99.put("eighty",80);
        decoder_from_0_to_99.put("ninety",90);
        return;
    }
    private static int convertToNum(String str){
        String[] words = str.toLowerCase().split(" ");
        int result=0;
        int startIndex = 0;
        decoderInit();
        if(words.length>2){
            if(words[1].equals("hundred")){
                startIndex = 2;
                if(words[0].equals("one")){
                    result+=100;
                }else if(words[0].equals("two")){
                    result+=200;
                }else if(words[0].equals("three")){
                    result+=300;
                }else if(words[0].equals("four")){
                    result+=400;
                }else if(words[0].equals("five")){
                    result+=500;
                }else if(words[0].equals("six")){
                    result+=600;
                }else if(words[0].equals("seven")){
                    result+=700;
                }else if(words[0].equals("eight")){
                    result+=800;
                }else if(words[0].equals("nine")){
                    result+=900;
                }else{
                    return -1;
                }
            }
        }
        for(int i=startIndex;i<words.length;i++){
            if(decoder_from_0_to_99.containsKey(words[i])){
                result+=decoder_from_0_to_99.get(words[i]);
            }else{
                return -1;
            }
        }
        return result;
    }
    private static int uniqueSubstringWorker(Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String currLine = input.nextLine();
            String res = uniqueSubstring(currLine);
            output.println(res);
        }
        return 0;
    }
    private static String uniqueSubstring(String inputStr){
        if(inputStr.length()<1){
            return "";
        }
        Hashtable<Character,Integer> uniqueChars = new Hashtable<Character, Integer>();
        String currUniqueRow = inputStr.substring(0,1);
        uniqueChars.put(inputStr.charAt(0),0);

        String currMaxRow = currUniqueRow;
        char firstCharInCluster = currUniqueRow.charAt(0);
        int flag=0;
        for(int charIndex=1;charIndex<inputStr.length();charIndex++){
            char currChar = inputStr.charAt(charIndex);
            if(! uniqueChars.containsKey(currChar)){
                currUniqueRow+= inputStr.substring(charIndex,charIndex+1);
                uniqueChars.put(currChar,charIndex);
            }else{
                if(currUniqueRow.length()>currMaxRow.length()){
                    currMaxRow=currUniqueRow;
                }
                int oldNonUniqueIndex = uniqueChars.get(currChar);
                currUniqueRow = inputStr.substring(oldNonUniqueIndex+1,charIndex+1);
                uniqueChars.clear();
                for(int i=0;i<currUniqueRow.length();i++){
                    uniqueChars.put(currUniqueRow.charAt(i),i+oldNonUniqueIndex+1);
                }
                //System.out.println(String.valueOf(currUniqueRow.length()) + " " + String.valueOf(uniqueChars.size()));
            }
        }
        if(currUniqueRow.length()>currMaxRow.length()){
            currMaxRow=currUniqueRow;
        }
        return currMaxRow; //currMaxRow.length();
    }
    private static int shortestWayWorker(Scanner input, PrintWriter output){
        while(input.hasNextInt()){
            int size = input.nextInt();
            int[][] weights = new int[size][size];
            for(int rowNum=0;rowNum<size;rowNum++){
                for(int colNum = 0;colNum<size;colNum++){
                    if(input.hasNextInt()){
                        weights[rowNum][colNum] = input.nextInt();
                    }else{
                        output.println("err: not enougth data found");
                        return -1;
                    }
                }
            }
            int res = shortestWay(weights);
            output.println(res);
        }
        return 0;
    }
    private static int shortestWay(int[][] weights){
        int result=0;
        if(weights.length<1){
            return 0;
        }
        int[][] minWeigthsSum = new int[weights.length][weights.length];
        minWeigthsSum[0][0]=weights[0][0];
        for(int i=1;i<weights.length;i++){
            minWeigthsSum[i][0]=weights[i][0]+minWeigthsSum[i-1][0];
        }
        for(int i=1;i<weights[0].length;i++){
            minWeigthsSum[0][i]=weights[0][i]+minWeigthsSum[0][i-1];
        }
        for(int rowNum=1;rowNum<weights.length;rowNum++){
            for(int colNum=1;colNum<weights[0].length;colNum++){
                minWeigthsSum[rowNum][colNum] = weights[rowNum][colNum] + (minWeigthsSum[rowNum-1][colNum] < minWeigthsSum[rowNum][colNum-1] ? minWeigthsSum[rowNum-1][colNum] : minWeigthsSum[rowNum][colNum-1]);
            }
        }
        result = minWeigthsSum[weights.length-1][weights[0].length-1];
        return result;
    }
    private static int numericOrderWorker(Scanner input, PrintWriter output){
        while(input.hasNextLine()){
            String currLine = input.nextLine();
            String res = numericOrder(currLine);
            output.println(res);
        }
        return 0;
    }
    private static String numericOrder(String inputStr){
        String[] wordsBefore = inputStr.split(" ");
        Hashtable<Integer,String> positionedWords= new Hashtable<Integer,String>();
        Integer minNum = 1;
        Integer maxNum = wordsBefore.length;
        for(int wordNumber=0;wordNumber<wordsBefore.length;wordNumber++){
            String currWord="";
            Integer currNumber =0;
            for(int i=0;i<wordsBefore[wordNumber].length();i++){
                char currChar = wordsBefore[wordNumber].charAt(i);
                if((currChar<='9')&&(currChar>='0')){
                    currNumber= currNumber*10 + (currChar-'0');
                }else{
                    currWord+=String.valueOf(currChar);
                }
            }
            if(currNumber<minNum){
                minNum=currNumber;
            }
            if(currNumber>maxNum){
                maxNum=currNumber;
            }
            positionedWords.put(currNumber,currWord);
        }
        String result = "";
        for(int number = minNum-1;number<=maxNum+1;number++){
            if(positionedWords.containsKey(number)){
                result+=positionedWords.get(number) + " ";
            }
        }
        return result.strip();
    }
    private static int switchNumsWorker(Scanner input, PrintWriter output){
        while(input.hasNextInt()){
            int fromNumber = input.nextInt();
            int replacingNumber;
            if(! input.hasNextInt()){
                output.println("");
                return -1;
            }else{
                replacingNumber = input.nextInt();
            }
            int res = switchNums(fromNumber,replacingNumber);
            output.println(res);
        }
        return 0;
    }
    private static int switchNums(int fromNumber, int replacingNumber){
        int[] digitCount = new int[10];
        for(int i=0;i<10;i++){
            digitCount[i]=0;
        }
        String fromString = String.valueOf(fromNumber);
        String replacingString = String.valueOf(replacingNumber);
        int result=0;
        for(int i=0;i<fromString.length();i++){
            char currChar = fromString.charAt(i);
            if((currChar<='9')&&(currChar>='0')){
                digitCount[currChar-'0']++;
            }
        }
        for(int i=0;i<replacingString.length();i++){
            int currDigit = replacingString.charAt(i) - '0';
            int newDigit=currDigit;
            for(int j=9;j>currDigit;j--){
                if(digitCount[j]!=0){
                    newDigit = j>currDigit ? j :currDigit;
                    digitCount[j]--;
                    break;
                }else{
                    continue;
                }
            }
            result=result*10+newDigit;
        }
        return result;
    }
}