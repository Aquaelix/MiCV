package dad.javafx.mvc.model;

import javax.xml.bind.annotation.XmlAttribute;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email {

	private StringProperty direccion = new SimpleStringProperty();

	public final StringProperty direccionProperty() {
		return this.direccion;
	}

	@XmlAttribute
	public final String getDireccion() {
		return this.direccionProperty().get();
	}

	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}

}
