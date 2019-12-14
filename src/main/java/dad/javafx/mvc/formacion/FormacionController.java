package dad.javafx.mvc.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.mvc.RootController;
import dad.javafx.mvc.model.Titulo;
import dad.javafx.mvc.model.dialog.FormacionDialogController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.converter.LocalDateStringConverter;

public class FormacionController implements Initializable {

	// model
	private ObjectProperty<Titulo> seleccionado = new SimpleObjectProperty<Titulo>();

	@FXML
	private HBox view;

	@FXML
	private TableView<Titulo> tableView;

	@FXML
	private TableColumn<Titulo, LocalDate> desdeColumn;

	@FXML
	private TableColumn<Titulo, LocalDate> hastaColumn;

	@FXML
	private TableColumn<Titulo, String> denominacionColumn;

	@FXML
	private TableColumn<Titulo, String> organizadorColumn;

	@FXML
	private Button addButton;

	@FXML
	private Button eliminarButton;

	@FXML
	void onAddButton(ActionEvent event) throws IOException {

		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setTitle("Nuevo título");

		ButtonType okButton = new ButtonType("Crear", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

		FormacionDialogController tituloView = new FormacionDialogController();

		dialog.getDialogPane().setContent(tituloView.getView());

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.get() == okButton) {
			RootController.getModel().getFormacion().add(tituloView.getModel());
		}

	}

	@FXML
	void onEliminarAction(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente elimnar este título?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (getSeleccionado() != null) {
				RootController.getModel().getFormacion().remove(getSeleccionado());
			} else if (RootController.getModel().getFormacion().size() != 0) {
				RootController.getModel().getFormacion().remove(RootController.getModel().getFormacion().size() - 1);
			}
			tableView.getSelectionModel().clearSelection();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		seleccionadoProperty().bind(tableView.getSelectionModel().selectedItemProperty());

		RootController.getModel().formacionProperty().bindBidirectional(tableView.itemsProperty());

		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());

		desdeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		hastaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
		denominacionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		organizadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	public FormacionController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FormacionView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public HBox getView() {
		return view;
	}

	public final ObjectProperty<Titulo> seleccionadoProperty() {
		return this.seleccionado;
	}

	public final Titulo getSeleccionado() {
		return this.seleccionadoProperty().get();
	}

	public final void setSeleccionado(final Titulo seleccionado) {
		this.seleccionadoProperty().set(seleccionado);
	}

}
