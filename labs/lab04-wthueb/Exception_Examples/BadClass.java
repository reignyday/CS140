package lab04;

class BadClass {
    private int[] myArray;

    public BadClass() {
        System.out.println("There are " + myArray.length + " elements");
    }

    public static void main(String[] args){
    	BadClass breaks = new BadClass();
    }
}