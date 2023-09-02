package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeIOException;
import duke.exceptions.DukeIllegalArgumentException;
import duke.storage.Storage;

/**
 * A list of tasks.
 */
public class TaskList {
    // Error messages
    private static final String ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS =
            "The task number is out of range. Use \"list\" to see your tasks.";

    // Message Templates
    private static final String ADD_TASK_TEMPLATE =
            "Got it. I've added this task:%n%s%nNow you have %d %s in the list.";
    private static final String DELETE_TASK_TEMPLATE =
            "Noted. I've removed this task:%n%s%nNow you have %d %s in the list.";

    // An ArrayList that stores the list of tasks.
    protected final ArrayList<Task> list;

    /**
     * Enum for types of tasks.
     * Available types: TODO, DEADLINE, EVENT
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    // Storage to save and load tasks from disk.
    private final Storage taskStorage;

    /**
     * Constructor for a TaskList.
     *
     * @param saveFileName The name of the file to save the data to. Eg: tasks.txt
     */
    public TaskList(String saveFileName) {
        this.taskStorage = new Storage(saveFileName);
        this.list = new ArrayList<>();
        this.importData();
    }

    // Returns "task" if there is only 1 task in the list, else returns "tasks".
    private String taskOrTasks() {
        return this.list.size() == 1 ? "task" : "tasks";
    }

    /**
     * Adds a task to the TaskList. This method is private.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     * @param by The deadline of the task to add.
     * @param start The start date/time of the task to add.
     * @param end The end date/time of the task to add.
     * @return String message
     */
    private String add(TaskType taskType, String description, String by, String start, String end)
            throws DukeIllegalArgumentException {

        // Avoids early return statements in switch statement.
        String output = "";

        switch (taskType) {
        case TODO:
            Task toDoTask = new ToDo(description);
            this.list.add(toDoTask);
            this.exportData();
            output = String.format(ADD_TASK_TEMPLATE,
                    toDoTask, this.list.size(), taskOrTasks());
            break;
        case DEADLINE:
            Task deadlineTask = new Deadline(description, by);
            this.list.add(deadlineTask);
            this.exportData();
            output = String.format(ADD_TASK_TEMPLATE,
                    deadlineTask, this.list.size(), taskOrTasks());
            break;
        case EVENT:
            Task eventTask = new Event(description, start, end);
            this.list.add(eventTask);
            this.exportData();
            output = String.format(ADD_TASK_TEMPLATE,
                    eventTask, this.list.size(), taskOrTasks());
            break;
        default:
            break;
        }
        return output;
    }

    /**
     * Adds a task with description to the TaskList. Used for ToDo tasks.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     * @return String message
     */
    public String add(TaskType taskType, String description) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("The description of a ToDo task cannot be blank.");
        }
        if (taskType != TaskType.TODO) {
            throw new DukeIllegalArgumentException("Only ToDo tasks can be added with just a description.");
        }
        return this.add(taskType, description, "", "", "");
    }

    /**
     * Adds a task with description and deadline to the TaskList. Used for Deadline tasks.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     * @param by The deadline of the task to add.
     * @return String message
     */
    public String add(TaskType taskType, String description, String by) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("The description of a Deadline task cannot be blank.");
        }
        if (by.isBlank()) {
            throw new DukeIllegalArgumentException("The date/time of a Deadline task cannot be blank.");
        }
        if (taskType != TaskType.DEADLINE) {
            throw new DukeIllegalArgumentException(
                    "Only Deadline tasks can be added with a description and deadline.");
        }
        return this.add(taskType, description, by, "", "");
    }

    /**
     * Adds a task with description, start and end date/time to the TaskList. Used for Event tasks.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     * @param start The start date/time of the task to add.
     * @param end The end date/time of the task to add.
     * @return String message
     */
    public String add(TaskType taskType, String description, String start, String end)
            throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("The description of an Event task cannot be blank.");
        }
        if (start.isBlank()) {
            throw new DukeIllegalArgumentException("The start date/time of an Event task cannot be blank.");
        }
        if (end.isBlank()) {
            throw new DukeIllegalArgumentException("The end date/time of an Event task cannot be blank.");
        }
        if (taskType != TaskType.EVENT) {
            throw new DukeIllegalArgumentException(
                    "Only Event tasks can be added with a description, start and end date/time.");
        }
        return this.add(taskType, description, "", start, end);
    }

    /**
     * Marks a task as done.
     *
     * @param num The number of the task to be marked as done.
     * @return String message
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    public String mark(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= this.list.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        this.list.get(index).markAsDone();
        this.exportData();
        return String.format("Nice! I've marked this task as done:%n%s", this.list.get(index).toString());
    }

    /**
     * Marks a task as undone.
     *
     * @param num The number of the task to be marked as undone.
     * @return String message
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    public String unmark(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= this.list.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        this.list.get(index).unmarkAsDone();
        this.exportData();
        return String.format("OK, I've marked this task as not done yet:%n%s", this.list.get(index).toString());
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param num The number of the task to be deleted.
     * @return String message
     */
    public String delete(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        Task task = list.remove(index);
        this.exportData();
        return String.format(DELETE_TASK_TEMPLATE, task, this.list.size(), taskOrTasks());
    }

    /**
     * Prints tasks that contain the keyword in the description.
     *
     * @param keyword The keyword to search for.
     * @return The String representation of the tasks that contain the keyword in the description.
     */
    public String find(String keyword) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.hasKeywordInDescription(keyword)) {
                count++;
                sb.append("\n").append(i + 1).append(".").append(task);
            }
        }
        if (count == 0) {
            return "There are no matching tasks in your list.";
        } else {
            String isOrAre = count == 1 ? "is" : "are";
            String taskOrTasks = count == 1 ? "task" : "tasks";
            return String.format("Here %s the %d matching %s in your list:%s", isOrAre, count, taskOrTasks, sb);
        }
    }

    /**
     * Returns the String representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        if (list.isEmpty()) {
            sb.append("\nYou have no tasks in your list.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                sb.append("\n").append((i + 1)).append(".").append(list.get(i));
            }
        }
        return sb.toString();
    }

    /**
     * Exports the TaskList's contents to the save file.
     * Each task should have the format:
     * TASK_TYPE || IS_DONE || DESCRIPTION || BY_DATE_TIME || START_DATE_TIME || END_DATE_TIME
     *
     * @throws DukeIOException If there is an error saving the tasks to the file.
     */
    private void exportData() throws DukeIOException {
        StringBuilder sb = new StringBuilder();
        for (Task task : list) {
            sb.append(task.export()).append("\n");
        }
        taskStorage.save(sb.toString());
    }

    /**
     * Reads the exported data from the save file and restores the state of the Task List.
     *
     * @throws DukeIOException If there is an error reading from the file.
     */
    private void importData() throws DukeIOException {
        String exportedData = taskStorage.load();
        if (exportedData.isBlank()) {
            return;
        }
        String[] tasks = exportedData.split("\n");
        for (String t : tasks) {
            String[] args = t.split(" \\|\\| ");
            TaskType taskType = TaskType.valueOf(args[0]);
            switch (taskType) {
            case TODO:
                this.add(taskType, args[2]);
                break;
            case DEADLINE:
                this.add(taskType, args[2], args[3]);
                break;
            case EVENT:
                this.add(taskType, args[2], args[4], args[5]);
                break;
            default:
                break;
            }
            if (args[1].equals("X")) {
                this.mark(this.list.size());
            }
        }
    }
}
