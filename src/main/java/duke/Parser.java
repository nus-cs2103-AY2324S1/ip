package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Reads the users command.
 */
public class Parser {

    /**
     * Reads and processes the user command.
     * If it is modifying a task, perform it straight away, else, call addTask.
     *
     * @param command The user command.
     * @param tasklist The task list to perform actions on.
     * @throws DukeException If there's an issue with the command or task processing.
     */
    public static void readTask(String command, TaskList tasklist) throws DukeException {
        String[] commandSplit = command.split(" ");
        if (command.equals("list")) {
            Ui.listTasks(tasklist);
        } else if (command.startsWith("find")) {
            tasklist.findTasks(commandSplit[1]);
        } else {
            if (command.startsWith("todo") || command.startsWith("deadline")
                    || command.startsWith("event")) {
                addTask(command, tasklist);
            } else if (command.startsWith("mark") || command.startsWith("unmark")
                    || command.startsWith("delete")) {
                try {
                    if (commandSplit.length > 2 || Integer.parseInt(commandSplit[1]) > tasklist.taskCount()) {
                        throw new DukeException("Please enter a valid number");
                    }
                    int index = Integer.parseInt(commandSplit[1]) - 1;
                    if (command.startsWith("mark")) {
                        Ui.markTask(tasklist.getTask(index));
                        tasklist.markTask(index);
                    } else if (command.startsWith("unmark")) {
                        Ui.unMarkTask(tasklist.getTask(index));
                        tasklist.unMarkTask(index);
                    } else {
                        Ui.deleteTask(tasklist.getTask(index), tasklist.taskCount());
                        tasklist.deleteTask(index);
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid number");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Please enter a valid number");
                }
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Add task to the task list based on the command.
     *
     * @param command The user command.
     * @param tasklist The task list to perform actions on.
     * @throws DukeException If there's an issue with the command or task processing.
     */
    private static void addTask(String command, TaskList tasklist) throws DukeException {
        if (command.startsWith("todo")) {
            try {
                String taskDescription = command.substring(5);
                if (taskDescription.length() == 0) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Task t = new Todo(taskDescription);
                tasklist.addTask(t);
                Ui.addTask(t, tasklist.taskCount());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] taskAndDeadline = command.substring(9).split(" /by ");
                if (taskAndDeadline[0].length() == 0) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                Task t = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
                tasklist.addTask(t);
                Ui.addTask(t, tasklist.taskCount());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The deadline is not specified.");
            }
        } else if (command.startsWith("event")) {
            try {
                String[] taskAndDate = command.substring(6).split(" /from | /to ");
                if (taskAndDate[0].length() == 0) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                Task t = new Event(taskAndDate[0], taskAndDate[1], taskAndDate[2]);
                tasklist.addTask(t);
                Ui.addTask(t, tasklist.taskCount());
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The description of an event cannot be empty.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The dates of an event cannot be empty.");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
