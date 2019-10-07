package Review1;
public abstract class Shape implements Comparable<Shape>
{
	public abstract double getArea();
	public abstract double getCircumference();
	public abstract String getShapeName();	
	public Shape() {}
	@Override 
	public int compareTo(Shape o)
	{
		return Double.compare(this.getArea(), o.getArea());
	}
	@Override
	public String toString()
	{
		return "Shape with Area: " + this.getArea();
	}
}
