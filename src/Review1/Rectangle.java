package Review1;

public class Rectangle implements iShape	
{
	private final double length;
	private final double width;
	private final String name = "Rectangle";
	public Rectangle(double length, double width)
	{
		this.length = length;
		this.width = width;
	}
	public double getCircumference()
	{
		return (2*this.width) + (2*this.length);
	}
	public double getArea()
	{
		return this.width*this.length;
	}
	public String getShapeName()
	{
		return this.name;		
	}
}
