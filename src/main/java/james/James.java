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
     */

    public James() {
        String savePath = "data/James.txt";
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
        new James().run();
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

    public String processInput(String input) {
        String output = this.ui.processInput(this.tasks, input);
        if (output == "Bye. Hope to see you again soon!") {
            try {
                this.storage.save(tasks);
            } catch (SavingException e) {
                System.out.println("Error saving file");
            }
        }
        return output;
    }

    public String getWelcomeMessage() {
        return "Hello! I'm James\nWhat can I do for you?";
    }
}
