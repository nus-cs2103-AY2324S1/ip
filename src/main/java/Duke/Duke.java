package Duke;
import Duke.Exceptions.DukeException;

/**
 * Main class for the Duke application.
 * This class handles user interactions and manages tasks using the Archive class.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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
                } else {
                    tasks.addTask(input);
                }
                storage.save(tasks);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/saved.txt").run();
    }
}
