package lab03;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastaFileUtilOptional extends lab03.FastaFileUtil
{
	public void saveFastaSummaryReportOptional(String sourceFilePath, String targetFilePath) throws IOException
	{
		List<FastaObj> summaryList = this.getDnaCountsFromFastaFileOptional(sourceFilePath);
		this.printDnaCounts(summaryList);
		this.writeTabDelimitedFile(summaryList, targetFilePath);		
	}	
	private List<FastaObj> getDnaCountsFromFastaFileOptional(String filePath) throws IOException
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
				String sequenceId = this.getSequenceId(nextLine);		
				obj = new FastaObj(sequenceId);
				list.add(obj);				
				seqCount++;				
			}
			else
			{
				if(!nextLine.trim().equals(""))
				{					
					this.setCountsOptional(nextLine, obj);					
					obj.appendSequence(nextLine.trim());
				}
			}		
		}
		reader.close();	
		return list;
	}	
	private void setCountsOptional(String nextLine, FastaObj obj)
	{
		char[] cArray = nextLine.toUpperCase().toCharArray();
		Arrays.sort(cArray);
		String str = new String(cArray);
		int aCount = (str.lastIndexOf('A') - str.indexOf('A')) + 1;
		int gCount = (str.lastIndexOf('G') - str.indexOf('G')) + 1;
		int tCount = (str.lastIndexOf('T') - str.indexOf('T')) + 1;
		int cCount = (str.lastIndexOf('C') - str.indexOf('C')) + 1;	
		obj.incrementCounts(aCount, gCount, tCount, cCount);		
	}
}

