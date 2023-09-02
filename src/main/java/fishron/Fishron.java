package fishron;

/**
 * The main class for the Fishron task management application.
 */
public class Fishron {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initializes a new instance of the Fishron class.
     *
     * @param filePath The file path for storing task data.
     */
    public Fishron(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.loadTasksFromFile();
    }

    /**
     * Runs the Fishron application.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand, this.taskList);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (FishronException e) {
                ui.showErrorMessage(e.getMessage());
                ui.showLine();
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the Fishron application.
     *
     * @param args The command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Fishron("data/tasks.txt").run();
    }
}
