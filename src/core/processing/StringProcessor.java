package core.processing;

import core.Run;
import core.data.Emotion;
import core.data.Message;

public class StringProcessor
{
	public String processString(String input)
	{
		if(input.equals("END:"))
		{
			Run.sd.saveSpecialDatabase();
			Run.end = true;
			return "ending";
		}
		else if(input.contains("ADD:"))
		{
			Run.sd.add(input.substring(4));
			Run.sd.saveSpecialDatabase();
			return "successfully added word to dictionary";
		}
		else if(input.equals("RELOAD:"))
		{
			Run.sd.load();
			Run.md.load();
			return "reload complete";
		}
		input = input.replace(",", "").replace(".", "").replace("!", "").replace("'", "").toLowerCase();
		Message message = Run.sd.getFromSpecialDatabase(input);
		if(message.getEmotions().size() == 0)
		{
			message = Run.md.getFromMatrixDatabase(input);
			if(message.getEmotions().size() == 0)
			{
				message = new Message(message.getMessage(), Emotion.UNKNOWN);
			}
		}
		Message response = (new Response(message)).getResponse();
		
		
		
		
		
		return response.getMessage();
	}
}