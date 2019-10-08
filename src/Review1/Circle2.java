package Review1;

public class Circle2 extends Shape2
{
	private final double radius;
	
	public double getRadius()
	{
		return this.radius;
	}
	
	public Circle2(double radius)
	{
		this.radius = radius;
	}

	@Override
	public double getArea()
	{
		return Math.PI * radius * radius;
	}
	
	@Override
	public String getShapeName()
	{
		return "Circle";
	}
}

