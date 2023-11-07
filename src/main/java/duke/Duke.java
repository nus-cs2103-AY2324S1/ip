package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The main class controlling the chatbot's actions.
 * This class should be able to keep track of tasks, and
 * send messages to the user.
 */
public class Duke extends Application {
    /**
     * Common text elements to be reused by the chatbot, such as the chatbot name
     */
    private static final String NAME = "JOHNATHAN CENATOR\n";

    /**
     * Store tasks in a separate class
     */
    private static Storage taskStorage = new Storage();

    // Variables for GUI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/cena.jpg"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.JPG"));

    /**
     * Initialize the GUI
     *
     * @param stage The window the GUI is contained in
     */
    @Override
    public void start(Stage stage) {
        createWindow(stage);
        formatWindow();
        createEventListener();
    }

    /**
     * Set up GUI components
     *
     * @param stage The window the GUI is contained in
     */
    private void createWindow(Stage stage) {
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

        // Format the window to look as expected
        stage.setTitle("CENATOR");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
    }

    /**
     * Format and resize GUI components
     */
    private void formatWindow() {
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
    }

    /**
     * Set up interactivity with GUI and chatbot
     */
    private void createEventListener() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = processText(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();

        // Assert there should be no text in the user's input field
        assert (userInput.getText().equals(""));
    }

    /**
     * Performs requisite functions by the bot and return a message
     * showing the current status of the chatbot
     *
     * @param input Input by the user
     * @return The message the chatbot will send
     */
    public static String processText(String input) {
        switch (Parser.parseInput(input)) {
        case MARK:
            return mark(input);
        case UNMARK:
            return unmark(input);
        case DELETE:
            return delete(input);
        case LIST:
            return list();
        case ECHO:
            return echo(input);
        case SAVE:
            return save();
        case FIND:
            return find(input);
        case TAG:
            return tag(input);
        case APPEND:
            Parser.TaskType type = Parser.parseTask(input);
            switch (type) {
            case TODO:
                return appendToDo(input);
            case EVENT:
                return appendEvent(input);
            case DEADLINE:
                return appendDeadline(input);
            case GENERIC:
                return append(new Task(input));
            }
        }
        return "how did you get here?";
    }

    /**
     * Sends a greeting message on startup of the chatbot.
     */
    private static String greet() {
        return "YOU DIDN'T SEE\n"
                + NAME
                + "COMING DID YOU!?\n";
    }

    /**
     * Sends a departing message on chatbot shutdown.
     */
    private static String save() {
        String out = "NOW GET OUTTA HERE!\n";
        try {
            taskStorage.write();
            out = "SAVED\n" + out;
        } catch (IOException e) {
            out = "Couldn't write to file, WHATEVER.\n" + out;
        } finally {
            return out;
        }
    }

    /**
     * Repeats the user's input
     *
     * @param input the user's text input
     * @return the user's text input
     */
    private static String echo(String input) {
        return input;
    }

    /**
     * Lists all tasks in the task array
     *
     * @return the tasks in the task array formatted into a list
     */
    private static String list() {
        return taskStorage.list();
    }

    private static String tag(String tagText) {
        try {
            String[] tags = Parser.parseTags(tagText);
            int index = Integer.parseInt(tags[0]) - 1;
            for (int i = 1; i < tags.length; i++) {
                taskStorage.tag(index, tags[i]);
            }
            return "AITE GOTTEM" + taskStorage.get(index);
        } catch (NumberFormatException e) {
            return "Index ain't a number fool!";
        } catch (IndexOutOfBoundsException e) {
            return "NOTHING THERE IDIOT!";
        }
    }

    /**
     * Appends a task to the task array
     *
     * @param task The task inputted by the user
     * @return a string indicating the task to be added
     */
    private static String append(Task task) {
        taskStorage.appendTask(task);
        return "YOU WANT TO "
                + task
                + "?\nSURE, WHATEVER.\n";
    }

    /**
     * Converts the string into a duke.ToDo object
     * then appends it to the task array
     *
     * @param task description of task
     * @return a string indicating the task to be added or an error message
     */
    private static String appendToDo(String task) {
        try {
            return append(new ToDo(Parser.parseToDo(task)));
        } catch (StringIndexOutOfBoundsException e) {
            return "WRONG FORMAT FOOL!!! IT'S:\n"
                    + "todo {task}\n";
        }
    }

    /**
     * Converts the string into a duke.Deadline object
     * then appends it to the task array
     *
     * @param task description of task with 'by' time
     * @return a string indicating the task to be added or an error message
     */
    private static String appendDeadline(String task) {
        try {
            String[] parsedDeadline = Parser.parseDeadline(task);
            return append(new Deadline(parsedDeadline[0], parsedDeadline[1]));
        } catch (StringIndexOutOfBoundsException e) {
            return "WRONG FORMAT FOOL!!! IT'S:\n"
                    + "deadline {task} /by {time}\n";
        } catch (DateTimeParseException e) {
            return "WHAT'S THIS DATE!? IT'S YYYY-MM-DD!!!\n";
        }
    }

    /**
     * Converts the string into an duke.Event object
     * then appends it to the task array
     *
     * @param task description of task with 'from' time and 'to' time
     * @return a string indicating the task to be added or an error message
     */
    private static String appendEvent(String task) {
        try {
            String[] parsedEvent = Parser.parseEvent(task);
            return append(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
        } catch (StringIndexOutOfBoundsException e) {
            return "WRONG FORMAT FOOL!!! IT'S:\n"
                    + "event {task} /from {time} /to {time}\n";
        } catch (DateTimeParseException e) {
            return "WHAT'S THIS DATE!? IT'S YYYY-MM-DD!!!\n";
        }
    }
    /**
     * Attempts to mark a task in the task array
     *
     * @param toMark the task to be marked
     * @return a message indicating the task to be marked or an error message
     */
    private static String mark(String toMark) {
        try {
            Task task = taskStorage.get(Parser.parseMark(toMark));

            // Check that the task is actually in the list, and is already done
            if (task == null) {
                throw new NullPointerException();
            }
            if (task.isDone) {
                throw new IllegalArgumentException();
            }

            task.markAsDone();
            return "MARKED:\n" + task;
        } catch (NumberFormatException e) {
            return "NOT A NUMBER IDIOT!!!";
        } catch (NullPointerException e) {
            return "NOTHING THERE IDIOT!!!";
        } catch (IndexOutOfBoundsException e) {
            return "NOTHING THERE IDIOT!!!";
        } catch (IllegalArgumentException e) {
            return "ALREADY DONE BRO!";
        }
    }

    /**
     * Attempts to unmark a task in the task array
     *
     * @param toUnmark the task to be unmarked
     * @return a message indicating the task to be unmarked or an error message
     */
    private static String unmark(String toUnmark) {
        try {
            Task task = taskStorage.get(Parser.parseUnmark(toUnmark));

            // Check that the task is actually in the list, and isn't already done
            if (task == null) {
                throw new NullPointerException();
            }
            if (task.isDone) {
                throw new IllegalArgumentException();
            }

            task.markAsUndone();
            return "UNMARKED:\n" + task;
        } catch (NumberFormatException e) {
            return "NOT A NUMBER IDIOT!!!";
        } catch (NullPointerException e) {
            return "NOTHING THERE IDIOT!!!";
        } catch (IndexOutOfBoundsException e) {
            return "NOTHING THERE IDIOT!!!";
        } catch (IllegalArgumentException e) {
            return "ALREADY UNDONE BRO!";
        }
    }

    /**
     * Attempts to delete a task from the task array
     *
     * @param toDelete the task to be deleted
     * @return a message indicating the task to be deleted or an error message
     */
    private static String delete(String toDelete) {
        try {
            int index = Parser.parseDelete(toDelete);
            Task deletedTask = taskStorage.get(index);
            taskStorage.delete(index);
            return "YOU SEE THIS?\n"
                    + deletedTask
                    + "\nNOW YOU DON'T";
        } catch (NumberFormatException e) {
            return "NOT A NUMBER IDIOT!!!";
        } catch (IndexOutOfBoundsException e) {
            return "YOU WANT ME TO DELETE THE AIR???";
        }
    }

    /**
     * List all tasks that have a specific keyphrase
     *
     * @param search a keyphrase used to check with the database of tasks
     * @return all tasks that contain the keyword, formatted into a list
     */
    public static String find(String search) {
        return "THIS WHAT YOU'RE LOOKING FOR?\n"
                + taskStorage.find(Parser.parseFind(search));
    }
}
