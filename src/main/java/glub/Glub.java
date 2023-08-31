package glub;

import glub.task.TaskList;

/**
 * A task management chatbot.
 */

public class Glub {
    /** Tasklist of the Glub object. */
    private TaskList taskList;
    /** Parser of the Glub object. */
    private Parser parser;
    /**
     * Create a Glub object with a Storage object to initialise the chatbot.
     *
     * @param storage The required storage object.
     */
    public Glub(Storage storage) {
        try {
            this.taskList = new TaskList(storage);
        } catch (GlubException ex) {
            Ui.printError(ex.getMessage());
        }
        this.parser = new Parser(taskList, storage);
    }

    /**
     * Start the application by beginning to listen to user inputs.
     */
    public void run() {
        parser.listen();
    }

    /**
     * The main method to launch the Glub application.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Glub glub = new Glub(new Storage("tasks.txt"));
        Ui.greet();
        glub.run();
    }

}
