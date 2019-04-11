package assignment01;

public class RectangleTest
{
    public static void main(String[] args)
    {
    	var rect = new Rectangle(Math.PI / 6, 10, 5, 20, 30);

    	System.out.printf("testing Rectangle.area: expecting %f got %f%n",
    			50.0, rect.area());

    	System.out.printf("testing Rectangle.findX1: expecting %f got %f%n",
    			28.660, rect.findX1());

    	System.out.printf("testing Rectangle.findY1: expecting %f got %f%n",
    			35.0, rect.findY1());

    	System.out.printf("testing Rectangle.findX2: expecting %f got %f%n",
    			17.5, rect.findX2());

    	System.out.printf("testing Rectangle.findY2: expecting %f got %f%n",
    			34.330, rect.findY2());

    	System.out.printf("testing Rectangle.findX3: expecting %f got %f%n",
    			26.16, rect.findX3());

    	System.out.printf("testing Rectangle.findY3: expecting %f got %f%n",
    			39.33, rect.findY3());

    	System.out.printf("testing Rectangle.findC: expecting %f got %f%n",
    			47.236, rect.findC());

    	System.out.printf("testing Rectangle.findA: expecting %f got %f%n",
    			.464, rect.findA());

    	System.out.println("testing Rectangle.extractTriangle");

    	var t = rect.extractTriangle();

    	System.out.println("success");
    }
}
