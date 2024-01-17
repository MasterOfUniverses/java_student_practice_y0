public class Block1{
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
                System.out.println(convert(Float.valueOf(args[1])));
                break;
            case 2:
                if(args.length-1 <2){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(fitCalc(Integer.parseInt(args[1]),Integer.parseInt(args[2])));
                break;
            case 3:
                if(args.length-1 <3){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(containers(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3])));
                break;
            case 4:
                if(args.length-1 <3){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(triangleType(Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3])));
                break;
            case 5:
                if(args.length-1 <2){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(ternaryEvaluation(Integer.parseInt(args[1]),Integer.parseInt(args[2])));
                break;
            case 6:
                if(args.length-1 <3){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(howManyItems(Float.valueOf(args[1]),Float.valueOf(args[2]),Float.valueOf(args[3])));
                break;
            case 7:
                if(args.length-1 <1){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(convert(Integer.parseInt(args[1])));
                break;
            case 8:
                if(args.length-1 <2){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(gcd(Integer.parseInt(args[1]),Integer.parseInt(args[2])));
                break;
            case 9:
                if(args.length-1 <2){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(ticketSaler(Integer.parseInt(args[1]),Integer.parseInt(args[2])));
                break;
            case 10:
                if(args.length-1 <2){
                    System.out.println("Hello, World!");
                    return;
                }
                System.out.println(tables(Integer.parseInt(args[1]),Integer.parseInt(args[2])));
                break;
            default:
                System.out.println("Hello, World!");
                break;
        }
        return;
    }
    //галлоны в литры
    public static float convert (float gallons){
        return gallons*3.785f;
    }
    // Каллории за минуты с уровнем сложности
    public static int fitCalc (int minutes, int level){
        return minutes*level;
    }
    // общий объем коробок, мешков и бочек
    public static int containers (int box, int bag, int barrel){
        return 20*box + 50*bag + 100*barrel;
    }
    //тип треугольника по длиннам сторон
    public static String triangleType(int a,int b,int c){
        if ((a==b)&&(b==c)){
            return "isosceles";
        }
        if((a==b)||(b==c)||(a==c)){
            return "equilateral";
        }
        if((a+b>c)&&(b+c>a)&&(a+c>b)){
            return "different-sided";
        }
        return "not a triangle";
    }
    //тензорный оператор
    public static int ternaryEvaluation(int a,int b){
        return a<b ? b:a;
    }
    //ткань в пододеяльники
    public static int howManyItems(float all, float w, float h){
        return (int)(all/(w*h*2));
    }
    //факториал 
    public static int factorial(int n){
        int res=1;
        for(int i=1;i<n+1;i++){
            res*=i;
        }
        return res;
    }
    //НОД
    public static int gcd(int a,int b){
        int tmp;
        if(a<b){
            tmp=a;
            a=b;
            b=tmp;
        }
        int r=a%b;
        while(r!=0){
            a=b;
            b=r;
            r=a%b;
        }
        return b;
    }
    //выручка от билетов
    public static int ticketSaler(int count,int price){
        return count*price;
    }
    //нехватка двухместных столов
    public static int tables(int students , int tables_count){
        if(students%2==1){
            students-=1;
        }
        if (tables_count*2 - students >0){
            return (tables_count*2 - students)/2;
        }
        return 0;
    }
}