package Practice5;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Block5 {
    public static void main(String[] args){
        try{
            String fileWithInstructionsAdress = "input.txt";
            if(args.length==1){
                fileWithInstructionsAdress = args[0];
            }
            File instuctionsFile = new File(fileWithInstructionsAdress);
            Scanner instructionsScanner = new Scanner(instuctionsFile);
            int result = doingCycle(instructionsScanner);
            System.out.println("Works was ended: " + String.valueOf(result));
            return;
        }catch (Exception e){
            System.out.println("Some problems with instruction file, no work to do");
            return;
        }
    }
    private static int doingCycle(Scanner instuctions){
        int res=0;
        while(instuctions.hasNextLine()){
            String newWork = instuctions.nextLine();
            if(newWork.isEmpty()){
                continue;
            }
            int isOK = choseOneFunction(newWork);
            if(isOK>=0){
                res++;
            }else{
                System.out.println("After " + String.valueOf(res) + " good works we had some trouble with code " + String.valueOf(isOK));
            }
        }
        return res;
    }
    private static int choseOneFunction(String newWork){
        try{
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
                    errorCode = sameLetterPatternWorker(input,output);
                    break;
                case 2:
                    //errorCode = (input,output);
                    break;
                case 3:
                    //errorCode = (input,output);
                    break;
                case 4:
                    //errorCode = (input,output);
                    break;
                case 5:
                    //errorCode = (input,output);
                    break;
                case 6:
                    //errorCode = (input,output);
                    break;
                case 7:
                    //errorCode = (input,output);
                    break;
                case 8:
                    //errorCode = (input,output);
                    break;
                case 9:
                    //errorCode = (input,output);
                    break;
                case 10:
                    //errorCode = (input,output);
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
        }catch(Exception e){
            System.out.println("Exception on work with task: "+newWork);
            return -4;
        }
    }
    private static int sameLetterPatternWorker(Scanner input, PrintWriter output){
        while(input.hasNext()){
            String str1 = input.next();
            if(!input.hasNext()){
                return -1;
            }
            String str2 = input.next();
            boolean res = sameLetterPattern (str1,str2);
            output.println(res);
        }
        return 0;
    }
    private static boolean sameLetterPattern(String str1, String str2){
        if(str1.length()!=str2.length()){
            return false;
        }
        for(int i=0;i<str1.length();i++){
            if(str1.indexOf(str1.substring(i,i+1),i+1)!=str2.indexOf(str2.substring(i,i+1),i+1)){
                return false;
            }
        }
        return true;
    }
    private static int spiderVsFlyWorker(Scanner input, PrintWriter output){
        while(input.hasNext()){
            String str1 = input.next();
            if(!input.hasNext()){
                return -1;
            }
            String str2 = input.next();
            if((str1.length()<2)||(str2.length()<2)){
                continue;
            }
            String res = spiderVsFly (str1,str2);
            output.println(res);
        }
        return 0;
    }
    private static String spiderVsFly(String spiderPos,String flyPos){
        String res="";
        final int COUNTBRANCHES = 8;
        final double MAXDELTABRANCHES = COUNTBRANCHES / 3.14159265;
        int spiderBranch = spiderPos.charAt(0)-'A';
        int flyBranch = flyPos.charAt(0)-'A';
        int spiderRadius = spiderPos.charAt(1)-'0';
        int flyRadius = flyPos.charAt(1)-'0';
        int deltaBranch = (spiderBranch-flyBranch+COUNTBRANCHES)%COUNTBRANCHES;// -7.-101.7 -> 1..789..15 -> 1...701...7
        if(((spiderRadius!=0)&&(flyRadius!=0))&&((deltaBranch>COUNTBRANCHES-MAXDELTABRANCHES)||(deltaBranch<MAXDELTABRANCHES))){
            res=noCentreWay(COUNTBRANCHES,spiderBranch,spiderRadius,flyBranch,flyRadius,deltaBranch);
        }else{
            res=centreWay(spiderBranch,spiderRadius,flyBranch,flyRadius);
        }
        return res;
    }
    private static String noCentreWay(int countBranches, int startBranch, int startRadius, int endBranch,int endRadius,int deltaBranch){
        int deltaRadius = startRadius-endRadius;
        boolean isToCentre = true;
        boolean isClockwise = true;
        if(deltaRadius<0){
            isToCentre=false;
            deltaRadius*=-1;
        }
        if(deltaBranch<0){
            isClockwise=false;
        }
        if(isToCentre){
            for(int i=startRadius;i>endRadius;i--){

            }
            if(isClockwise){

            }
        }
        String res="";
        return res;
    }
    private static String centreWay(int startBranch, int startRadius, int endBranch,int endRadius){
        String res="";
        res+=String.valueOf(startBranch+'A')+String.valueOf(startRadius);
        if(startRadius!=0){
            res+="-";
        }
        res+=oneRadiusWay(startBranch,startRadius,true);
        if((startRadius!=0)&&(endRadius!=0)){
            res+="A0-";
        }
        res+=oneRadiusWay(endBranch,endRadius,false);
        if(endRadius!=0){
            res+=String.valueOf(endBranch+'A')+String.valueOf(endRadius);
        }
        return res;
    }
    private static String oneRadiusWay(int branch, int oneEnd, boolean isToCentre){
        String res="";
        if(isToCentre){
            for(int i=oneEnd-1;i>0;i--){
                res+=String.valueOf(branch +'A')+String.valueOf(i)+"-";
            }
        }else{
            for(int i=1;i<oneEnd;i++){
                res+=String.valueOf(branch +'A')+String.valueOf(i)+"-";
            }
        }
        return res;
    }
}
