import command.Command;
import exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.Objects;

/**
 * Represents the main class for the Duke application.
 * This class is responsible for initializing the system and starting the main loop.
 */
public class Duke {

    private VBox dialogContainer;
    private TextField userInput;

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    /**
     * Constructs a Duke instance.
     *
     * @param filePath The file path where tasks are loaded from and saved to.
     * @throws DukeException If there is an error loading tasks from the file.
     */
    public Duke(String filePath) throws DukeException {
        assert filePath != null && !filePath.trim().isEmpty() : "File path should not be null or empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            throw new DukeException(Ui.loadingError(e.getMessage()));
        }
    }

    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
            throw new DukeException(Ui.loadingError(e.getMessage()));
        }
    }

    /**
     * Executes the main loop of the application.
     * This method will continuously prompt the user for commands until they exit the program.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                assert fullCommand != null && !fullCommand.trim().isEmpty() : "Command should not be null or empty";
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(Ui.error(e.getMessage()));
            }
        }
    }

    private void showWelcomeMessage() {
        String welcomeMessage = ui.showWelcome();
        Label welcomeLabel = new Label(welcomeMessage);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeLabel, new ImageView(duke)));
    }


    /**
     * The main method to run the Duke application.
     *
     * @param args Command line arguments.
     * @throws DukeException If there's an error initializing the application.
     */
    public static void main(String[] args) throws DukeException {
        new Duke("./data/duke.txt").run();
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.trim().isEmpty() : "User input should not be null or empty";
        // Creating a label for the user input
        Label userText = new Label(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(user)));

        // Processing the input and adding Duke's response
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
            if (response == null || response.trim().isEmpty()) {
                response = "Sorry, I couldn't understand your command or there's no response!";
            }
        } catch (DukeException e) {
            response = Ui.error(e.getMessage());
        }

        Label dukeText = new Label(response);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));

        userInput.clear();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert input != null && !input.trim().isEmpty() : "Input should not be null or empty";
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = Ui.error(e.getMessage());
        }
        return response;
    }
}
