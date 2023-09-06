import commands.Command;
import functional.*;
import tasks.Task;


/**
 * Duke class is a simple chatbot that allows users
 * to mark down their tasks.It allows users to add,
 * list, and manage tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList<Task> tasks;
    private Ui ui;
    private String filePath;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The file path to store task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new TaskList<Task>(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList<Task>();
        }
    }


    /**
     * Runs the Duke application.
     * It displays a welcome message and processes user commands
     * until the 'bye' command is received to terminate the program.
     */
    public void run() {
        ui.showWelcome();
        boolean hasExit = false;
        while (!hasExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, false, false);
                hasExit = c.hasExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }
}
