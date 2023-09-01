package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    /** List of tasks */
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.list = new ArrayList<>();
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
        this.list.add(s);
    }

    /**
     * Returns task at specific index.
     *
     * @param index Index from which Task is to be obtained.
     * @return Task object at index in the TaskList object.
     */
    public Task getTaskAt(int index) {
        return this.list.get(index);
    }

    /**
     * Returns number of tasks in the list of tasks.
     *
     * @return Number of tasks in the TaskList object.
     */
    public int getNumberOfTasks() {
        return this.list.size();
    }

    /**
     * Deletes task at specific index.
     *
     * @param index Index from which Task is to be deleted.
     */
    public void deleteTaskAt(int index) {
        this.list.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            s.append((i + 1) + "." + this.list.get(i));
            if (i < this.list.size() - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TaskList)) {
            return false;
        } else {
            TaskList taskList = (TaskList) o;
            if (taskList.getNumberOfTasks() == this.getNumberOfTasks()) {
                for (int i = 0; i < this.getNumberOfTasks(); i++) {
                    if (!(taskList.getTaskAt(i).equals(this.getTaskAt(i)))) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }
}
