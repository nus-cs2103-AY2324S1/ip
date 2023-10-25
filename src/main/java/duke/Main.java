package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

	private final Duke duke = new Duke();

	/**
	 * Initialise GUI elements
	 * Application scene can be set.
	 * Applications may create other stages, if needed, but they will not be.
	 *
	 * @param stage Primary stage for this application, onto which.
	 */
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
			AnchorPane ap = fxmlLoader.load();
			Scene scene = new Scene(ap);
			stage.setScene(scene);
			stage.setTitle("Duke ChatBot");
			fxmlLoader.<MainWindow>getController().setDuke(duke);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

