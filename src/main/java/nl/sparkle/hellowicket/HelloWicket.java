package nl.sparkle.hellowicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HelloWicket extends WebPage {
	private static final long serialVersionUID = -1099836337542784152L;

	public HelloWicket(){
		add(new Label("message", "Hello World!"));
	}
	
}
