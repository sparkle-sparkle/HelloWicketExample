package nl.sparkle.hellowicket;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.TabbedPanel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class HelloWicket extends WebPage {
	private static final long serialVersionUID = -1099836337542784152L;

	public HelloWicket() {
		add(new Label("message", "Hello World!"));
		List<AbstractTab> tabs = createTabs();
		add(new TabbedPanel<AbstractTab>("tabs", tabs));
	}

	// TODO: check clean code rules for how to make this code better 
	private List<AbstractTab> createTabs() {
		List<AbstractTab> tabs = new ArrayList<AbstractTab>();
		AbstractTab tab1 = new AbstractTab(new Model<String>("first tab")) {
			public Panel getPanel(String panelId) {
				return new TabPanel1(panelId);
			}
		};

		AbstractTab tab2 = new AbstractTab(new Model<String>("second tab")) {
			public Panel getPanel(String panelId) {
				return new TabPanel2(panelId);
			}
		};
		
		AbstractTab tab3 = new AbstractTab(new Model<String>("third tab")) {
			public Panel getPanel(String panelId) {
				return new TabPanel3(panelId);
			}
		};
		
		AbstractTab tab4 = new AbstractTab(new Model<String>("fourth tab")) {
			public Panel getPanel(String panelId) {
				return new TabPanel4(panelId);
			}
		};
		
		tabs.add(tab1);
		tabs.add(tab2);
		tabs.add(tab3);
		tabs.add(tab4);
		return tabs;
	}
}
