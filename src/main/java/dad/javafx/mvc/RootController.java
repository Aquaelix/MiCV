package dad.javafx.mvc;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RootController implements Initializable {

	private ConocimientosController conoController;

	private ContactoController contController;

	private ExperienciaController expController;

	private PersonalController persController;

	private FormacionController formController;

	private static CV model;
	
	private File fichero = null;

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
		
		FileChooser archivo = new FileChooser();
		archivo.getExtensionFilters().addAll(new ExtensionFilter("Curriculum", "*.cv"),
				new ExtensionFilter("Todos los archivos", "*.*"));
		try {
			CV modelo = JAXBUtil.load(CV.class, archivo.showOpenDialog(null));
			
	
			model.getPersonal().setIdentificacion(modelo.getPersonal().getIdentificacion());
			model.getPersonal().setNombre(modelo.getPersonal().getNombre());
			model.getPersonal().setApellidos(modelo.getPersonal().getApellidos());
			model.getPersonal().setDireccion(modelo.getPersonal().getDireccion());
			model.getPersonal().setCodigoPostal(modelo.getPersonal().getCodigoPostal());
			model.getPersonal().setFechaNacimiento(modelo.getPersonal().getFechaNacimiento());
			model.getPersonal().setLocalidad(modelo.getPersonal().getLocalidad());
			model.getPersonal().setNacionalidadListView(modelo.getPersonal().nacionalidadListViewProperty());
			model.getPersonal().setPais(modelo.getPersonal().getPais());
			
			if(modelo.getContacto().getEmails()!=null)
				model.getContacto().setEmails(modelo.getContacto().emailsProperty());
			else
				model.getContacto().getEmails().clear();
			
			if(modelo.getContacto().getTelefonos()==null)
				model.getContacto().setTelefonos(modelo.getContacto().telefonosProperty());
			else
				model.getContacto().getTelefonos().clear();
			
			if(modelo.getContacto().getWebs()!=null)
				model.getContacto().setWebs(modelo.getContacto().websProperty());
			else
				model.getContacto().getWebs().clear();
			
			if(modelo.getFormacion()!=null)
				model.setFormacion(modelo.formacionProperty());
			else
				model.getFormacion().clear();
			
			if(modelo.getExperiencias()!=null)
				model.setExperiencias(modelo.experienciasProperty());
			else
				model.getExperiencias().clear();
			
			if(modelo.getHabilidades()!=null)
				model.setHabilidades(modelo.habilidadesProperty());
			else
				model.getHabilidades().clear();
			
			
		} catch (Exception e) {
//			Alert alert = new Alert(AlertType.ERROR);
//			alert.setTitle("Abrir curriculum");
//			alert.setHeaderText("Error al cargar el curriculum");
//			alert.setContentText(e.getMessage());
//			alert.showAndWait();
			e.printStackTrace();
		}
		
		System.out.println(RootController.getModel().getExperiencias().toString());
		
	}

	@FXML
	void onGuardarAction(ActionEvent event) throws Exception {

		if(fichero == null) {
			String nombreFichero = "";
	
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("¿Nombre del fichero?");
			dialog.setHeaderText("Necesito un nombre o no guardo (se guardará con extensión .cv)");
			dialog.setContentText("Nombre: ");
	
			Optional<String> result = dialog.showAndWait();
	
			if (result.isPresent()) {
				nombreFichero = result.get();
			}

			fichero = new File(nombreFichero + ".cv");
			JAXBUtil.save(model, fichero);
						
		}else {
			JAXBUtil.save(model, fichero);
		}System.out.println(RootController.getModel().getExperiencias().toString());
	}

	@FXML
	void onGuardarTodoAction(ActionEvent event) throws Exception {

		String nombreFichero = "";
		
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("¿Nombre del fichero?");
		dialog.setHeaderText("Necesito un nombre o no guardo (se guardará con extensión .cv)");
		dialog.setContentText("Nombre: ");

		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			nombreFichero = result.get();
		}

		JAXBUtil.save(model, new File(nombreFichero + ".cv"));
		
	}

	@FXML
	void onNuevoAction(ActionEvent event) throws IOException {

		Alert confirmacion = new Alert(AlertType.WARNING);
		confirmacion.setTitle("Nueva agenda");
		confirmacion.setHeaderText("Se dispone a crear una nueva agenda.\nSi tiene información sin guardar se perderá para siempre.");
		confirmacion.setContentText("¿Seguro que desea continuar?");
		confirmacion.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		if (confirmacion.showAndWait().get().equals(ButtonType.YES)) {

			model.getPersonal().setIdentificacion("");
			model.getPersonal().setNombre("");
			model.getPersonal().setApellidos("");
			model.getPersonal().setPais("");
			model.getPersonal().setLocalidad("");
			model.getPersonal().setDireccion("");
			model.getPersonal().setCodigoPostal("");
			model.getPersonal().setFechaNacimiento(null);
			model.getPersonal().nacionalidadListViewProperty().clear();
			model.getContacto().telefonosProperty().clear();
			model.getContacto().emailsProperty().clear();
			model.getContacto().websProperty().clear();
			model.formacionProperty().clear();
			model.experienciasProperty().clear();
			model.habilidadesProperty().clear();

		}
		
	}

	@FXML
	void onSalirButton(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente salir?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			App.getPrimaryStage().close();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		model = new CV();

		try {
		conoController = new ConocimientosController();

		contController = new ContactoController();

		expController = new ExperienciaController();

		persController = new PersonalController();

		formController = new FormacionController();
		
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Abrir curriculum");
			alert.setHeaderText("Error al cargar el curriculum");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		
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

	public static CV getModel() {
		return model;
	}
	
}
