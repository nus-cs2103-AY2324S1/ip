package sillybot.tasks;

import java.util.ArrayList;
import java.util.Scanner;

import sillybot.exceptions.IncompleteInputException;

/**
 * Represents a TaskList object that contains the tasks.
 */
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
    public TaskList(Scanner tasks) throws IncompleteInputException {
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
        this.remove(index - 1);
        Task.decreaseTaskCountByOne();
    }

    /**
     * Prints the tasks in the TaskList.
     *
     * @return String containing the tasks in the TaskList.
     */
    public String printTasks() {
        StringBuilder output;

        if (this.size() == 0) {
            output = new StringBuilder("Either you are a very productive person, or you have nothing to do.");
        } else {
            output = new StringBuilder("This is what you've been procrastinating about...");

            for (int i = 0; i < this.size(); i++) {
                output.append("\n").append(i + 1).append(". ").append(this.get(i));
            }
        }

        return output.toString();
    }

    /**
     * Find tasks that contain the keyword.
     *
     * @param keyword keyword to be searched.
     * @return String containing the tasks that contain the keyword.
     */
    public String findTasks(String keyword) {
        int count = 0;
        StringBuilder output = new StringBuilder("you actually have this:");

        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getDescription().contains(keyword)) {
                output.append("\n").append(i + 1).append(". ").append(this.get(i));
                count++;
            }
        }

        if (count == 0) {
            output = new StringBuilder("how you find that when you dont have that?");
        }

        return output.toString();
    }
}
