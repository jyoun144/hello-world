package test;
import java.util.Random;

public class HelloWorld {
	private static Random random = new Random();
	private static float aFreq = 0.25f;
	private static float gFreq = 0.25f;
	private static float tFreq = 0.25f;
	private static float cFreq = 0.25f;
	

	public static void main(String[] args) {
		for(int i=0; i<5; i++)
		{
		System.out.println(GetNucleoTide());
		}
	}
	
	private static float GetNucleoTide()
	{
		return random.nextFloat();
	}
}
