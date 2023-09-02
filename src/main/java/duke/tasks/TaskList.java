package duke.tasks;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList extends ArrayList<Task> {
    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        super();
    }

    /**
     * Creates a TaskList object from a file.
     *
     * @param tasks The file containing the tasks.
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
     * Adds task to the TaskList.
     *
     * @param task the task to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.add(task);
    }

    /**
     * Deletes task from the TaskList.
     *
     * @param index the (index - 1) of the task to be deleted from the TaskList.
     */
    public void deleteTask(int index) {
        Task.taskCount--;
        this.remove(index - 1);
    }

    /**
     * Prints the tasks in the TaskList.
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