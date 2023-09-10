package deterministicparrot;


import java.io.FileNotFoundException;
import java.util.*;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * Main class for the Deterministic Parrot task management application.
 */
public class DeterministicParrot extends Application {
    //static variable storing the path to data file

    //init by setting input and output
    private Ui ui = new Ui();
    private TaskList taskList;
    private Parser parser = new Parser();
    private Storage storage = new Storage();
    private boolean endParrot = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/parrot2.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/parrot.png"));

    /**
     * Constructs a DeterministicParrot object.
     * Initializes the task list and sets up command handlers.
     */
    public DeterministicParrot() {
        this.taskList = new TaskList();
        this.initCommandHandlers();
    }

    /**
     * Initializes command handlers for various user commands.
     */
    private void initCommandHandlers() {
        parser.registerHandler("list", args -> printList());
        parser.registerHandler("bye", args -> bye());
        parser.registerHandler("mark", args -> {
            markAsDone(args);
        });
        parser.registerHandler("unmark", args -> {
            markAsUndone(args);
        });
        parser.registerHandler("todo", args -> {
            addToDo(args);
        });
        parser.registerHandler("deadline", args -> {
            addDeadline(args);
        });
        parser.registerHandler("event", args -> {
            addEvent(args);
        });
        parser.registerHandler("delete", args -> {
            deleteTask(args);
        });
        parser.registerHandler("find", args -> {
            findTask(args);
        });
    }

    /**
     * Saves the task list to a file.
     *
     * @throws FileNotFoundException If the file is not found.
     */
    private void dumpTaskListToFile() throws FileNotFoundException {
        storage.save(this.taskList.serialize());
    }


    /**
     * Adds a task to the list and updates the UI.
     *
     * @param t The task to be added.
     * @throws Exception If an error occurs while adding the task.
     */
    private void addToList(Task t) throws Exception {
        this.taskList.addTask(t);
        this.ui.println("     " + "Got it. I've added this task:");
        this.ui.println("       " + t);
        this.ui.println("     " + "Now you have " + this.taskList.getSize() + " tasks in the list.");
        dumpTaskListToFile();
    }

    /**
     * Marks a task as done and updates the UI.
     *
     * @param args The arguments passed to the mark command.
     * @throws Exception If an error occurs while marking the task.
     */
    private void markAsDone(String args[]) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        Task t = this.taskList.markAsDone(i);
        this.ui.println("    " + "Nice! I've marked this task as done:");
        this.ui.println("       " + t);
        dumpTaskListToFile();
    }

    /**
     * Marks a task as undone and updates the UI.
     *
     * @param toks The arguments passed to the unmark command.
     * @throws Exception If an error occurs while marking the task as undone.
     */
    private void markAsUndone(String toks[]) throws Exception {
        if (toks.length < 2) {
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(toks[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        Task t = this.taskList.markAsUndone(i);
        this.ui.println("    " + "OK, I've marked this task as not done yet:\n");
        this.ui.println("       " + t);
        dumpTaskListToFile();
    }

    /**
     * Adds a "ToDo" task to the list and updates the UI.
     *
     * @param args The arguments passed to the todo command.
     * @throws Exception If an error occurs while adding the task.
     */
    private void addToDo(String[] args) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        ToDo t = new ToDo(taskDescription);
        addToList(t);
    }

    /**
     * Adds a "Deadline" task to the list and updates the UI.
     *
     * @param args The arguments passed to the deadline command.
     * @throws Exception If an error occurs while adding the task.
     */
    private void addDeadline(String[] args) throws Exception {
        int byIndex = Arrays.asList(args).indexOf("/by");
        if (byIndex == -1 || byIndex == args.length - 1) {
            throw new DeterministicParrotException("Invalid deadline format. Use /by to specify deadline time.");
        }
        String taskName = String.join(" ", Arrays.copyOfRange(args, 1, byIndex));
        String deadline = String.join(" ", Arrays.copyOfRange(args, byIndex + 1, args.length));

        Deadline t = new Deadline(taskName, deadline);
        addToList(t);
    }

    /**
     * Adds an "Event" task to the list and updates the UI.
     *
     * @param args The arguments passed to the event command.
     * @throws Exception If an error occurs while adding the task.
     */
    private void addEvent(String[] args) throws Exception {
        int fromIndex = Arrays.asList(args).indexOf("/from");
        int toIndex = Arrays.asList(args).indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex || fromIndex == args.length - 1 ||
            toIndex == args.length - 1) {
            throw new DeterministicParrotException("Invalid event format. Use /from and /to to specify event time.");
        }
        String eventName = String.join(" ", Arrays.copyOfRange(args, 1, fromIndex));
        String startTime = String.join(" ", Arrays.copyOfRange(args, fromIndex + 1, toIndex));
        String endTime = String.join(" ", Arrays.copyOfRange(args, toIndex + 1, args.length));
        Event t = new Event(eventName, startTime, endTime);
        addToList(t);
    }

    /**
     * Deletes a task from the list and updates the UI.
     *
     * @param args The arguments passed to the delete command.
     * @throws Exception If an error occurs while deleting the task.
     */
    private void deleteTask(String args[]) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        Task t = this.taskList.deleteTask(i);
        this.ui.println("    " + "Noted. I've removed this task:");
        this.ui.println("       " + t);
        this.ui.println("     " + "Now you have " + this.taskList.getSize() + " tasks in the list.");
        dumpTaskListToFile();
    }

    /**
     * Displays the list of tasks in the UI.
     */
    private void printList() {
        this.ui.println("     " + "Here are the tasks in your list:");
        this.ui.println(this.taskList.formatAsString());
    }

    /**
     * Performs necessary actions before exiting the application.
     *
     * @throws Exception If an error occurs during the exit process.
     */

    private void bye() throws Exception {
        dumpTaskListToFile();
        this.endParrot = true;
        this.ui.bye();
    }

    /**
     * Polls for user input and handles commands until the application is exited.
     */
    private void poll() {
        this.ui.greet();
        try {
            this.taskList = TaskList.deserialize(storage.load());
        } catch (Exception e) {
            this.ui.println("     " + "No saved task list found. Starting with empty task list.");
            this.taskList = new TaskList();
        }

        while (true) {
            if (this.endParrot) {
                break;
            }
            String input = this.ui.readCommand();
            if (input.isEmpty()) {
                continue; // Skip empty input
            }
            this.ui.printDash();
            try {
                this.parser.handleCommand(input);
            } catch (Exception e) {
                this.ui.printError(e);
            }
            this.ui.printDash();
        }
    }

    private void findTask(String[] args) throws Exception {
        if (args.length < 2) {
            throw new DeterministicParrotException("Please provide a search term.");
        }
        String searchTerm = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        List<TaskList.SearchResult> results = this.taskList.findTask(searchTerm);
        this.ui.println("     " + "Here are the matching tasks in your list:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            sb.append("     " + results.get(i).index + ". " + results.get(i).task + "\n");
        }
        this.ui.println(sb.toString());
    }

    /**
     * Starts the Deterministic Parrot application.
     *
     * @param stage The stage to be used to display the UI.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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

        stage.setTitle("DeterministicParrot");
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user)),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Parrot heard: " + input;
    }
    /**
     * Main method to start the Deterministic Parrot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        DeterministicParrot parrot = new DeterministicParrot();
        parrot.poll();
    }
}
