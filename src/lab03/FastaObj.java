package lab03;

public class FastaObj
{
	private String sequenceId;
	private int aCount;
	private int gCount;
	private int tCount;
	private int cCount;
	/*
	public FastaObj(String sequenceId, int aCount, int gCount, int tCount, int cCount)
	{
		this.sequenceId = sequenceId;
		this.aCount = aCount;
		this.gCount = gCount;
		this.tCount = tCount;
		this.cCount = cCount;
	}
	*/
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
}
