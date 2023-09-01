package duke;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;
    private int numOfTasks = 0;
    private int numOfCompletedTasks = 0;
    public enum TaskType {
        TASK, TODO, DEADLINE, EVENT
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
        this.numOfTasks++;
        if (t.isCompleted) {
            this.numOfCompletedTasks++;
        }
    }

    public Task get(int i) {
        if (i > -1 && i < numOfTasks) {
            return tasks.get(i);
        } else {
            return null;
        }
    }

    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    public int getNumOfCompletedTasks() {
        return this.numOfCompletedTasks;
    }

    public boolean isEmpty() {
        return this.numOfTasks == 0;
    }

    public boolean hasCompletedTasks() {
        return this.numOfCompletedTasks != 0;
    }

    public void incrementCompletedTasks() {
        this.numOfCompletedTasks++;
    }

    public void decrementCompletedTasks() {
        this.numOfCompletedTasks--;
    }

    public boolean checkDuplicates(String details) {
        for (Task t : tasks) {
            if (details.equals(t.getDetails())) {
                return true;
            }
        }
        return false;
    }

    public TaskType getTaskType(int i) {
        Task t = this.get(i);
        if (t instanceof ToDo) {
            return TaskType.TODO;
        } else if (t instanceof Deadline) {
            return TaskType.DEADLINE;
        } else if (t instanceof Event) {
            return TaskType.EVENT;
        } else {
            return TaskType.TASK;
        }
    }

    public void remove(Task t) {
        tasks.remove(t);
        this.numOfTasks--;
        if (t.isCompleted) {
            this.numOfCompletedTasks--;
        }
    }
}
