package dad.javafx.mvc.model.dialog;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.javafx.mvc.model.Idioma;
import dad.javafx.mvc.model.Nivel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class IdiomaDialogController implements Initializable {

	@FXML
	private GridPane view;

	@FXML
	private Label denominacionLabel;

	@FXML
	private Label NivelLabel;

	@FXML
	private TextField denominacionText;

	@FXML
	private HBox BoxNivel;

	@FXML
	private ComboBox<Nivel> nivelCombo;

	@FXML
	private Button limpiaButton;

	@FXML
	private TextField certificadoText;

	@FXML
	private Label certificadoLabel;

	private Idioma model = new Idioma();

	public IdiomaDialogController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/addIdiomaDialog.fxml"));
		loader.setController(this);

		loader.load();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Bindings.bindBidirectional(denominacionText.textProperty(), model.denominacionProperty());
		Bindings.bindBidirectional(certificadoText.textProperty(), model.certificacionProperty());
		model.nivelProperty().bind(nivelCombo.getSelectionModel().selectedItemProperty());
		nivelCombo.getItems().setAll(Nivel.values());
		limpiaButton.setOnAction((e) -> onLimpiaButton(e));

	}

	private void onLimpiaButton(ActionEvent e) {
		nivelCombo.getSelectionModel().select(null);
	}

	public GridPane getView() {
		return view;
	}

	public Idioma getModel() {
		return model;
	}
}
