package Lab06;
import java.util.ArrayList;
import java.util.List;

public class AminoAcidSource
{
	private String singleLetterSym;
	private String fullName;
	private AminoAcidSource(String singleLetterSym, String fullName)
	{
		this.singleLetterSym = singleLetterSym;
		this.fullName = fullName;
	}
	private static final String[] SHORT_NAMES = 
		{ 	"A","R", "N", "D", "C", "Q", "E", 
			"G",  "H", "I", "L", "K", "M", "F", 
			"P", "S", "T", "W", "Y", "V" 
		};

	private static final String[] FULL_NAMES = 
		{
			"alanine","arginine", "asparagine", 
			"aspartic acid", "cysteine",
			"glutamine",  "glutamic acid",
			"glycine" ,"histidine","isoleucine",
			"leucine",  "lysine", "methionine", 
			"phenylalanine", "proline", 
			"serine","threonine","tryptophan", 
			"tyrosine", "valine"
		};	
	
	public static List<AminoAcidSource> getAminoAcids()
	{
		List<AminoAcidSource> list = new ArrayList<>();
		for(int i=0; i < FULL_NAMES.length; i++)
		{
			list.add(new AminoAcidSource(SHORT_NAMES[i], FULL_NAMES[i]));
		}
		
		return list;	
	}
}
