import commands.Command;
import functional.Storage;
import tasks.Task;


/**
 * Duke class is a simple chatbot that allows users
 * to mark down their tasks.It allows users to add,
 * list, and manage tasks.
 */
public class Duke {
    private Storage storage;
    private functional.TaskList<Task> tasks;
    private functional.Ui ui;
    private String filePath;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The file path to store task data.
     */
    public Duke(String filePath) {
        ui = new functional.Ui();
        storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new functional.TaskList<Task>(storage.load());
        } catch (functional.DukeException e) {
            ui.showLoadingError();
            tasks = new functional.TaskList<Task>();
        }
    }


    /**
     * Runs the Duke application.
     * It displays a welcome message and processes user commands
     * until the 'bye' command is received to terminate the program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = functional.Parser.parse(fullCommand);
                c.execute(tasks, ui, false, false);
                isExit = c.isExit();
            } catch (functional.DukeException e) {
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
