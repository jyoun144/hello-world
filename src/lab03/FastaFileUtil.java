package lab03;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FastaFileUtil
{
	public List<FastaObj> getDnaCountsFromFastaFile(String filePath) throws IOException
	{
		List<FastaObj> list = new ArrayList<FastaObj>();		
		String targetFile = filePath;
		Integer seqCount = 0;
		BufferedReader reader = new BufferedReader(new FileReader(new File(targetFile)));		
		FastaObj obj = null;
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		{	
			if(nextLine.contains(">"))
			{
				var num = nextLine.indexOf(' ');				
				obj = new FastaObj(nextLine.substring(1, num));
				list.add(obj);				
				seqCount++;
			}
			else
			{				
				this.setCounts(nextLine, obj);		
			}		
		}
		reader.close();	
		return list;
	}
	public void printDnaCounts(List<FastaObj> list)
	{
		System.out.println("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence\n");
		for(int i=0;i<list.size();i++)
		{
			
		}
	}
	private void setCounts(String nextLine, FastaObj obj)
	{
		for(int i=0; i<nextLine.length(); i++)
		{
			int aCount = 0;
			int gCount = 0;
			int tCount = 0;
			int cCount = 0;
			if(Character.toUpperCase(nextLine.charAt(i)) == 'A')
			{
				aCount++;
			}
			else if(Character.toUpperCase(nextLine.charAt(i)) == 'G')
			{
				gCount++;
			}
			else if(Character.toUpperCase(nextLine.charAt(i)) == 'T')
			{
				tCount++;
			}
			else if(Character.toUpperCase(nextLine.charAt(i)) == 'C')
			{
				cCount++;
			}
			obj.incrementCounts(aCount, gCount, tCount, cCount);
		}		
	}
}

