package duke;

import duke.exception.EmptyCommandException;
import duke.exception.InvalidCommandException;
import duke.utility.Command;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * Bobi class encapsulates all components of the Bobi tracker bot.
 *
 * @author ruo-x
 */
public class Bobi {
    private Storage storage;
    private TaskList list;
    private Ui ui;
    private Command command;

    /**
     * Constructor of a Bobi object.
     * Load tasks saved from the backend into the active Bobi.
     */
    public Bobi() {
        this.storage = new Storage();
        this.list = new TaskList();

        // Load list of tasks stored in text file "task.txt" into the local task List
        storage.handleLoad(list);

        // Start Scanner to read user inputs
        this.ui = new Ui(list);
        this.command = new Command(storage, ui, list);
    }

    /**
     * Returns Bobi's response to user's inputs.
     *
     * @param input User's input
     * @return Bobi's response
     */
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return Ui.exit();
        } else if (input.equalsIgnoreCase("list")) {
            return ui.listTasks();
        } else if (input.startsWith("mark")) {
            return command.handleMark(input);
        } else if (input.startsWith("unmark")) {
            return command.handleUnMark(input);
        } else if (input.startsWith("todo")) {
            return command.handleToDo(input);
        } else if (input.startsWith("deadline")) {
            return command.handleDeadline(input);
        } else if (input.startsWith("event")) {
            return command.handleEvent(input);
        } else if (input.startsWith("delete")) {
            return command.handleDelete(input);
        } else if (input.startsWith("find")) {
            return command.handleFind(input);
        } else if (input.length() == 0) {
            return Ui.printError(new EmptyCommandException());
        } else {
            return Ui.printError(new InvalidCommandException());
        }
    }

    /**
     * Runs Bobi.
     */
    public void run() {
        System.out.println(Ui.greeting());

        String answer = ui.getInput();

        // Listens to user commands and perform the relevant functions
        while (!answer.equalsIgnoreCase("bye")) {
            System.out.println(this.getResponse(answer));
            answer = ui.getInput();
        }
        System.out.println(Ui.exit());
        ui.stopScanner();
    }

    public static void main(String[] args) {
        new Bobi().run();
    }
}
