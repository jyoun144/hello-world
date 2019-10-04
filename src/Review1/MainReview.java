package Review1;
import java.util.*;

public class MainReview
{

	public static void main(String[] args)
	{		
		List<iShape> list = new ArrayList<>();
		iShape circle = new Circle(3);
		list.add(circle);
		iShape rectangle = new Rectangle(2, 3);
		list.add(rectangle);
		printShapes(list);
		

	}
	private static void printShapes(List<iShape> shapes)
	{
		for(iShape shape : shapes)
		{
			System.out.println("Shape: " + shape.getShapeName() + "\n" + ""
								+ "Circ:  "  + shape.getCircumference() + "\n" 
								+ "Area:  " + shape.getArea() + "\n");
		}
		
	}

}
