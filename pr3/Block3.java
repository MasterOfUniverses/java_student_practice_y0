
import java.io.File;
import java.util.Scanner;
//import java.util.AbstractCollection;
public class Block3{
    public static void main(String[] args) throws Exception{
        if(args.length<2){
            System.out.println("Hello, World!");
            return;
        }
        File file = new File(args[1]);
        Scanner sc = new Scanner(file);
        String inputStr="";
        switch (Integer.parseInt(args[0])){
            case 1:
                while(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                    //System.out.println(inputStr);
                    System.out.println(replaceVovels(inputStr));
                }
                break;
            case 2:
                while(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                    //System.out.println(inputStr);
                    System.out.println(stringTransform(inputStr));
                }
                break;
            case 3:
                while(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                    String[] fileArgs4 = inputStr.split(" ");
                    if(fileArgs4.length<5){
                        System.out.println("Hello, World!");
                        return;
                    }
                    System.out.println(doesBlockFit(Integer.parseInt(fileArgs4[0]),Integer.parseInt(fileArgs4[1]),
                                                    Integer.parseInt(fileArgs4[2]),Integer.parseInt(fileArgs4[3]),Integer.parseInt(fileArgs4[4])));
                }
                break;
            case 4:
                while(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                    System.out.println(numCheck(Integer.parseInt(inputStr)));
                }
                break;
            case 5:
                while(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                    String[] fileArgs5 = inputStr.split(" ");
                    if(fileArgs5.length<3){
                        System.out.println("Hello, World!");
                        return;
                    }
                    int[] inputIntArr = {Integer.parseInt(fileArgs5[0]),Integer.parseInt(fileArgs5[1]),Integer.parseInt(fileArgs5[2])};
                    System.out.println(countRoots(inputIntArr));
                }
                break;
            case 6:
                if(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                }else{
                        System.out.println("Hello, World!");
                        return;
                }
                String[] dataSize = inputStr.split(" ");
                int thingsCount = Integer.parseInt(dataSize[0]);
                int maxShopsCount = Integer.parseInt(dataSize[1]);
                String[][] inputData= new String[thingsCount][maxShopsCount+1];
                for(int i=0;i<thingsCount;i++){
                    for(int j=0;j<maxShopsCount+1;j++){
                        inputData[i][j]="";
                    }
                }
                int index6=0;
                while((sc.hasNextLine())&&(index6<thingsCount)){
                    inputStr = sc.nextLine();
                    String[] inputRow6 = inputStr.split(" ");
                    for(int j=0;j<inputRow6.length;j++){
                        inputData[index6][j] = inputRow6[j];
                    }
                    index6++;
                }
                String[] res6_arr = salesData(inputData);
                String res6_s="";
                for(String s:res6_arr){
                    res6_s+=s+" ";
                }
                System.out.println(res6_s);
                break;
            
            case 7:
                while(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                    //System.out.println(inputStr);
                    System.out.println(validSplit(inputStr));
                }
                break;
            case 8:
                while(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                    String[] fileArgs8 = inputStr.split(" ");
                    int[] intArgs8 = new int[fileArgs8.length];
                    for(int i=0;i<fileArgs8.length;i++){
                        intArgs8[i] = Integer.parseInt(fileArgs8[i]);
                    }
                    //System.out.println(inputStr);
                    System.out.println(waveForm(intArgs8));
                }
                break;
            case 9:
                while(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                    //System.out.println(inputStr);
                    System.out.println(commonVovel(inputStr));
                }
                break;
            case 10:
                if(sc.hasNextLine()){
                    inputStr = sc.nextLine();
                }else{
                        System.out.println("Hello, World!");
                        return;
                }
                int matrixSize = Integer.parseInt(inputStr);
                Double[][] dataMatrix= new Double[matrixSize][matrixSize];
                for(int i=0;i<matrixSize;i++){
                    for(int j=0;j<matrixSize;j++){
                        dataMatrix[i][j]=0.0;
                    }
                }
                int index10=0;
                while((sc.hasNextLine())&&(index10<matrixSize)){
                    inputStr = sc.nextLine();
                    String[] inputRow6 = inputStr.split(" ");
                    for(int j=0;j<inputRow6.length;j++){
                        dataMatrix[index10][j] = Double.parseDouble(inputRow6[j]);
                    }
                    index10++;
                }
                dataScience(dataMatrix);
                String res10_str="";
                for(int i=0;i<matrixSize;i++){/*
                    String rowStr="";
                    for(int j=0;j<matrixSize;j++){
                        rowStr+=dataMatrix[i][j]+" ";
                    }
                    System.out.println(rowStr);*/
                    res10_str+=dataMatrix[i][i]+" ";
                }
                System.out.println(res10_str);
                break;
            default:
                System.out.println("Hello, World!");
                break;
        }
        return;
    }
    public static String replaceVovels(String input){
        String res="";
        char[] vovelsTemplate={'a','e','i','o','u','y'};
        for(char ch:input.toCharArray()){
            boolean isVovel=false;
            for(char vov:vovelsTemplate){
                if((vov==ch)||(vov==ch-('A'-'a'))){
                    isVovel=true;
                }
            }
            if(isVovel){
                res+="*";
            }else{
                res+=ch;
            }
        }
        return res;
    }
    public static String stringTransform(String input){
        boolean isFirstChar = true;
        boolean wasReplaced = false;
        char oldChar = '\n';
        String res="";
        for(char ch:input.toCharArray()){
            if(isFirstChar){
                oldChar=ch;
                isFirstChar=false;
            }else{
                if((oldChar==ch)&&(!wasReplaced)){
                    res+="Double"+oldChar;
                    isFirstChar=true;
                    wasReplaced = true;
                }else{
                    res+=oldChar;
                    oldChar=ch;
                    wasReplaced=false;
                }
            }
        }
        if(!isFirstChar){
            res+=oldChar;
        }
        return res;
    }
    public static boolean doesBlockFit(int a,int b,int c, int w,int h){
        int min1 = a;
        int min2 = b;
        int min3 = c;
        if(min1>b){
            min1=b;
            min2=a;
        }
        if(min1>c){
            min3=min1;
            min1=c;
        }
        int tmp=0;
        if(min2>min3){
            tmp=min2;
            min2=min3;
            min3=tmp;
        }
        int max1 = w;
        int max2 = h;
        if(max1<max2){
            max1=h;
            max2=w;
        }
        return (min2<=max1)&&(min1<=max2);
    }
    public static boolean numCheck(int num){
        int sum=0;
        int tail=num;
        while(tail>0){
            sum+=(tail%10)*(tail%10);
            tail=tail/10;
        }
        return sum%2==num%2;
    }
    public static int countRoots(int[] polyNums){
        if(polyNums.length!=3){
            return -1;
        }
        if((polyNums[0]==0)&&(polyNums[1]==0)&&(polyNums[2]==0)){
            return -1;
        }else if((polyNums[0]==0)&&(polyNums[1]==0)){
            return 0;
        }else if(polyNums[0]==0){
            return 1;
        }else{
            int Discr = polyNums[1]*polyNums[1]-4*polyNums[0]*polyNums[2];
            if(Discr>0){
                return 2;
            }else if(Discr<0){
                return 0;
            }else{
                return 1;
            }
        }
    }
    public static String[] salesData(String[][] data){
        String[] allShops = new String[data[0].length - 1];
        for(int i=0;i<allShops.length;i++){
            allShops[i]="";
        }
        int realShopCount=0;
        for(int i=0;i<data.length;i++){                 //O(m*n*n) -> with hashSet O(m*n*log(n))
            for(int j=1;j<allShops.length+1;j++){
                for(int k=0;k<allShops.length;k++){
                    if(data[i][j].equals(allShops[k])){
                        break;
                    }else if(allShops[k].equals("")){
                        allShops[k]=data[i][j];
                        if(k+1>realShopCount){
                            realShopCount=k+1;
                        }
                        break;
                    }
                }
            }
        }
        String res="";
        for(int i=0;i<data.length;i++){                 //O(m*n) -> with ArrayList O(m) or ArrayList+hashSet O(m*n*log(n)) <- second var can fix duplicated shops for one thing
            int realRowCount = 0;
            for(int j=1;j<data[i].length;j++){
                if(data[i][j]!=""){
                    realRowCount++;
                }else{
                    break;
                }
            }
            if(realRowCount==realShopCount){
                res+=data[i][0]+" ";
            }
        }
        return res.split(" ");
    }
    public static boolean validSplit(String str){
        String[] words = str.toLowerCase().split(" ");
        for(int i=1;i<words.length;i++){
            if(words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0)){
                return false;
            }
        }
        return true;
    }
    public static int sgn(int a){
        if(a==0){
            return 0;
        }
        if(a<0){
            return -1;
        }
        return 1;
    }
    public static boolean waveForm(int[] nums){
        if(nums.length<2){
            return true;
        }
        int flag=sgn(nums[0]-nums[1]);
        if(flag==0){
            return false;
        }
        for(int i=2;i<nums.length;i++){
            if(flag*sgn(nums[i-1]-nums[i]) != -1){
                return false;
            }else{
                flag = sgn(nums[i-1] - nums[i]);
            }
        }
        return true;
    }
    public static char commonVovel(String str){
        char[] vovelsTemplate={'a','e','i','o','u','y'};
        int maxCount=0;
        int count=0;
        int index=0;
        for(int i=0;i<vovelsTemplate.length;i++){
            count=0;
            for(char ch:str.toLowerCase().toCharArray()){
                if(ch==vovelsTemplate[i]){
                    count++;
                }
            }
            if(maxCount<count){
                maxCount=count;
                index=i;
            }
        }
        return vovelsTemplate[index];
    }
    public static void dataScience(Double[][] data){
        for(int i=0;i<data.length;i++){
            Double avg=0.0;
            for(int j=0;j<data.length;j++){
                if(i!=j){
                    avg+=data[j][i]/(data.length -1);
                }
            }
            data[i][i] = avg;
        }
        return;
    }
}