package thea;

/**
 * Thea is a chatbot that manages user tasks.
 */
public class Thea {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    /**
     * Constructs a new Thea object.
     * Initializes Ui, Storage and TaskList objects.
     *
     * @param fileName the fileName in which the task data is/should be saved.
     */
    public Thea(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        this.parser = new Parser();
        try {
            this.tasks = new TaskList(storage.retrieveTasks());
        } catch (FileCorruptedException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    public void changeFileName(String fileName) {
        this.storage.setFileName(fileName);
    }
}

