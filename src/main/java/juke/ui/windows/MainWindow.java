package juke.ui.windows;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import juke.commands.JukeCommand;
import juke.commands.JukeExceptionCommand;
import juke.commands.JukeExitCommand;
import juke.commons.exceptions.JukeException;
import juke.commons.exceptions.JukeInitialisationException;
import juke.commons.exceptions.parsers.JukeParseException;
import juke.commons.exceptions.storage.JukeStorageException;
import juke.responses.Dialog;
import juke.responses.Response;
import juke.storage.Storage;
import juke.tasks.TaskList;

//@@author asdfghjkxd-reused
// Code is largely reused with some major modifications from
// https://se-education.org/guides/tutorials/javaFxPart4.html
/**
 * Controller class for the main window of the application.
 */
public class MainWindow extends AnchorPane {
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
            this.dialogContainer.getChildren().add(
                    Dialog.ofJuke(ex.getMessage()).getDialogBoxRepresentation());
            this.exit();
        }
    }

    /**
     * Initialises the main window and configures it with the preconfigured parameters.
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
        this.addIntroductoryDialog();

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
        Response response = Response.of(Dialog.ofUser(inputCommand));

        try {
            JukeCommand action = JukeCommand.of(inputCommand, this.taskList);

            // invokes the exit action should the user key in "bye" as the command
            if (action instanceof JukeExitCommand) {
                exit();
            }

            // otherwise, execute the command and get the responses from the user and Juke
            response = action.execute(response);
        } catch (JukeException ex) {
            // execute an exception command and add the response to the user
            response = new JukeExceptionCommand(ex).execute(response);
        } finally {
            // finalise the dialog boxes to add
            this.dialogContainer
                    .getChildren()
                    .addAll(response.getDialogBoxes());
            this.inputField.clear();
        }
    }

    /**
     * Adds the introduction dialog to the user's screen on initialisation.
     */
    private void addIntroductoryDialog() {
        String introductoryMessage = "Hello! I'm Juke (J|ava D|uke)!\nWhat can I do for you today?";
        this.dialogContainer
                .getChildren()
                .add(Dialog.ofJuke(introductoryMessage).getDialogBoxRepresentation());
    }

    /**
     * Forces the application to exit. This may be deprecated in the future
     * if the "bye" command is deprecated.
     */
    private void exit() {
        System.exit(0);
    }
}
//@@author
