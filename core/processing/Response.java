package core.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import core.data.Emotion;
import core.data.Message;

public class Response
{
	private Message message;
	
	public Response(Message message)
	{
		this.message = message;
	}
	
	public Message getResponse()
	{
		Random rand = new Random();
		List<String> responses = getResponses(message.getEmotions(), rand);
		Message response = new Message(responses.get(rand.nextInt(responses.size())), Emotion.NONE);
		
		
		
		return response;
	}
	
	private List<String> getResponses(List<Emotion> emotions, Random rand)
	{
		List<String> responses = new ArrayList<String>();
		for(Emotion emotion: emotions)
		{
			responses.add(getResponse(emotion, rand));
		}
		return responses;
	}
	
	private String getResponse(Emotion emotion, Random rand)
	{
		String response = "";
		switch(emotion)
		{
		case ANGRY:
			response = "NEED MORE THAN EMOTION";
			break;
		case FAREWELL:
			switch(rand.nextInt(6))
			{
			case 0:
				response = "Bye.";
				break;
			case 1:
				response = "Goodbye.";
				break;
			case 2:
				response = "Farewell.";
				break;
			case 3:
				response = "See you later.";
				break;
			case 4:
				response = "Why do you have to go?";
				break;
			case 5:
				response = "I'll miss you!";
				break;
			}
			break;
		case GREETING:
			switch(rand.nextInt(6))
			{
			case 0:
				response = "Hi.";
				break;
			case 1:
				response = "Hello.";
				break;
			case 2:
				response = "Hey!";
				break;
			case 3:
				response = "Hi, how are you?";
				break;
			case 4:
				response = "What's up?";
				break;
			case 5:
				response = "How's it going?";
				break;
			}
			break;
		case HAPPY:
			switch(rand.nextInt(4))
			{
			case 0:
				response = "Yay!";
				break;
			case 1:
				response = "That's great!";
				break;
			case 2:
				response = "I'm so happy for you.";
				break;
			case 3:
				response = "Same.";
				break;
			}
			break;
		case NONE:
			response = "ok.";
			break;
		case QUESTION:
			response = "NEED MORE THAN EMOTION";
			break;
		case SAD:
			response = "NEED MORE THAN EMOTION";
			break;
		case UNKNOWN:
			response = "That doesn't make sense.";
			break;
		default:
			response = emotion.toString();
			break;
		}
		return response;
	}
}