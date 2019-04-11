package assignment01;

public class Rectangle
{
    private double refAngle;
    private double sideA, sideB;
    private double xBase, yBase;

    public Rectangle(double refAngle, double sideA, double sideB, double xBase, double yBase)
    {
    	this.refAngle = refAngle;
    	
    	this.sideA = sideA;
    	this.sideB = sideB;

    	this.xBase = xBase;
    	this.yBase = yBase;
    }

    public double area()
    {
    	return this.sideA * this.sideB;
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
    	var newAng = this.refAngle + Math.PI/2;

    	var offset = this.sideB * Math.cos(newAng);
    	
    	return this.xBase + offset;
    }

    public double findY2()
    {
    	var newAng = this.refAngle + Math.PI/2;

    	var offset = this.sideB * Math.sin(newAng);
    	
    	return this.yBase + offset;
    }
    
    public double findX3()
    {
    	return this.findX1() + this.findX2() - this.xBase;
    }

    public double findY3()
    {
    	return this.findY1() + this.findY2() - this.yBase;
    }

    public double findC()
    {
    	var x3 = this.findX3();
    	var y3 = this.findY3();

    	return Math.sqrt(x3*x3 + y3*y3);
    }

    public double findA()
    {
    	return Math.atan(this.sideB / this.sideA);
    }

    public Triangle extractTriangle()
    {
    	return Triangle.makeTriangleWithRadians(this.refAngle, this.sideA, this.findA(),
    			this.findC(), this.xBase, this.yBase);
    }
}
