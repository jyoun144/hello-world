package Review1;

public class Square extends Shape	
{
	private final double length;	
	private final String name = "Square";
	public Square(double length)
	{
		this.length = length;		
	}
	public double getCircumference()
	{
		return (4*this.length);
	}
	public double getArea()
	{
		return Math.pow(this.length, 2);
	}
	public String getShapeName()
	{
		return this.name;		
	}
}
