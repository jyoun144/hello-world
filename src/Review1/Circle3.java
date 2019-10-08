package Review1;

public class Circle3 implements Shape3
{
	private final double radius;
	
	public double getRadius()
	{
		return this.radius;
	}
	
	public Circle3(double radius)
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

