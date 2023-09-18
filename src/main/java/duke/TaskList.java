package duke;

import java.util.ArrayList;

import exception.InvalidCommandException;
import exception.InvalidIndexException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Contains the list of tasks. The tasks stored from the file loaded
 * and from inputs given by user.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs a duke.TaskList with a empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a duke.TaskList with data load from the file.
     *
     * @param taskList The task list stored in the file.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += (i + 1) + "." + taskList.get(i).toString();

            if (i < taskList.size() - 1) {
                tasks += "\n";
            }
        }

        return tasks;
    }

    /**
     * Returns a string of tasks to be written into the file. Formats the data stored in duke.TaskList
     * to be written into file.
     *
     * @return The string representing duke.TaskList to be written into file.
     */
    public String toFileString() {
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += taskList.get(i).fileString();

            if (i < taskList.size() - 1) {
                tasks += "\n";
            }
        }
        return tasks;
    }

    /**
     * Adds a new task into duke.TaskList. The task can either be a task.ToDo,
     * task.Deadline or task.Event.
     *
     * @param taskType The type of task to add.
     * @param args Additional information for task to be instantiated.
     * @return A string description of the task.
     */
    public String addTask(String taskType, String[] args) {
        switch (taskType) {
        case "todo":
            taskList.add(new ToDo(args[0]));
            break;

        case "deadline":
            taskList.add(new Deadline(args[0], args[1]));
            break;

        case "event":
            taskList.add(new Event(args[0], args[1], args[2]));
            break;

        default:
            assert false : "Task type should be checked by parser, no other tasks type should be allowed";
        }

        int size = taskList.size();

        String taskDesc = taskList.get(taskList.size() - 1).toString();
        String addTaskDesc = String.format(
                "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.", taskDesc, size);

        return addTaskDesc;
    }

    /**
     * Edits a task in the duke.TaskList. task.Task can be marked, unmarked as done or deleted.
     *
     * @param taskType The type of edits to be made.
     * @param ind The task to edit.
     * @return The string description of the edit being made.
     */
    public String editTask(String taskType, int ind) throws InvalidIndexException, InvalidCommandException {

        if (!isValidIndex(ind)) {
            throw new InvalidIndexException();
        }

        switch (taskType) {
        case "mark":
            taskList.get(ind - 1).markTask();
            return "Nice! I've marked this task as done:\n" + taskList.get(ind - 1).toString();

        case "unmark":
            taskList.get(ind - 1).unmarkTask();
            return "OK, I've marked this task as not done yet:\n" + taskList.get(ind - 1).toString();

        case "delete":
            String editDesc = "Noted. I've removed this task:\n" + taskList.get(ind - 1).toString();
            taskList.remove(ind - 1);
            editDesc += "\nNow you have " + taskList.size() + " tasks in the list.";
            return editDesc;

        default:
            assert false : "Task type should be checked by parser, no other tasks type should be allowed";
            throw new InvalidCommandException("No such edit command");
        }
    }

    /**
     * Checks if the index given is out of taskList range.
     *
     * @param ind The input index.
     * @return If the index is within range.
     */
    public boolean isValidIndex(int ind) {
        if (ind <= 0) {
            return false;
        }

        if (ind > taskList.size()) {
            return false;
        }

        return true;
    }

    /**
     * Returns string of tasks matching user's search. Finds list of task description
     * matching user's input keyword. Returns empty string if no matching task found.
     *
     * @param keyword The user input keyword.
     * @return The string of tasks description matching keyword.
     */
    public String findMatchingTasks(String keyword) {
        StringBuilder tasksMatched = new StringBuilder(100);

        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            String desc = taskList.get(i).getName().toLowerCase();

            if (!desc.contains(keyword)) {
                continue;
            }

            String taskDesc = String.format("%d.%s\n", count, taskList.get(i).toString());
            tasksMatched.append(taskDesc);
            count++;
        }

        if (tasksMatched.length() == 0) {
            return "No task matching keyword found!";
        }

        return String.format("Here are the matching tasks in your list:\n%s", tasksMatched);
    }

    /**
     * Return a list of upcoming task due to remind user.
     *
     * @return The list of tasks due.
     */
    public String remindTasks() {
        StringBuilder tasksReminder = new StringBuilder(100);

        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (!task.needReminder()) {
                continue;
            }

            String taskDesc = String.format("%d.%s\n", count, task);

            tasksReminder.append(taskDesc);

            count++;
        }

        if (tasksReminder.length() == 0) {
            return "No upcoming tasks. Take a break =)";
        }

        return String.format("Here is a list of upcoming tasks due:\n%s", tasksReminder);
    }
}
