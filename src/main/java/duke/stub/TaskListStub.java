package duke.stub;

import duke.TaskComparator;
import duke.TaskList;
import duke.task.Task;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * A stub for the TaskList class.
 */
public class TaskListStub extends TaskList {
    /** A list to store the tasks. */
    private PriorityQueue<Task> tasks;

    /**
     * Constructs a new empty TaskListStub.
     */
    public TaskListStub() {
        this.tasks = new PriorityQueue<>(new TaskComparator());
    }

    /**
     * Constructs a new TaskListStub.
     *
     * @param isEmpty If true, the list will be empty.
     * @param isUnmarked If true, all the tasks in the list will be unmarked.
     */
    public TaskListStub(boolean isEmpty, boolean isUnmarked) {
        if (isEmpty) {
            this.tasks = new PriorityQueue<>(new TaskComparator());
        } else {
            this.tasks = new PriorityQueue<>(new TaskComparator());
            Task t1 = new Task("task1", 3);
            Task t2 = new Task("task2", 2);
            Task t3 = new Task("task3", 1);
            if (!isUnmarked) {
                t1.markDone();
                t2.markDone();
                t3.markDone();
            }
            this.tasks.add(t1);
            this.tasks.add(t2);
            this.tasks.add(t3);
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
