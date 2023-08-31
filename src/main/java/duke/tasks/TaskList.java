package duke.tasks;

import duke.storage.Storage;
import duke.exception.DukeException;

import java.util.ArrayList;

/**
 * Represents a Task List to store User's Tasks.
 */
public class TaskList {

    /** The Indentation Level to format text. */
    private static final String INDENTATION = "    ";

    /** The ArrayList to represent the Task List. */
    private ArrayList<Task> taskList;

    /** The Storage for the Chatbot. */
    private Storage storage;


    /**
     * The Constructor for the TaskList.
     *
     * @param taskList The ArrayList to initialise as the TaskList.
     * @param storage The Storage of the Chatbot.
     */
    public TaskList(ArrayList<Task> taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Formats the Task List to output.
     *
     * @return The formatted task list as a String.
     */
    public String formatList() {
        String str = "";
        int len = taskList.size();
        for (int i = 0; i < len; i++) {
            str = str + (i + 1) + ". " + taskList.get(i);
            if (i != (len - 1)) {
                str += "\n " + INDENTATION;
            }
        }
        return str;
    }

    /**
     * Handles the Marking of the Tasks in the Task List.
     *
     * @param commandNum The Index of the Task as a String.
     * @param status The status of the marking as either 'mark' or 'unmark'.
     * @return The Success output as a String.
     * @throws DukeException If Task Index provided is invalid.
     */
    public String handleMarking(String commandNum, String status) throws DukeException {

        try {
            int index = Integer.parseInt(commandNum) - 1;
            String buffer = "";

            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException("Invalid duke.tasks.Task Index provided!");
            }
            Task selectedTask = taskList.get(index);
            if (status.equals("mark")) {
                selectedTask.markTask();
                buffer = "Nice! I've marked the task as done:\n   " +
                        INDENTATION + selectedTask;
            } else if (status.equals("unmark")) {
                selectedTask.unmarkTask();
                buffer = "OK, I've marked this task as not done yet:\n   " +
                        INDENTATION + selectedTask;
            }
            this.saveList();
            return buffer;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid duke.tasks.Task Index provided!");
        }
    }

    /**
     * Handles the Deleting of a Task from the Task List.
     *
     * @param commandNum The Index of the Task as a String.
     * @return The Success output as a String.
     * @throws DukeException If Task Index provided is invalid.
     */
    public String handleDelete(String commandNum) throws DukeException {
        try {
            int index = Integer.parseInt(commandNum) - 1;

            if (index > taskList.size() - 1 || index < 0) {
                throw new DukeException("Invalid duke.tasks.Task Index provided!");
            }
            Task selectedTask = taskList.remove(index);
            this.saveList();
            return "Noted. I've removed this task:\n   " +
                    INDENTATION + selectedTask + "\n " + INDENTATION + "Now you have " +
                    taskList.size() + " tasks in the list.";

        } catch (NumberFormatException e) {
            throw new DukeException("Invalid duke.tasks.Task Index provided!");
        }
    }

    /**
     * Handles the Adding of a ToDo Task to the Task List.
     *
     * @param task The Description of the Task.
     * @return The Success output as a String.
     */
    public String handleToDo(String task) {
        ToDo item = new ToDo(task);
        taskList.add(item);
        this.saveList();
        return "Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the Adding of a Deadline Task to the Task List.
     *
     * @param desc The Description of the Task.
     * @param by The deadline of the Task.
     * @return The Success output as a String.
     * @throws DukeException if there is an Error in creating the Deadline Task.
     */
    public String handleDeadline(String desc, String by) throws DukeException {

        Deadline item = new Deadline(desc, by);
        taskList.add(item);
        this.saveList();
        return "Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list.";
    }

    /**
     * Handles the Adding of an Event Task to the Task List.
     *
     * @param desc The Description of the Task.
     * @param start The start time of the Task.
     * @param end The end time of the Task.
     * @return The Success output as a String.
     * @throws DukeException if there is an Error in creating the Event Task.
     */
    public String handleEvent(String desc, String start, String end) throws DukeException {
        Event item = new Event(desc, start, end);
        taskList.add(item);
        this.saveList();
        return "Got it. I've added this task:\n   " +
                INDENTATION + item + "\n " + INDENTATION + "Now you have " +
                taskList.size() + " tasks in the list.";
    }

    /**
     * Formats the Task List to be saved to Storage.
     *
     * @return The formatted List as a String.
     */
    public String exportList() {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(task.exportData()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Saves the Task List to the Storage.
     */
    public void saveList() {
        String data = this.exportList();
        storage.saveData(data);
    }


}
