package james;

import java.util.ArrayList;

/**
 * James is a personal assistant chatbot that helps a person to keep track of various things.
 */
public class James {
    /** Handles loading and saving to hard disk */
    private Storage storage;

    /** Stores the tasks. */
    private TaskList tasks;

    /** Handles user interaction. */
    private Ui ui;

    /**
     * Creates a James object.
     *
     * @param savePath The path to the save file.
     */
    public James(String savePath) {
        Ui ui = new Ui();
        this.ui = ui;
        Storage storage = new Storage(savePath);
        this.storage = storage;
        try {
            ArrayList<Task> tasks = storage.load();
            this.tasks = new TaskList(tasks);
        } catch (LoadingException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    public static void main(String[] args) {
        new James("data/James.txt").run();
    }

    /**
     * Starts the James program.
     */
    public void run() {
        ui.start(tasks);
        try {
            this.storage.save(tasks);
        } catch (SavingException e) {
            System.out.println("Error saving file");
        }
    }

}
