package nl.sparkle.hellowicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.select.IOptionRenderer;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.extensions.markup.html.form.select.SelectOptions;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.WildcardCollectionModel;
import org.apache.wicket.util.io.IClusterable;

public class TabPanel3 extends Panel {
	private static final long serialVersionUID = 7651630091351694942L;
	private static final List<String> SELECTABLE_SITES = Arrays.asList(
			"The Server Side", "Java Lobby", "Java.Net");
	private static final List<? extends String> SELECTABLE_CHOICES = Arrays
			.asList("Choice1", "Choice2", "Choice3", "Choice4", "Choice5",
					"Choice6", "Choice7", "Choice8", "Choice9");

	public TabPanel3(String id) {
		super(id);

		addFormToPage();

		addFeedbackToPage();
	}

	private void addFormToPage() {
		final Input input = new Input();
		setDefaultModel(new CompoundPropertyModel<Input>(input));
		Form<?> form = new Form<Void>("form") {
			private static final long serialVersionUID = 6199046900018722476L;

			@Override
			protected void onSubmit() {
				info("input: " + input);
			}
		};
		
		add(form);
		addSiteSelectToForm(form);
		addchoicesToForm(form);
	}

	private void addchoicesToForm(Form<?> form) {
		Select<List<String>> choices = new Select<List<String>>("choices");
		form.add(choices);
		IOptionRenderer<String> renderer = new IOptionRenderer<String>() {
			private static final long serialVersionUID = 1L;

			public String getDisplayValue(String object) {
				return object;
			}

			public IModel<String> getModel(String value) {
				return new Model<String>(value);
			}

		};
		IModel<Collection<? extends String>> model = new WildcardCollectionModel<String>(
				SELECTABLE_CHOICES);
		choices.add(new SelectOptions<String>("manychoices", model, renderer));
	}

	private void addSiteSelectToForm(Form<?> form) {
		Select<String> site = new Select<String>("site");
		form.add(site);
		site.add(new SelectOption<String>("site1", new Model<String>("tss")));
		site.add(new SelectOption<String>("site2", new Model<String>("jl")));
		site.add(new SelectOption<String>("site3", new Model<String>("sd")));
		site.add(new SelectOption<String>("site4", new Model<String>("bn")));
	}

	private void addFeedbackToPage() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
	}

	private class Input implements IClusterable {
		private static final long serialVersionUID = -8143268119201036184L;
		
		public String site = "sd";
		public List<String> choices = new ArrayList<String>();

		public Input() {
			choices.add("Choice3");
			choices.add("Choice4");
			choices.add("Choice5");
		}

		@Override
		public String toString() {
			return "selected site = '" + site + "', selected choices='"
					+ listAsString(choices) + "'";
		}

		private String listAsString(List<String> list) {
			StringBuilder b = new StringBuilder();
			for (Iterator<String> i = list.iterator(); i.hasNext();) {
				b.append(i.next());
				if (i.hasNext()) {
					b.append(", ");
				}
			}
			return b.toString();
		}

	}

}
