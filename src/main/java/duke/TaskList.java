package duke;

import Command.InvalidCommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    private static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    /**
     * Constructor for the TaskList class.
     *
     * @param storedData Data stored in a file.
     */
    public TaskList(ArrayList<String> storedData) {
        tasks = new ArrayList<>();
        loadState(storedData);
    }

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the total number of tasks.
     *
     * @return Total number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @return Task added to the list.
     */
    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Removes a task to the list.
     *
     * @param index Index of the task to remove.
     * @return Task removed from the list.
     * @throws InvalidCommandException If the provided index is out of range.
     */
    public Task remove(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.remove(index);
        return task;
    }


    /**
     * Prints all tasks in the list.
     */
    public void printContents() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }

    /**
     * Return a list tasks on a given date.
     *
     * @param date Date of interest to search for.
     * @return List of tasks on the given date.
     */
    public ArrayList<Task> getTasksOn(LocalDate date) {
        ArrayList<Task> tasksOn = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).isOnDate(date)) {
                tasksOn.add(this.tasks.get(i));
            }
        }
        return tasksOn;
    }

    /**
     * Return a list tasks on a given date.
     *
     * @param keyword The keyword to match with in the task name.
     * @return List of tasks on the given date.
     */
    public ArrayList<Task> getTasksContainingKeyword(String keyword) {
        ArrayList<Task> tasksOn = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).containsKeyword(keyword)) {
                tasksOn.add(this.tasks.get(i));
            }
        }
        return tasksOn;
    }

    /**
     * Marks a task to in the list as done.
     *
     * @param index Index of the task to mark.
     * @return Task marked in the list.
     * @throws InvalidCommandException If the provided index is out of range.
     */
    public Task mark(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks a task to in the list as done.
     *
     * @param index Index of the task to unmark.
     * @return Task unmarked in the list.
     * @throws InvalidCommandException If the provided index is out of range.
     */
    public Task unmark(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Saves the tasks to a file.
     *
     * @param storage storage object to save the tasks.
     */
    public void saveState(Storage storage) throws StorageException {
        ArrayList<String> stringRepresentation = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            stringRepresentation.add(this.tasks.get(i).toSaveStateString());
        }
        storage.saveData(stringRepresentation);
    }

    /**
     * Loads the tasks from a file.
     *
     * @param storedData data stored in a file.
     */
    private void loadState(ArrayList<String> storedData) {
        for (int i = 0; i < storedData.size(); i++) {
            String[] taskArray = storedData.get(i).split(" / ");
            Task task;

            if (taskArray[0].equals("todo")) {
                task = new ToDo(taskArray[2]);
            } else if (taskArray[0].equals("deadline")) {
                task = new Deadline(taskArray[2], LocalDateTime.parse(taskArray[3], dateTimeInputFormatter));
            } else {
                task = new Event(taskArray[2], LocalDateTime.parse(taskArray[3], dateTimeInputFormatter),
                        LocalDateTime.parse(taskArray[4], dateTimeInputFormatter));
            }

            if (taskArray[1].equals("1")) {
                task.markAsDone();
            }
            this.tasks.add(task);
        }
        System.out.println("Successfully loaded saved state");
    }

    /**
     * Checks if a index is valid.
     *
     * @param index Index of the task.
     * @return A boolean representation of whether the index is valid.
     */
    private boolean isTaskValid(int index) {
        return index >= 0 && index < this.tasks.size();
    }
}
