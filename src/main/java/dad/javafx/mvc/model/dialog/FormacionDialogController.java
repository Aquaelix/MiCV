package dad.javafx.mvc.model.dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import dad.javafx.mvc.model.Titulo;
import javafx.fxml.Initializable;

public class FormacionDialogController implements Initializable {

	private Titulo model = new Titulo();

	@FXML
	private GridPane view;

	@FXML
	private TextField denText;

	@FXML
	private DatePicker desdePicker;

	@FXML
	private DatePicker hastaPicker;

	@FXML
	private TextField orgText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		model.desdeProperty().bind(desdePicker.valueProperty());
		model.hastaProperty().bind(hastaPicker.valueProperty());
		model.denominacionProperty().bind(denText.textProperty());
		model.organizadorProperty().bind(orgText.textProperty());
	}

	public FormacionDialogController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addFormDialog.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	public Titulo getModel() {
		return model;
	}
}
