package duke;

import exception.InvalidIndexException;
import task.*;

import java.util.ArrayList;

/**
 * Contains the list of tasks. The tasks stored from the file loaded
 * and from inputs given by user.
 */
public class TaskList {
    private ArrayList<Task> taskList;

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

        }

        return "Got it. I've added this task:\n" +
                taskList.get(taskList.size() - 1).toString() +
                "\nNow you have " + (taskList.size()) + " tasks in the list.";
    }

    /**
     * Edits a task in the duke.TaskList. task.Task can be marked, unmarked as done or deleted.
     *
     * @param taskType The type of edits to be made.
     * @param ind The task to edit.
     * @return The string description of the edit being made.
     */
    public String editTask(String taskType, int ind) throws InvalidIndexException {

        if (!isValidIndex(ind)) {
            throw new InvalidIndexException();
        }

        String editDesc = "";

        switch (taskType) {
        case "mark":
            taskList.get(ind - 1).markTask();
            editDesc += "Nice! I've marked this task as done:\n" + taskList.get(ind - 1).toString();
            break;

        case "unmark":
            taskList.get(ind - 1).unmarkTask();
            editDesc += "OK, I've marked this task as not done yet:\n" + taskList.get(ind - 1).toString();
            break;

        case "delete":
            editDesc += "Noted. I've removed this task:\n" + taskList.get(ind - 1).toString();
            taskList.remove(ind - 1);
            editDesc += "\nNow you have " + taskList.size() + " tasks in the list.";
            break;
        }

        return editDesc;
    }

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
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            String desc = taskList.get(i).getName().toLowerCase();

            if (desc.contains(keyword)) {
                matchingTasks.add(taskList.get(i));
            }
        }

        String action = "";
        for (int i = 0; i < matchingTasks.size(); i++) {
            action += (i + 1) + "." + matchingTasks.get(i).toString() ;

            if (i < matchingTasks.size() - 1) {
                action += "\n";
            }
        }

        return action;
    }
}
