package duke;
import duke.exceptions.DukeException;

/**
 * Main class for the Duke application.
 * This class handles user interactions and manages tasks using the Archive class.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath file path for storing task data.
     */

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Duke application, handling user interactions and task management.
     */
    public void run() {
        ui.printIntro();
        while (true) {
            String input = ui.getInput();
            try {
                if (input.equals("GET SCHWIFTY")) {
                    System.out.print("I LIKE WHAT YOU'VE GOT. GOOD JOB.");
                    return;
                } else if (input.equals("list")) {
                    tasks.print();
                } else if (input.startsWith("mark")) {
                    tasks.markTask(input);
                } else if (input.startsWith("unmark")) {
                    tasks.unmarkTask(input);
                } else if (input.startsWith("delete")) {
                    tasks.deleteTask(input);
                } else if (input.startsWith("find")) {
                    tasks.find(input);
                } else {
                    tasks.addTask(input);
                }
                storage.save(tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * The entry point for running the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/saved.txt").run();
    }
}
