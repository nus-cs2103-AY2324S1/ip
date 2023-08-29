package duke;

import exception.DukeException;


public class Duke {

    /** name of ChatBot */
    private final String name = "Ken";

    /** store user Input in task.Task array */
    private TaskList tasks;

    private Ui ui;

    private Storage storage;

    /**
     * Initialize the fixed sized array.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    private void run() {
        ui.printGreeting(this.name);
        while (!ui.isExit()) {
            ui.handleUserInput(this.tasks);
        }
    }

    public static void main(String[] args) {

        Duke chatBot = new Duke();
        chatBot.run();
        chatBot.exit();
    }

    private void exit() {
        try {
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}
