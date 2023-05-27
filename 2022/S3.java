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
    private static ArrayList<Character> letters = new ArrayList<>();
    private static ArrayList<Integer> vals = new ArrayList<>();
    /*
     * Complete the 'getTraversals' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING input as parameter.
     */

    public static String getTraversals(String input) {
        letters.add(input.charAt(0));
        vals.add(0);
        for(int i=1; i<input.length(); i++){
            char c = input.charAt(i);
            if(c<=letters.get(0)){
                letters.add(0, c);
                vals.add(0, vals.get(0)+1);
            } else if(c>letters.get(letters.size()-1)){
                letters.add(c);
                vals.add(vals.get(vals.size()-1)+1);
            } else{
                for(int j=0; j<letters.size()-1; j++){
                    if(c>letters.get(j)&&c<=letters.get(j+1)){
                        letters.add(j+1, c);
                        vals.add(j+1, Math.max(vals.get(j), vals.get(j+1))+1);
                        break;
                    }
                }
            }
        }
        
        String rtn = createString();
        
        return rtn;
    }
    
    public static String createString(){
        String firstPart = "";
        String secondPart = "";
        
        int zeroIndex = 0;
        for(int i=0; i<vals.size(); i++){
            if(vals.get(i)==0){
                zeroIndex = i;
                break;
            }
        }
        
        firstPart = letters.get(zeroIndex)+searchLeft(zeroIndex, 0)+searchRight(zeroIndex, 0);
        secondPart = searchLeftReverse(zeroIndex, 0)+searchRightReverse(zeroIndex, 0)+letters.get(zeroIndex);
        
        return firstPart+" "+secondPart;
    }
    
    
    public static String searchLeftReverse(int sIndex, int curVal){
        sIndex--;
        while(sIndex>=0&&vals.get(sIndex)>curVal){
            if(vals.get(sIndex)==curVal+1){
                String rtn = searchLeftReverse(sIndex, curVal+1)+searchRightReverse(sIndex, curVal+1)+letters.get(sIndex).toString();
                return rtn;
            }
            sIndex--;
        }
        return "";
    }
    public static String searchRightReverse(int sIndex, int curVal){
        sIndex++;
        while(sIndex<vals.size()&&vals.get(sIndex)>curVal){
            if(vals.get(sIndex)==curVal+1){
                String rtn = searchLeftReverse(sIndex, curVal+1)+searchRightReverse(sIndex, curVal+1)+letters.get(sIndex).toString();
                return rtn;
            }
            sIndex++;
        }
        return "";
    }
    
    
    public static String searchLeft(int sIndex, int curVal){
        sIndex--;
        while(sIndex>=0&&vals.get(sIndex)>curVal){
            if(vals.get(sIndex)==curVal+1){
                String rtn = letters.get(sIndex).toString()+searchLeft(sIndex, curVal+1)+searchRight(sIndex, curVal+1);
                return rtn;
            }
            sIndex--;
        }
        return "";
    }
    public static String searchRight(int sIndex, int curVal){
        sIndex++;
        while(sIndex<vals.size()&&vals.get(sIndex)>curVal){
            if(vals.get(sIndex)==curVal+1){
                String rtn = letters.get(sIndex).toString()+searchLeft(sIndex, curVal+1)+searchRight(sIndex, curVal+1);
                return rtn;
            }
            sIndex++;
        }
        return "";
    }

}
public class S3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String input = bufferedReader.readLine();

        String result = Result.getTraversals(input);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
