package duke;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import commands.Command;
import data.Actions;
import data.Save;
import parser.Parser;
import tasks.Task;
import ui.DialogBox;
import ui.UI;

/**
 * Represents the main chatbot. Manages the user interface, task list, command parsing,
 * and saving/loading tasks to/from duke.txt.
 */
public class Duke {
    private final UI ui = new UI();
    private final Actions actionList = new Actions();
    private final Parser parser = new Parser();
    private final Save savior = new Save();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke chatbot instance.
     * On instantiation loads tasks from duke.txt (if it exists).
     */
    public Duke() {
        ArrayList<Task> loadedTasks = savior.loadTasks();
        assert loadedTasks != null;
        actionList.add(loadedTasks);
    }

    /**
     * Sets up the main stage of the chatbot. Initializes and configures all UI components.
     *
     * @param stage The primary stage of the application.
     */
    /*@Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null;
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, duke)
        );
        userInput.clear();
    }*/

    /**
     * Processes user input and returns a response from the chatbot.
     *
     * @param input The input string from the user.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        try {
            Command command = parser.issueCommand(input);
            if (command.isExit()) {
                savior.saveTasks(actionList.list());
                return " Finally, I can rest.";
            } else {
                return command.executeCommand(ui, actionList);
            }
        } catch (DukeException ohno) {
            return ohno.getMessage();
        }
    }
}
