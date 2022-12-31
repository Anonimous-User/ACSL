import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'findModeCount' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER num
     *  2. INTEGER base
     *  3. STRING start
     */
    private static int[] occurs = new int[16];
    public static int findModeCount(int num, int base, String start) {
        int st = Integer.parseInt(convert(start, base, 10));
        for(int i=st; i<st+num; i++){
            String number = convert(Integer.toString(i), 10, base);
            for(int j=0; j<number.length(); j++){
                if(number.charAt(j)-48<10){
                    occurs[number.charAt(j)-48] += 1;
                } else{
                    occurs[number.charAt(j)-97+10] += 1;
                }
            }
        }
        int rtn = occurs[0];
        for(int i : occurs){
            if(i>rtn){
                rtn = i;
            }
        }
        return rtn;
    }
    public static String convert(String num, int current, int target){
        return Integer.toString(Integer.parseInt(num, current), target);
    }

}

public class S1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int num = Integer.parseInt(bufferedReader.readLine().trim());

        int base = Integer.parseInt(bufferedReader.readLine().trim());

        String start = bufferedReader.readLine();

        int result = Result.findModeCount(num, base, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
