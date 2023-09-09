package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

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


/**
 * The main class controlling the chatbot's actions.
 * This class should be able to keep track of tasks, and
 * send messages to the user.
 */
public class Duke extends Application {
    /**
     * Common text elements to be reused by the chatbot, such as the chatbot name,
     * and the horizontal line element.
     */
    private static final String name = "JOHNATHAN CENATOR\n";
    private static final String horizontalLine = "-------------------------------\n";

    /**
     * Store tasks in a separate class
     */
    private static Storage taskStorage = new Storage();

    /**
     * An enum to track the status of the chatbot
     * RUNNING, STOPPING, etc.
     * [To add more if needed]
     */
    enum Status {RUNNING, STOPPING}

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
        //Step 1. Setting up required components

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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
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
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "JOHNATHAN heard: " + input;
    }

    /**
     * Sends a greeting message on startup of the chatbot.
     */
    private static void greet() {
        System.out.print(horizontalLine +
                "YOU DIDN'T SEE\n" +
                name +
                "COMING DID YOU!?\n" +
                horizontalLine);
    }

    /**
     * Sends a departing message on chatbot shutdown.
     */
    private static void exit() {
        System.out.print(horizontalLine +
                "NOW GET OUTTA HERE!\n" +
                "RESPECTFULLY,\n" +
                name +
                horizontalLine);
    }

    /**
     * Repeats the user's input
     *
     * @param input the user's text input
     */
    private static void echo(String input) {
        System.out.print(horizontalLine + input + "\n" + horizontalLine);
    }

    /**
     * Lists all tasks in the task array
     */
    private static void list() {
        System.out.print(horizontalLine);
        System.out.print(taskStorage.list());
        System.out.print(horizontalLine);
    }

    /**
     * Appends a task to the task array
     *
     * @param task The task inputted by the user
     */
    private static void append(Task task) {
        taskStorage.appendTask(task);
        System.out.print(horizontalLine + "YOU WANT TO " + task + "?\nSURE, WHATEVER.\n" + horizontalLine);
    }

    /**
     * Converts the string into a duke.ToDo object
     * then appends it to the task array
     *
     * @param task description of task
     */
    private static void appendToDo(String task) {
        try {
            append(new ToDo(Parser.parseToDo(task)));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(horizontalLine +
                    "WRONG FORMAT FOOL!!! IT'S:\n" +
                    "todo {task}\n" +
                    horizontalLine);
        }
    }

    /**
     * Converts the string into a duke.Deadline object
     * then appends it to the task array
     *
     * @param task description of task with 'by' time
     */
    private static void appendDeadline(String task) {
        try {
            String[] parsedDeadline = Parser.parseDeadline(task);
            append(new Deadline(parsedDeadline[0], parsedDeadline[1]));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(horizontalLine +
                    "WRONG FORMAT FOOL!!! IT'S:\n" +
                    "deadline {task} /by {time}\n" +
                    horizontalLine);
        } catch (DateTimeParseException e) {
            System.out.print(horizontalLine + "Date format should be yyyy-mm-dd\n"
                    + horizontalLine);
        }
    }

    /**
     * Converts the string into an duke.Event object
     * then appends it to the task array
     *
     * @param task description of task with 'from' time and 'to' time
     */
    private static void appendEvent(String task) {
        try {
            String[] parsedEvent = Parser.parseEvent(task);
            append(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print(horizontalLine +
                    "WRONG FORMAT FOOL!!! IT'S:\n" +
                    "event {task} /from {time} /to {time}\n" +
                    horizontalLine);
        } catch (DateTimeParseException e) {
            System.out.print(horizontalLine + "Date format should be yyyy-mm-dd\n"
                    + horizontalLine);
        }
    }
    /**
     * Attempts to mark a task in the task array
     *
     * @param toMark
     */
    private static void mark(String toMark) {
        System.out.print(horizontalLine);
        try {
            Task task = taskStorage.get(Parser.parseMark(toMark));
            if (task == null) throw new NullPointerException();
            if (task.isDone) throw new IllegalArgumentException();
            task.markAsDone();
            System.out.println("MARKED:\n" + task);
        } catch (NumberFormatException e) {
            System.out.print("NOT A NUMBER IDIOT!!!\n");
        } catch (NullPointerException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IllegalArgumentException e) {
            System.out.print("ALREADY DONE BRO!\n");
        } finally {
            System.out.print(horizontalLine);
        }
    }

    /**
     * Attempts to unmark a task in the task array
     *
     * @param toUnmark the task to be unmarked
     */
    private static void unmark(String toUnmark) {
        System.out.print(horizontalLine);
        try {
            Task task = taskStorage.get(Parser.parseUnmark(toUnmark));
            if (task == null) throw new NullPointerException();
            if (!task.isDone) throw new IllegalArgumentException();
            task.markAsUndone();
            System.out.println("UNMARKED:\n" + task);
        } catch (NumberFormatException e) {
            System.out.print("NOT A NUMBER IDIOT!!!\n");
        } catch (NullPointerException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("NOTHING THERE IDIOT!!!\n");
        } catch (IllegalArgumentException e) {
            System.out.print("ALREADY UNDONE BRO!\n");
        } finally {
            System.out.print(horizontalLine);
        }
    }

    /**
     * Attempts to delete a task from the task array
     * @param toDelete
     */
    private static void delete(String toDelete) {
        System.out.print(horizontalLine);
        try {
            int index = Parser.parseDelete(toDelete);
            System.out.print("YOU SEE THIS?\n" +
                    taskStorage.get(index) +
                    "\nNOW YOU DON'T\n");
            taskStorage.delete(index);
        } catch (NumberFormatException e) {
            System.out.print("NOT A NUMBER IDIOT!!!\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("YOU WANT ME TO DELETE THE AIR???\n");
        } finally {
            System.out.print(horizontalLine);
        }
    }

    /**
     * List all tasks that have a specific keyphrase
     *
     * @param search a keyphrase used to check with the database of tasks
     */
    public static void find(String search) {
        System.out.print(horizontalLine);
        System.out.print("THIS WHAT YOU'RE LOOKING FOR?\n");
        System.out.print(taskStorage.find(Parser.parseFind(search)));
        System.out.print(horizontalLine);
    }

    public static void main(String[] args) throws IOException {
        greet();
        Scanner textInput = new Scanner(System.in);
        Status botStatus = Status.RUNNING;


        while (botStatus == Status.RUNNING) {
            String nextLine = textInput.nextLine();
            Parser.ParserOutput signal = Parser.parseInput(nextLine);
            switch (signal) {
            case MARK:
                mark(nextLine);
                continue;
            case UNMARK:
                unmark(nextLine);
                continue;
            case DELETE:
                delete(nextLine);
                continue;
            case LIST:
                list();
                continue;
            case ECHO:
                echo(nextLine);
                continue;
            case EXIT:
                botStatus = Status.STOPPING;
                continue;
            case FIND:
                find(nextLine);
                continue;
            case APPEND:
                Parser.TaskType type = Parser.parseTask(nextLine);
                switch (type) {
                case TODO:
                    appendToDo(nextLine);
                    continue;
                case EVENT:
                    appendEvent(nextLine);
                    continue;
                case DEADLINE:
                    appendDeadline(nextLine);
                    continue;
                case GENERIC:
                    append(new Task(nextLine));
                }
            }
        }

        taskStorage.write();
        exit();
    }
}
