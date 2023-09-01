package duke.stub;

import duke.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A stub for the TaskList class.
 */
public class TaskListStub extends TaskList {
    /** A list to store the tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskListStub.
     *
     * @param b If true, the list will be empty.
     * @param unmarked If true, all the tasks in the list will be unmarked.
     */
    public TaskListStub(boolean b, boolean unmarked) {
        if (b) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = new ArrayList<>();
            Task t1 = new Task("task1");
            Task t2 = new Task("task2");
            Task t3 = new Task("task3");
            if (!unmarked) {
                t1.markDone();
                t2.markDone();
                t3.markDone();
            }
            this.tasks.add(t1);
            this.tasks.add(t2);
            this.tasks.add(t3);
        }
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public int size() {
        return tasks.size();
    }

    @Override
    public Task get(int i) {
        return tasks.get(i);
    }

    @Override
    public void add(Task t) {
        tasks.add(t);
    }

    @Override
    public void remove(int i) {
        tasks.remove(i);
    }

    @Override
    public ArrayList<String> toStringInFile() {
        return null;
    }
}
