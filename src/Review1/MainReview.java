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
		String a = "Jack";
		String b = "Jack";
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		
		//runFullyOverriddenCircleClass();
		
		
	}
	private static void runFullyOverriddenCircleClass()
	{
		Set<Circle> set = new HashSet<Circle>();
		Circle c1 = new Circle(5);
		Circle c2 = new Circle(5);
		System.out.println(c1.equals(c2));
		set.add(c1);
		set.add(c2);
		System.out.println("Size: " + set.size());
		System.out.println("toString() method output:  " + c1.toString());
	}
	private static void runReverseArray()
	{
		//float[] a = new float[]{1,2,3};
				float[] arr = new float[3];
				arr[0] = 1;
				arr[1] = 2;
				arr[2] = 3;
				reverseArray(arr);
				for(float f : arr)
				{
					System.out.println(f);
				}		
	}
	private static void reverseArray(float[] a)
	{		
		float[] tmp = a.clone();
		int index = 0;
		for(int i = (a.length - 1); i>= 0; i--)
		{
			a[index] = tmp[i];	
			index++;
		}	
	}

	private static void runOptionsToIterateThroughAnArrayList()
	{
		// List<String> list = Arrays.asList("one", "two", "three");
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		list.add("three");
		// Option 1:  Iterator
		System.out.println("Option 1");
		Iterator<String> itr = list.iterator();
		while(itr.hasNext())
		{			
			System.out.println(itr.next());
		}
		// Option 2:  Colon method
		System.out.println("Option 2");
		for(String s : list)
		{
			System.out.println(s);			
		}
		
		// Option 3:  forEach method
		System.out.println("Option 3");
		list.forEach(x -> System.out.println(x));
		
		// Option 4:  for loop method
				System.out.println("Option 4");
			for(int i=0; i < list.size(); i++)
			{
				System.out.println(list.get(i));				
			}
	}
	private static void runStringConcatTrialInitial()
	{
	long startTime = System.currentTimeMillis();
			
			String s="";
			
			for( int x=0; x < 10000; x++)
				s += x;
				
	float numSeconds = (System.currentTimeMillis() - startTime) / 1000f;
	
			System.out.println( numSeconds + " seconds"  );
			//System.out.println(s);
	}
	private static void runStringConcatTrialRevised()
	{
		long startTime = System.currentTimeMillis();
		StringBuilder s = new StringBuilder();	
		// var s = new StringBuffer();		
		for( int x=0; x < 10000; x++)
		{
			s.append(x);
		}			
		float numSeconds = (System.currentTimeMillis() - startTime) / 1000f;	
		System.out.println( numSeconds + " seconds"  );			
	}
	
	private static void implementAbstractClassAndInterface()
	{
		int aRadius = 5;
		Shape2 shape = new Circle2(aRadius);
		System.out.println(shape.getArea());		
		System.out.println(((Circle2)(shape)).getRadius());
		
		Shape3 shape3 = new Circle3(aRadius);
		System.out.println(shape3.getArea());		
		System.out.println(((Circle3)(shape3)).getRadius());
	}
	public static void swap(float f1, float f2)
	{
		float temp = f1;
		f1 = f2;
		f2 = temp;
		System.out.println("Value of f1: " + f1 + "\n" + "Value of f2: " + f2);
	}

	private static void skipEveryOther(String s)
	{
		for(int i = 0; (2*i) < s.length(); i++)
		{
			System.out.println(s.charAt(2*i));
		}
	}
	private static int numGCs(String s)
	{
		int gCount = 0;
		int cCount = 0;
		if(s != null)
		{
			s = s.toUpperCase();
			for(int i = 0; i < s.length(); i++)
			{
				if(s.charAt(i) == 'G')
				{
					gCount++;					
				}
				else if(s.charAt(i) == 'C')
				{
					cCount++;
				}
			}
		}
		return (gCount + cCount);
	}
	private static int bothPositive(int a, int b)
	{
		return (a > 0 && b > 0) ? 1 : 0;
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
