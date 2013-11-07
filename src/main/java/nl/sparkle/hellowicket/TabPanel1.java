package nl.sparkle.hellowicket;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.io.IClusterable;

public class TabPanel1 extends Panel {
	private static final long serialVersionUID = 9144825869663361969L;

	public TabPanel1(String id) {
		super(id);

		addInputFormToPage();
		addFeedbackToPage();
	}

	private void addFeedbackToPage() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
	}

	private void addInputFormToPage() {
		final Input input = new Input();
		setDefaultModel(new CompoundPropertyModel<Input>(input));
		
		Form<?> form = new Form("form") {
			@Override
			protected void onSubmit() {
				info("input: " + input);
			}
		};
		add(form);

		form.add(new TextField<String>("text"));
		form.add(new TextField<Integer>("integer", Integer.class));
	}

	private class Input implements IClusterable {
		public String text;
		public Integer integer;

		@Override
		public String toString(){
			return "text = " + text + ", integer = " + integer;
		}
	}

}
