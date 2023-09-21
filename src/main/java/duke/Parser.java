package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import javafx.application.Platform;


/**
 * Creates a new instance of the Parser class.
 *
 * The Parser class is responsible for parsing user commands and performing
 * tasks accordingly.
 */
public class Parser {

    /**
     * Checks if the given input string represents an exit command.
     *
     * @param input The input string to check.
     * @return True if the input string is "bye" (case-insensitive), indicating an exit command; otherwise, false.
     */
    public static boolean isExitCommand(String input) {
        return input.equalsIgnoreCase("bye");
    }

    /**
     * Parses and handles the user's command to perform various tasks.
     *
     * @param command  The user's command to be processed.
     * @param taskList The user's tasklist to be ammended.
     */
    public static String parseCommand(String command, TaskList taskList) {
        assert command != null;
        String result = "";
        try {
            String[] separateCommand = command.split(" ");
            System.out.println("--------------------------");
            if (command.equals("list")) {
                result += parseList(taskList);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                result += parseEditTask(command, separateCommand, taskList);
            } else if (command.startsWith("bye")) {
                Ui.showByeMessage();
                Platform.exit();
            } else {
                result += parseTasksCommand(command, separateCommand, taskList);
            }
        } catch (DukeException e) {
            result += e.getMessage();
            return result;
        }
        return result;
    }

    /**
     * Executes the 'list' command to display all tasks.
     *
     * @param taskList The TaskList object containing tasks to list.
     * @return A string containing the list of tasks.
     */
    public static String parseList(TaskList taskList) {
        String result = "";
        result += " Task List:\n";
        return result + taskList.printTaskListString();
    }

    /**
     * Executes the 'mark' or 'unmark' command to mark or unmark a task as done.
     *
     * @param command         The user command to execute ('mark' or 'unmark').
     * @param taskList        The TaskList object containing tasks.
     * @param separateCommand An array containing command and task number.
     * @return A string containing the result of marking or unmarking a task.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String parseEditTask(String command,
                                       String[] separateCommand, TaskList taskList) throws DukeException {
        assert command != null;
        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > taskList.getSize()) {
                throw new DukeException("☹ OOPS!!! Invalid number");
            }
            String result = "";
            int taskNumber = Integer.parseInt(separateCommand[1]);
            Task task = taskList.getTaskItem(taskNumber - 1);
            if (command.startsWith("mark")) {
                taskList.getTaskItem(taskNumber - 1).markAsDone();
                result += " Marked as done: ";
            } else if (command.startsWith("unmark")) {
                taskList.getTaskItem(taskNumber - 1).markAsUndone();
                result += " Marked as not done yet: ";
            }
            result += "   " + task.toString() + "\n";
            Ui.showMessage(result);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Invalid number");
        }
    }
    /**
     * Parses the user's command to perform various tasks based on the command type.
     *
     * @param command      The user's command to be processed.
     * @param separateCommand An array containing command and task details.
     * @param taskList     The TaskList object containing tasks.
     * @return A string containing the result of the parsed command.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String parseTasksCommand(String command, String[] separateCommand,
                                           TaskList taskList) throws DukeException {
        assert command != null;
        if (command.startsWith("find")) {
            return parseFindTask(separateCommand, taskList);
        } else if (command.startsWith("todo")) {
            return parseToDoCommand(command, taskList);
        } else if (command.startsWith("deadline")) {
            return parseDeadlineCommand(command, taskList);
        } else if (command.startsWith("event")) {
            return parseEventCommand(command, taskList);
        } else if (command.startsWith("delete")) {
            return parseDeleteCommand(command, separateCommand, taskList);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    /**
     * Parses the 'find' command to search for tasks matching a keyword.
     *
     * @param separateCommand An array containing command and keyword.
     * @param taskList     The TaskList object containing tasks.
     * @return A string containing the matching tasks or a message if no matches are found.
     */
    public static String parseFindTask(String[] separateCommand, TaskList taskList) {
        assert separateCommand != null;
        int count = 0;
        String keyword = separateCommand[1];
        String result = "";
        result += "Here are the matching tasks in your list:";
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                count += 1;
                result += count + ". " + task + "\n";
            }
        }
        Ui.showMessage(result);
        return result;
    }
    /**
     * Parses the 'todo' command to add a new task to the task list.
     *
     * @param command  The user command containing task details.
     * @param taskList The TaskList object to which the task will be added.
     * @return A string containing a confirmation message after adding the task.
     * @throws DukeException If there is an issue with the task description.
     */
    public static String parseToDoCommand(String command, TaskList taskList) throws DukeException {
        assert command != null;
        String result = "";
        try {
            String description = command.substring(5);
            if (description.length() == 0) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            Task currTask = new ToDo(description);
            taskList.addTask(currTask);
            result += " I've added this task:" + "\n" + "   " + currTask
                    + "\n" + " Now you have " + taskList.getSize() + " tasks in the list." + "\n";
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Ui.showMessage(result);
        return result;
    }
    /**
     * Parses a user command to add a deadline task to the task list and returns a response message.
     *
     * @param command   The user input command containing deadline details.
     * @param taskList  The task list where the deadline task will be added.
     * @return A response message indicating the result of the operation.
     * @throws DukeException If the command is invalid, the description is empty, or the date/time format is incorrect.
     */
    public static String parseDeadlineCommand(String command, TaskList taskList) throws DukeException {
        String result = "";
        try {
            String[] parts = command.split("/by"); //   2/12/2019 1800
            String description = parts[0].substring(9).trim();
            if (description.length() == 0) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (parts.length == 1) {
                throw new DukeException("☹ OOPS!!! There must be a date and time.");
            }
            String byID = parts[1].trim();
            Task currTask = new Deadline(description, byID);
            if (currTask.toString() != null) {
                taskList.addTask(currTask);
                result += " I've added this task:" + "\n" + "   " + currTask
                        + "\n" + " Now you have " + taskList.getSize() + " tasks in the list.";
            } else {
                throw new DukeException("☹ OOPS!!! Invalid Date/Time format, use DD/MM/YYYY HHmm");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Ui.showMessage(result);
        return result;
    }

    /**
     * Parses a user command to add an event task to the task list and returns a response message.
     *
     * @param command   The user input command containing event details.
     * @param taskList  The task list where the event task will be added.
     * @return A response message indicating the result of the operation.
     * @throws DukeException If the command is invalid or the description of the event is empty.
     */
    public static String parseEventCommand(String command, TaskList taskList) throws DukeException {
        String result = "";
        try {
            String[] parts = command.split("/from");
            String description = parts[0].substring(6).trim();
            if (description.length() == 0) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] timeParts = parts[1].split("/to");
            String start = timeParts[0].trim();
            String end = timeParts[1].trim();
            Task currTask = new Event(description, start, end);
            taskList.addTask(currTask);
            result += " I've added this task:" + "\n" + "   " + currTask
                    + "\n" + " Now you have " + taskList.getSize() + " tasks in the list.";
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        Ui.showMessage(result);
        return result;
    }

    /**
     * Parses the 'delete' command to remove a task.
     *
     * @param command The user command to execute ('delete').
     * @param separateCommand The command that has been split.
     * @param taskList   The TaskList object containing tasks.
     * @return A string containing the result of deleting a task.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String parseDeleteCommand(String command, String[] separateCommand,
                                            TaskList taskList) throws DukeException {
        String result = "";
        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > taskList.getSize()) {
                throw new DukeException("☹ OOPS!!! Invalid number");
            }
            int taskNumber = Integer.parseInt(separateCommand[1]);
            Task task = taskList.getTaskItem(taskNumber - 1);
            if (command.startsWith("delete")) {
                result += " Noted. I've removed this task:\n";
                result += task.toString();
                taskList.deleteTask(taskNumber - 1);
                result += "\nNow you have " + taskList.getSize() + " tasks in the list.";
            }
            Ui.showMessage(result);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Invalid number");
        }
        return result;
    }
}
