package Duke.tasks;

import Duke.parser.Parser;
import core.DukeException;

public class TaskManager {
    private TaskList tasks;
    private Parser p = new Parser();

    public TaskManager(TaskList tasks) {
        this.tasks = tasks;
    }

    public TaskManager() {
        this.tasks = new TaskList();
    }
    /**
     * Handles the deletion of a task from the list based on the user command.
     *
     * @param userCommand The command given by the user to delete a task.
     * @param tasks       A list of tasks from which a task will be deleted.
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

    private String updateNumMessage(int numTasks) {
        return "\n Now you have " + String.valueOf(numTasks) + " task(s) in the list";
    }

    /**
     * Handles the marking of a task as isCompleted based on the user command.
     *
     * @param userCommand The command given by the user to mark a task as isCompleted.
     * @param tasks       A list of tasks from which a specific task will be marked
     *                    as isCompleted.
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
     * Handles the unmarking of a task, reverting its status to not isCompleted based
     * on the user command.
     *
     * @param userCommand The command given by the user to unmark a task.
     * @param tasks       A list of tasks from which a specific task will be
     *                    unmarked.
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

    public String addTask(String userCommand) {
        try {
            Task task = p.parseTaskFromCommand(userCommand);
            tasks.addTask(task);
            return "Got it I have added this task:" + "\n" + task + updateNumMessage(tasks.size());
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
