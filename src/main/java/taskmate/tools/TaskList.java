package taskmate.tools;

import java.util.ArrayList;

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

    public TaskList() {
    }

    /**
     * Constructs a TaskList object with the contents of the saved task file read from a Storage object.
     * @param contentsFromDisk A String object containing the saved task file contents
     * @throws NoDataException Thrown when the task file is empty
     */
    public TaskList(String contentsFromDisk) throws NoDataException {
        if (contentsFromDisk.isEmpty()) {
            throw new NoDataException();
        }

        String[] lines = contentsFromDisk.split("\\n");
        boolean taskIsDone;
        Task newTask;
        for (String line: lines) {
            String taskType = line.substring(1, 2);
            taskIsDone = line.charAt(4) == 'X';

            if (taskType.equals("T")) {
                // To-do task
                String name = line.substring(7);
                newTask = new Todo(name, taskIsDone);
                this.addTask(newTask, taskIsDone);
            } else if (taskType.equals("D")) {
                // Deadline
                String delimiter = "(by: ";
                int indexOfByParam = line.lastIndexOf(delimiter);
                String name = line.substring(7, indexOfByParam);
                String by = line.substring(indexOfByParam + delimiter.length(), line.length() - 1);
                newTask = new Deadline(name, by, taskIsDone);
                this.addTask(newTask, taskIsDone);
            } else if (taskType.equals("E")) {
                // Event
                String delimiter = "(from: ";
                String delimiter2 = " to: ";
                int indexOfFromParam = line.lastIndexOf(delimiter);
                int indexOfToParam = line.lastIndexOf(delimiter2);
                String name = line.substring(7, indexOfFromParam);
                String from = line.substring(indexOfFromParam + delimiter.length(), indexOfToParam);
                String to = line.substring(indexOfToParam + delimiter2.length(), line.length() - 1);
                newTask = new Event(name, from, to, taskIsDone);
                this.addTask(newTask, taskIsDone);
            } else {
                // Invalid event
                System.out.println("Invalid task: " + line);
            }

            System.out.println("Found and loaded saved task: " + line);
        }
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
        }
    }

    /**
     * Removes a Task object t
     * @param i An int variable representing the 1-based index of the task to be removed
     * @throws TaskNotFoundException Thrown when i is outside of the range [max(0, tasks.size()), tasks.size()]
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

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public int getNumTotalTasks() {
        return this.numTotalTasks;
    }

    public int getNumIncompleteTasks() {
        return this.numIncompleteTasks;
    }

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
