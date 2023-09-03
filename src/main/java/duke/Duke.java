package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exceptions.DukeException;
import javafx.application.Application;
import javafx.application.Platform;
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
import tasks.Task;
import tasks.TaskList;

/**
 * Duke is a chatbot that allows users to manage tasks.
 */
public class Duke extends Application {

    private static TaskList taskList = new TaskList();
    private static Storage storage;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/userAvatar.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeAvatar.png"));

    /**
     * Marks task as done.
     *
     * @param index Index of task.
     * @return
     * @throws DukeException If index is out of range.
     */
    public static String mark(int index)
            throws DukeException {
        Task task = Duke.taskList.markTaskAsDone(index);
        storage.saveChanges(Duke.taskList);
        return Ui.printMarkingOfTask(task);
    }

    /**
     * Marks task as not done.
     *
     * @param index Index of task.
     * @return
     * @throws DukeException If index is out of range.
     */
    public static String unmark(int index)
            throws DukeException {
        Task task = Duke.taskList.unmarkTask(index);
        storage.saveChanges(Duke.taskList);
        return Ui.printUnmarkingOfTask(task);
    }

    /**
     * Deletes task.
     *
     * @param index Index of task.
     * @return
     * @throws DukeException If index is out of range.
     */
    public static String deleteTask(int index)
            throws DukeException {
        Task task = Duke.taskList.deleteTask(index);
        storage.saveChanges(Duke.taskList);
        return Ui.printDeletingOfTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates todo task.
     *
     * @param desc Description of task.
     * @return
     */
    public static String createTodo(String desc) {
        Task task = Duke.taskList.addTodo(desc, 0);
        storage.saveChanges(Duke.taskList);
        return Ui.printAddingOfTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates deadline task.
     *
     * @param desc     Description of task.
     * @param deadline Deadline date/time.
     * @return
     * @throws DateTimeParseException If deadline doesn't match format "yyyy-MM-dd HHmm".
     */
    public static String createDeadline(String desc, String deadline)
            throws DateTimeParseException {
        Task task = Duke.taskList.addDeadline(desc, deadline, 0);
        storage.saveChanges(Duke.taskList);
        return Ui.printAddingOfTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates event task.
     *
     * @param desc  Description of task.
     * @param start Start date/time.
     * @param end   End date/time.
     * @return
     * @throws DateTimeParseException If start/end don't match format "yyyy-MM-dd HHmm".
     */
    public static String createEvent(String desc, String start, String end)
            throws DateTimeParseException {
        Task task = Duke.taskList.addEvent(desc, start, end, 0);
        storage.saveChanges(Duke.taskList);
        return Ui.printAddingOfTask(task, Duke.taskList.getSize());
    }

    /**
     * Prints out current tasks.
     *
     * @return
     */
    public static String listTasks() {
        return Ui.printTaskList(Duke.taskList);
    }

    /**
     * Prints list of filtered tasks.
     *
     * @param keyword String of list of filtered tasks.
     * @return
     */
    public static String listFilteredTasks(String keyword) {
        return Duke.taskList.getMatchingTasks(keyword);
    }

    /**
     * Runs the chatbot.
     *
     * @param filename Name of file to store task data.
     */
    public static void run(String filename) {
        Storage storage = new Storage(filename);
        Duke.storage = storage;
        Duke.taskList = storage.retrieveSavedData();

        Ui.printIntro();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String bye = "bye";
        String output = "";

        while (!userInput.toLowerCase().equals(bye)) {
            try {
                Parser.parseUserInput(userInput, Duke.taskList.getSize());
            } catch (RuntimeException e) {
                Ui.printMessage(e.getMessage());
            } finally {
                userInput = scanner.nextLine();
            }
        }

        storage.saveChanges(Duke.taskList);
        scanner.close();
        Ui.printOutro();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(VBox dialogContainer, TextField userInput) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        if (userInput.getText().trim().toLowerCase().equals("bye")) {
            Platform.exit();
        }
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
    private String getResponse(String userInput) {
//        return "Duke heard: " + userInput;

        if (userInput.trim().toLowerCase().equals("bye")) {
            return Ui.printOutro();
        }

        String output = "";
        try {
            output = Parser.parseUserInput(userInput, Duke.taskList.getSize());
        } catch (RuntimeException e) {
            output = e.getMessage();
        } finally {
            return output;
        }
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add.
     * @return A label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    @Override
    public void start(Stage stage) {
        // MY SETUP
        Storage storage = new Storage("duke.txt");
        Duke.storage = storage;
        Duke.taskList = storage.retrieveSavedData();
        Label introLabel = getDialogLabel(Ui.printIntro());


        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        dialogContainer.getChildren().add(0, introLabel);
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(dialogContainer, userInput);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(dialogContainer, userInput);
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // FINAL STEP
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Entrypoint into chatbot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke.run("duke.txt");
    }

}
