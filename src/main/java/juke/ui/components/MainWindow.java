package juke.ui.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import juke.commands.JukeCommand;
import juke.commands.JukeExceptionCommand;
import juke.commands.JukeExitCommand;
import juke.exceptions.JukeException;
import juke.exceptions.JukeInitialisationException;
import juke.exceptions.parsers.JukeParseException;
import juke.exceptions.storage.JukeStorageException;
import juke.responses.Response;
import juke.storage.Storage;
import juke.tasks.TaskList;

/**
 * Controller class for the main window of the application.
 */
public class MainWindow extends AnchorPane {
    /** Name of the application. */
    public static final String APPLICATION_NAME = "Juke";

    /** Offset for the widgets. */
    public static final double OFFSETS = 1.5d;

    /** Storage object that handles the loading and saving of tasks. */
    @FXML
    private Storage storage;

    /** TaskList object that handles the manipulation of tasks. */
    private TaskList taskList;

    /** ScrollPane container that contains a scrollable object. */
    @FXML
    private ScrollPane scrollPane;

    /** VBox container that contains the dialog bubbles. */
    @FXML
    private VBox dialogContainer;

    /** TextField that accepts user input. */
    @FXML
    private TextField inputField;

    /** Button that submits the user input. */
    @FXML
    private Button submitButton;

    /**
     * Initialises the core services of Juke, such as the
     * Storage and TaskList services.
     */
    private void initialiseCoreServices() {
        try {
            // init services
            this.storage = Storage.of();
            this.taskList = TaskList.of(this.storage);
        } catch (JukeInitialisationException | JukeStorageException
                 | JukeParseException ex) {
            this.dialogContainer.getChildren().add(DialogBox.ofJuke(ex.getMessage()));
            this.exit();
        }
    }

    /**
     * Initialises the main window and configures it with the specified
     * parameters.
     */
    public void initialize() {
        // anchor the widgets to the parent layout's edges
        AnchorPane.setTopAnchor(this.scrollPane, MainWindow.OFFSETS);
        AnchorPane.setBottomAnchor(this.submitButton, MainWindow.OFFSETS);
        AnchorPane.setBottomAnchor(this.inputField, MainWindow.OFFSETS);
        AnchorPane.setRightAnchor(this.submitButton, MainWindow.OFFSETS);
        AnchorPane.setLeftAnchor(this.inputField, MainWindow.OFFSETS);

        // anchor the scroll
        this.scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());

        // add the introductory dialog to the dialog container
        this.dialogContainer.getChildren().add(this.getIntroductoryDialog());

        // handle user inputs
        this.submitButton.setOnMouseClicked((event) -> this.handleInput());
        this.inputField.setOnAction((event) -> this.handleInput());

        // initialise core services after setting the handlers
        this.initialiseCoreServices();
    }

    /**
     * Dispatches the command and acts on it.
     */
    @FXML
    private void handleInput() {
        String inputCommand = this.inputField.getText();

        try {
            Response response = Response.ofUser(inputCommand);
            JukeCommand action = JukeCommand.of(inputCommand, this.taskList);

            if (action instanceof JukeExitCommand) {
                exit();
            }

            Response returns = action.execute(response);
            String inputMessage = returns.getInputMessage();
            String outputMessage = returns.getOutputMessage();

            if (inputMessage != null) {
                DialogBox userDialog = getAsUserInput(inputMessage);
                this.dialogContainer.getChildren().addAll(userDialog);
            }

            if (outputMessage != null) {
                DialogBox jukeDialog = getAsJukeOutput(outputMessage);
                this.dialogContainer.getChildren().addAll(jukeDialog);
            }
        } catch (JukeException ex) {
            Response returns = new JukeExceptionCommand(ex).execute(Response.ofUser(inputCommand));
            this.dialogContainer.getChildren().addAll(
                    getAsUserInput(returns.getInputMessage()),
                    getAsJukeOutput(returns.getOutputMessage()));
        } finally {
            this.inputField.clear();
        }
    }

    /**
     * Returns a UserDialog object that contains the inputs given by
     * the user.
     *
     * @param userInput User input
     * @return {@code UserDialog} object
     */
    private DialogBox getAsUserInput(String userInput) {
        return DialogBox.ofUser(userInput);
    }

    /**
     * Returns a JukeDialog object that contains the outputs returned by
     * Juke.
     *
     * @param jukeOutput Juke output
     * @return {@code JukeDialog} object
     */
    private DialogBox getAsJukeOutput(String jukeOutput) {
        return DialogBox.ofJuke(jukeOutput);
    }

    /**
     * Returns a DialogBox object that contains the introductory message
     * when the user first starts Juke.
     *
     * @return {@code JukeDialog} object with the introductory message
     */
    private DialogBox getIntroductoryDialog() {
        String introductoryMessage = "Hello! I'm Juke (J|ava D|uke)!\nWhat can I do for you today?";
        return this.getAsJukeOutput(introductoryMessage);
    }

    /**
     * Forces an exit of the application. This may be deprecated in the future
     * if the application is to deprecate support for the "bye" command.
     */
    private void exit() {
        System.exit(0);
    }
}
