package dad.javafx.mvc.personal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.mvc.RootController;
import dad.javafx.mvc.model.Nacionalidad;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class PersonalController implements Initializable {

	//model
	
	private ListProperty<String> paisList = new SimpleListProperty<String>(this, "paisList",
			FXCollections.observableArrayList());
	
	private ListProperty<Nacionalidad> nacionalidadLista = new SimpleListProperty<Nacionalidad>(this, "nacionalidadLista",
			FXCollections.observableArrayList());

	
	@FXML
	private GridPane view;

	@FXML
	private TextField dniText;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField apellidosText;

	@FXML
	private TextArea direccionArea;

	@FXML
	private TextField localidadText;

	@FXML
	private TextField codPostalText;

	@FXML
	private ComboBox<String> paisCombo;

	@FXML
	private DatePicker fechaNac;

	@FXML
	private ListView<Nacionalidad> nacionalidadList;

	@FXML
	private Button addButton;

	@FXML
	private Button eliminarButton;

	@FXML
	void onAddNacionalidadAction(ActionEvent event) {

		ChoiceDialog<Nacionalidad> dialog = new ChoiceDialog<Nacionalidad>(getNacionalidadLista().get(0),
				getNacionalidadLista());
		dialog.setTitle("Elige...");
		dialog.setHeaderText("Seleccione una nacionalidad");
		dialog.setContentText("Mi nacionalidad:");

		Optional<Nacionalidad> result = dialog.showAndWait();
		if (result.isPresent()) {
			RootController.getModel().getPersonal().getNacionalidadListView().add(result.get());
		}

	}

	@FXML
	void onEliminarNacionalidadAction(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente eliminar esta nacionalidad?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (RootController.getModel().getPersonal().getNacionalidad() != null) {
				RootController.getModel().getPersonal().getNacionalidadListView().remove(RootController.getModel().getPersonal().getNacionalidad());
			} else if (RootController.getModel().getPersonal().getNacionalidadListView().size() != 0) {
				RootController.getModel().getPersonal().getNacionalidadListView().remove(RootController.getModel().getPersonal().getNacionalidadListView().size() - 1);
			}
			nacionalidadList.getSelectionModel().clearSelection();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		

		// bindeos
		RootController.getModel().getPersonal().identificacionProperty().bindBidirectional(dniText.textProperty());
		RootController.getModel().getPersonal().nombreProperty().bindBidirectional(nombreText.textProperty());
		RootController.getModel().getPersonal().apellidosProperty().bindBidirectional(apellidosText.textProperty());
		RootController.getModel().getPersonal().fechaNacimientoProperty().bindBidirectional(fechaNac.valueProperty());
		RootController.getModel().getPersonal().direccionProperty().bindBidirectional(direccionArea.textProperty());
		RootController.getModel().getPersonal().codigoPostalProperty().bindBidirectional(codPostalText.textProperty());
		RootController.getModel().getPersonal().localidadProperty().bindBidirectional(localidadText.textProperty());
		RootController.getModel().getPersonal().paisProperty().bindBidirectional(paisCombo.valueProperty());
		RootController.getModel().getPersonal().nacionalidadProperty().bind(nacionalidadList.getSelectionModel().selectedItemProperty());

		eliminarButton.disableProperty().bind(RootController.getModel().getPersonal().nacionalidadProperty().isNull());

		nacionalidadList.itemsProperty().bindBidirectional(RootController.getModel().getPersonal().nacionalidadListViewProperty());
		paisCombo.itemsProperty().bind(paisListProperty());

		cargarPaises();
		cargarNacionalidades();

	}

	private void cargarNacionalidades() {

		BufferedReader br = null;

		try {
			System.out.println("Funcion cargar nacionalidades, personal controller");
			// br =new BufferedReader(new
			// FileReader(getClass().getResource("/paises.csv").getPath()));
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(
							"C:\\Users\\Adán\\eclipse-workspace\\MiCV\\src\\main\\resources\\nacionalidades.csv"),
					"ISO-8859-1"));

			String line = br.readLine();

			ObservableList<Nacionalidad> items = FXCollections.observableArrayList();

			while (null != line) {
				Nacionalidad aux = new Nacionalidad();

				aux.setDenominacion(line);
				items.add(aux);

				//System.out.println(line);
				line = br.readLine();
			}

			setNacionalidadLista(items);

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Algo ha ido mal con la lectura de los paises");
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {
			if (null != br) {
				try {
					br.close();
					System.out.println("FIN cargar nacionalidades");
				} catch (IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Algo ha ido mal con el cierre del buffer");
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
			}
		}
	}

	private void cargarPaises() {
		String SEPARATOR = "\n";

		BufferedReader br = null;

		try {
			System.out.println("Funcion cargar paises, personal controller");
			// br =new BufferedReader(new
			// FileReader(getClass().getResource("/paises.csv").getPath()));
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream("C:\\Users\\Adán\\eclipse-workspace\\MiCV\\src\\main\\resources\\paises.csv"),
					"ISO-8859-1"));

			String line = br.readLine();

			ObservableList<String> items = FXCollections.observableArrayList();

			while (null != line) {
				String[] lines = line.split(SEPARATOR);
				for (int i = 0; i < lines.length; i++) {
					items.add(lines[i]);
				}

				//System.out.println(Arrays.toString(lines));
				line = br.readLine();
			}

			setPaisList(items);

		} catch (Exception e) {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setHeaderText("Algo ha ido mal con la lectura de los paises");
			alert.setContentText(e.getMessage());
			alert.showAndWait();

		} finally {
			if (null != br) {
				try {
					br.close();
					System.out.println("FIN cargar paises");
				} catch (IOException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setHeaderText("Algo ha ido mal con el cierre del buffer");
					alert.setContentText(e.getMessage());
					alert.showAndWait();
				}
			}
		}

	}

	public PersonalController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	public final ListProperty<String> paisListProperty() {
		return this.paisList;
	}
	

	public final ObservableList<String> getPaisList() {
		return this.paisListProperty().get();
	}
	

	public final void setPaisList(final ObservableList<String> paisList) {
		this.paisListProperty().set(paisList);
	}
	

	public final ListProperty<Nacionalidad> nacionalidadListaProperty() {
		return this.nacionalidadLista;
	}
	

	public final ObservableList<Nacionalidad> getNacionalidadLista() {
		return this.nacionalidadListaProperty().get();
	}
	

	public final void setNacionalidadLista(final ObservableList<Nacionalidad> nacionalidadLista) {
		this.nacionalidadListaProperty().set(nacionalidadLista);
	}
	
	
}
