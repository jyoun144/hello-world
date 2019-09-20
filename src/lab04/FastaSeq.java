package lab04;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.Map;

public class FastaSeq {		

	public static void main(String[] args) 
	{	
		//runHashMapTest();		
		// setCounts("CGCCA");
		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;
		System.out.println(((float)(a + b)/(float)(a + b + c + d)));
	}
	private static void runHashMapTest()
	{
		 Map<String, Integer> map = new HashMap<String, Integer>(); 
		 int count = 0;
		 Integer a = map.get("dlsl");
		 count++;
		 map.put("one", count);
		 count++;
		 map.put("one", count);
		 printHashMap(map);
		 
	}
	private static void printHashMap( Map<String, Integer> map)
	{
		for(String key : map.keySet())
		{
			System.out.println("Key: " + key + " / " + "Value:  " + map.get(key));
		}
	}
	private static void setCounts(String nextLine)
	{
		char[] cArray = nextLine.toUpperCase().toCharArray();
		Arrays.sort(cArray);
		String str = new String(cArray);
		int aCount = str.contains("A") ? ((str.lastIndexOf('A') - str.indexOf('A')) + 1) : 0;
		int gCount = str.contains("G") ? (str.lastIndexOf('G') - str.indexOf('G')) + 1 : 0;
		int tCount = str.contains("T") ? (str.lastIndexOf('T') - str.indexOf('T')) + 1 : 0;
		int cCount = str.contains("C") ? (str.lastIndexOf('C') - str.indexOf('C')) + 1 : 0;	
		System.out.println("A count: " + aCount + "\n" + 
				"G count: " + gCount + "\n" + 
				"T count: " + tCount + "\n" +
				"C count: " + cCount);
	}
}
