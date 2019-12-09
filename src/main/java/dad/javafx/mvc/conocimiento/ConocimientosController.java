package dad.javafx.mvc.conocimiento;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.mvc.model.Conocimiento;
import dad.javafx.mvc.model.Nivel;
import dad.javafx.mvc.model.dialog.ConocimientoDialogController;
import dad.javafx.mvc.model.dialog.IdiomaDialogController;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;

public class ConocimientosController implements Initializable {

	// model
	private ObjectProperty<Conocimiento> seleccionado = new SimpleObjectProperty<Conocimiento>();

	private ListProperty<Conocimiento> conocimientos = new SimpleListProperty<Conocimiento>();

	@FXML
	private HBox view;

	@FXML
	private TableView<Conocimiento> conocimientosTable;

	@FXML
	private TableColumn<Conocimiento, String> denominacionColumn;

	@FXML
	private TableColumn<Conocimiento, Nivel> nivelColumn;

	@FXML
	private Button addConocimientoButton;

	@FXML
	private Button addIdiomaButton;

	@FXML
	private Button eliminarButton;

	@FXML
	void onAddConocimientoAction(ActionEvent event) {

		ConocimientoDialogController conocimientoView = new ConocimientoDialogController();

		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setTitle("Nuevo conocimiento");

		ButtonType okButton = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		dialog.getDialogPane().setContent(conocimientoView.getView());
		
		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == okButton) {
			getConocimientos().add(conocimientoView.getModel());
		}
	}

	@FXML
	void onAddIdiomaAction(ActionEvent event) throws IOException {
		IdiomaDialogController idiomaView = new IdiomaDialogController();

		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setTitle("Nuevo Idioma");

		ButtonType okButton = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		dialog.getDialogPane().setContent(idiomaView.getView());
		
		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == okButton) {
			getConocimientos().add(idiomaView.getModel());
		}
	}

	@FXML
	void onEliminarButton(ActionEvent event) {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente elimnar este conocimiento/idioma?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (getSeleccionado() != null) {
				getConocimientos().remove(getSeleccionado());
			} else if (getConocimientos().size() != 0) {
				getConocimientos().remove(getConocimientos().size() - 1);
			}
			conocimientosTable.getSelectionModel().clearSelection();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		seleccionadoProperty().bind(conocimientosTable.getSelectionModel().selectedItemProperty());

		conocimientosProperty().bindBidirectional(conocimientosTable.itemsProperty());
		
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		nivelColumn.setCellValueFactory(v -> v.getValue().nivelProperty());

		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		nivelColumn.setCellFactory(ComboBoxTableCell.forTableColumn(Nivel.values()));
	}

	public ConocimientosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConocimientosView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public HBox getView() {
		return view;
	}
	
	public final ObjectProperty<Conocimiento> seleccionadoProperty() {
		return this.seleccionado;
	}

	public final Conocimiento getSeleccionado() {
		return this.seleccionadoProperty().get();
	}

	public final void setSeleccionado(final Conocimiento seleccionado) {
		this.seleccionadoProperty().set(seleccionado);
	}

	public final ListProperty<Conocimiento> conocimientosProperty() {
		return this.conocimientos;
	}

	public final ObservableList<Conocimiento> getConocimientos() {
		return this.conocimientosProperty().get();
	}

	public final void setConocimientos(final ObservableList<Conocimiento> conocimientos) {
		this.conocimientosProperty().set(conocimientos);
	}

}
