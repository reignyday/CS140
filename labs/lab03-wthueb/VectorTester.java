package lab03;

//imports our static methods from the VectorMath class
//now we don't need to write out VectorMath.magnitude for instance
import static lab03.VectorMath.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class VectorTester{
	public static void main(String[] args){
		//here vect1 is made to be an Immutable List, which means we can not change it in any way.
		List<Double> vect1 = List.of(1.0, 2.0, 3.0, 4.0, 5.0, 3.0);

		//the other vectors are mutable so they can be altered by scalarProduct() below
		List<Double> vect2 = new ArrayList<>(Arrays.asList(7.0, 24.0));
		List<Double> vect3 = new ArrayList<>(Arrays.asList(12.0, 4.0, 3.0));
		List<Double> orthogonal_23 = new ArrayList<>(Arrays.asList(72.0, -21.0, -260.0));
		List<Double> singleton = new ArrayList<>(Arrays.asList(7.0));
		List<Double> emptyVect = new ArrayList<>();
		List<Double> nullVect = null;

		System.out.println("**********ORIGINAL VECTORS*********");
		System.out.println("vect1: " + vect1);
		System.out.println("vect2: " + vect2);
		System.out.println("vect3: " + vect3);
		System.out.println("singleton: " + singleton);
		System.out.println("emptyVect: " + emptyVect);
		System.out.println("nullVect: " + nullVect);

		System.out.println("\n**********Dot Product of VECTORS*********");
		System.out.println("vect1 . vect1: \n\tExpected: 64.0\tActual: " + dotProduct(vect1, vect1).get());
		System.out.println("vect2 . vect2: \n\tExpected: 625.0\tActual: " + dotProduct(vect2, vect2).get());
		System.out.println("vect3 . vect3: \n\tExpected: 169.0\tActual: " + dotProduct(vect3, vect3).get());
		System.out.println("vect1 . vect2: \n\tExpected: 55.0\tActual: " + dotProduct(vect1, vect2).get());
		System.out.println("vect2 . vect3: \n\tExpected: 180.0\tActual: " + dotProduct(vect2, vect3).get());
		System.out.println("singleton . singleton: \n\tExpected: 49.0\tActual: " + dotProduct(singleton, singleton).get());

		System.out.println("\n*************Dot Products involving empty and null vectors**************");
		System.out.println("emptyVect . emptyVect: \n\tExpected: 0.0\tActual: " + dotProduct(emptyVect, emptyVect).get());
		System.out.println("nullVect . nullVect: \n\tExpected: Optional.empty\tActual: " + dotProduct(nullVect, nullVect));
		System.out.println("emptyVect . vect2: \n\tExpected: 0.0\tActual: " + dotProduct(emptyVect, vect2).get());
		System.out.println("vect3 . emptyVect: \n\tExpected: 0.0\tActual: " + dotProduct(vect3, emptyVect).get());
		System.out.println("nullVect . vect2: \n\tExpected: Optional.empty\tActual: " + dotProduct(nullVect, vect2));
		System.out.println("vect3 . nullVect: \n\tExpected: Optional.empty\tActual: " + dotProduct(vect3, nullVect));

		System.out.println("\n**********MAGNITUDE OF VECTORS*********");
		System.out.println("vect1: \n\tExpected: 8.0\tActual: " + magnitude(vect1).get());
		System.out.println("vect2: \n\tExpected: 25.0\tActual: " + magnitude(vect2).get());
		System.out.println("vect3: \n\tExpected: 13.0\tActual: " + magnitude(vect3).get());
		System.out.println("singleton: \n\tExpected: 7.0\tActual: " + magnitude(singleton).get());
		System.out.println("emptyVect: \n\tExpected: 0.0\tActual: " + magnitude(emptyVect).get());
		System.out.println("nullVect: \n\tExpected: Optional.empty\tActual: " + magnitude(nullVect));

		System.out.println("\n**********ORTHOGANALITY OF VECTORS*********");
		System.out.println("vect3 should be orthogonal to orthogonal_23. \n\tRESULT: " + isOrthogonal(vect3, orthogonal_23));
		System.out.println("\nvect2 should be orthogonal to orthogonal_23. \n\tRESULT: " + isOrthogonal(vect2, orthogonal_23));
		System.out.println("\nvect1 should NOT be orthogonal to vect2. \n\tRESULT: " + isOrthogonal(vect1, vect2));
		System.out.println("\nvect3 should be orthogonal to emptyVect. \n\tRESULT: " + isOrthogonal(vect3, emptyVect));
		System.out.println("\nvect2 should NOT be orthogonal to nullVect. \n\tRESULT: " + isOrthogonal(vect2, nullVect));

		System.out.println("\n**********ADDITION of VECTORS*********");
		System.out.println("vect1 + vect1: \n\tExpected: [2.0, 4.0, 6.0, 8.0, 10.0, 6.0]\n\tActual: " + vectorAddition(vect1, vect1).get());
		System.out.println("vect2 + vect2: \n\tExpected: [14.0, 48.0]\n\tActual: " + vectorAddition(vect2, vect2).get());
		System.out.println("vect3 + vect3: \n\tExpected: [24.0, 8.0, 6.0]\n\tActual: " + vectorAddition(vect3, vect3).get());
		System.out.println("vect1 + vect2: \n\tExpected: [8.0, 26.0, 3.0, 4.0, 5.0, 3.0]\n\tActual: " + vectorAddition(vect1, vect2).get());
		System.out.println("vect2 + vect3: \n\tExpected: [19.0, 28.0, 3.0]\n\tActual: " + vectorAddition(vect2, vect3).get());
		System.out.println("singleton + singleton: \n\tExpected: [14.0]\n\tActual: " + vectorAddition(singleton, singleton).get());

		System.out.println("\n*************Vector Additions involving empty and null vectors**************");
		System.out.println("emptyVect + emptyVect: \n\tExpected: []\n\tActual: " + vectorAddition(emptyVect, emptyVect).get());
		System.out.println("nullVect + nullVect: \n\tExpected: Optional.empty\n\tActual: " + vectorAddition(nullVect, nullVect));
		System.out.println("emptyVect + nullVect: \n\tExpected: Optional.empty\n\tActual: " + vectorAddition(emptyVect, nullVect));
		System.out.println("nullVect + emptyVect: \n\tExpected: Optional.empty\n\tActual: " + vectorAddition(nullVect, emptyVect));
		System.out.println("emptyVect + vect2: \n\tExpected: [7.0, 24.0]\n\tActual: " + vectorAddition(emptyVect, vect2).get());
		System.out.println("vect3 + emptyVect: \n\tExpected: [12.0, 4.0, 3.0]\n\tActual: " + vectorAddition(vect3, emptyVect).get());
		System.out.println("nullVect + vect2: \n\tExpected: Optional.empty\n\tActual: " + vectorAddition(nullVect, vect2));
		System.out.println("vect3 + nullVect: \n\tExpected: Optional.empty\n\tActual: " + vectorAddition(vect3, nullVect));

		System.out.println("\n**********Scalar Product of 3 Applied to VECTORS*********");
		//here is an example of a try catch block
		//we are catching an exception that we known will occur.
		//you will learn more about exceptions later on in the course.
		try{
			scalarProduct(vect1, 3);
		}
		catch(UnsupportedOperationException e){
			System.out.println(e);
			System.out.println("Error caught: Cannot alter vect1 since it was made immutable.");
		}
		
		scalarProduct(vect2, 3);
		scalarProduct(vect3, 3);
		scalarProduct(singleton, 3);
		scalarProduct(emptyVect, 3);
		scalarProduct(nullVect, 3);

		System.out.println("vect1 (Should NOT change): \n\tExpected: [1.0, 2.0, 3.0, 4.0, 5.0, 3.0]\n\tActual: " + vect1);
		System.out.println("vect2: \n\tExpected: [21.0, 72.0]\n\tActual: " + vect2);
		System.out.println("vect3: \n\tExpected: [36.0, 12.0, 9.0]\n\tActual: " + vect3);
		System.out.println("singleton: \n\tExpected: [21.0]\n\tActual: " + singleton);
		System.out.println("emptyVect: \n\tExpected: []\n\tActual: " + emptyVect);
		System.out.println("nullVect: \n\tExpected: null\n\tActual: " + nullVect);
	}
	
}