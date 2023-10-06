package taskmate.tools;

import java.util.ArrayList;
import java.util.HashMap;

import taskmate.exceptions.FileCorruptedException;
import taskmate.exceptions.InvalidByException;
import taskmate.exceptions.InvalidDeadlineUpdateException;
import taskmate.exceptions.InvalidEventUpdateException;
import taskmate.exceptions.InvalidFromException;
import taskmate.exceptions.InvalidToException;
import taskmate.exceptions.InvalidTodoUpdateException;
import taskmate.exceptions.NoDataException;
import taskmate.exceptions.TaskNotFoundException;
import taskmate.tools.tasks.Deadline;
import taskmate.tools.tasks.Event;
import taskmate.tools.tasks.Task;
import taskmate.tools.tasks.Todo;

/**
 * This TaskList class represents the list of undeleted tasks that the user has
 */
public class TaskList {

    private final ArrayList<Task> tasks = new ArrayList<Task>();
    private int numTotalTasks = 0;
    private int numIncompleteTasks = 0;
    private final int indexOfTaskName = 7; // start index of task name (e.g. index of "read book" in "[T][X] read book")

    public TaskList() {
    }

    /**
     * Constructs a TaskList object with the contents of the saved task file read from a Storage object.
     * @param contentsFromDisk A String object containing the saved task file contents
     * @throws NoDataException Thrown when the task file is empty
     */
    public TaskList(String contentsFromDisk) throws NoDataException, FileCorruptedException {
        if (contentsFromDisk.isEmpty()) {
            throw new NoDataException();
        }
        String[] allTasksAsString = contentsFromDisk.split("\\n");
        for (String taskAsString: allTasksAsString) {
            String taskType = getSavedTaskType(taskAsString);
            boolean taskIsDone = getSavedTaskIsDone(taskAsString);
            Task newTask;
            switch (taskType) {
            case "T": // To-do task
                newTask = createTodoTaskFromSavedTask(taskAsString, taskIsDone);
                break;
            case "D": // Deadline task
                newTask = createDeadlineTaskFromSavedTask(taskAsString, taskIsDone);
                break;
            case "E": // Event task
                newTask = createEventTaskFromSavedTask(taskAsString, taskIsDone);
                break;
            default: // Invalid event. Happens when the saved file is corrupted/tampered with
                throw new FileCorruptedException("Invalid task: " + taskAsString);
            }
            this.addTask(newTask, taskIsDone);
            System.out.println("Found and loaded saved task: " + taskAsString);
        }
    }

    /**
     * @return an integer representing the number of incomplete tasks in the task-list
     */
    public int getNumIncompleteTasks() {
        return numIncompleteTasks;
    }

    private String getSavedTaskType(String taskAsString) {
        int indexOfTaskType = 1; // The index of each line where task type is located (e.g. 'T' in "[T][X] read book")
        return taskAsString.substring(indexOfTaskType, indexOfTaskType + 1);
    }

    private boolean getSavedTaskIsDone(String taskAsString) {
        char markSymbol = 'X';
        int indexOfX = 4; // The index of each line where 'X' is located (e.g. 'X' in "[T][X] read book")
        return taskAsString.charAt(indexOfX) == markSymbol;
    }

    private Todo createTodoTaskFromSavedTask(String taskAsString, boolean taskIsDone) {
        String name = taskAsString.substring(indexOfTaskName);
        return new Todo(name, taskIsDone);
    }

    private Deadline createDeadlineTaskFromSavedTask(String taskAsString, boolean taskIsDone) {
        String delimiter = "(by: ";
        int indexOfByParam = taskAsString.lastIndexOf(delimiter);
        String name = taskAsString.substring(indexOfTaskName, indexOfByParam);
        String by = taskAsString.substring(indexOfByParam + delimiter.length(), taskAsString.length() - 1);
        return new Deadline(name, by, taskIsDone);
    }

    private Event createEventTaskFromSavedTask(String taskAsString, boolean taskIsDone) {
        String delimiter = "(from: ";
        String delimiter2 = " to: ";
        int indexOfFromParam = taskAsString.lastIndexOf(delimiter);
        int indexOfToParam = taskAsString.lastIndexOf(delimiter2);
        String name = taskAsString.substring(indexOfTaskName, indexOfFromParam);
        String from = taskAsString.substring(indexOfFromParam + delimiter.length(), indexOfToParam);
        String to = taskAsString.substring(indexOfToParam + delimiter2.length(), taskAsString.length() - 1);
        return new Event(name, from, to, taskIsDone);
    }

    /**
     * Adds a Task object t
     * @param t A Task object representing the task to be added
     */
    public void addTask(Task t) {
        this.tasks.add(t);
        numTotalTasks++;
        numIncompleteTasks++;
    }

    /**
     * Adds a Task object t and marks it as done if isDone == true
     * @param t A Task object representing the task to be added
     * @param isDone A boolean variable representing if the task to be added is completed
     */
    public void addTask(Task t, boolean isDone) {
        this.addTask(t);
        if (!isDone) {
            return;
        }

        assert this.tasks.contains(t);
        try {
            this.markAsDone(t);
        } catch (TaskNotFoundException e) {
            // Assertion above guarantees that t can be found within tasks so no TaskNotFoundException will be thrown
            assert false;
        }
    }

    /**
     * Removes a Task object t
     * @param i An int variable representing the 1-based index of the task to be removed
     * @throws TaskNotFoundException Thrown when `i` is outside of the range [max(0, tasks.size()), tasks.size()]
     */
    public void removeTask(int i) throws TaskNotFoundException {
        boolean indexOutOfBounds = i > tasks.size() | i < 0;
        if (indexOutOfBounds) {
            throw new TaskNotFoundException();
        }
        Task t = tasks.get(i);
        tasks.remove(i);
        if (t.getIsDone()) {
            numIncompleteTasks--;
        }
        numTotalTasks--;
    }

    /**
     * Marks an existing task as done
     * @param t A Task object representing the task to be marked as done
     * @throws TaskNotFoundException Thrown when t cannot be found
     */
    public void markAsDone(Task t) throws TaskNotFoundException {
        if (!this.tasks.contains(t)) {
            throw new TaskNotFoundException();
        }

        if (!t.getIsDone()) {
            t.markAsDone();
            numIncompleteTasks--;
        }
    }

    /**
     * Unmarks an existing task from done to not done
     * @param t A Task object representing the task to be unmarked as done
     * @throws TaskNotFoundException Thrown when t cannot be found
     */
    public void markAsNotDone(Task t) throws TaskNotFoundException {
        if (!this.tasks.contains(t)) {
            throw new TaskNotFoundException();
        }
        if (t.getIsDone()) {
            t.markAsNotDone();
            numIncompleteTasks++;
        }
    }

    /**
     * Updates attributes of a task to a new value
     * @param taskToUpdate a Task object representing the task to be updated
     * @param changes a Hashmap that contains the attribute changes to be made to taskToUpdate
     * @return a HashMap containing the successful updates to taskToUpdate
     * @throws TaskNotFoundException thrown when taskToUpdate is not in the list of undeleted tasks
     * @throws InvalidTodoUpdateException thrown when taskToUpdate is a Todo task but the update is invalid
     * @throws InvalidDeadlineUpdateException thrown when taskToUpdate is a Deadline task but the update is invalid
     * @throws InvalidEventUpdateException thrown when taskToUpdate is a Event task but the update is invalid
     */
    public HashMap<String, String> updateTask(Task taskToUpdate, HashMap<String, String> changes) throws
            TaskNotFoundException, InvalidTodoUpdateException, InvalidDeadlineUpdateException, InvalidByException,
            InvalidToException, InvalidEventUpdateException, InvalidFromException {
        if (!this.tasks.contains(taskToUpdate)) {
            throw new TaskNotFoundException();
        }
        return taskToUpdate.update(changes);
    }

    /**
     * @return an ArrayList of tasks representing the user's undeleted tasks
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public int getNumTotalTasks() {
        return this.numTotalTasks;
    }

    /**
     * Returns the task specified by an index i (Note: one-indexed)
     * @param i an int representing the one-indexed task in the user's task list
     * @return a Task object at the one-indexed i in the user's task list
     * @throws TaskNotFoundException thrown when the value of i is not between 1 (inclusive) and # of tasks (inclusive)
     */
    public Task getTask(int i) throws TaskNotFoundException {
        boolean indexOutOfBounds = i > tasks.size() | i < 0;
        if (indexOutOfBounds) {
            throw new TaskNotFoundException();
        }
        return tasks.get(i);
    }

    /**
     * Returns a String which contains all task-related information to be saved to the disk.
     * @return A String object representing all data about the user's tasks
     */
    public String formatAllTasksForSaving() {
        StringBuilder returnString = new StringBuilder();
        for (Task t : getAllTasks()) {
            returnString.append(t.formatTaskForSaving());
            returnString.append("\n");
        }
        return returnString.toString();
    }
}
