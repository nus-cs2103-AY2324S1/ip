package thea;

/**
 * Thea is a chatbot that manages user tasks.
 * The name Thea comes from Alethea which means "truth".
 */
public class Thea {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Thea object.
     * Initializes Ui, Storage and TaskList objects.
     *
     * @param fileName the fileName in which the task data is/should be saved.
     */
    public Thea(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        try {
            this.tasks = new TaskList(storage.retrieveTasks());
        } catch (FileCorruptedException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Contains logic the main logic of Thea that needs to be run.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readNextLine();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (EmptyDescriptionException | WrongCommandException
                     | WrongDateTimeFormatException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        new Thea("thea.txt").run();
    }
}
