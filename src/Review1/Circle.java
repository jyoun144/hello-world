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
}
