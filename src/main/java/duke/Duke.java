package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidFileException;
import duke.exceptions.DukeInvalidInputException;
import duke.tasks.TaskList;

/**
 * The <code>Duke</code> class encapsulates the entire chatbot.
 * Runs the program.
 */

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * The class constructor.
     *
     * @param filePath File to be loaded from/written to. May or may not exist initially.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeInvalidFileException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        boolean running = true;
        System.out.println(ui.startup());
        while (running) {
            try {
                String command = ui.getInput();
                Handler handler = new Handler(taskList, ui, storage);
                if (command.equals("bye")) {
                    System.out.println(ui.exit());
                    running = false;
                } else if (command.equals("list")) {
                    System.out.println(ui.getList(taskList));
                } else if (command.startsWith("mark ")) {
                    System.out.println(handler.handleMark(command));
                } else if (command.startsWith("unmark ")) {
                    System.out.println(handler.handleUnmark(command));
                } else if (command.startsWith("todo ")) {
                    System.out.println(handler.handleTodo(command));
                } else if (command.startsWith("event ")) {
                    System.out.println(handler.handleEvent(command));
                } else if (command.startsWith("deadline ")) {
                    System.out.println(handler.handleDeadline(command));
                } else if (command.startsWith("delete ")) {
                    System.out.println(handler.handleDelete(command));
                } else {
                    throw new DukeInvalidInputException();
                }
            } catch (DukeException e) {
                System.out.println(ui.errorMsg(e.getMessage()));
            }
        }
    }

    /**
     * Calls the run() command.
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
