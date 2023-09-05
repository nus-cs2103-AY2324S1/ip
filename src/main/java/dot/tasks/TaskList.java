package dot.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;

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
        // We will set maxSize to 2x of length of tasks, or maxSize, whichever is larger
        this.tasks = tasks;
        this.maxSize = Math.max(tasks.size() * 2, maxSize);
        this.storage = storage;
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
     * Adds the given task to the TaskList.
     *
     * @param newTask         New task to add.
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     */
    public void addTask(Task newTask, Consumer<String> handleDotOutput) {
        if (this.tasks.size() < this.maxSize) {
            this.tasks.add(newTask);
            handleDotOutput.accept(Ui.wrapStringWithHorizontalRules(
                    String.format("Got it. I've added this task:\n"
                            + "  %s\nNow you have %d tasks in the list.", newTask, this.tasks.size())));
        } else {
            handleDotOutput.accept(Ui.wrapStringWithHorizontalRules(
                    String.format("Your task list has reached the limit of %d tasks. "
                            + "Please remove some tasks to proceed.", this.maxSize)));
        }
    }

    /**
     * Lists out all tasks in the TaskList.
     *
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     */
    public void list(Consumer<String> handleDotOutput) {
        ArrayList<String> linesToBePrinted = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            linesToBePrinted.add(String.format("%d.%s", i + 1, currTask));
        }
        handleDotOutput.accept(Ui.concatArrayList(linesToBePrinted));
    }

    /**
     * Changes the taskStatus of the Task as position
     * to boolean isCompleted.
     *
     * @param position        This is the position which Task resides
     *                        as shown in ListCommand.
     * @param isCompleted     This is the done status of the Task.
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     */
    public void setTaskComplete(int position, boolean isCompleted, Consumer<String> handleDotOutput) {
        if (position >= 0 && position < this.tasks.size()) {
            this.tasks.get(position).setComplete(isCompleted, handleDotOutput);
        } else {
            handleDotOutput.accept(Ui.wrapStringWithHorizontalRules("Invalid position."));
        }
    }

    /**
     * Deletes the Task at a given position.
     *
     * @param position        This is the position which Task resides
     *                        as shown in ListCommand.
     * @param handleDotOutput This is the consumer used to display any output
     *                        due the execution of the command to the GUI.
     */
    public void deleteTask(int position, Consumer<String> handleDotOutput) {
        if (position >= 0 && position < this.tasks.size()) {
            Task removedTask = this.tasks.remove(position);
            handleDotOutput.accept(Ui.wrapStringWithHorizontalRules(
                    String.format("Task \"%s\" removed successfully!", removedTask)));
        } else {
            handleDotOutput.accept(Ui.wrapStringWithHorizontalRules("Invalid position."));
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
     * Returns the display list for all queried tasks.
     *
     * @param query This is the query string for the tasks.
     * @return A list of lines to be displayed.
     */
    public ArrayList<String> getDisplayForQueriedTasks(String query) {
        int count = 1;
        ArrayList<String> outputList = new ArrayList<>();
        outputList.add("Here are the matching tasks in your list:");
        for (Task currTask : this.tasks) {
            if (currTask.isQueriedTask(query)) {
                outputList.add(String.format("%d.%s", count++, currTask));
            }
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
