public class Main {
    public static void main(String[] args){
        System.out.println(testMethod(3));
    }
    public static int testMethod(int x){
        if(x<=1){
            return x;
        }
        return testMethod(x-1)+testMethod(x-2);
    }
}
