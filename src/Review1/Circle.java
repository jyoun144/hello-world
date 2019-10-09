package Review1;

public class Circle implements iShape
{
	private final double radius;
	private final String name = "Circle";
	public Circle(double radius) 
	{
		this.radius = radius;
	}
	public double getArea()
	{
		return (Math.PI * Math.pow(this.radius, 2));
	}
	public double getCircumference()
	{
		return 2*Math.PI*this.radius;		
	}
	public String getShapeName()
	{
		return this.name;		
	}
	@Override
	public boolean equals(Object obj)
	{
		Circle circ = (Circle)obj;
		return circ.radius == this.radius;
	}
	@Override
	public int hashCode()
	{	
		long bits = Double.doubleToLongBits(this.radius);
		return (int)(bits ^ (bits >>> 32));
		// return new Double(this.radius).hashCode();		
	}
	@Override 
	public String toString()
	{
		return "radius(" + this.radius +")";
	}
}
