package duke;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private VBox dialogContainer;
	@FXML
	private TextField userInput;
	@FXML
	private Button sendButton;
	private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
	private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

	private Duke duke;
	private static final String filePathMain = "./duke.txt";
	private static final String filePathArchive = "./archive.txt";
	private final Ui ui = new Ui("Duke");
	private final Storage storage = new Storage(filePathMain, filePathArchive);
	private final TaskList tasks = getTasks();

	/**
	 * Loads tasks from storage into a taskList.
	 * @return TaskList loaded from storage.
	 */
	private TaskList getTasks() {
		try {
			return storage.loadFiles();
		} catch (IOException e) {
			ui.showLoadingError(e);
			return new TaskList();
		}
	}

	/**
	 * Runs ChatBot.
	 *
	 * @param args Main method to run ChatBot.
	 */
	public static void main(String[] args) {
		// filepath was here
		new Duke().run();
	}


	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
		dialogContainer.getChildren().addAll(
			DialogBox.getDukeDialog(ui.greet(), dukeImage)
		);
	}

	public void setDuke(Duke d) {
		duke = d;
	}

	/**
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
	 * and then appends them to the dialog container.
	 * Clears the user input after processing.
	 */
	@FXML
	private void handleUserInput() {
		String input = userInput.getText();
		// with input i use ui to execute and get a response!
		String response = duke.getResponse(input);
		dialogContainer.getChildren().addAll(
				DialogBox.getUserDialog(input, userImage),
				DialogBox.getDukeDialog(response, dukeImage)
		);
		userInput.clear();
	}
}
