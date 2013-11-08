package nl.sparkle.hellowicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWicket extends WebPage {
	private static final long serialVersionUID = -1099836337542784152L;
	private static final transient Logger LOGGER = LoggerFactory.getLogger(HelloWicket.class);

	public HelloWicket() {
		add(new Label("message", "Hello World!"));
		List<AbstractTab> tabs = addTabs();
		add(new TabbedPanel<AbstractTab>("tabs", tabs));
	}

	private List<AbstractTab> addTabs() {
		List<AbstractTab> tabs = new ArrayList<AbstractTab>();
		
		AbstractTab tab1 = createTab(TabPanel1.class, "first tab");
		tabs.add(tab1);
		
		AbstractTab tab2 = createTab(TabPanel2.class, "second tab");
		tabs.add(tab2);
		
		AbstractTab tab3 = createTab(TabPanel3.class,"third tab");
		tabs.add(tab3);
		
		AbstractTab tab4 = createTab(TabPanel4.class, "fourth tab");
		tabs.add(tab4);
		
		return tabs;
	}

	private AbstractTab createTab(final Class<? extends Panel> tabClass, final String tabId) {
		AbstractTab tab = new AbstractTab(new Model<String>(tabId)) {
			private static final long serialVersionUID = 1096593543134689283L;

			public Panel getPanel(String panelId) {
				try {
					return tabClass.getDeclaredConstructor(String.class).newInstance(panelId);
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
				}
				return null;
			}
		};
		return tab;
	}
	
	
}
