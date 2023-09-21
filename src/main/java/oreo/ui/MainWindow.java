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
//        Font dmSansFont = Font.loadFont(getClass().getResourceAsStream("/fonts/DMSans-Medium.ttf"), 12);
//        userInput.setFont(dmSansFont);
    }

    public void setOreo(Oreo oreo) {
        this.oreo = oreo;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command c = Parser.parse(input);
        if (c.isExit()) {
            exit(input);
            return;
        } else if (c.isEdit()) {
            userToEditHandler(c, input);
            return;
        }
        String response = oreo.execute(c);
        setDialogContainer(input, response);
        userInput.clear();
    }

    @FXML
    private void handleEditInput() {
        String input = userInputEdit.getText();
        Command c = Parser.parseEditMode(input);
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
            oreo.clearCache();
            sendButton.setVisible(true);
            editButton.setVisible(false);
            userInputEdit.clear();
            userInputEdit.setVisible(false);
            userInput.setVisible(true);
            userInput.clear();
            userInput.requestFocus();
        }
    }


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
        oreo.cache(input);    // cache input
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

    @FXML
    public void startUp() {
        try {
            oreo.startUp();
            greetUser();
        } catch (FileNotFoundException | IllegalDateTimeException |
                 InputMismatchException e) {
            oreo.clearTaskAndFile();
            String fileCorruptMessage = "saved file is corrupt, creating new file...";
            dialogContainer.getChildren().addAll(
                    DialogBox.getOreoDialog(fileCorruptMessage, oreoImage));
            greetUser();
        }
    }

   @FXML
    private void greetUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getOreoDialog(oreo.greet(), oreoImage));
    }

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
            }, 1000);
        } catch (IOException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getOreoDialog(e.getMessage(), oreoImage));
        }

    }

    @FXML
    private void setDialogContainer(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOreoDialog(response, oreoImage)
        );
    }
}
