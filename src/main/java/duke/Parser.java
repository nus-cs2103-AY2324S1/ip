package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Reads the users command.
 */
public class Parser {

    /**
     * Reads the user's command, processes it, and returns the appropriate response.
     *
     * @param command   The user's input command.
     * @param tasklist  The task list to which the command should be applied.
     * @return          The response or result of processing the user's command.
     */
    public static String readTask(String command, TaskList tasklist) {
        String[] commandSplit = command.split(" ");
        if (command.equals("bye")) {
            System.exit(0);
            return Ui.exit();
        } else if (command.equals("list")) {
            return Ui.listTasks(tasklist);
        } else if (command.startsWith("find")) {
            return tasklist.findTasks(commandSplit[1]);
        } else {
            if (command.startsWith("todo") || command.startsWith("deadline")
                    || command.startsWith("event")) {
                return addTask(command, tasklist);
            } else if (command.startsWith("mark") || command.startsWith("unmark")
                    || command.startsWith("delete")) {
                try {
                    if (commandSplit.length > 2 || Integer.parseInt(commandSplit[1]) > tasklist.taskCount()) {
                        return Ui.showError(new DukeException("Please enter a valid number"));
                    }
                    int index = Integer.parseInt(commandSplit[1]) - 1;
                    if (command.startsWith("mark")) {
                        return tasklist.markTask(index);
                    } else if (command.startsWith("unmark")) {
                        return tasklist.unMarkTask(index);
                    } else {
                        return tasklist.deleteTask(index);
                    }
                } catch (NumberFormatException e) {
                    return Ui.showError(new DukeException("Please enter a valid number"));
                } catch (ArrayIndexOutOfBoundsException e) {
                    return Ui.showError(new DukeException("Please enter a valid number"));
                }
            } else {
                return Ui.showError(new DukeException("I'm sorry, but I don't know what that means :-("));
            }
        }
    }

    /**
     * Parses and adds a task based on the user's input command.
     *
     * @param command   The user's input command for adding a task.
     * @param tasklist  The task list to which the task should be added.
     * @return          The response or result of adding the task.
     */
    private static String addTask(String command, TaskList tasklist) {
        if (command.startsWith("todo")) {
            try {
                String taskDescription = command.substring(5);
                if (taskDescription.length() == 0) {
                    return Ui.showError(new DukeException("The description of a todo cannot be empty."));
                }
                Task t = new Todo(taskDescription);
                return tasklist.addTask(t);
            } catch (StringIndexOutOfBoundsException e) {
                return Ui.showError(new DukeException("The description of a todo cannot be empty."));
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] taskAndDeadline = command.substring(9).split(" /by ");
                if (taskAndDeadline[0].length() == 0) {
                    return Ui.showError(new DukeException("The description of a deadline cannot be empty."));
                }
                Task t = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
                return tasklist.addTask(t);
            } catch (StringIndexOutOfBoundsException e) {
                return Ui.showError(new DukeException("The description of a deadline cannot be empty."));
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.showError(new DukeException("The deadline (yyyy-MM-dd) is not specified."));
            }
        } else if (command.startsWith("event")) {
            try {
                String[] taskAndDate = command.substring(6).split(" /from | /to ");
                if (taskAndDate[0].length() == 0) {
                    return Ui.showError(new DukeException("The description of a todo cannot be empty."));
                }
                Task t = new Event(taskAndDate[0], taskAndDate[1], taskAndDate[2]);
                return tasklist.addTask(t);
            } catch (StringIndexOutOfBoundsException e) {
                return Ui.showError(new DukeException("The description of an event cannot be empty."));
            } catch (ArrayIndexOutOfBoundsException e) {
                return Ui.showError(new DukeException("The dates (yyyy-MM-dd) of an event cannot be empty."));
            }
        } else {
            return Ui.showError(new DukeException("I'm sorry, but I don't know what that means :-("));
        }
    }
}
