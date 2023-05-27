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


class Node {
    int tag;
    int value;
    Node left;
    Node right;
    public Node(int value, int tag){
        this.value = value;
        left = null;
        right = null;
        this.tag = tag;
    }
}

class Result {

    public static Result bt = new Result();
    public static Node root;
    /*
     * Complete the 'countUniqueSums' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING inputString as parameter.
     */
    
    public static ArrayList<Integer> arr = new ArrayList<>();

    public static int countUniqueSums(String inputString) {
        for(int i=0; i<inputString.length(); i++){
            bt.add(Integer.parseInt(inputString.substring(i, i+1)), i);
        }
        
        int i=0;
        while(i<inputString.length()){
            i++;
            sumWithDistance(root, i, 0, 0);
        }
        
        
        return arr.size();
    }

    public static void sumFromCur(Node n, int maxstep, int curcount, int sum, ArrayList<Integer> sums){
        
        if(n==null){
            return;
        }
        
        sum+=n.value;
        if(curcount==maxstep){
            sums.add(sum);
            return;
        }
        
        curcount++;
        sumFromCur(n.left, maxstep, curcount, sum, sums);
        sumFromCur(n.right, maxstep, curcount, sum, sums);
        return;
    }

    public static void sumWithDistance(Node n, int maxstep, int curcount, int sum){
        
        if(n==null){
            return;
        }
        
        
        if(curcount==0){
            for(int i=0; i<maxstep-1; i++){
                ArrayList<Integer> sumsL = new ArrayList<>();
                sumFromCur(n.left, i, 1, 0, sumsL);
                ArrayList<Integer> sumsR = new ArrayList<>();
                sumFromCur(n.right, maxstep-1-i, 1, 0, sumsR);
                if(sumsL==null||sumsR==null){
                    break;
                }
                for(int l : sumsL){
                    for(int r : sumsR){
                        int x = l+r+n.value;
                        if(arr.contains(x)){
                            continue;
                        }
                        arr.add(x);
                    }
                }
            }
        }
        
        //starts new on current node
        sumWithDistance(n.left, maxstep, 0, 0);
        sumWithDistance(n.right, maxstep, 0, 0);
        
        sum+=n.value;
        if(curcount==maxstep){
            if(arr.contains(sum)){
                return;
            }
            arr.add(sum);
            return;
        }
        
        curcount++;

        //continues current search
        sumWithDistance(n.left, maxstep, curcount, sum);
        sumWithDistance(n.right, maxstep, curcount, sum);
    }

    private Node addRecursive(Node current, int value, int tag) {
        if (current == null) {
            return new Node(value, tag);
        }
    
        if (value < current.value) {
            current.left = addRecursive(current.left, value, tag);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value, tag);
        } else {
            current.left = addRecursive(current.left, value, tag);
            return current;
        }
    
        return current;
    }
    public void add(int value, int tag) {
        root = addRecursive(root, value, tag);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        } 
        if (value == current.value) {
            return true;
        } 
        return value < current.value ? containsNodeRecursive(current.left, value) : containsNodeRecursive(current.right, value);
    }
    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }


}

public class FinalsQ2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String inputString = bufferedReader.readLine();

        int result = Result.countUniqueSums(inputString);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
