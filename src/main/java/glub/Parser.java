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
     * Listen to user input and execute the corresponding command.
     */
    public String parse(String input) throws GlubException {
        String args = "";
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
        case "todo":
        case "deadline":
        case "event":
            taskList.addTask(args, command, false);
            storage.saveTasks(taskList.getTaskList());
            return Ui.printAddMsg(taskList.getTaskList());
        case "find":
            return Ui.printFindMsg(taskList, args);
        default:
            throw new GlubException("OOPS!! I'm sorry, but I don't know what that means :-(\n");
        }
    }
}
