package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

	private Duke duke;

	private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
	private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

	@FXML
	public void initialize() {
		scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
	}

	public void setDuke(Duke d) {
		duke = d;
	}

	/**
	 * Prints welcome message when chatbot is started.
	 */
	public void showWelcome() {
		String logo = "\n" +
								   "  _   _                  ____   U  ___ u   _     U _____ u \n" +
								   " | \\ |\"|       ___    U /\"___|   \\/\"_ \\/  |\"|    \\| ___\"|/ \n" +
								   "<|  \\| |>     |_\"_|   \\| | u     | | | |U | | u   |  _|\"   \n" +
								   "U| |\\  |u      | |     | |/__.-,_| |_| | \\| |/__  | |___   \n" +
								   " |_| \\_|     U/| |\\u    \\____|\\_)-\\___/   |_____| |_____|  \n" +
								   " ||   \\\\,-.-,_|___|_,-._// \\\\      \\\\     //  \\\\  <<   >>  \n" +
								   " (_\")  (_/ \\_)-' '-(_/(__)(__)    (__)   (_\")(\"_)(__) (__) \n";

		dialogContainer.getChildren().addAll(
				DialogBox.getDukeDialog(logo + "Hello! I'm Nicole\n" + "What can I do for you?", dukeImage)
		);
	}

	/**
	 * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
	 * the dialog container. Clears the user input after processing.
	 */
	@FXML
	private void handleUserInput() {
		String input = userInput.getText();
		String response = duke.getResponse(input);
		dialogContainer.getChildren().addAll(
				DialogBox.getUserDialog(input, userImage),
				DialogBox.getDukeDialog(response, dukeImage)
		);
		userInput.clear();
	}
}