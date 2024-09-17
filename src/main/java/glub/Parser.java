package glub;

import glub.task.TaskList;

/**
 * Parser that handles user inputs.
 */
public class Parser {
    /** Tasklist associated to parser. */
    private TaskList taskList;
    /** Storage associated to parser. */
    private Storage storage;
    private final String UNKNOWN_ERROR_MSG = "OOPS!! I'm sorry, but I don't know what that means :-(\n\n"
            + "The available commands are: \n"
            + "list\n"
            + "mark TASK_INDEX\n"
            + "unmark TASK_INDEX\n"
            + "delete TASK_INDEX\n"
            + "tag TASK_INDEX TAG\n"
            + "todo TASK_DESCRIPTION\n"
            + "deadline TASK_DESCRIPTION /by DATETIME\n"
            + "event TASK_DESCRIPTION /by START_DATETIME /to END_DATETIME\n"
            + "find SEARCH_PATTERN";

    /**
     * Initialises Parser object.
     * @param taskList TaskList with all tasks.
     * @param storage Storage object which saves tasks.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Listens to user input and executes the corresponding command.
     */
    public String parse(String input) throws GlubException {
        String args = "";
        assert input != null : "Input cannot be null";
        String[] parsedCommand = input.split(" ", 2);
        String command = parsedCommand[0];
        if (parsedCommand.length > 1) {
            args = parsedCommand[1];
        }
        switch (command) {
        case "bye":
            return Ui.sayGoodbye();
        case "list":
            return Ui.printListMsg(this.taskList);
        case "mark":
            return taskList.mark(Integer.parseInt(args));
        case "unmark":
            return taskList.unmark(Integer.parseInt(args));
        case "delete":
            return taskList.deleteTask(Integer.parseInt(args));
        case "tag":
            return taskList.tagTask(args);
        case "todo":
        case "deadline":
        case "event":
            taskList.addTask(args, command, false, " ");
            storage.saveTasks(taskList.getTaskList());
            return Ui.printAddMsg(taskList.getTaskList());
        case "find":
            return Ui.printFindMsg(taskList, args);
        default:
            throw new GlubException(UNKNOWN_ERROR_MSG);
        }
    }
}
