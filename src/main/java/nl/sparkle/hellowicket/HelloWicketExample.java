package nl.sparkle.hellowicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class HelloWicketExample extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return HelloWicket.class;
	}

}
