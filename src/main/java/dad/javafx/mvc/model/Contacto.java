package dad.javafx.mvc.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contacto {

	private ListProperty<Web> webs = new SimpleListProperty<Web>(this, "webs", FXCollections.observableArrayList());

	private ListProperty<Email> emails = new SimpleListProperty<Email>(this, "emails",
			FXCollections.observableArrayList());

	private ListProperty<Telefono> telefonos = new SimpleListProperty<Telefono>(this, "telefonos",
			FXCollections.observableArrayList());

	private ObjectProperty<Email> email = new SimpleObjectProperty<Email>();
	private ObjectProperty<Web> web = new SimpleObjectProperty<Web>();
	private ObjectProperty<Telefono> telefono = new SimpleObjectProperty<Telefono>();

	public final ListProperty<Web> websProperty() {
		return this.webs;
	}

	public final ObservableList<Web> getWebs() {
		return this.websProperty().get();
	}

	public final void setWebs(final ObservableList<Web> webs) {
		this.websProperty().set(webs);
	}

	public final ListProperty<Email> emailsProperty() {
		return this.emails;
	}

	public final ObservableList<Email> getEmails() {
		return this.emailsProperty().get();
	}

	public final void setEmails(final ObservableList<Email> emails) {
		this.emailsProperty().set(emails);
	}

	public final ListProperty<Telefono> telefonosProperty() {
		return this.telefonos;
	}

	public final ObservableList<Telefono> getTelefonos() {
		return this.telefonosProperty().get();
	}

	public final void setTelefonos(final ObservableList<Telefono> telefonos) {
		this.telefonosProperty().set(telefonos);
	}

	public final ObjectProperty<Email> emailProperty() {
		return this.email;
	}

	public final Email getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final Email email) {
		this.emailProperty().set(email);
	}

	public final ObjectProperty<Web> webProperty() {
		return this.web;
	}

	public final Web getWeb() {
		return this.webProperty().get();
	}

	public final void setWeb(final Web web) {
		this.webProperty().set(web);
	}

	public final ObjectProperty<Telefono> telefonoProperty() {
		return this.telefono;
	}

	public final Telefono getTelefono() {
		return this.telefonoProperty().get();
	}

	public final void setTelefono(final Telefono telefono) {
		this.telefonoProperty().set(telefono);
	}

}
