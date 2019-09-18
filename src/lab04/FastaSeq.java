package lab04;
import java.util.HashMap; 
import java.util.Map;

public class FastaSeq {		

	public static void main(String[] args) 
	{	
		runHashMapTest();		
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
}
