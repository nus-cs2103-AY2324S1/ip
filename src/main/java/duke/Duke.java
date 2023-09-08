package duke;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Solution below inspired by https://stackoverflow.com/questions/47150081/while-loop-for-multiple-inputs
// Solution below inspired by ChatGPT, to solve the issue in the else block of incrementing the num_items counter to add
// the new item subsequently.
/* Solution below inspired by ChatGPT, to solve the issue for the command list, to show the separators only at the start
and end, by moving
the statement outside the for loop
 */
// Solution below inspired by ChatGPT, regarding the method .contains("mark"), as it might be a bug if the input
// contains mark, without a corresponding integer.
// Solution below inspired by https://stackoverflow.com/questions/12973871/read-multiple-user-input-words-and-split-them
// Solution below inspired by ChatGPT, employed its help to solve my indexing problem. (ie mark 1 and unmark 1 refers to
// diff items)
/* Solution below inspired by ChatGPT, employed its help to solve the list not updating and showing the items with their
correct status icon, by creating a new task array of tasks instead of a string array.
 */
// Solution below inspired by https://stackoverflow.com/questions/10405789/regex-append-or-replace-only-the-first-
// letter-of-each-word
// Solution below inspired by https://www.programiz.com/java-programming/library/string/replacefirst
// Solution below adapted by ChatGPT, to solve the exception error when invoking last line of the loop.
// when there is no next line.
// Solution below inspired by https://stackoverflow.com/questions/32733084/pass-a-simple-enum-into-a-constructor-in-java
// Solution below inspired from ChatGPT, seeked clarification if the enums have to be passed into the child classes of
// parent class Task's constructor

/**
 * The main class for the Duke application.
 * Duke is a task management application that allows users to manage their tasks, including todos, deadlines, and events
 */
public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private static String filePath = "./data/duke.txt";
    private Stage stage;
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser;

    public Duke() {
        ArrayList<Task> tasks = new ArrayList<>();
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser = new Parser();

        try {
            tasks = storage.loadTasks(); // Load tasks from file
            taskList.setTasks(tasks);
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        ui.displayWelcomeText();
    }

    String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeException.ToDoException e) {
            return ui.printToDoException();
        } catch (DukeException.NoSuchItemException e) {
            return ui.printNoSuchElementException();
        } catch (DukeException.EventException e) {
            return ui.printEventException();
        } catch (DukeException.DeadlineException e) {
            return ui.printDeadlineException();
        } catch (DukeException.MarkException e) {
            return ui.printMarkException();
        } catch (DukeException.UnmarkException e) {
            return ui.printUnmarkException();
        } catch (DukeException.DeadlineFormatException e) {
            return ui.printDeadlineFormatException();
        } catch (DukeException.EventFormatException e) {
            return ui.printEventFormatException();
        } catch (DukeException.SearchException e) {
            return ui.printSearchException();
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        //The container for the content of the chat to scroll.
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

        //Step 2. Formatting the window to look as expected
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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
     */

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }
}
