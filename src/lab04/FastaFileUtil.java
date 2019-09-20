package lab04;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FastaFileUtil
{	
	public static List<FastaSequence> readFastaFile(String filePath) throws IOException
	{
		List<FastaSequence> list = new ArrayList<FastaSequence>();		
		String targetFile = filePath;
		Integer seqCount = 0;
		BufferedReader reader = new BufferedReader(new FileReader(new File(targetFile)));		
		FastaSequence obj = null;
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{	
			if(nextLine.contains(">"))
			{
				obj = new FastaSequence();
				list.add(obj);				
				seqCount++;				
			}
			else
			{
				if(!nextLine.trim().equals(""))
				{														
					obj.appendSequence(nextLine.trim());
				}
			}		
		}
		reader.close();	
		return list;
	}	
	
}

