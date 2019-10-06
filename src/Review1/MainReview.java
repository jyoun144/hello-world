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
		runFastaTests();
		
	}
	private static void runFastaTests() throws IOException
	{
		System.out.println("**********Sort Alphabetically**********");
		sortSequenceAlpha();
		System.out.println();
		System.out.println("**********Sort By Header**********");	
		sortSequenceAlphaHeader();
		System.out.println();
		System.out.println("**********Sort By GC Ratio**********");	
		sortSequenceGCRatio();
		System.out.println();
		System.out.println("**********Sort By Legal Character Count**********");	
		sortSequenceByLegalCharacters();
	}
	private static void sortSequenceAlpha() throws IOException
	{
		List<FastaSequence> list = GetFastaSequences();
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
	private static void sortSequenceAlphaHeader() throws IOException
	{
		List<FastaSequence> list = GetFastaSequences();
		Comparator<iFastaSequence> fastaComparator = new Comparator<>()
		{
			public int compare(iFastaSequence o1, iFastaSequence o2) 
			{				
				return o1.getHeader().compareTo(o2.getHeader());			
			}			
		};
		Collections.sort(list, fastaComparator);
		printFastaList(list);		
	}
	private static void sortSequenceGCRatio() throws IOException
	{
		List<FastaSequence> list = GetFastaSequences();
		Comparator<iFastaSequence> fastaComparator = new Comparator<>()
		{
			public int compare(iFastaSequence o1, iFastaSequence o2) 
			{				
				return Float.compare(o1.getGCRatio(), o2.getGCRatio());			
			}			
		};
		Collections.sort(list, fastaComparator);
		printFastaList(list);		
	}
	private static void sortSequenceByLegalCharacters() throws IOException
	{
		  List<FastaSequence> list = GetFastaSequences();			
		  Collections.sort(list);
		  /*
		  Collections.sort(list, new Comparator<FastaSequence>()
				  {
			  public int compare(FastaSequence o1, FastaSequence o2)
			  {
				  return Integer.compare(o1.getLegalCharacterCount(), o2.getLegalCharacterCount());
			  }			  
				  });
		*/
		  
		 //list.sort((o1, o2) -> o1.getLegalCharacterCount() -  o2.getLegalCharacterCount());
		/*
	    Collections.sort(list, new Comparator<iFastaSequence>()
	    		{
	    			public int compare(iFastaSequence o1, iFastaSequence o2)
	    			{
	    				return (o1.getLegalCharacterCount() - o2.getLegalCharacterCount());
	    			}	    	
	    		});
	    		
		*/
		printFastaList(list);		
	}	
	private static List<FastaSequence> GetFastaSequences() throws IOException
	{
		return FastaSequence.readFastaFile("./src/Review1/FastaInput.txt");		
	}
	private static void printFastaList(List<FastaSequence> fastaList)
	{				
		for(iFastaSequence sequence : fastaList)
		{			
			System.out.println(sequence.getHeader() + "\n" + sequence.getSequence() + 
								"\n" + "GC Ratio: " + sequence.getGCRatio() + "\n" + "Sequence Length: " + 
					           sequence.getSequence().trim().length());
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
