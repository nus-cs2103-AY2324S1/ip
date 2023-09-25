package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.Task;

import exceptions.DukeException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

/**
 * The Duke class handles the main processes for running the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static ArrayList<Task> taskArray = new ArrayList<>();
    static TaskList taskList = new TaskList(taskArray);
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
            tasks = new TaskList();
        }
    }

    /**
     * Loads saved tasks from hard disk.
     *
     * @return List of tasks as a String.
     */
    public String loadTasks() {
        String dukeText;
        try {
            taskList = new TaskList(Storage.load());
            dukeText = taskList.handleListCommand();
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
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();
            Parser.processUserInput(userInput, tasks);
        }
    }

    /**
     * Starts the Duke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Generates response to user input.
     *
     * @param input User input as a String.
     */
    @FXML
    String getResponse(String input) {
        return Parser.processUserInput(input, tasks);
    }
}
