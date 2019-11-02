package lab06;
import javax.swing.*;

public class DebugUtil
{
	public static void writeMessage(String message)
	{
		writeDebugMsg(message != null ? message : "");			        
	}
	public static void logIsEventDispatchThread(String prefix)
	{
		String parsedPrefix = prefix != null ? prefix : "";
		String msg = parsedPrefix + ": " + "Programming is running on Event Dispatch Thread = " + 
		Boolean.toString(SwingUtilities.isEventDispatchThread());
		writeDebugMsg(msg);		
	}
	
	private static void writeDebugMsg(String message)
	{
		boolean isDebug =
			     java.lang.management.ManagementFactory.getRuntimeMXBean().
			         getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
			        
			         if(isDebug)
			         {
			        	 	System.out.println(message);
			         }
	}

}
