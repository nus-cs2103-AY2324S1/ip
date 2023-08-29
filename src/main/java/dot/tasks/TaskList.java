package dot.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import dot.errors.DotException;
import dot.storage.Storage;
import dot.ui.Ui;

/**
 * TaskList class that contains an ArrayList of tasks, where
 * the Dot can tell it to perform actions on the tasks, and
 * perform storage actions, enabled by the storage package.
 * <p>
 * Note that the errors package is not used for TaskList as
 * this class can be repurposed.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    private final int maxSize;

    private final Storage storage;

    /**
     * Protected constructor for an empty TaskList, used by
     * the newTaskList factory method.
     *
     * @param maxSize of TaskList.
     * @param storage object for file read/write.
     */
    protected TaskList(int maxSize, Storage storage) {
        this.tasks = new ArrayList<>();
        this.maxSize = maxSize;
        this.storage = storage;
    }

    protected TaskList(int maxSize, ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.maxSize = maxSize;
        this.storage = storage;
    }

    /**
     * Adds the given task to the TaskList.
     *
     * @param newTask New task to add.
     */
    public void addTask(Task newTask) {
        if (this.tasks.size() < this.maxSize) {
            this.tasks.add(newTask);
            Ui.wrapPrintWithHorizontalRules(String.format("Got it. I've added this task:\n"
                    + "  %s\nNow you have %d tasks in the list.", newTask, this.tasks.size()));
        } else {
            Ui.wrapPrintWithHorizontalRules(String.format("Your task list has reached the limit of %d tasks. "
                    + "Please remove some tasks to proceed.", this.maxSize));
        }
    }

    /**
     * Lists out all tasks in the TaskList.
     */
    public void list() {
        ArrayList<String> linesToBePrinted = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            linesToBePrinted.add(String.format("%d.%s", i + 1, currTask));
        }
        Ui.displayArrayList(linesToBePrinted);
    }

    /**
     * Factory method allows for future flexibility.
     * For instance, if they are multiple empty TaskLists,
     * we are able to use a singleton.
     *
     * @param maxSize This is the limit of items the TaskList can store.
     * @param storage This is the storage object which handles fileIO.
     * @return The new TaskList.
     */
    public static TaskList getNewTaskList(int maxSize, Storage storage) {
        return new TaskList(maxSize, storage);
    }

    /**
     * Factory method for TaskList that allows the user to pass in
     * an existing <code>{@literal ArrayList<Task>}</code>, and
     * return a TaskList.
     *
     * @param maxSize  This is the limit of items the TaskList can store.
     * @param taskList This is the input task list.
     * @param storage  This is the storage object which handles fileIO.
     * @return The new TaskList.
     */
    public static TaskList getTaskListFromArrayList(int maxSize, ArrayList<Task> taskList, Storage storage) {
        return new TaskList(maxSize, taskList, storage);
    }

    /**
     * Changes the taskStatus of the Task as position
     * to boolean isCompleted.
     *
     * @param position    This is the position which Task resides
     *                    as shown in ListCommand.
     * @param isCompleted This is the done status of the Task.
     */
    public void setTaskComplete(int position, boolean isCompleted) {
        if (position >= 0 && position < this.tasks.size()) {
            this.tasks.get(position).setComplete(isCompleted);
        } else {
            Ui.wrapPrintWithHorizontalRules("Invalid position.");
        }
    }

    /**
     * Deletes the Task at a given position.
     *
     * @param position This is the position which Task resides
     *                 as shown in ListCommand.
     */
    public void deleteTask(int position) {
        if (position >= 0 && position < this.tasks.size()) {
            Task removedTask = this.tasks.remove(position);
            Ui.wrapPrintWithHorizontalRules(String.format("Task \"%s\" removed successfully!", removedTask));
        } else {
            Ui.wrapPrintWithHorizontalRules("Invalid position.");
        }
    }

    /**
     * This method displays all the tasks falling on the given LocalDateTime.
     *
     * @param dateTime The queried date-time.
     * @return An ArrayList of lines for the caller to display using Ui package.
     */
    public ArrayList<String> getDisplayForTasksFallingOnDate(LocalDateTime dateTime) {
        // Deadline must be within the day
        // Event can either start or end on the date itself, or both

        // Note that dateTime is at the start of day due to parsing standardisation
        // Create a copy of dateTime to represent the endOfDay
        LocalDateTime endOfDay = LocalDateTime.from(dateTime).withHour(23).withMinute(59).withSecond(59);
        ArrayList<String> outputList = new ArrayList<>();
        outputList.add("Finding the dots... to illuminate a constellation of "
                + "tasks happening today!");
        boolean hasTaskToday = false;
        for (Task currTask : this.tasks) {
            if (currTask.isOnDate(dateTime, endOfDay)) {
                outputList.add(currTask.toString());
                hasTaskToday = true;
            }
        }
        if (!hasTaskToday) {
            outputList.add("Like a tiny dot in the sky, you're schedule is empty! ^o^");
        }
        return outputList;
    }

    /**
     * This method invokes the saveTasks method of the storage object,
     * which saves all task to the data file.
     *
     * @throws DotException On detected error.
     */
    public void saveTaskListToStorage() throws DotException {
        this.storage.saveTasks(this.tasks);
    }

}
