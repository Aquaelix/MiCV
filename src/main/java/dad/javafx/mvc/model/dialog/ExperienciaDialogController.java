package dad.javafx.mvc.model.dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import dad.javafx.mvc.model.Experiencia;
import javafx.fxml.Initializable;

public class ExperienciaDialogController implements Initializable {

	private Experiencia model = new Experiencia();

	@FXML
	private GridPane view;

	@FXML
	private TextField denText;

	@FXML
	private DatePicker desdePicker;

	@FXML
	private DatePicker hastaPicker;

	@FXML
	private TextField empText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.desdeProperty().bind(desdePicker.valueProperty());
		model.hastaProperty().bind(hastaPicker.valueProperty());
		model.denominacionProperty().bind(denText.textProperty());
		model.empleadorProperty().bind(empText.textProperty());
	}

	public ExperienciaDialogController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addExpDialog.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	public Experiencia getModel() {
		return model;
	}
}
