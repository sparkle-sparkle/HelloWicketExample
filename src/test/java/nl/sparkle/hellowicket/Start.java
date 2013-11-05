package nl.sparkle.hellowicket;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Start {
	private static final Logger LOGGER = LoggerFactory.getLogger(Start.class);

	/**
	 * Startup method.
	 * 
	 * @param args
	 *            the arguments (none?)
	 * @throws Exception
	 *             any exception.
	 */
	public static void main(String args[]) throws Exception {
		new Start().start();
	}

	/**
	 * Starts the server.
	 * 
	 * @throws Exception
	 *             when bad things happen.
	 */
	public void start() throws Exception {
		String hostname = "localhost";
		int port = 8080;
		String webappPath = Start.class.getClassLoader().getResource("webapp")
				.getFile();

		Server server = new Server();

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setResourceBase(webappPath);
		webapp.setParentLoaderPriority(true);

		server.setHandler(webapp);

		// socket connector supposedly solves problem with windows lock on files
		SocketConnector connector = new SocketConnector();
		connector.setPort(port);
		connector.setHost(hostname);
		server.addConnector(connector);

		server.start();

		String hostedAddress = "http://" + hostname + ":" + port + "/";
		LOGGER.info("Started web application on: {}", hostedAddress);
		server.join();
	}

}
