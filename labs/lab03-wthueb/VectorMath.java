package lab03;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VectorMath{

    //returns the magnitude of vector
    public static Optional<Double> magnitude(List<Double> vect){
    	if (vect == null)
    		return Optional.empty();

    	if (vect.size() == 0)
    		return Optional.of(0.0);

    	double sum = 0;

    	for (int i = 0; i < vect.size(); ++i)
    		sum += vect.get(i) * vect.get(i);

    	return Optional.of(Math.sqrt(sum));
    }

    //multiplies vect by some scalar value
    //actually modifies the vector, does not return a separate one
    public static void scalarProduct(List<Double> vect, double scale){
    	if (vect == null || vect.size() == 0)
    		return;
    	
    	for (int i = 0; i < vect.size(); ++i)
    	{
    		// do we actually have to do this since ArrayList.get
    		// returns a reference?
    		var x = vect.get(i);

    		x *= scale;

    		vect.set(i, x);
    	}
    }

    //returns the dotProduct between two vectors
    public static Optional<Double> dotProduct(List<Double> vect1, List<Double> vect2){
    	if (vect1 == null || vect2 == null)
    		return Optional.empty();

    	if (vect1.size() == 0 || vect2.size() == 0)
    		return Optional.of(0.0);

    	double ret = 0;
    	int minSize = Math.min(vect1.size(), vect2.size());

    	for (int i = 0; i < minSize; ++i)
    		ret += vect1.get(i) * vect2.get(i);

    	return Optional.of(ret);
    }

    //returns whether two vectors are orthogonal
    public static boolean isOrthogonal(List<Double> vect1, List<Double> vect2){
    	// Optional.orElse returns the value if it exists, or else
    	// the value that you pass

    	var res = VectorMath.dotProduct(vect1, vect2).orElse(null);

    	return res != null && res == 0.0;
    }

    //performs vector addition, returning a new vector
    public static Optional<List<Double>> vectorAddition(List<Double> vect1, List<Double> vect2){
    	if (vect1 == null || vect2 == null)
    		return Optional.empty();

    	if (vect1.size() == 0)
    		return Optional.of(vect2);
    	
    	if (vect2.size() == 0)
    		return Optional.of(vect1);

    	List<Double> sum = new ArrayList<Double>();
    	int maxSize = Math.max(vect1.size(), vect2.size());

    	for (int i = 0; i < maxSize; ++i)
    	{
    		double component = 0.0;

    		if (i < vect1.size())
    			component += vect1.get(i);
    		
    		if (i < vect2.size())
    			component += vect2.get(i);

    		sum.add(component);
    	}

    	return Optional.of(sum);
    }
}
