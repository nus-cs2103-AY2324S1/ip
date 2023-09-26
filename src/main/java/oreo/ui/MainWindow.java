package oreo.ui;

import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.scene.text.Font;
import oreo.Oreo;
import oreo.command.Command;
import oreo.command.EditCommand;
import oreo.exception.IllegalCommandException;
import oreo.exception.IllegalDateTimeException;
import oreo.parser.Parser;
import oreo.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private TextField userInputEdit;
    @FXML
    private ImageView sendButton;
    @FXML
    private ImageView editButton;

    private Oreo oreo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image oreoImage = new Image(this.getClass().getResourceAsStream("/images/oreo.png"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); // scroll follows
    }

    /**
     * Sets oreo as an instance of oreo.
     *
     * @param oreo instance of oreo.
     */
    public void setOreo(Oreo oreo) {
        this.oreo = oreo;
    }

    /**
     * Creates two dialog boxes, one for user input and other one containing response to that particular use input.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command c = Parser.parse(input);    // parses input and generates command
        if (c.isExit()) {
            exit(input);                    // pass to exit method to handle
            return;
        } else if (c.isEdit()) {
            userToEditHandler(c, input);    // pass to userToEditHandler to handle
            return;
        }
        String response = oreo.execute(c);
        setDialogContainer(input, response);
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one for user input and other one containing response to that particular use input
     * in edit mode. Clears the user input after processing and changes text fields accordingly.
     */
    @FXML
    private void handleEditInput() {
        String input = userInputEdit.getText();
        Command c = Parser.parseEditMode(input);    // reads input in edit mode, generate command accordingly
        String response;
        try {
            response = oreo.executeEditMode(c);
        } catch (IllegalDateTimeException | IllegalCommandException e) {
            setDialogContainer(input, e.getMessage());
            Task taskToEdit = (Task) oreo.getCache(1);
            userInputEdit.setText(taskToEdit.getTaskInEditFormat());
            return;
        }
        if (c.isEdit()) {
            setDialogContainer(input, response);
            Task taskToEdit = (Task) oreo.getCache(1);
            userInputEdit.setText(taskToEdit.getTaskInEditFormat());
        } else {
            setDialogContainer(input, response);
            oreo.clearCache();                  // clear cache once edit mode exits
            sendButton.setVisible(true);        // show normal sendButton
            editButton.setVisible(false);       // hides editButton
            userInputEdit.clear();              // clears edit text field
            userInputEdit.setVisible(false);    // hides edit text field
            userInput.setVisible(true);         // show normal text field
            userInput.clear();                  // clear normal text field
            userInput.requestFocus();           // brings focus to text field ready for next input
        }
    }

    /**
     * Sets up UI for editing mode.
     *
     * @param command generated command based on input.
     * @param input from user.
     */
    @FXML
    public void userToEditHandler(Command command, String input) {
        String response = "";
        try {
            response = oreo.execute(command);
        } catch (IllegalCommandException e){
            setDialogContainer(input, e.getMessage());
            userInput.clear();
            return;
        }
        Task taskToEdit = oreo.getTask((EditCommand) command);  // get task to edit
        oreo.cache(input);      // cache input
        oreo.cache(taskToEdit); // cache task
        setDialogContainer(input, response);
        // changes from normal input mode to edit mode
        userInput.clear();
        userInput.setVisible(false);
        userInputEdit.setVisible(true);
        userInputEdit.setText(taskToEdit.getTaskInEditFormat());
        sendButton.setVisible(false);
        editButton.setVisible(true);
    }

    /**
     * Displays UI for start up.
     */
    @FXML
    public void startUp() {
        try {
            oreo.startUp();
            greetUser();
        } catch (FileNotFoundException | IllegalDateTimeException |
                 InputMismatchException e) {
            // if file is corrupt
            oreo.clearTaskAndFile();
            String fileCorruptMessage = "saved file is corrupt, creating new file...";
            dialogContainer.getChildren().addAll(
                    DialogBox.getOreoDialog(fileCorruptMessage, oreoImage));
            greetUser();
        }
    }

    /**
     * Displays UI for greeting user.
     */
    @FXML
    private void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getOreoDialog(oreo.greet(), oreoImage));
    }

    /**
     * Displays UI when user enters "bye" command.
     *
     * @param input user input.
     */
    @FXML
    private void exit(String input) {
        try {
            oreo.closeProcess();
            setDialogContainer(input, oreo.sayBye());
            userInput.clear();
            new Timer().schedule(new TimerTask() {
                public void run () {
                    System.exit(0);
                }
            }, 1000);   // shut down 1s after dialogbox displays
        } catch (IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getOreoDialog(e.getMessage(), oreoImage));
        }

    }

    /**
     * Sets string for dialog containers.
     *
     * @param input user input.
     * @param response bot response.
     */
    @FXML
    private void setDialogContainer(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOreoDialog(response, oreoImage)
        );
    }
}
