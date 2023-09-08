package evo;

import evo.commands.Command;
import evo.exceptions.EvoException;
import evo.exceptions.InvalidDateAndTimeInputException;
import evo.exceptions.InvalidDateInputException;
import evo.exceptions.InvalidInputException;
import evo.exceptions.InvalidOperationException;
import evo.exceptions.MissingDeadlineException;
import evo.exceptions.MissingDescriptionAndDeadlineException;
import evo.exceptions.MissingDescriptionAndDurationException;
import evo.exceptions.MissingDurationException;
import evo.exceptions.MissingTaskToDeleteException;
import evo.exceptions.MissingTaskToFindException;
import evo.exceptions.MissingTaskToMarkException;
import evo.exceptions.MissingTaskToUnmarkException;
import evo.exceptions.MissingToDoDescriptionException;
import evo.exceptions.NoSuchTaskDeleteException;
import evo.exceptions.NoSuchTaskException;
import evo.exceptions.NoTaskDeleteException;
import evo.exceptions.NoTaskFindException;
import evo.parser.Parser;
import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.ui.Ui;
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
import javafx.stage.Stage;

/**
 * Evo is a Personal Assistant Chatbot that helps a person to keep track of various things.
 * Tasks are represented by the nested static class Task.
 */
public class Evo extends Application {
    private TaskList tasksList;
    /** The storage component responsible for loading and saving task data. */
    private Storage storage;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/leaf.png"));
    private Image evo = new Image(this.getClass().getResourceAsStream("/images/milkglass.png"));

    /**
     * Constructor for the Evo class. Initializes the user interface (UI),
     * storage, and task list components of the Evo application.
     */
    public Evo() {
        ui = new Ui();
        storage = new Storage("./data/evo.txt");
        try {
            tasksList = new TaskList(storage.loadTasksFromFile());
        } catch (EvoException e) {
            ui.showLoadingError();
            tasksList = new TaskList();
        }
    }

    /**
     * Initializes the JavaFX application and sets up the user interface components.
     *
     * @param stage The JavaFX stage to display the user interface.
     */
    @Override
    public void start(Stage stage) {
        /* The container for the content of the chat to scroll. */
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.setTitle("Evo");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show(); // Render the stage.


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

    /**
     * Displays the welcome message to the user.
     *
     * @return The welcome message as a string.
     */
    public String showWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label evoText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.getText(), user),
                DialogBox.getEvoDialog(evoText.getText(), evo)
        );
        userInput.clear();
    }

    /**
     * Processes user input and returns a response message based on the input.
     *
     * @param input The user's input as a string.
     * @return A response message generated based on the input.
     */
    protected String getResponse(String input) {
        Command c;
        try {
            Parser p = new Parser(tasksList);
            c = p.parse(input);
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            // Catch the exception when the user types in an invalid date and time
            return "Please type in a valid date/time input.\n(Eg: event project meeting /from "
                    + "2023-09-05 1800 /to 2000)";
        } catch (InvalidDateInputException invalidDateInputException) {
            // Catch the exception when the user types in an invalid date
            return "Please type in a valid date input.\n(Eg: deadline return book /from "
                    + "2023-09-05 1800)";
        } catch (InvalidInputException invalidInputException) {
            // Catch the exception when the user types in an invalid integer after the command
            return "Please type in a valid integer after the command.";
        } catch (InvalidOperationException invalidOperationException) {
            // Catch the exception when the operation is invalid
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        } catch (MissingDescriptionAndDeadlineException missingDescriptionAndDeadlineException) {
            // Catch the exception when the description and deadline of Deadline object are missing
            return "Description and deadline of this task are missing.\n"
                    + "Please specify the description and the deadline of this task.\n"
                    + "(Eg: deadline return book /from 2023-09-05 1800)";
        } catch (MissingDeadlineException missingDeadlineException) {
            // Catch the exception when the deadline of Deadline object are missing
            return "Deadline of this task are missing. Please specify the deadline of this task.";
        } catch (MissingDescriptionAndDurationException missingDescAndDurationExp) {
            // Catch the exception when the description and duration of Event object are missing
            return "Description and duration of this event are missing.\n"
                    + "Please specify the description and the duration of this event.\n"
                    + "(Eg: event project meeting /from 2023-09-05 1800 /to 2000)";
        } catch (MissingDurationException missingDurationException) {
            // Catch the exception when the duration of Event object is missing
            return "Duration of this event is incomplete.\n"
                    + "Please specify the start date/time and/or end date/time.";
        } catch (MissingTaskToDeleteException missingTaskToDeleteException) {
            // Catch the exception when user never specifies which task to be deleted
            return "Please specify the task to be deleted.";
        } catch (MissingTaskToFindException missingTaskToFindException) {
            // Catch the exception when user never specifies a keyword to find a task
            return "Please specify a keyword to find a task.";
        } catch (MissingTaskToMarkException missingTaskToMarkException) {
            // Catch the exception when user never specifies which task to be marked
            return "Please specify the task to be marked.";
        } catch (MissingTaskToUnmarkException missingTaskToUnmarkException) {
            // Catch the exception when user never specifies which task to be unmarked
            return "Please specify the task to be unmarked.";
        } catch (MissingToDoDescriptionException missingToDoDescriptionException) {
            // Catch the exception when the description of ToDo task is missing
            return "Description of this task is missing.\n"
                    + "Please specify the description of this task.";
        } catch (NoTaskDeleteException noTaskDeleteException) {
            // Catch the exception when user tries to delete task from an empty taskList
            return "This task cannot be deleted as there is no task in the list.";
        } catch (NoTaskFindException noTaskFindException) {
            // Catch the exception when user tries to find task from an empty taskList
            return "No task can be found as there is no task in the list.";
        } catch (NoSuchTaskException noSuchTaskException) {
            // Catch the exception when the task to be marked/unmarked does not exist.
            return "The task to be marked/unmarked does not exist.";
        } catch (NoSuchTaskDeleteException noSuchTaskDeleteException) {
            // Catch the exception when the task to be deleted does not exist.
            return "The task to be deleted does not exist.";
        } catch (Exception e) {
            return e.getMessage();
        }
        return c.execute(tasksList, ui, storage);
    }
}
