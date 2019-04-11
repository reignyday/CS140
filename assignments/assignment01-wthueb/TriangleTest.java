package assignment01;

public class TriangleTest
{
    public static void main(String[] args)
    {
    	var triang = Triangle.makeTriangleWithRadians(Math.PI / 6, 3, Math.PI / 2, 4, 20, 30);

    	System.out.printf("testing Triangle.area: expecting %f got %f%n",
    			6.0, triang.area());

    	System.out.printf("testing Triangle.findX1: expecting %f got %f%n",
    			22.598, triang.findX1());

    	System.out.printf("testing Triangle.findY1: expecting %f got %f%n",
    			31.5, triang.findY1());

    	System.out.printf("testing Triangle.findX2: expecting %f got %f%n",
    			18.0, triang.findX2());

    	System.out.printf("testing Triangle.findY2: expecting %f got %f%n",
    			33.464, triang.findY2());

    	System.out.printf("testing Triangle.findC: expecting %f got %f%n",
    			5.0, triang.findC());

    	System.out.printf("testing Triangle.findA: expecting %f got %f%n",
    			.927, triang.findA());

    	System.out.println("testing Triangle.doubleAreaRectangle");

    	var t = triang.doubleAreaRectangle();

    	System.out.println("success");
    }
}
