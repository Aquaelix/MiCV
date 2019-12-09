package dad.javafx.mvc.experiencia;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.mvc.model.Experiencia;
import dad.javafx.mvc.model.dialog.ExperienciaDialogController;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
public class ExperienciaController implements Initializable{

	//model
	private ObjectProperty<Experiencia> seleccionada = new SimpleObjectProperty<Experiencia>();
		
	private ListProperty<Experiencia> experiencias = new SimpleListProperty<Experiencia>();	
	
    @FXML
    private HBox view;

    @FXML
    private TableView<Experiencia> experienciasTable;

    @FXML
    private TableColumn<Experiencia, LocalDate> desdeColumn;

    @FXML
    private TableColumn<Experiencia, LocalDate> hastaColumn;

    @FXML
    private TableColumn<Experiencia, String> denominadorColumn;

    @FXML
    private TableColumn<Experiencia, String> empleadorColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button eliminarButton;

    @FXML
    void onAddExpButton(ActionEvent event) throws IOException {
    	
    	Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setTitle("Nueva experiencia");
				
		ButtonType okButton = new ButtonType("Crear", ButtonData.OK_DONE);
	    dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);
	    
	    ExperienciaDialogController experienciaView = new ExperienciaDialogController();
	    
	    dialog.getDialogPane().setContent(experienciaView.getView());
	    
	    Optional<ButtonType> result = dialog.showAndWait();

	    if (result.get() == okButton){			
		    getExperiencias().add(experienciaView.getModel());
		}
    }

    @FXML
    void onEliminarButton(ActionEvent event) {
    	
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmación");
		alert.setHeaderText("¿Estás seguro?");
		alert.setContentText("¿Desea realmente elimnar esta experiencia?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (getSeleccionada() != null) {
				getExperiencias().remove(getSeleccionada());
			} else if (getExperiencias().size() != 0) {
				getExperiencias().remove(getExperiencias().size() - 1);
			}
			experienciasTable.getSelectionModel().clearSelection();
		}
		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		seleccionadaProperty().bind(experienciasTable.getSelectionModel().selectedItemProperty());
		
		experienciaProperty().bindBidirectional(experienciasTable.itemsProperty());
		
		desdeColumn.setCellValueFactory(v -> v.getValue().desdeProperty());
		hastaColumn.setCellValueFactory(v -> v.getValue().hastaProperty());
		denominadorColumn.setCellValueFactory(v -> v.getValue().denominacionProperty());
		empleadorColumn.setCellValueFactory(v -> v.getValue().empleadorProperty());
		
		
		/*desdeColumn.setCellFactory(dateCellFactory);
		hastaColumn.setCellFactory(dateCellFactory);*/
		denominadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		empleadorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	
	public ExperienciaController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ExperienciaView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	public HBox getView() {
		return view;
	}

	public final ObjectProperty<Experiencia> seleccionadaProperty() {
		return this.seleccionada;
	}
	

	public final Experiencia getSeleccionada() {
		return this.seleccionadaProperty().get();
	}
	

	public final void setSeleccionada(final Experiencia seleccionada) {
		this.seleccionadaProperty().set(seleccionada);
	}
	

	public final ListProperty<Experiencia> experienciaProperty() {
		return this.experiencias;
	}
	

	public final ObservableList<Experiencia> getExperiencias() {
		return this.experienciaProperty().get();
	}
	

	public final void setExperiencias(final ObservableList<Experiencia> experiencia) {
		this.experienciaProperty().set(experiencia);
	}
	
}
