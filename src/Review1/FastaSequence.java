package Review1;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastaSequence implements iFastaSequence, Comparable<FastaSequence>
{
	private StringBuilder sequence;
	private String header = null;
	private int aCount = 0;
	private int gCount = 0;
	private int tCount = 0;
	private int cCount = 0;
	private int iCount = 0;
	
	@Override
	public int compareTo(FastaSequence s)
	{
		//return this.getSequence().length() - s.getSequence().length();
		
		return ((this.getLegalCharacterCount() + this.getIllegalCharacterCount()) - 
			   ( s.getLegalCharacterCount() + s.getIllegalCharacterCount()));
	}	
	public static List<FastaSequence> readFastaFile(String filePath) throws IOException
	{
		List<FastaSequence> list = new ArrayList<>();		
		Integer seqCount = 0;
		BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));		
		FastaSequence obj = null;		
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{	
			if(nextLine.contains(">"))
			{				
				obj = new FastaSequence(nextLine);				
				list.add(obj);				
				seqCount++;				
			}
			else
			{
				if(!nextLine.trim().equals(""))
				{														
					obj.appendSequence(nextLine);
				}
			}		
		}
		reader.close();		
		return list;
	}	
	public static void writeUnique (File inFile, File outFile) throws Exception			
	{
		List<FastaSequence> list = readFastaFile(inFile.getAbsolutePath());
		HashMap<String, Integer>  map = new HashMap<>();
		for(iFastaSequence item : list)
		{
			String currentSequence = item.getSequence();			
			Integer count = map.get(currentSequence);
			if(count == null)
			{
				count = 0;
			}
			count++;
			map.put(currentSequence, count);			
		}
		HashMap<String, Integer>  sortedMap = sortByValue(map);		
		writeSeqEntries(sortedMap, outFile.getAbsolutePath());
	}	
	public FastaSequence(String header)
	{
		this.header = header;
		this.sequence = new StringBuilder();		
	}
	public String getHeader()
	{
		String result = "";					
		Pattern p = Pattern.compile(">(\\s*\\w*)*");		
		Matcher matcher = p.matcher(this.header);		
		while(matcher.find())
		{
			int start =  matcher.start();			
			int end = matcher.end();			
			result = this.header.substring(start + 1, end).trim();
		}	
			
	return result;	
	}
	public String getSequence()
	{	
		return this.sequence.toString().replaceAll("\\s*", "");		
	}
	public float getGCRatio()
	{		
		return (float)(gCount + cCount)/(float)(aCount + gCount + cCount + tCount);
	}
	public int getIllegalCharacterCount()
	{
		return this.iCount;
	}
	public int getLegalCharacterCount()
	{
		return (this.aCount + this.gCount + this.cCount + this.tCount);
	}
	public void appendSequence(String sequence)
	{
		if(sequence != null && !sequence.trim().equals(""))
		{
			this.sequence.append(sequence);
			this.setCounts(sequence);
		}
	}	
	private void setCounts(String input)
	{
		if(input != null)
		{
			input = input.trim();
			for(int i=0; i < input.length(); i++)
			{
				char currentCharacter = input.toUpperCase().charAt(i);
				if(currentCharacter  == 'A')
				{
					this.aCount++;
				}
				else if(currentCharacter  == 'G')
				{
					this.gCount++;
				}
				else if(currentCharacter  == 'T')
				{
					this.tCount++;
				}
				else if(currentCharacter  == 'C')
				{
					this.cCount++;
				}
				else
				{
					this.iCount++;
				}
			}
		}	
	}
	// Code obtained from https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
	private static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) 
    {        
        List<Map.Entry<String, Integer> > list = 
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());       
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() { 
            public int compare(Map.Entry<String, Integer> o1,  
                               Map.Entry<String, Integer> o2) 
            { 
                return (o1.getValue()).compareTo(o2.getValue());                 
            } 
        });          
        
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>(); 
        for (Map.Entry<String, Integer> aa : list) { 
            temp.put(aa.getKey(), aa.getValue()); 
        } 
        return temp; 
    } 
	private static void writeSeqEntries(HashMap<String, Integer>  map, String filePath) throws IOException
	{		
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)));
		
		for (Map.Entry<String, Integer> en : map.entrySet()) 
		{ 			
            System.out.println(">" + en.getValue() + "\n" + en.getKey()); 
            writer.write(">" + en.getValue() + "\n" + en.getKey() + "\n");
        } 		
		writer.flush();
		writer.close();		
	}	
}
