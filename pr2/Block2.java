//import java.util.ArrayList;
import java.util.Random;
//import java.util.Arrays;

public class Block2{
    public static void main(String[] args) {
        if(args.length<1){
            System.out.println("Hello, World!");
            return;
        }
        switch (Integer.parseInt(args[0])){
            case 1:
                if(args.length-1 <1){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(duplicateChars(args[1]));
                break;
            case 2:
                if(args.length-1 <1){
                    System.out.println("Hello, World!");
                    return;
                }
                String input2=args[1];
                for(int i=2;i<args.length;i++){
                    input2+=" "+args[i];
                }
                System.out.println(getInitials(input2));
                break;
            case 3:
                int[] input3 = new int[args.length-1];
                //ArrayList<Integer> input3 = new ArrayList<Integer>();
                for(int i=1;i<args.length;i++){
                    //input3.add(Integer.parseInt(args[i]));
                    input3[i-1]=Integer.parseInt(args[i]);
                }
                System.out.println(differenceEvenOdd(input3));
                break;
            case 4:
                int[] input4 = new int[args.length-1];
                //ArrayList<Integer> input4 = new ArrayList<Integer>();
                for(int i=1;i<args.length;i++){
                    //input4.add(Integer.parseInt(args[i]));
                    input4[i-1]=Integer.parseInt(args[i]);
                }
                System.out.println(equalToAvg(input4));
                break;
            case 5:
                int[] input5 = new int[args.length-1];
                //ArrayList<Integer> input5 = new ArrayList<Integer>();
                for(int i=1;i<args.length;i++){
                    //input5.add(Integer.parseInt(args[i]));
                    input5[i-1]=Integer.parseInt(args[i]);
                }
                //System.out.println(indexMult(input5));
                
                int[] res5 = indexMult(input5);
                String res5_s="";
                for(int i:res5){
                    res5_s+=i+" ";
                }
                System.out.println(res5_s);
                //System.out.println(Arrays.toString(indexMult(input5)));
                break;
            case 6:
                if(args.length-1 <1){
                    System.out.println("Hello, World!");
                    return;
                }
                String input6=args[1];
                for(int i=2;i<args.length;i++){
                    input6+=" "+args[i];
                }
                System.out.println(reverse(input6));
                break;
            case 7:
                if(args.length-1 <1){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(Tribonacci(Integer.parseInt(args[1])));
                break;
            case 8:
                if(args.length-1 <1){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(pseudoHash(Integer.parseInt(args[1])));
                break;
            case 9:
                if(args.length-1 <1){
                    System.out.println("Hello, World!");
                    return;
                }
                String input9=args[1];
                for(int i=2;i<args.length;i++){
                    input9+=" "+args[i];
                }
                System.out.println(botHelper(input9));
                break;
            case 10:
                if(args.length-1 <1){
                    System.out.println("Hello, World!");
                    return;
                }
                String[] input10 = new String[2];
                input10[0]=args[1];
                input10[1]="";
                int flag = 0;
                for(int i=2;i<args.length;i++){
                    if(flag==0){
                        if(args[i].equals("0")){
                            //System.out.println("0 is here");
                            flag=1;
                        }else{
                            input10[0]+= " "+args[i];
                        }
                        
                    }else{
                        if(args[i]=="0"){
                            flag+=1;
                        }else{
                            input10[1]+= " "+args[i];
                        }
                    }
                }
                if(flag!=1){
                    System.out.println("Hello, World!");
                    break;
                }
                //System.out.println(/*input10[0]+" "+*/input10[1]);
                System.out.println(isAnagram(input10[0],input10[1]));
                break;
            default:
                System.out.println("Hello, World!");
                break;
        }
        return;
    }
    //есть ли дублирующиеся буквы
    public static boolean duplicateChars(String a){
        for(int i=0;i<a.length();i++){
            if(a.indexOf(a.charAt(i),i+1)>-1){
                return false;
            }
        }
        return true;
    }
    //инициалы по полному имени (первый способ - проверка через ASCII, второй - любой следующий за пробелом)
    public static String getInitials(String name){
        //method 1
        String res="";
        boolean space_flag=true;
        for(int i=0;i<name.length();i++){
            if((name.charAt(i)>='A')&&(name.charAt(i)<='Z')){
                if(space_flag){
                    res+=String.valueOf(name.charAt(i));
                    space_flag=false;
                }
            }else if(name.charAt(i)==' '){
                space_flag=true;
            }
        }
        return res;
        //method 2
        /*
        String res=String.valueOf(name.charAt(i));
        for(int i=0;i<name.length();i++){
            int index = name.indexOf(" ",i);
            if((index>-1)&&(index<name.length()-1)){
              res+=String.valueOf(name.charAt(index+1));
            }
        }
        return res;
        */
    }
    //разница между четными и нечетными
    /*
    public static int differenceEvenOdd(ArrayList<Integer> input){
        int sumEven=0;
        int sumOdd=0;
        for(int i=0;i<input.size();i++){
            if(input.get(i)%2==0){
                sumEven+=input.get(i);
            }else{
                sumOdd+=input.get(i);
            }
        }
        return sumEven-sumOdd;
    }*/
    public static int differenceEvenOdd(int[] input){
        int sumEven=0;
        int sumOdd=0;
        for(int i=0;i<input.length;i++){
            if(input[i]%2==0){
                sumEven+=input[i];
            }else{
                sumOdd+=input[i];
            }
        }
        return sumEven-sumOdd;
    }
    //равный среднему (для целых)
    /*public static boolean equalToAvg(ArrayList<Integer> input){
        int sum=0;        
        for(int i=0;i<input.size();i++){
            sum+=input.get(i);
        }
        if(sum%input.size()==0){
            for(int i=0;i<input.size();i++){
                if(sum/input.size()==input.get(i)){
                    return true;
                }
            }
        }
        return false;
    }*/
    public static boolean equalToAvg(int[] input){
        int sum=0;        
        for(int i=0;i<input.length;i++){
            sum+=input[i];
        }
        if(sum%input.length==0){
            for(int i=0;i<input.length;i++){
                if(sum/input.length==input[i]){
                    return true;
                }
            }
        }
        return false;
    }
    //элемент умножить на индекс
    /*
    public static ArrayList<Integer> indexMult(ArrayList<Integer> input){
        ArrayList<Integer> res = new ArrayList<Integer>();
        for(int i=0;i<input.size();i++){
            res.add(i*input.get(i));
        }
        return res;
    }*/
    public static int[] indexMult(int[] input){
        int[] res = new int[input.length];
        for(int i=0;i<input.length;i++){
            res[i]=i*input[i];
        }
        return res;
    }
    //обращение строки
    public static String reverse(String s){
        String res="";
        for(int i=s.length()-1;i>-1;i--){
            res+=String.valueOf(s.charAt(i));
        }
        return res;
    }
    //числа Трибоначчи
    public static int Tribonacci(int n){
        int a=0;
        int b=0;
        int c=1;
        if(n==1){return a;}
        if(n==2){return b;}
        if(n==3){return c;}
        int r=a+b+c;
        for(int i=4;i<n;i++){
            a=b;
            b=c;
            c=r;
            r=a+b+c;
        }
        return r;
    }
    //хэш заданной длины
    public static String pseudoHash(int input){
        int number_last_char = 6; //f
        String template="";
        for(int i=0;i<10;i++){
            template+=String.valueOf(i);
        }
        /*for(int i=0;i<number_last_char;i++){
            template+=String.valueOf(i+'A');
        }*/
        for(int i=0;i<number_last_char;i++){
            template+=String.valueOf((char)(i+'a'));
        }
        System.out.println(template);
        String res="";
        Random rnd= new Random();
        for(int i=0;i<input;i++){
            int rnd_index=rnd.nextInt(0,template.length());
            res+=template.substring(rnd_index,rnd_index+1);
        }
        return res;
    }
    //Поиск слова 'help'
    public static String botHelper(String input){
        if(input.toLowerCase().indexOf("help")>-1){
            return "Calling for a staff member";
        }else{
            return "Keep waiting";
        }
    }
    //Анаграммы из букв
    public static boolean isAnagram(String a, String b){
        int[] countAChars = new int[26];
        int[] countBChars = new int[26];
        for(int i=0;i<26;i++){
            countAChars[i]=0;
            countBChars[i]=0;
        }
        for(int i=0;i<a.length();i++){
            if((a.charAt(i)>='A')&&(a.charAt(i)<='Z')){
                countAChars[a.charAt(i)-'A']++;
            }
            if((a.charAt(i)>='a')&&(a.charAt(i)<='z')){
                countAChars[a.charAt(i)-'a']++;
            }

        }

        for(int i=0;i<b.length();i++){
            if((b.charAt(i)>='A')&&(b.charAt(i)<='Z')){
                countBChars[b.charAt(i)-'A']++;
            }
            if((b.charAt(i)>='a')&&(b.charAt(i)<='z')){
                countBChars[b.charAt(i)-'a']++;
            }

        }
        for(int i=0;i<26;i++){
            if(countAChars[i]!=countBChars[i]){
                return false;
            }
        }
        return true;
    }
}