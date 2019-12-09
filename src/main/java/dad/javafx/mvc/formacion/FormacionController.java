package dad.javafx.mvc.formacion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.mvc.model.Titulo;
import dad.javafx.mvc.model.dialog.FormacionDialogController;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class FormacionController implements Initializable {

	// model
	private ObjectProperty<Titulo> seleccionado = new SimpleObjectProperty<Titulo>();

	private ListProperty<Titulo> titulos = new SimpleListProperty<Titulo>();

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
			getTitulos().add(tituloView.getModel());
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
				getTitulos().remove(getSeleccionado());
			} else if (getTitulos().size() != 0) {
				getTitulos().remove(getTitulos().size() - 1);
			}
			tableView.getSelectionModel().clearSelection();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Callback<TableColumn<Titulo, LocalDate>, TableCell<Titulo, LocalDate>> dateCellFactory = (
				TableColumn<Titulo, LocalDate> param) -> new DateEditingCell();

		seleccionadoProperty().bind(tableView.getSelectionModel().selectedItemProperty());

		titulosProperty().bindBidirectional(tableView.itemsProperty());

		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominacionColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		organizadorColumn.setCellValueFactory(v -> v.getValue().organizadorProperty());

		desdeColumn.setCellFactory(dateCellFactory);
		hastaColumn.setCellFactory(dateCellFactory);
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

	class DateEditingCell extends TableCell<Titulo, LocalDate> {

		private DatePicker datePicker;

		private DateEditingCell() {
		}

		@Override
		public void startEdit() {
			if (!isEmpty()) {
				super.startEdit();
				createDatePicker();
				setText(null);
				setGraphic(datePicker);
			}
		}

		@Override
		public void cancelEdit() {
			super.cancelEdit();

			setText(getDate().toString());
			setGraphic(null);
		}

		@Override
		public void updateItem(LocalDate item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				if (isEditing()) {
					if (datePicker != null) {
						datePicker.setValue(getDate());
					}
					setText(null);
					setGraphic(datePicker);
				} else {
					setText(getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
					setGraphic(null);
				}
			}
		}

		private void createDatePicker() {
			datePicker = new DatePicker(getDate());
			datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
			datePicker.setOnAction((e) -> {
				System.out.println("Committed: " + datePicker.getValue().toString());
				commitEdit(datePicker.getValue());
			});
//            datePicker.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                if (!newValue) {
//                    commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//                }
//            });
		}

		private LocalDate getDate() {
			return getItem() == null ? LocalDate.now() : LocalDate.now(ZoneId.systemDefault());
		}
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

	public final ListProperty<Titulo> titulosProperty() {
		return this.titulos;
	}

	public final ObservableList<Titulo> getTitulos() {
		return this.titulosProperty().get();
	}

	public final void setTitulos(final ObservableList<Titulo> titulos) {
		this.titulosProperty().set(titulos);
	}

}
