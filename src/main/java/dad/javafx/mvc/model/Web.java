package dad.javafx.mvc.model;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Web {

	private StringProperty url = new SimpleStringProperty();

	public final StringProperty urlProperty() {
		return this.url;
	}

	@XmlAttribute
	public final String getUrl() {
		return this.urlProperty().get();
	}

	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}

}
