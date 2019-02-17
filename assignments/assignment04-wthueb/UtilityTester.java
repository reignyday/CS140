package assignment04;

import java.util.Arrays;

public class UtilityTester
{
    public static void main(String[] args)
    {
        System.out.println("----- Utility::concat -----");
        System.out.println("expect: [3, 6, 10, 11, -10, 6, 9, 10, 12, 2, 7, 7, 12, -1, 1, 1, 4, 9, 10]");
        System.out.println(Arrays.toString(Utility.concat(
                    new int[] {3, 6, 10, 11},
                    new int[] {-10, 6, 9, 10, 12},
                    new int[] {2, 7, 7, 12},
                    new int[] {-1, 1, 1, 4, 9, 10})));

        System.out.println("\n----- Utility::merge -----");
        System.out.println("expect: [-10, -1, 1, 1, 2, 3, 4, 6, 6, 7, 7, 9, 9, 10, 10, 10, 11, 12, 12]");
        System.out.println(Arrays.toString(Utility.merge(
                    new int[] {3, 6, 10, 11},
                    new int[] {-10, 6, 9, 10, 12},
                    new int[] {2, 7, 7, 12},
                    new int[] {-1, 1, 1, 4, 9, 10})));
    }
}
