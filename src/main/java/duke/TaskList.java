package duke;

import java.util.ArrayList;

/**
 * TaskList class storing all the tasks in an ArrayList.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i).toString());
        }
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void changeStatusOfTask(int index) {
        this.tasks.get(index).changeStatus();
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Finds task objects that has matching keyword.
     * @param keyword keyword in String look for
     * @return an ArrayList of task that has matching keyword
     */
    public ArrayList<Task> findTaskUsingKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskDescription.contains(keyword)) {
                matchingTasks.add(tasks.get(i));
            }
        }

        return matchingTasks;
    }

}
