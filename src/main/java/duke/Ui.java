package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * The Ui class handles user interface interactions for the Duke chatbot.
 * It provides methods for greeting the user, displaying messages, getting user input,
 * and closing the input scanner.
 */
public class Ui {
    private Scanner scanner;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;


    /**
     * Constructs a new Ui object and initializes the input scanner to read user input from the console.
     */
    public Ui(ScrollPane scrollPane, VBox dialogContainer, TextField userInput,
              Button sendButton, Scene scene, AnchorPane mainLayout) {
        scanner = new Scanner(System.in);
        this.scrollPane = scrollPane;
        this.dialogContainer = dialogContainer;
        this.userInput = userInput;
        this.sendButton = sendButton;
        this.scene = scene;
        this.mainLayout = mainLayout;
    }

    /**
     * Displays a goodbye message when the chatbot is about to exit.
     *
     * @return String representing an exit message
     */
    protected String exit() {
        return "Bye. Hope to see you again soon!";
    }


    public void start(Stage stage) {
        setupChatUI(stage);
        setupStageProperties(stage);
    }

    private void setupChatUI(Stage stage) {
        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    private void setupStageProperties(Stage stage) {
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
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Displays a help message explaining the available commands and their usage.
     */
    public String showHelp() {
        StringBuilder helpMessage = new StringBuilder();
        helpMessage.append("Welcome to Duke! Here are the available commands:\n");
        helpMessage.append("- 'bye': Exits the chatbot and saves the task list to a file.\n");
        helpMessage.append("- 'list': Lists all tasks in the current task list.\n");
        helpMessage.append("- 'mark <task_id>': Marks a task as done by its ID.\n");
        helpMessage.append("- 'unmark <task_id>': Unmarks a previously marked task.\n");
        helpMessage.append("- 'todo <description>': Adds a to-do task with a description.\n");
        helpMessage.append("- 'event <description> /from <datetime> /to <datetime>': Adds an event task with a description, start date, and end date.\n");
        helpMessage.append("- 'deadline <description> /by <datetime>': Adds a deadline task with a description and due date.\n");
        helpMessage.append("- 'delete <task_id>': Deletes a task by its ID.\n");
        helpMessage.append("- 'find <keyword>': Searches for tasks containing the specified keyword.\n");
        helpMessage.append("To use a command, simply type it in the chat and press 'Send'.\n");
        helpMessage.append("For example: 'todo Buy groceries' or 'mark 1'.\n");

        return helpMessage.toString();
    }
}

