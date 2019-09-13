package lab03;

public class FastaObj
{
	private String sequenceId;
	private int aCount;
	private int gCount;
	private int tCount;
	private int cCount;
	
	public FastaObj(String sequenceId)
	{
		this.sequenceId = sequenceId;
		this.aCount = 0;
		this.gCount = 0;
		this.tCount = 0;
		this.cCount = 0;
	}
	public void incrementCounts(int aCount, int gCount, int tCount, int cCount) 
	{
		this.aCount += aCount;
		this.gCount += gCount;
		this.tCount += tCount;
		this.cCount += cCount;		
	}
	public String getSequenceId()
	{
		return this.sequenceId;
	}
	public int getACount()
	{
		return this.aCount;
	}
	public int getGCount()
	{
		return this.gCount;
	}
	public int getTCount()
	{
		return this.tCount;
	}
	public int getCCount()
	{
		return this.cCount;
	}
}
