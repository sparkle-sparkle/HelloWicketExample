package nl.sparkle.hellowicket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class TabPanel4 extends Panel {
	private static final String ELEMENT_NAME_FORM = "form";
	private static final String ELEMENT_NAME_LETTER_DROPDOWN = "letters";
	private static final String ELEMENT_NAME_ARTIST_DROPDOWN = "artists";
	private static final String ELEMENT_NAME_FEEDBACK = "feedback";
	private static final String AJAX_EVENT_ONCHANGE = "onchange";
	private static final long serialVersionUID = 7651630091351694942L;
	private String selectedLetter;
	private final Map<String, List<String>> lettersMappedToArtists = new HashMap<String, List<String>>();

	public TabPanel4(String id) {
		super(id);
		renderForm();
		addFeedbackToPage();
	}
	
	private void renderForm() {
		Form<?> form = new Form(ELEMENT_NAME_FORM);
		add(form);
		renderElements(form);
		createLettersMappedToArtistsContent();
	}

	private void renderElements(Form<?> form) {
		DropDownChoice<String> letterChoices = renderLetterDropDown(form);
		DropDownChoice<String> artistChoices = renderArtistDropDown(form);
		addOnChangeAjaxBehaviorToDropDown(letterChoices, artistChoices);
	}
	
	private DropDownChoice<String> renderLetterDropDown(Form<?> form) {
		IModel<List<? extends String>> letterChoicesModel = createLetterChoiceModel();
		DropDownChoice<String> letterChoices = createLetterChoices(letterChoicesModel);
		form.add(letterChoices);
		return letterChoices;
	}
	
	private IModel<List<? extends String>> createLetterChoiceModel() {
		return new AbstractReadOnlyModel<List<? extends String>>() {
			private static final long serialVersionUID = -2034209672439429500L;

			@Override
			public List<String> getObject() {
				return new ArrayList<String>(lettersMappedToArtists.keySet());
			}

		};
	}
	
	private DropDownChoice<String> createLetterChoices(
			IModel<List<? extends String>> letterChoicesModel) {
		return new DropDownChoice<String>(ELEMENT_NAME_LETTER_DROPDOWN, new PropertyModel<String>(
				this, "selectedLetter"), letterChoicesModel);
	}
	
	private DropDownChoice<String> renderArtistDropDown(Form<?> form) {
		IModel<List<? extends String>> artistChoicesModel = createArtistChoiceModel();
		DropDownChoice<String> artistChoices = createArtistChoices(artistChoicesModel);
		form.add(artistChoices);
		return artistChoices;
	}
	
	private IModel<List<? extends String>> createArtistChoiceModel() {
		return new AbstractReadOnlyModel<List<? extends String>>() {
			private static final long serialVersionUID = -7171025794753873894L;

			@Override
			public List<? extends String> getObject() {
				List<String> artists = lettersMappedToArtists
						.get(selectedLetter);
				if (artists == null) {
					artists = Collections.emptyList();
				}
				return artists;
			}

		};
	}
	
	private DropDownChoice<String> createArtistChoices(
			IModel<List<? extends String>> artistChoicesModel) {
		DropDownChoice<String> dropDownChoice = new DropDownChoice<String>(
				ELEMENT_NAME_ARTIST_DROPDOWN, new Model<String>(), artistChoicesModel);
		dropDownChoice.setOutputMarkupId(true);
		return dropDownChoice;
	}
	
	private void addOnChangeAjaxBehaviorToDropDown(DropDownChoice<String> dropDown, final DropDownChoice<String> dropDownTarget) {
		dropDown.add(new AjaxFormComponentUpdatingBehavior(AJAX_EVENT_ONCHANGE) {
			private static final long serialVersionUID = -3752769630158541070L;

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				target.add(dropDownTarget);
			}
			
		});
	}
	
	private void createLettersMappedToArtistsContent() {
		lettersMappedToArtists.put("A", Arrays.asList("Avril Lavigne"));
		lettersMappedToArtists.put("C", Arrays.asList("Christina Stürmer"));
		lettersMappedToArtists.put("N", Arrays.asList("Nickelback"));
	}

	private void addFeedbackToPage() {
		FeedbackPanel feedbackPanel = new FeedbackPanel(ELEMENT_NAME_FEEDBACK);
		add(feedbackPanel);
	}

	public String getSelectedLetter() {
		return selectedLetter;
	}

	public void setSelectedLetter(String selectedLetter) {
		this.selectedLetter = selectedLetter;
	}

}
