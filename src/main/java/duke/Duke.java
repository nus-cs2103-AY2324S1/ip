package duke;

import exception.DukeException;

/**
 * Main class to run Duke chatbot.
 *
 * @author syamfarh
 */
public class Duke {

    /** name of ChatBot */
    private final String name = "Ken";

    /** store user Input in task.Task array */
    private TaskList tasks;

    /** Ui class that display out to user based on userInput */
    private Ui ui;

    /** storage class that handle fetch/saving task list from file */
    private Storage storage;

    /**
     * Initialize Duke and fetch task list from duke.txt file.
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

    /**
     * Run the Duke chatbot.
     */
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

    /**
     * save the current TaskList to duke.txt file
     */
    private void exit() {
        try {
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getGreeting() {
        return ui.printGreeting(name);
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String result = Parser.replyUser(input, tasks, this.ui);
            storage.save(tasks.getTasks());
            return result;
        } catch (DukeException e){
            return ui.showErrorMsg(e);
        }
    }

}
