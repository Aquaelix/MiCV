package dad.javafx.mvc.contacto;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.mvc.RootController;
import dad.javafx.mvc.model.Email;
import dad.javafx.mvc.model.Telefono;
import dad.javafx.mvc.model.TipoTelefono;
import dad.javafx.mvc.model.Web;
import dad.javafx.mvc.model.dialog.TelefonoDialogController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

public class ContactoController implements Initializable {

	@FXML
	private SplitPane view;

	@FXML
	private TableView<Telefono> telefonosTable;

	@FXML
	private TableColumn<Telefono, String> numeroColumn;

	@FXML
	private TableColumn<Telefono, TipoTelefono> tipoColumn;

	@FXML
	private Button addTelButton;

	@FXML
	private Button eliminarTelButton;

	@FXML
	private TableView<Email> mailTable;

	@FXML
	private TableColumn<Email, String> mailColumn;

	@FXML
	private Button addMailButton;

	@FXML
	private Button eliminarMailButton;

	@FXML
	private TableView<Web> URLTable;

	@FXML
	private TableColumn<Web, String> URLColumn;

	@FXML
	private Button addWebsButton;

	@FXML
	private Button eliminarWebsButton;

	@FXML
	void onAddMailAction(ActionEvent event) {

		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nuevo e-mail");
		dialog.setHeaderText("Crear una nueva dirección de correo.");
		dialog.setContentText("E-mail:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Email aux = new Email();
			aux.setDireccion(result.get());
			RootController.getModel().getContacto().getEmails().add(aux);
		}

	}

	Telefono telModel = new Telefono();

	@FXML
	void onAddTelAction(ActionEvent event) throws IOException {

		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setTitle("Nuevo teléfono");
		dialog.setHeaderText("Introduzca el nuevo número de teléfono");

		ButtonType okButton = new ButtonType("Añadir", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		TelefonoDialogController telefonoView = new TelefonoDialogController();

		dialog.getDialogPane().setContent(telefonoView.getView());

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.get() == okButton) {
			RootController.getModel().getContacto().getTelefonos().add(telefonoView.getModel());
		}

	}

	@FXML
	void onAddWebsAction(ActionEvent event) {

		TextInputDialog dialog = new TextInputDialog("http://");
		dialog.setTitle("Nueva web");
		dialog.setHeaderText("Crear una nueva dirección web.");
		dialog.setContentText("URL:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			Web aux = new Web();
			aux.setUrl(result.get());
			RootController.getModel().getContacto().getWebs().add(aux);
		}

	}

	@FXML
	void onEliminarMailButton(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente elimnar este correo?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (RootController.getModel().getContacto().getEmail() != null) {
				RootController.getModel().getContacto().getEmails().remove(RootController.getModel().getContacto().getEmail());
			} else if (RootController.getModel().getContacto().getEmails().size() != 0) {
				RootController.getModel().getContacto().getEmails().remove(RootController.getModel().getContacto().getEmails().size() - 1);
			}
			mailTable.getSelectionModel().clearSelection();
		}

	}

	@FXML
	void onEliminarTelAction(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente elimnar este teléfono?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (RootController.getModel().getContacto().getTelefono() != null) {
				RootController.getModel().getContacto().getTelefonos().remove(RootController.getModel().getContacto().getTelefono());
			} else if (RootController.getModel().getContacto().getTelefonos().size() != 0) {
				RootController.getModel().getContacto().getTelefonos().remove(RootController.getModel().getContacto().getTelefonos().size() - 1);
			}
			telefonosTable.getSelectionModel().clearSelection();
		}

	}

	@FXML
	void onEliminarWebsButton(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente elimnar esta web?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			if (RootController.getModel().getContacto().getWebs() != null) {
				RootController.getModel().getContacto().getWebs().remove(RootController.getModel().getContacto().getWeb());
			} else if (RootController.getModel().getContacto().getWebs().size() != 0) {
				RootController.getModel().getContacto().getWebs().remove(RootController.getModel().getContacto().getWebs().size() - 1);
			}
			URLTable.getSelectionModel().clearSelection();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		RootController.getModel().getContacto().emailProperty().bind(mailTable.getSelectionModel().selectedItemProperty());
		RootController.getModel().getContacto().telefonoProperty().bind(telefonosTable.getSelectionModel().selectedItemProperty());
		RootController.getModel().getContacto().webProperty().bind(URLTable.getSelectionModel().selectedItemProperty());

		RootController.getModel().getContacto().emailsProperty().bindBidirectional(mailTable.itemsProperty());
		RootController.getModel().getContacto().telefonosProperty().bindBidirectional(telefonosTable.itemsProperty());
		RootController.getModel().getContacto().websProperty().bindBidirectional(URLTable.itemsProperty());

/*		mailTable.itemsProperty().bind(model.emailsProperty());
		telefonosTable.itemsProperty().bind(model.telefonosProperty());
		URLTable.itemsProperty().bind(model.websProperty());
*/		
		URLColumn.setCellValueFactory(v -> v.getValue().urlProperty());
		mailColumn.setCellValueFactory(v -> v.getValue().direccionProperty());
		numeroColumn.setCellValueFactory(v -> v.getValue().numeroProperty());
		tipoColumn.setCellValueFactory(v -> v.getValue().tipoProperty());

		URLColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		mailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		numeroColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		tipoColumn.setCellFactory(ComboBoxTableCell.forTableColumn(TipoTelefono.values()));

	}

	public ContactoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ContactoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public SplitPane getView() {
		return view;
	}
	
}
