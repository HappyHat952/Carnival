package core.message;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

public class MessageManager {
	public ArrayList<Message> messages;

	

	public MessageManager()
	{
		 messages = new ArrayList<Message>();
	}

	public void addMessage(Message message) {
		if (message instanceof FootNoteMessage)
		{
			//removeDiffFootnoteMessages( message.getText());
		}

		messages.add(message);

	}

	public void removeDiffFootnoteMessages(String unremovable)
	{
		for (int m = 0; m<messages.size(); m++)
		{
			if (messages.get(m) instanceof FootNoteMessage && !messages.get(m).getText().equals(unremovable) )
			{
				messages.remove(m);
				m--;
			}
		}
	}
	public void removeDiffMiddleMEssages(String unremovable)
	{
		for (int m = 0; m<messages.size(); m++)
		{
			if (messages.get(m) instanceof MiddleMessage && !messages.get(m).getText().equals(unremovable) )
			{
				messages.remove(m);
				m--;
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < messages.size(); i++) {
			messages.get(i).update();
			
			if(messages.get(i).getTimeLeft() == 0) {
				messages.remove(i);
				i--;
			}
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < messages.size(); i++) {
			messages.get(i).render(g);
		}
	}

	public void clear()
	{
		messages = new ArrayList<Message>();
	}
	
}
