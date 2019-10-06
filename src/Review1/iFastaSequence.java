package Review1;

public interface iFastaSequence
{
	public String getHeader();
	public String getSequence();
	public float getGCRatio();
	public int getIllegalCharacterCount();
	public int getLegalCharacterCount();
}
