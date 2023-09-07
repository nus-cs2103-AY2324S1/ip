package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.Task;

import exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static ArrayList<Task> taskArray = new ArrayList<>();
    static TaskList taskList = new TaskList(taskArray);
    @FXML
    private VBox dialogContainer;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/girl.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));

    /**
     * Constructs a Duke instance with the specified file path for task storage.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        } catch (IOException e){
            System.out.println("Something went wrong while loading saved task file.");
        }
    }

    public String loadTasks() {
        String dukeText;
        try {
            taskList = new TaskList(Storage.load());
            String listMessage = taskList.listTasks();
            if (listMessage.isEmpty()) {
                System.out.println("There are no tasks in your list at the moment. Add some!");
                dukeText = "There are no tasks in your list at the moment. Add some!";
            } else {
                System.out.println("Here are the tasks in your list:");
                System.out.println(listMessage);
                dukeText = "Here are the tasks in your list:\n" + listMessage;
            }
        } catch (FileNotFoundException e) {
            System.out.println("There are no tasks in your list at the moment. Add some!");
            dukeText = "There are no tasks in your list at the moment. Add some!";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            dukeText = "Error: " + e.getMessage();
        }
        return dukeText;
    }


    /**
     * Runs the Duke application, handling user interactions and task management.
     */
    public void run() {
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.printWelcomeMessage(), dukeImage));
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(loadTasks(), dukeImage));
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();
            Parser.parseInput(userInput, tasks);
            if (userInput.equals("bye")) {
                break;
            }
        }

        scanner.close();
    }

    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    String getResponse(String input) {
        return Parser.parseInput(input, tasks);
    }
}
