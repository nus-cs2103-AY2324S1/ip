package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidFileException;
import duke.exceptions.DukeInvalidInputException;
import duke.tasks.TaskList;

public class Runner {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * The class constructor.
     *
     * @param filePath File to be loaded from/written to. May or may not exist initially.
     */
    public Runner(String filePath) {
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
    public String runCommand(String command) {
        try {
            Handler handler = new Handler(taskList, ui, storage);
            if (command.equals("startup")) {
                return ui.startup();
            } else if (command.equals("bye")) {
                return ui.exit();
            } else if (command.equals("list")) {
                return ui.getList(taskList);
            } else if (command.startsWith("mark ")) {
                return handler.handleMark(command);
            } else if (command.startsWith("unmark ")) {
                return handler.handleUnmark(command);
            } else if (command.startsWith("todo ")) {
                return handler.handleTodo(command);
            } else if (command.startsWith("event ")) {
                return handler.handleEvent(command);
            } else if (command.startsWith("deadline ")) {
                return handler.handleDeadline(command);
            } else if (command.startsWith("delete ")) {
                return handler.handleDelete(command);
            } else if (command.startsWith("find ")) {
                return handler.handleFind(command);
            } else if (command.startsWith("duration ")) {
                return handler.handleDuration(command);
            } else {
                throw new DukeInvalidInputException();
            }
        } catch (DukeException e) {
            return ui.errorMsg(e.getMessage());
        }
    }
}
