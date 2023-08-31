package Duke.tasks;

import Duke.parser.Parser;
import core.DukeException;

/**
 * Manages tasks, handling operations like adding, deleting, etc.
 */
public class TaskManager {
    private TaskList tasks;
    private Parser p = new Parser();

    /**
     * Constructs a TaskManager with a given TaskList.
     * @param tasks TaskList to manage.
     */
    public TaskManager(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor. Initializes an empty TaskList.
     */
    public TaskManager() {
        this.tasks = new TaskList();
    }

    /**
     * Deletes a task based on the given user command.
     *
     * @param userCommand Command to specify which task to delete.
     * @return Result of the delete operation.
     */
    public String handleDelete(String userCommand) {
        String[] parts = userCommand.split(" ", 2);
        Task removedTask;
        try {
            if (parts.length != 2) {
                throw new DukeException("invalid delete command");
            }
            int num = Integer.parseInt(parts[1]);
            if (num > tasks.size()) {
                throw new DukeException("invalid delete command (this task number does not exist)");
            }
            removedTask = tasks.deleteTask(num - 1);
            return "Noted. I've removed this task:" + removedTask.toString() + updateNumMessage(tasks.size());
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Constructs a message indicating the current number of tasks.
     *
     * @param numTasks The current number of tasks.
     * @return A formatted message indicating the count of tasks.
     */
    private String updateNumMessage(int numTasks) {
        return "\n Now you have " + numTasks + " task(s) in the list";
    }

    /**
     * Marks a task as completed based on the given user command.
     *
     * @param userCommand Command to specify which task to mark as completed.
     * @return Result of the mark operation.
     */
    public String handleMark(String userCommand) {
        String[] parts = userCommand.split(" ");
        try {
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                if (num > tasks.size()) {
                    throw new DukeException("invalid mark command (task number does not exist)");
                }
                Task task = tasks.getTask(num - 1);
                task.toggleCompleted();
                return "Nice! I've marked this task as done:" + task;
            } else {
                throw new DukeException("invalid mark command");
            }
        } catch (Exception e) {
           return e.getMessage();
        }
    }

    /**
     * Reverts a task to its incomplete status based on the given user command.
     *
     * @param userCommand Command to specify which task to unmark.
     * @return Result of the unmark operation.
     */
    public String handleUnmark(String userCommand) {
        try {
            String[] parts = userCommand.split(" ");
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1]);
                if (num > tasks.size()) {
                    throw new DukeException("invalid mark command (task number does not exist)");
                }
                Task task = tasks.getTask(num - 1);
                task.toggleCompleted();
                return "Okay. I see you haven't done this task yet" + (task);
            } else {
                throw new DukeException("invalid unmark command");
            }
        } catch (Exception e) {
            return  e.getMessage();
        }
    }

    /**
     * Adds a task based on the given user command.
     *
     * @param userCommand Command describing the task to add.
     * @return Result of the add operation.
     */
    public String addTask(String userCommand) {
        try {
            Task task = p.parseTaskFromCommand(userCommand);
            tasks.addTask(task);
            return "Got it I have added this task:" + "\n" + task + updateNumMessage(tasks.size());
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String findTasks(String userCommand) {
        StringBuilder msg = new StringBuilder();
        try {
            TaskList matchingTasks = p.parseMatchingTasks(userCommand, tasks);
            if (matchingTasks.size() == 0) {
                return "No matching tasks.";
            }
            msg.append("Here are the tasks in your list:");
            for (Task task : matchingTasks.getTasks()) {
                msg.append("\n").append(task.toString());
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return msg.toString();
    }
}
