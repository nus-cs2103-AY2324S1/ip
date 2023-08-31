package duke.task;

import duke.storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents and organises a list of task.
 */
public class TaskList {
    /**
     * Thrown if a function is trying to access a task index out of range.
     */
    public static class TaskIndexOutOfRange extends Exception {
        private TaskIndexOutOfRange() {
            super();
        }
    }

    private ArrayList<Task> taskList;

    /**
     * Instantiates the task list with no task.
     * Creates a new empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Instantiates the task list with the given task.
     * @param taskList the task list to start with
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the end of the list.
     * @param task the task to add
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks a task with the given index as done
     * @param index the task index to mark done
     * @return the task that has been marked done
     * @throws TaskIndexOutOfRange if the task index given is out of range
     */
    public Task markTaskAsDone(int index) throws TaskIndexOutOfRange {
        try {
            this.taskList.get(index).markAsDone();
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Marks a task with the given index as not done.
     * @param index the task index to mark as not done
     * @return the task that has been marked not done
     * @throws TaskIndexOutOfRange if the task index given is out of range
     */
    public Task markTaskAsNotDone(int index) throws TaskIndexOutOfRange {
        try {
            this.taskList.get(index).markAsNotDone();
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Deletes a task with the given index.
     * @param index the task index to delete
     * @return the task that has been deleted
     * @throws TaskIndexOutOfRange if the task index given is out of range
     */
    public Task deleteTask(int index) throws TaskIndexOutOfRange {
        try {
            Task task = this.taskList.get(index);
            this.taskList.remove(index);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfRange();
        }
    }

    /**
     * Displays the list of task with the given filters.
     * @param taskList the task list to display
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date to include deadlines before and events happening on,
     *             null if to not filter by date
     */
    private static void displayTasks(ArrayList<Task> taskList, boolean isExcludingDone, LocalDate date) {
        if (isExcludingDone) {
            taskList.removeIf(Task::isDone);
        }
        if (date != null) {
            taskList.removeIf(task -> !task.containsDate(date));
        }
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
    }

    /**
     * Displays to-do tasks with the given filters.
     * @param isExcludingDone whether to filter out tasks already done
     */
    public void displayTodos(boolean isExcludingDone) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        taskList.removeIf(task -> !(task instanceof ToDo));
        TaskList.displayTasks(taskList, isExcludingDone, null);
    }

    /**
     * Displays deadlines with the given filters.
     * @param isExcludingDone whether to filter out tasks already done
     * @param date the date to choose deadlines with end time before,
     *             null if to not filter by date
     */
    public void displayDeadlines(boolean isExcludingDone, LocalDate date) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        taskList.removeIf(task -> !(task instanceof Deadline));
        TaskList.displayTasks(taskList, isExcludingDone, date);
    }

    /**
     * Displays events with the given filters.
     * @param isExcludingDone whether to filter out tasks already done
     * @param date the date to choose events happening on,
     *             null if to not filter by date
     */
    public void displayEvents(boolean isExcludingDone, LocalDate date) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        taskList.removeIf(task -> !(task instanceof Event));
        TaskList.displayTasks(taskList, isExcludingDone, date);
    }

    /**
     * Displays tasks with the given filters.
     * Assume that there is no filtering by task type (todo/deadline/event)
     * @param isExcludingDone whether to exclude tasks already done
     * @param date the date to filter in deadlines before and events happening on
     */
    public void displayTasks(boolean isExcludingDone, LocalDate date) {
        ArrayList<Task> taskList = (ArrayList<Task>) this.taskList.clone();
        TaskList.displayTasks(taskList, isExcludingDone, date);
    }

    /**
     * Returns the number of tasks in this task list.
     * @return number of tasks in this task list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Save data to a given storage,
     * by first converting this list of task to storage-readable form.
     * @param storage the storage to save data to
     * @throws Storage.FileIOException if there is an IO error
     */
    public void saveData(Storage storage) throws Storage.FileIOException {
        StringBuilder data = new StringBuilder();
        for (Task task: this.taskList) {
            data.append(task.data()).append("\n");
        }
        storage.saveData(data.toString());
    }

    /**
     * Display the tasks that match the given input.
     * @param input the search parameter
     */
    public void showResults(String input) {
        ArrayList<Task> list = (ArrayList<Task>) this.taskList.clone();
        list.removeIf(task -> !task.containsString(input));
        TaskList.displayTasks(list, false, null);
    }
}
