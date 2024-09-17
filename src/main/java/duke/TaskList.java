package duke;

import duke.exception.InvalidInputException;
import duke.task.Task;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * A list to store the tasks.
 */
public class TaskList {
    /** A list to store the tasks. */
    private PriorityQueue<Task> tasks;

    /**
     * Constructs a new TaskList that have an empty list.
     */
    public TaskList() {
        this.tasks = new PriorityQueue<>(new TaskComparator());
    }

    /**
     * Constructs a new TaskList that contains the tasks in the provided list.
     *
     * @param strings List containing the tasks.
     */
    public TaskList(ArrayList<String> strings) {
        this.tasks = new PriorityQueue<>(new TaskComparator());
        Parser parser = new Parser(this);
        assert strings != null : "No lines in file";
        for (String s : strings) {
            try {
                parser.parseFromFile(s);
            } catch (InvalidInputException e) {
                continue;     // ignore
            }
        }
    }

    /**
     * Returns true if this list contains no elements.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds the task into the list.
     *
     * @param t Task to add.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Pops the head of the queue.
     */
    public Task poll() {
        return tasks.poll();
    }

    /**
     * Returns the ith task in the tasklist.
     */
    public Task get(int i) {
        ArrayList<Task> tempTasks = new ArrayList<>();
        Task t = tasks.poll();
        for (int j = 0; j < i; j++) {
            tempTasks.add(t);
            t = tasks.poll();
        }
        tempTasks.add(t);
        tasks.addAll(tempTasks);
        return t;
    }

    /**
     * Updates the tasklist.
     */
    public void update() {
        ArrayList<Task> tempTasks = new ArrayList<>();
        while (!tasks.isEmpty()) {
            Task t = tasks.poll();
            tempTasks.add(t);
        }
        tasks.addAll(tempTasks);
    }

    /**
     * Returns true if a task has the same rank.
     *
     * @param rank Rank of the task.
     */
    public boolean hasRank(int rank) {
        ArrayList<Task> tempTasks = new ArrayList<>();
        boolean isFound = false;
        while (!tasks.isEmpty()) {
            Task t = tasks.poll();
            if (t.getPriority() == rank) {
                isFound = true;
            }
            tempTasks.add(t);
        }
        tasks.addAll(tempTasks);
        return isFound;
    }

    /**
     * Returns the highest rank in the task list.
     */
    public int getHighestRank() {
        int rank = 0;
        ArrayList<Task> tempTasks = new ArrayList<>();
        while (!tasks.isEmpty()) {
            Task t = tasks.poll();
            if (t.getPriority() > rank) {
                rank = t.getPriority();
            }
            tempTasks.add(t);
        }
        tasks.addAll(tempTasks);
        return rank;
    }
    /**
     * Removes the task at the specific position.
     *
     * @param t The task to remove.
     */
    public void remove(Task t) {
        this.tasks.remove(t);
    }

    /**
     * Returns a list of string representation of all the tasks for file writing.
     */
    public ArrayList<String> toStringInFile() {
        ArrayList<String> strings = new ArrayList<>();
        assert !tasks.isEmpty() : "Nothing to write to file";
        for (Task t : tasks) {
            strings.add(t.convertToStringInFile());
        }
        return strings;
    }
}
