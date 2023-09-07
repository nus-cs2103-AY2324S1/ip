package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;


/**
 * Program to run a task manager that can add, delete and mark tasks.
 *
 * @author Teo Kai Sheng
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static Path filePath = Paths.get(".", "data", "duke.txt"); // ./data/duke.txt

    public String getResponse(String input) {
        Parser parser = new Parser(ui, tasks);
        String response = parser.parse(input);
        return response;
    }
    /**
     * Constructor to start the program.
     */
    public Duke() {
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
        updateTaskList();
    }

    public void updateTaskList() {
        storage.updateTaskList();
    }

    /**
     * Starts the program and loads in the saved task list.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
