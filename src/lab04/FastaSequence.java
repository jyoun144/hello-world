package lab04;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.*;

import lab03.FastaObj;

public class FastaSequence implements iFastaSequence
{
	private StringBuilder sequence;
	private String header = null;
	private int aCount = 0;
	private int gCount = 0;
	private int tCount = 0;
	private int cCount = 0;
	
	public static List<FastaSequence> readFastaFile(String filePath) throws IOException
	{
		List<FastaSequence> list = new ArrayList<FastaSequence>();		
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
	public static void writeUniquewriteUnique (File inFile, File outFile) throws Exception			
	{
		List<FastaSequence> list = readFastaFile(inFile.getAbsolutePath());
		HashMap<String, Integer>  map = new HashMap<>();
		for(FastaSequence item : list)
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
			char[] cArray = input.toUpperCase().toCharArray();
			Arrays.sort(cArray);
			String str = new String(cArray);
			this.aCount += str.contains("A") ? ((str.lastIndexOf('A') - str.indexOf('A')) + 1) : 0;
			this.gCount += str.contains("G") ? (str.lastIndexOf('G') - str.indexOf('G')) + 1 : 0;
			this.tCount += str.contains("T") ? (str.lastIndexOf('T') - str.indexOf('T')) + 1 : 0;
			this.cCount += str.contains("C") ? (str.lastIndexOf('C') - str.indexOf('C')) + 1 : 0;
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
