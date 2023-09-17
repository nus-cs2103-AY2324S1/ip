package duke.task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskList to store all the tasks required for the particular user
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructor when taskList is first initially created
     */
    public TaskList() {
        super();
    }

    /**
     * TaskList when the user is not using the bot for the first time
     * Used to start the bot back with the saved data
     * 
     * @param sc is used to read the contents in the saved file
     *           so that the user is able to continue changing
     *           the taskList such as adding, removing and editing task details
     */
    public TaskList(Scanner sc) {
        super();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Task task = Task.createTaskFromSavedData(line);
            this.add(task);
        }
    }

    /**
     * Add a task into the taskList
     * 
     * @param task is the task to be added
     */
    public void addTask(Task task) {
        assert task != null : "task should not be null";
        this.add(task);
    }

    /**
     * Removes a task from a taskList
     * 
     * @param taskNumber is the order of the task that is in the
     *                   taskList
     * @return the task to be deleted
     */
    public Task deleteTask(int taskNumber) {
        assert taskNumber > 0 : "taskNumber should be more than 0";
        return this.remove(taskNumber - 1);
    }

    /**
     * Prints out the current taskList
     */
    public String printList() {
        String output = "Pika Pika!! Here are the tasks in your list:\n";
        System.out.println("Here are the tasks in your list:");
        for (int length = 1; length < this.size() + 1; length += 1) {
            output += length + "." + this.get(length - 1) + "\n";
            System.out.println(length + "." + this.get(length - 1));
        }
        return output;
    }

    public String findTaskFromTaskList(String keyword) {
        assert keyword != null : "keyword should not be null";
        int count = 1;
        String output = "";
        for (Task task : this) {
            if (task.toString().contains(keyword)) {
                output += count + ": " + task + "\n";
                count += 1;
            }
        }
        return output;
    }
}
