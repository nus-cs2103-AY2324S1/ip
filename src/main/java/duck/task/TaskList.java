package duck.task;

import java.util.ArrayList;

import duck.DuckException;

public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    // Initialise new TaskList with no tasks
    // For when no history is found
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.taskCount = 0;
    }

    // Initialise TaskList with existing tasks
    // For when history is available
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    // Returns the number of tasks in the list
    public int getTaskCount() {
        return this.taskCount;
    }

    // Appends a task to the list
    public void add(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public Task getTask(int index) throws DuckException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Error - invalid task number.");
        }
    }

    public void mark(int index) throws DuckException {
        Task currTask;
        try {
            currTask = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Error - invalid task number.");
        }
        currTask.mark();
    }

    public void unmark(int index) throws DuckException {
        Task currTask;
        try {
            currTask = tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Error - invalid task number.");
        }
        currTask.unmark();
    }

    public void delete(int index) throws DuckException{
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Error - invalid task number.");
        }
        taskCount--;
    }

    public ArrayList<Task> find(String keyword) throws DuckException {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (int i = 0; i < taskCount; i++) {
            if (tasks.get(i).containsKeyword(keyword)) {
                matchingTasks.add(tasks.get(i));
            }
        }
        return matchingTasks;
    }

    @Override
    public String toString() {
        String allTasks = "";
        for (int i = 0; i < taskCount; i++) {
            allTasks += "\n" + (i + 1) + ". " + tasks.get(i);
        }
        return allTasks;
    }
}


