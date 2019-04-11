package lab04;

public class ArrayExample{
    public static void main(String[] args){
    	double[] array = new double[10];
    	
    	//insert the numbers 1 through 10
    	for(int i = 1; i <= array.length; i++){
    		array[i-1] = i;
    	}

    	for(int i = 0; i < array.length; i++){
    		System.out.println(array[i-1]);
    	}
    }
}
