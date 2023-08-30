import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * The List class represents a collection of tasks managed by the ChatterChicken task manager.
 * This class is responsible for adding, marking, unmarking, and deleting tasks, as well as printing
 * the list of tasks along with their respective indexes.
 * Tasks can be of different types: ToDo, Deadline, and Event.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = Storage.loadTasksFromFile();
    }

    /**
     * Adds a new task to the task list based on the provided task type and input.
     * Depending on the task type (todo, deadline, event) a corresponding task is parsed from the input and
     * added to the list.
     *
     * @param type The type of the task to be added (todo, deadline, event).
     * @param input The input containing task details and information.
     * @throws CCException If there is an error in parsing the input or adding the task.
     */
    public void addTask(Task task) throws CCException {
        taskList.add(task);
        Storage.saveTaskToFile(task);
        System.out.println(ChatterChicken.LINE
                + ChatterChicken.INDENT + "Got it. I've added this task:\n"
                + ChatterChicken.INDENT_BIG + task.getTask() + "\n"
                + ChatterChicken.INDENT + "Now you have " + taskList.size() + " tasks in the list."
                + ChatterChicken.LINE);
    }


    /**
     * Marks a task as done based on the provided input.
     * The method attempts to retrieve the task from the list using the provided input, marks it as done,
     * and displays a confirmation message.
     *
     * @param input The input containing task information to mark as done.
     *              The input should be in the format "mark task_index"
     * @throws CCException If there is an error in marking the task, or if the input is invalid.
     */
    public void markTask(String input) throws CCException {
        try {
            Task task = taskList.get(getIndex(input));
            task.setDone(true);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "Nice! I've marked this task as done:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask()
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for marking list of length " + taskList.size());
        }
    }

    /**
     * Unmarks a previously marked task as not done based on the provided input.
     * The method attempts to retrieve the task from the list using the provided input, marks it as undone,
     * and displays a confirmation message.
     *
     * @param input The input containing task information to unmark.
     *              The input should be in the format "unmark task_index"
     * @throws CCException If there is an error in unmarking the task, or if the input is invalid.
     */
    public void unmarkTask(String input) throws CCException {
        try {
            Task task = taskList.get(getIndex(input));
            task.setDone(false);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "OK, I've marked this task as not done yet:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask()
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + taskList.size());
        }
    }

    /**
     * Deletes a task from the task list based on the provided input.
     * The method attempts to retrieve the task index from the input, removes the task from the list,
     * and displays a confirmation message.
     *
     * @param input The input containing task information to be deleted.
     *              The input should be in the format "delete task_index".
     * @throws CCException If there is an error in deleting the task or if the input is invalid.
     */
    public void deleteTask(String input) throws CCException {
        try {
            int index = getIndex(input);
            Task task = taskList.get(index);
            taskList.remove(index);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "Noted. I've removed this task:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask() + "\n"
                    + ChatterChicken.INDENT + "Now you have " + taskList.size() + " tasks in your list."
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + taskList.size());
        }
    }

    /**
     * Retrieves the index of a task based on the provided input.
     *
     * @param input The input containing task information and index as the last character.
     * @return The index of the task parsed from the input.
     */
    private int getIndex(String input) {
        return input.charAt(input.length() - 1) - '0' - 1;
    }

    /**
     * Prints the list of tasks with their respective indexes.
     */
    public void printList() {
        System.out.println(ChatterChicken.LINE + ChatterChicken.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(ChatterChicken.INDENT_BIG + (i + 1) + "." + taskList.get(i).getTask());
        }
        System.out.println(ChatterChicken.LINE);
    }
}