package dad.javafx.mvc.model.dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.mvc.model.Telefono;
import dad.javafx.mvc.model.TipoTelefono;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class TelefonoDialogController implements Initializable {

	private Telefono model = new Telefono();

	@FXML
	private GridPane view;

	@FXML
	private ComboBox<TipoTelefono> tipoCombo;

	@FXML
	private TextField numText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindeos
		model.numeroProperty().bind(numText.textProperty());
		model.tipoProperty().bind(tipoCombo.valueProperty());

		tipoCombo.getItems().addAll(TipoTelefono.values());

	}

	public TelefonoDialogController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addTelDialog.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	public Telefono getModel() {
		return model;
	}
}
