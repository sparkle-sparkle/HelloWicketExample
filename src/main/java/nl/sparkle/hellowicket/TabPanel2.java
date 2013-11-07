package nl.sparkle.hellowicket;

import java.util.List;
import java.util.ArrayList;

import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.util.CollectionModel;
import org.apache.wicket.model.util.ListModel;

public class TabPanel2 extends Panel {
	private static final long serialVersionUID = 7418348007969701460L;

	public TabPanel2(String id) {
		super(id);

		List<String> persons = createPersons();
		IChoiceRenderer<String> renderer = new ChoiceRenderer<String>();

		final Palette<String> palette = new Palette<String>("palette",
				new ListModel<String>(new ArrayList<String>()),
				new CollectionModel<String>(persons), renderer, 10, true);

		Form<?> form = new Form("form") {
			@Override
			protected void onSubmit() {
				info("selected person(s): "
						+ palette.getDefaultModelObjectAsString());
			}
		};

		add(form);
		form.add(palette);
	}

	private List<String> createPersons() {
		List<String> persons = new ArrayList<String>();
		persons.add("Amberle");
		persons.add("Questor");
		persons.add("Abernathy");
		persons.add("Parsnip");
		persons.add("Ben Holiday");
		persons.add("Wilgje");
		persons.add("Bunion");
		return persons;
	}
}
