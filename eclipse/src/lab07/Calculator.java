package lab07;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator{
    private static List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
    
    public static int reduce(int start, List<Integer> list, Operation op){
        int ret = start;
        for(Integer i : list) ret = op.operation(ret, i);
        return ret;
    }

    public static void main(String[] args){
        int sum = reduce(0, list, new Add());
        int difference = reduce(0, list, new Subtract());
        int product = reduce(1, list, new Multiply());
        int concat = reduce(0, list, (a,b) -> Integer.parseInt(a + "" + b));
        
        System.out.println("sum: " + sum);
        System.out.println("difference: " + difference);
        System.out.println("product: " + product);
        System.out.println("concat: " + concat);
    }
}
