package assignment01;

public class Triangle
{
    private double refAngle;
    
    private double angle;

    private double sideA, sideB;

    private double xBase, yBase;

    private Triangle(double refAngle, double sideA, double angle, double sideB, double xBase,
    		double yBase)
    {
    	this.refAngle = refAngle;

    	this.angle = angle;

    	this.sideA = sideA;
    	this.sideB = sideB;

    	this.xBase = xBase;
    	this.yBase = yBase;
    }

    public static Triangle makeTriangleWithRadians(double refAngle, double sideA, double angle,
    		double sideB, double xBase, double yBase)
    {
    	return new Triangle(refAngle, sideA, angle, sideB, xBase, yBase);
    }

    public static Triangle makeTriangleWithDegrees(double refAngle, double sideA, double angle,
    		double sideB, double xBase, double yBase)
    {
    	return new Triangle(Math.toRadians(refAngle), sideA, Math.toRadians(angle), sideB,
    			xBase, yBase);
    }

    public double area()
    {
    	// ab*sinC/2
    	var ab = this.sideA * this.sideB;

    	return ab * Math.sin(this.angle) / 2.0;
    }
    
    public double findX1()
    {
    	var offset = this.sideA * Math.cos(this.refAngle);

    	return this.xBase + offset;
    }

    public double findY1()
    {
    	var offset = this.sideA * Math.sin(this.refAngle);

    	return this.yBase + offset;
    }

    public double findX2()
    {
    	var newAngle = this.refAngle + this.angle;

    	var offset = this.sideB * Math.cos(newAngle);

    	return this.xBase + offset;
    }

    public double findY2()
    {
    	var newAngle = this.refAngle + this.angle;

    	var offset = this.sideB * Math.sin(newAngle);

    	return this.yBase + offset;
    }

    public double findC()
    {
    	var x = this.findX1() - this.findX2();
    	var y = this.findY1() - this.findY2();

    	return Math.sqrt(x*x + y*y);
    }

    public double findA()
    {
    	return Math.atan(this.sideB / this.sideA);
    }

    public Rectangle doubleAreaRectangle()
    {
    	return new Rectangle(this.refAngle, this.sideA, this.sideB * Math.sin(this.findA()),
    			this.xBase, this.yBase);
    }
}
