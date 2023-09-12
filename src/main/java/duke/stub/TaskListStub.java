package duke.stub;

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
     * Constructs a new TaskListStub.
     *
     * @param b If true, the list will be empty.
     * @param unmarked If true, all the tasks in the list will be unmarked.
     */
    public TaskListStub(boolean b, boolean unmarked) {
        if (b) {
            this.tasks = new PriorityQueue<>();
        } else {
            this.tasks = new PriorityQueue<>();
            Task t1 = new Task("task1", 0);
            Task t2 = new Task("task2", 0);
            Task t3 = new Task("task3", 0);
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

    @Override
    public void add(Task t) {
        tasks.add(t);
    }

    @Override
    public void remove(Task t) {
        tasks.remove(t);
    }

    @Override
    public ArrayList<String> toStringInFile() {
        return null;
    }
}
