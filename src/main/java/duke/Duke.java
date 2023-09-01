package duke;

import exceptions.DukeException;
import exceptions.FileUnloadableException;
import exceptions.ParseTaskFromStringException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a task management application that allows users to manage their tasks.
 * It provides features to add, mark, unmark, and delete tasks, and also displays the list of tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static ArrayList<Task> taskArray = new ArrayList<>();
    static TaskList taskList =  new TaskList(taskArray);

    /**
     * Constructs a Duke instance with the specified file path for task storage.
     *
     * @param filePath The file path for task storage.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        } catch (IOException e){
            System.out.println("Something went wrong while loading saved task file.");
        }
    }

    /**
     * Runs the Duke application, handling user interactions and task management.
     */
    public void run() {
        Ui.printWelcomeMessage();

        try {
            taskList = new TaskList(Storage.load());
            String listMessage = taskList.listTasks();
            if (listMessage.isEmpty()) {
                System.out.println("There are no tasks in your list at the moment. Add some!");
            } else {
                System.out.println("Here are the tasks in your list:");
                System.out.println(listMessage);
            }
        } catch (FileNotFoundException e) {
            System.out.println("There are no tasks in your list at the moment. Add some!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

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
        new Duke("tasks.txt").run();
    }
}
