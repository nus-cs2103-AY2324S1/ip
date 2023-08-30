package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }
        return tasks.remove(index);
    }

    /**
     * Finds tasks containing the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markTaskAsUndone (int index) {
        tasks.get(index).markAsNotDone();
    }

    public Task get(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Other task-related methods can go here
}
