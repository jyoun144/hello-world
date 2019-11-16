package lab08;

public class PrimeCounter
{
	private long startNum;
	private long endNum;
	
	public PrimeCounter(long startNum, long endNum)
	{
		this.startNum = startNum;
		this.endNum = endNum;
	}
	public long getStartNum()
	{
		return this.startNum;
	}
	public long getEndNum()
	{
		return this.endNum;
	}
}
