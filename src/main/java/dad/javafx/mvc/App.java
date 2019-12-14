package dad.javafx.mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class App extends Application {

	private RootController controller;
	
	private static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.primaryStage = primaryStage;
		
		controller = new RootController();

		Scene scene = new Scene(controller.getView());

		primaryStage.setTitle("MiCV");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

}
