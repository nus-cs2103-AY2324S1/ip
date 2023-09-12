package seedu.duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Encapsulates the TaskList class.
 * A TaskList represents the user's accumulated tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    private enum UpdateType {DELETE, MARK, UNMARK};

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Obtains the accumulated tasks.
     *
     * @return An ArrayList<Task> containing the current tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Appends the new task at the end of the current ArrayList<Task> and saves it.
     *
     * @param task The task to be appended.
     * @return The ArrayList<Task> containing the updated tasks.
     */
    // Adds the Task argument at the end of the list
    public ArrayList<Task> addTask(Task task) {
        tasks.add(task);
        new Storage("./data/duke.txt").save(tasks);
        return tasks;
    }

    public void saveTasks() {
        new Storage("./data/duke.txt").save(this.tasks);
    }

    /**
     * Reads the duke.txt file, unserializes it and obtain the arraylist representing saved tasks.
     * Depending on the purpose, information in the arraylist is updated, and the whole arraylist is
     * serialized and saved again in the duke.txt file.
     *
     * @param index The task number specified.
     * @param purpose The type of update: mark or delete.
     * @return The task which has been marked/deleted.
     * @throws InvalidDataFormatException if the data in the duke.txt file is not in the correct format.
     */
    //
    public Task updateTasks(int index, String purpose) throws InvalidDataFormatException {
        if (UpdateType.valueOf(purpose) == UpdateType.DELETE) {
            Task deletedTask = this.tasks.get(index);
            this.tasks.remove(index);
            saveTasks();
            return deletedTask;
        } else if (UpdateType.valueOf(purpose) == UpdateType.MARK) {
            this.tasks.get(index).markTask();
            saveTasks();
            return this.tasks.get(index);
        } else if (UpdateType.valueOf(purpose) == UpdateType.UNMARK) {
            this.tasks.get(index).unMarkTask();
            saveTasks();
            return this.tasks.get(index);
        }
        return null;
    }

    /**
     * Finds tasks in the accumulated tasks that contain the keyword specified.
     *
     * @param keyword The string to be matched with the accumulated tasks.
     * @return An ArrayList<Task> containing the tasks that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).name.contains(keyword)) {
                results.add(tasks.get(i));
            }
        }
        return results;
    }

    /**
     * Postpones the task at the index specified.
     *
     * @param cmd The user input.
     * @param index The index of the task in the arraylist.
     * @return The postponed task.
     */
    public Task postponeTask(String cmd, int index) {
        Task snoozedTask = this.tasks.get(index);
        if (snoozedTask instanceof Deadline) {
            if (hasValidDeadline(cmd)) {
                return postponeDeadline(snoozedTask, getDeadline(cmd));
            }
        } else {
            if (hasValidDuration(cmd)) {
                return postponeEvent(snoozedTask, getStartDuration(cmd), getEndDuration(cmd));
            }
        }
        return null;
    }

    /**
     * Checks if a valid deadline has been specified.
     *
     * @param cmd The user input.
     * @return True if the deadline is valid and false otherwise.
     */
    public boolean hasValidDeadline(String cmd) {
        String integerWithDeadline = cmd.split(" ", 2)[1];
        Parser parser = new Parser(new Storage(""), new TaskList(), new Ui());

        if (parser.hasNoDeadline(integerWithDeadline)) {
            return false;
        }

        String deadlineDescription = integerWithDeadline.split("/", 2)[1];
        if (parser.checkDeadline(deadlineDescription) == null) {
            return false;
        }
        return true;
    }

    /**
     * Converts the deadline provided by the user to a LocalDateTime.
     *
     * @param cmd The user input.
     * @return The LocalDateTime representing the deadline given.
     */
    public LocalDateTime getDeadline(String cmd) {
        String deadlineDescription = cmd.split("/")[1];
        Parser parser = new Parser(new Storage(""), new TaskList(), new Ui());
        return parser.checkDeadline(deadlineDescription);
    }

    /**
     * Checks if a valid duration has been specified.
     *
     * @param cmd The user input.
     * @return  True if the duration is valid and false otherwise.
     */
    public boolean hasValidDuration(String cmd) {
        String taskWithDuration = cmd.split(" ", 2)[1];
        String[] time = taskWithDuration.split("/");

        for (int i = 0; i < time.length; i++) {
            System.out.println(time[i]);
        }

        // Check if there is a valid duration
        if (time.length != 3) {
            return false;
        }

        String starting = time[1];
        String ending = time[2];

        Parser parser = new Parser(new Storage(""), new TaskList(), new Ui());
        if (parser.checkStarting(starting) == null || parser.checkEnding(ending) == null) {
            return false;
        }
        return true;
    }

    /**
     * Converts the start date and time to a LocalDateTime.
     *
     * @param cmd The user input.
     * @return A LocalDateTime representing the start date and time.
     */
    public LocalDateTime getStartDuration(String cmd) {
        String start = cmd.split("/")[1];
        Parser parser = new Parser(new Storage(""), new TaskList(), new Ui());
        return parser.checkStarting(start);
    }

    /**
     * Converts the end date and time to a LocalDateTime.
     *
     * @param cmd The user input.
     * @return A LocalDateTime representing the end date and time.
     */
    public LocalDateTime getEndDuration(String cmd) {
        String end = cmd.split("/")[2];
        Parser parser = new Parser(new Storage(""), new TaskList(), new Ui());
        return parser.checkEnding(end);
    }

    /**
     * Changes the deadline of a Deadline Task.
     *
     * @param task The Deadline to be postponed.
     * @param dateTime The LocalDateTime to be postponed to.
     * @return The postponed task.
     */
    public Task postponeDeadline(Task task, LocalDateTime dateTime) {
        Deadline deadline = (Deadline) task;
        deadline.changeDeadline(dateTime);
        return deadline;
    }

    /**
     * Changes the duration of an Event Task.
     *
     * @param task The Event to be postponed.
     * @param start The new start LocalDateTime.
     * @param end The new end LocalDateTime.
     * @return The postponed task.
     */
    public Task postponeEvent(Task task, LocalDateTime start, LocalDateTime end) {
        Event event = (Event) task;
        event.changeDuration(start, end);
        return event;
    }
}
