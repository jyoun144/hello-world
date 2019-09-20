package lab04;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastaSeq {		

	public static void main(String[] args) 
	{	
		String seq1 = " > Seq1 This is a header line \n" + "ACGGGGGG";
		String seq2 = " > f  \n" + "ACGGGGGG";
		String seq3 = " >   \n" + "ACGGGGGG";
		System.out.println(getHeader(seq1));
		
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
	private static String getHeader(String singleFastaLine)
	{
		String result = "";
		if(singleFastaLine != null)
		{			
			Pattern p = Pattern.compile(">(\\s*\\w*)*\\n");		
			Matcher matcher = p.matcher(singleFastaLine);
			while(matcher.find())
			{
				int start =  matcher.start();			
				int end = matcher.end();			
				result = singleFastaLine.substring(start + 1, end).trim();
			}	
		}		
		return result;	
	}	
}
