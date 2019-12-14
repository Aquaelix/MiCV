package dad.javafx.mvc.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

@XmlRootElement
@XmlType
public class CV {

	private ObjectProperty<Personal> personal;
	private ObjectProperty<Contacto> contacto;
	private ListProperty<Titulo> formacion;
	private ListProperty<Experiencia> experiencias;
	private ListProperty<Idioma> habilidades;
	
	public CV() {
		 personal = new SimpleObjectProperty<Personal>(this, "personal", new Personal());
		 contacto = new SimpleObjectProperty<Contacto>(this, "contacto", new Contacto());
		 formacion = new SimpleListProperty<Titulo>(this, "formacion", new SimpleListProperty<Titulo>());
		 experiencias = new SimpleListProperty<Experiencia>(this, "experiencias", new SimpleListProperty<Experiencia>());
		 habilidades = new SimpleListProperty<Idioma>(this, "habilidades", new SimpleListProperty<Idioma>());
	}
	
	public final ObjectProperty<Personal> personalProperty() {
		return this.personal;
	}

	public final Personal getPersonal() {
		return this.personalProperty().get();
	}

	public final void setPersonal(final Personal personal) {
		this.personalProperty().set(personal);
	}

	public final ObjectProperty<Contacto> contactoProperty() {
		return this.contacto;
	}

	@XmlElement(name = "contacto")
	public final Contacto getContacto() {
		return this.contactoProperty().get();
	}

	public final void setContacto(final Contacto contacto) {
		this.contactoProperty().set(contacto);
	}

	public final ListProperty<Titulo> formacionProperty() {
		return this.formacion;
	}
	
	@XmlElement(name = "formacion")
	public ObservableList<Titulo> getFormacion() {
		return this.formacionProperty().get();
	}

	public final void setFormacion(final ObservableList<Titulo> formacion) {
		this.formacionProperty().set(formacion);
	}

	public final ListProperty<Experiencia> experienciasProperty() {
		return this.experiencias;
	}

	@XmlElement(name = "experiencias")
	public ObservableList<Experiencia> getExperiencias() {
		return this.experienciasProperty().get();
	}

	public final void setExperiencias(final ObservableList<Experiencia> experiencias) {
		this.experienciasProperty().set(experiencias);
	}

	public final ListProperty<Idioma> habilidadesProperty() {
		return this.habilidades;
	}

	@XmlElement(name = "habilidades")
	public ObservableList<Idioma> getHabilidades() {
		return this.habilidadesProperty().get();
	}

	public final void setHabilidades(final ObservableList<Idioma> habilidades) {
		this.habilidadesProperty().set(habilidades);
	}

}
