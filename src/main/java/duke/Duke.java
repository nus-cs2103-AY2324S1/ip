package duke;

import duke.task.Task;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program to run a task manager that can add, delete and mark tasks.
 *
 * @author Teo Kai Sheng
 */
public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor to start the program.
     *
     * @param filePath The file path of the saved task list.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskList());
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Parser parser = new Parser(ui, tasks);
        parser.parse(input);
        while (!parser.isDone()) {
            input = scanner.nextLine();
            parser.parse(input);
        }
        storage.updateTaskList();
    }

    /**
     * Main function that starts the program loading in the saved task list.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke(Paths.get(".", "data", "duke.txt")).run(); // ./data/duke.txt
    }
}
