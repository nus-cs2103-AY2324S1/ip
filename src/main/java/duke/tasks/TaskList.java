package duke.tasks;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList extends ArrayList<Task> {
    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Constructor for TaskList.
     *
     * @param tasks Scanner object containing tasks.
     */
    public TaskList(Scanner tasks) {
        super();
        while (tasks.hasNextLine()) {
            String line = tasks.nextLine();
            Task task = Task.createTaskFromFile(line);
            this.add(task);
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index (Index - 1) of the task to be deleted.
     */
    public Task deleteTask(int index) {
        Task.taskCount--;
        return this.remove(index - 1);
    }

    /**
     * Prints all the tasks in the list.
     */
    public void printTasks() {
        if (this.size() == 0) {
            System.out.println("Either you are a very productive person, or you have nothing to do.");
        } else {
            System.out.println("This is what you've been procrastinating about...");
            for (int i = 0; i < this.size(); i++) {
                System.out.println((i + 1) + ". " + this.get(i));
            }
        }
    }
}
