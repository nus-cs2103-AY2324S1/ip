package duke;

import java.time.format.DateTimeParseException;

import command.UserInterface;

/**
 * Parser class will parse the input data and generate a taskList from the input the file.
 */
public class Parser {

    private final Storage storage;
    private TaskManager taskManager;
    private UserInterface userInterface;

    /**
     * Instantiates a new Parser.
     *
     * @param taskManager   the task manager
     * @param userInterface the user interface
     * @param storage       the storage
     */
    public Parser(TaskManager taskManager, UserInterface userInterface, Storage storage) {
        this.taskManager = taskManager;
        this.userInterface = userInterface;
        this.storage = storage;
    }

    /**
     * Parse command string.
     *
     * @param command the command
     * @return the string
     */
    public String parseCommand(String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        String action = parts[0];
        StringBuilder result = new StringBuilder();
        switch (action) {
        case "todo":
            result.append(this.todo(parts));
            break;
        case "deadline":
            result.append(this.deadline(parts));
            break;
        case "event":
            result.append(this.event(parts));
            break;
        case "delete":
            result.append(this.delete(parts));
            break;
        case "mark":
            result.append(this.mark(parts));
            break;
        case "unmark":
            result.append(this.unmark(parts));
            break;
        case "list":
            result.append(userInterface.showTaskList(taskManager.displayList()));
            break;
        case "find":
            result.append(userInterface.showFindTasks(taskManager.find(parts[1])));
            break;
        case "update":
            result.append(this.update(parts));
            break;
        case "bye":
            storage.save(taskManager.displayList());
            result.append(userInterface.showGoodbyeMessage());
            break;
        default:
            result.append(userInterface.showUnknownCommandMessage());
            break;
        }
        return result.toString();
    }

    /**
     * Todo string.
     *
     * @param parts the parts
     * @return the string
     */
    public String todo(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        taskManager.todo(parts[1]);
        return userInterface.showTaskAddedMessage(taskManager.displayList());

    }

    /**
     * Deadline string.
     *
     * @param parts the parts
     * @return the string
     */
    public String deadline(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            String[] fullDesc = parts[1].split(" /by ");
            taskManager.deadline(fullDesc[0], fullDesc[1]);
            return userInterface.showTaskAddedMessage(taskManager.displayList());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wrong format for deadline task.\n"
                    + "Correct format: deadline taskDescription /by dd-MM-yyyy HH:mm");
        }
    }

    /**
     * Event string.
     *
     * @param parts the parts
     * @return the string
     */
    public String event(String[] parts) throws DukeException {

        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }

        try {
            String[] fullDesc = parts[1].split(" /from | /to ");
            String description = fullDesc[0];
            taskManager.event(description, fullDesc[1], fullDesc[2]);
            return userInterface.showTaskAddedMessage(taskManager.displayList());
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Wrong format for event task.\n"
                    + "Correct format: event taskDescription /from dd-MM-yyyy HH:mm /to HH:mm");
        }
    }

    /**
     * Delete string.
     *
     * @param parts the parts
     * @return the string
     */
    public String delete(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task number to delete.");
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                throw new DukeException("OOPS!!! Invalid task number.");
            }
            taskManager.delete(taskIndex);
            return userInterface.showTaskDeletedMessage(taskManager.displayList());
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Mark string.
     *
     * @param parts the parts
     * @return the string
     */
    public String mark(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task number to mark.");
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                throw new DukeException("OOPS!!! Invalid task number.");
            }
            taskManager.mark(taskIndex);
            return userInterface.showTaskMarkedMessage(taskManager.displayList(), taskIndex);
        } catch (NumberFormatException e) {
            return userInterface.showError("OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Unmark string.
     *
     * @param parts the parts
     * @return the string
     */
    public String unmark(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task number to unmark.");
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= taskManager.displayList().size()) {
                throw new DukeException("OOPS!!! Invalid task number.");
            }
            taskManager.unmark(taskIndex);
            return userInterface.showTaskUnmarkedMessage(taskManager.displayList(), taskIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Update tasks.
     *
     * @param parts the parts
     * @return the string
     */
    public String update(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please specify the task number and new task to update.");
        }

        if (taskManager.displayList().size() == 0) {
            throw new DukeException("OOPS!!! Your task list is empty.");
        }
        try {
            String[] newTask = parts[1].split(" /to ");
            int taskIndex = Integer.parseInt(newTask[0]) - 1;
            taskManager.update(taskIndex, newTask[1]);
            return userInterface.showTaskUpdatedMessage(taskManager.displayList(), taskIndex);
        } catch (NumberFormatException | DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! Wrong format found.\n"
            + "Correct format: update taskNumber /to taskType taskDescription");
        }
    }
}

