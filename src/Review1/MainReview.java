package Review1;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Arrays;
import java.util.*;
public class MainReview
{

	public static void main(String[] args) throws IOException
	{	
		sortSequenceAlpha();		
	}
	private static void sortSequenceAlpha() throws IOException
	{
		List<iFastaSequence> list = GetFastaSequences();
		Comparator<iFastaSequence> fastaComparator = new Comparator<>()
		{
			public int compare(iFastaSequence o1, iFastaSequence o2) 
			{				
				return o1.getSequence().compareTo(o2.getSequence());			
			}			
		};
		Collections.sort(list, fastaComparator);
		printFastaList(list);		
	}
	private static void processFastaFile() throws IOException
	{
		List<iFastaSequence> fastaList = FastaSequence.readFastaFile("./src/Review1/FastaInput.txt");			
		for(iFastaSequence sequence : fastaList)
		{
			
			System.out.println(sequence.getHeader() + "\n" + sequence.getSequence());
		}
	}
	private static List<iFastaSequence> GetFastaSequences() throws IOException
	{
		return FastaSequence.readFastaFile("./src/Review1/FastaInput.txt");		
	}
	private static void printFastaList(List<iFastaSequence> fastaList)
	{				
		for(iFastaSequence sequence : fastaList)
		{			
			System.out.println(sequence.getHeader() + "\n" + sequence.getSequence());
		}
	}
	private static void printShapes()
	{
		List<iShape> list = new ArrayList<>();
		iShape circle = new Circle(3);
		list.add(circle);
		iShape rectangle = new Rectangle(2, 3);
		list.add(rectangle);		
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		for(iShape shape : list)
		{
			System.out.println("Shape: " + shape.getShapeName() + "\n" + ""
								+ "Circ:  "  + nf.format(shape.getCircumference()) + "\n" 
								+ "Area:  " + nf.format(shape.getArea()) + "\n");
		}		
	}
	

}
