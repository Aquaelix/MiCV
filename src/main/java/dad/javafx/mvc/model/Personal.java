package dad.javafx.mvc.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import dad.javafx.mvc.persistencia.LocalDateAdapter;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Personal {

	private StringProperty identificacion;
	private ObjectProperty<Nacionalidad> nacionalidad;
	private ListProperty<Nacionalidad> nacionalidadListView;
	private StringProperty nombre;
	private StringProperty apellidos;
	private ObjectProperty<LocalDate> fechaNacimiento;
	private StringProperty direccion;
	private StringProperty codigoPostal;
	private StringProperty localidad;
	private StringProperty pais;

	public Personal() {
		identificacion = new SimpleStringProperty();
		nacionalidad = new SimpleObjectProperty<Nacionalidad>();
		nacionalidadListView = new SimpleListProperty<Nacionalidad>(this, "nacionalidadListView",
				FXCollections.observableArrayList());
		nombre = new SimpleStringProperty();
		apellidos = new SimpleStringProperty();
		fechaNacimiento = new SimpleObjectProperty<LocalDate>();
		direccion = new SimpleStringProperty();
		codigoPostal = new SimpleStringProperty();
		localidad = new SimpleStringProperty();
		pais = new SimpleStringProperty();
	}

	public final StringProperty identificacionProperty() {
		return this.identificacion;
	}
	
	@XmlAttribute
	public final String getIdentificacion() {
		return this.identificacionProperty().get();
	}

	public final void setIdentificacion(final String identificacion) {
		this.identificacionProperty().set(identificacion);
	}

	public final ObjectProperty<Nacionalidad> nacionalidadProperty() {
		return this.nacionalidad;
	}

	public final Nacionalidad getNacionalidad() {
		return this.nacionalidadProperty().get();
	}

	public final void setNacionalidad(final Nacionalidad nacionalidad) {
		this.nacionalidadProperty().set(nacionalidad);
	}

	public final StringProperty nombreProperty() {
		return this.nombre;
	}

	public final String getNombre() {
		return this.nombreProperty().get();
	}

	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public final StringProperty apellidosProperty() {
		return this.apellidos;
	}

	public final String getApellidos() {
		return this.apellidosProperty().get();
	}

	public final void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}

	public final ObjectProperty<LocalDate> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public final LocalDate getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}

	public final void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}

	public final StringProperty direccionProperty() {
		return this.direccion;
	}

	public final String getDireccion() {
		return this.direccionProperty().get();
	}

	public final void setDireccion(final String direccion) {
		this.direccionProperty().set(direccion);
	}

	public final StringProperty codigoPostalProperty() {
		return this.codigoPostal;
	}

	public final String getCodigoPostal() {
		return this.codigoPostalProperty().get();
	}

	public final void setCodigoPostal(final String codigoPostal) {
		this.codigoPostalProperty().set(codigoPostal);
	}

	public final StringProperty localidadProperty() {
		return this.localidad;
	}

	public final String getLocalidad() {
		return this.localidadProperty().get();
	}

	public final void setLocalidad(final String localidad) {
		this.localidadProperty().set(localidad);
	}

	public final StringProperty paisProperty() {
		return this.pais;
	}

	public final String getPais() {
		return this.paisProperty().get();
	}

	public final void setPais(final String pais) {
		this.paisProperty().set(pais);
	}

	public final ListProperty<Nacionalidad> nacionalidadListViewProperty() {
		return this.nacionalidadListView;
	}
	
	@XmlElementWrapper(name="nacionalidades")
	@XmlElement(name = "nacionalidad")
	public final ObservableList<Nacionalidad> getNacionalidadListView() {
		return this.nacionalidadListViewProperty().get();
	}

	public final void setNacionalidadListView(final ObservableList<Nacionalidad> nacionalidadListView) {
		this.nacionalidadListViewProperty().set(nacionalidadListView);
	}

}
