package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeIOException;
import duke.exceptions.DukeIllegalArgumentException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * A list of tasks.
 */
public class TaskList {
    // Error messages
    private static final String ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS =
            "The task number is out of range. Use \"list\" to see your tasks.";

    // Stores the list of tasks
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

    /**
     * Storage to save and load tasks.
     */
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

    /**
     * Prints an OK message when a task is added.
     */
    private void printAddTaskMessage() {
        Ui.printMessage("Got it. I've added this task:");
    }

    /**
     * Prints the number of tasks in the list after adding/removing a task.
     */
    private void printNumberOfTasks() {
        String taskOrTasks = this.list.size() == 1 ? "task" : "tasks";
        Ui.printMessage(String.format("Now you have %d %s in the list.", this.list.size(), taskOrTasks));
    }

    /**
     * Add a task to the TaskList. This method is private.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     * @param by The deadline of the task to add.
     * @param start The start date/time of the task to add.
     * @param end The end date/time of the task to add.
     */
    private void add(TaskType taskType, String description, String by, String start, String end)
            throws DukeIllegalArgumentException {
        switch (taskType) {
        case TODO:
            Task toDoTask = new ToDo(description);
            this.list.add(toDoTask);
            printAddTaskMessage();
            Ui.printMessage(toDoTask.toString());
            printNumberOfTasks();
            break;
        case DEADLINE:
            Task deadlineTask = new Deadline(description, by);
            this.list.add(deadlineTask);
            printAddTaskMessage();
            Ui.printMessage(deadlineTask.toString());
            printNumberOfTasks();
            break;
        case EVENT:
            Task eventTask = new Event(description, start, end);
            this.list.add(eventTask);
            printAddTaskMessage();
            Ui.printMessage(eventTask.toString());
            printNumberOfTasks();
            break;
        default:
            break;
        }
        this.exportData();
    }

    /**
     * Add a task with description to the TaskList. Used for ToDo tasks.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     */
    public void add(TaskType taskType, String description) throws DukeIllegalArgumentException {
        if (description.isBlank()) {
            throw new DukeIllegalArgumentException("The description of a ToDo task cannot be blank.");
        }
        if (taskType != TaskType.TODO) {
            throw new DukeIllegalArgumentException("Only ToDo tasks can be added with just a description.");
        }
        this.add(taskType, description, "", "", "");
    }

    /**
     * Add a task with description and deadline to the TaskList. Used for Deadline tasks.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     * @param by The deadline of the task to add.
     */
    public void add(TaskType taskType, String description, String by) throws DukeIllegalArgumentException {
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
        this.add(taskType, description, by, "", "");
    }

    /**
     * Add a task with description, start and end date/time to the TaskList. Used for Event tasks.
     *
     * @param taskType The type of task to add.
     * @param description The description of the task to add.
     * @param start The start date/time of the task to add.
     * @param end The end date/time of the task to add.
     */
    public void add(TaskType taskType, String description, String start, String end)
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
        this.add(taskType, description, "", start, end);
    }

    /**
     * Marks a task as done.
     *
     * @param num The number of the task to be marked as done.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    public void mark(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= this.list.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        this.list.get(index).markAsDone();
        Ui.printMessage("Nice! I've marked this task as done:");
        Ui.printMessage(this.list.get(index).toString());
        this.exportData();
    }

    /**
     * Marks a task as undone.
     *
     * @param num The number of the task to be marked as undone.
     * @throws DukeIllegalArgumentException If the task number is out of range of the list.
     */
    public void unmark(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= this.list.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        this.list.get(index).unmarkAsDone();
        Ui.printMessage("OK, I've marked this task as not done yet:");
        Ui.printMessage(this.list.get(index).toString());
        this.exportData();
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param num The number of the task to be deleted.
     */
    public void delete(int num) throws DukeIllegalArgumentException {
        int index = num - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeIllegalArgumentException(ERROR_MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        Task task = list.remove(index);
        Ui.printMessage("Noted. I've removed this task:");
        Ui.printMessage(task.toString());
        printNumberOfTasks();
        this.exportData();
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
        Ui.printMessage("[RESTORE] Please wait, restoring task list from save file...");
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
        Ui.printMessage(String.format("[RESTORE] Restored %d tasks successfully!%n", this.list.size()));
    }
}
