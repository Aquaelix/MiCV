package dad.javafx.mvc;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.mvc.conocimiento.ConocimientosController;
import dad.javafx.mvc.contacto.ContactoController;
import dad.javafx.mvc.experiencia.ExperienciaController;
import dad.javafx.mvc.formacion.FormacionController;
import dad.javafx.mvc.model.CV;
import dad.javafx.mvc.persistencia.JAXBUtil;
import dad.javafx.mvc.personal.PersonalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class RootController implements Initializable {

	private ConocimientosController conoController = new ConocimientosController();

	private ContactoController contController = new ContactoController();

	private ExperienciaController expController = new ExperienciaController();

	private PersonalController persController = new PersonalController();

	private FormacionController formController = new FormacionController();
	
	private CV model = new CV();
	
	@FXML
	private VBox view;

	@FXML
	private Menu archivoMenu;

	@FXML
	private MenuItem nuevoButton;

	@FXML
	private MenuItem abrirButton;

	@FXML
	private MenuItem guardarButton;

	@FXML
	private MenuItem guardarTodoButton;

	@FXML
	private MenuItem salirButton;

	@FXML
	private Tab personalTab;

	@FXML
	private Tab contactoTab;

	@FXML
	private Tab formacionTab;

	@FXML
	private Tab experienciaaTab;

	@FXML
	private Tab conocimientosTab;

	@FXML
	void onAbrirAction(ActionEvent event) {

	}

	@FXML
	void onGuardarAction(ActionEvent event) throws Exception {
/*
		String nombreFichero="";
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("¿Nombre del fichero?");
		dialog.setHeaderText("Necesito un nombre o no guardo (se guardará con extensión .cv)");
		dialog.setContentText("Nombre: ");
		
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent()) {
			nombreFichero = result.get();
		}
		
		//personal
		model.getPersonal().setIdentificacion(persController.getModel().getIdentificacion());
        model.getPersonal().setNombre(persController.getModel().getNombre());
        model.getPersonal().setApellidos(persController.getModel().getApellidos());
        model.getPersonal().setCodigoPostal(persController.getModel().getCodigoPostal());
        model.getPersonal().setDireccion(persController.getModel().getDireccion());
        model.getPersonal().setFechaNacimiento(persController.getModel().getFechaNacimiento());
        model.getPersonal().setLocalidad(persController.getModel().getLocalidad());
        model.getPersonal().setNacionalidad(persController.getModel().getNacionalidad());
        model.getPersonal().setPais(persController.getModel().getPais());

        //
        
        //
		
		
		JAXBUtil.save(model, new File(nombreFichero+".cv"));
	*/	
	}

	@FXML
	void onGuardarTodoAction(ActionEvent event) {

	}

	@FXML
	void onNuevoAction(ActionEvent event) throws IOException {	
	
		//reseteo personal
		persController.getModel().setApellidos("");
		persController.getModel().setDireccion("");
		persController.getModel().setCodigoPostal("");
		persController.getModel().setFechaNacimiento(null);
		persController.getModel().setIdentificacion("");
		persController.getModel().setLocalidad("");
		persController.getModel().setNombre("");
		persController.getModel().setPais("");
		
		persController.getModel().setNacionalidadList(FXCollections.observableArrayList());
		persController.getModel().setNacionalidadListView(FXCollections.observableArrayList());
		persController.getModel().setPaisList(FXCollections.observableArrayList());
		
		//reseteo contacto
		contController.getModel().setEmails(FXCollections.observableArrayList());
		contController.getModel().setTelefonos(FXCollections.observableArrayList());
		contController.getModel().setWebs(FXCollections.observableArrayList());
		
		//reseteo formacion
		formController.setTitulos(FXCollections.observableArrayList());
		
		//reseteo experiencia
		expController.setExperiencias(FXCollections.observableArrayList());
		
		//reseteo conocimientos
		conoController.setConocimientos(FXCollections.observableArrayList());
		
	}

	@FXML
	void onSalirButton(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente salir?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		personalTab.setContent(persController.getView());
		contactoTab.setContent(contController.getView());
		formacionTab.setContent(formController.getView());
		experienciaaTab.setContent(expController.getView());
		conocimientosTab.setContent(conoController.getView());
		
	}

	public RootController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public VBox getView() {
		return view;
	}

}
