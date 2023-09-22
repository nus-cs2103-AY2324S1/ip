package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.tasks.Task;

/**
 * Implementation for the task list
 */
public class TaskList {

    /** List of tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a list of tasks with tasks passed as arguments.
     *
     * @param args Tasks to be added to the TaskList object.
     * @return TaskList object.
     */
    public static TaskList of(Task ... args) {
        TaskList taskList = new TaskList();
        for (Task task: args) {
            taskList.addToList(task);
        }
        return taskList;
    }

    /**
     * Adds task to list of tasks.
     *
     * @param s Task to add to the TaskList object.
     */
    public void addToList(Task s) {
        this.tasks.add(s);
    }

    /**
     * Returns task at specific index.
     *
     * @param index Index from which Task is to be obtained.
     * @return Task object at index in the TaskList object.
     */
    public Task getTaskAt(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns number of tasks in the list of tasks.
     *
     * @return Number of tasks in the TaskList object.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Deletes task at specific index.
     *
     * @param index Index from which Task is to be deleted.
     */
    public void deleteTaskAt(int index) {
        this.tasks.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            s.append((i + 1) + "." + this.tasks.get(i));
            if (i < this.tasks.size() - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    /**
     * Returns task list filtered using the keyword.
     *
     * @param userInput User input in String format.
     * @return Filtered TaskList.
     */
    public TaskList getFilteredTaskList(String userInput) {
        String keyword = userInput.substring(5);
        return new TaskList(tasks.stream()
                .filter(task -> task.containsKeyword(keyword))
                .collect(Collectors.toCollection(ArrayList<Task>::new)));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TaskList)) {
            return false;
        } else {
            TaskList taskList = (TaskList) o;
            if (taskList.getNumberOfTasks() != this.getNumberOfTasks()) {
                return false;
            }
            for (int i = 0; i < this.getNumberOfTasks(); i++) {
                if (!(taskList.getTaskAt(i).equals(this.getTaskAt(i)))) {
                    return false;
                }
            }
            return true;
        }
    }
}
