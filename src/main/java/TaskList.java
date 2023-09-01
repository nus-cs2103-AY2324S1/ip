import Events.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList encapsulates the task lists and operations related to the task lists.
 *
 * @author Sebastian Tay
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>(100);
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the saved data from file.
     *
     * @return
     */
    public boolean load() {
        if (storage.checkFileExists()) {
            //File does exist
            try {
                tasks = storage.retrieveTasks();
            } catch (FileNotFoundException e) {
                System.out.println("Unable to find file.");
            }
        } else {
            //File does not exist
            storage.addFile();
        }

        return true;
    }

    /**
     * Deletes the task at the given index of the list.
     *
     * @param taskIndex is null if encounter an exception.
     */
    public Task deleteTask(int taskIndex) {
        try {
            Task task = tasks.remove(taskIndex);
            storage.updateData(tasks, false);

            System.out.println("Noted. I have removed the following mission:");
            System.out.println(task);

            return task;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index! Please ensure you correctly key in your target index.");
        } catch (IOException e) {
            System.out.println("Unable to update file.");
        }

        return null;
    }

    /**
     * Adds the given task into the list of tasks.
     *
     * @param task
     */
    public boolean addTask(Task task) {
        if (task != null && tasks.add(task)) {
            System.out.println("added in mission:\n" + task);

            try {
                storage.updateData(tasks, true);
            } catch(IOException e) {
                System.out.println("Error writing to file.");
            }

            return true;
        } else {
            System.out.println("System is unable to accommodate the new mission");

            return false;
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param taskIndex
     */
    public void markAsDone(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex);

            if (task.isDone()) {
                //Events.Task already marked as done
                System.out.println("Mission has been completed previously.");
                return;
            }

            task.updateCompletionStatus();
            storage.updateData(tasks, false);

            System.out.println("Mission status updated! Mission completed successfully.");
            System.out.println(task);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index! Please ensure you correctly key in your target index.");
        } catch (IOException e) {
            System.out.println("Unable to update file.");
        }
    }

    /**
     * Marks the task at the given index as undone.
     *
     * @param taskIndex
     */
    public void markUndone(int taskIndex) {
        try {
            Task task = tasks.get(taskIndex);

            if (!(task.isDone())) {
                //task already marked as undone
                System.out.println("Mission is already marked as undone!");
                return;
            }

            task.updateCompletionStatus();
            storage.updateData(tasks, false);

            System.out.println("Mission status updated! Mission completion status reverted.");
            System.out.println(task);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index! Please ensure you correctly key in your target index.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to update file.");
        }
    }

    public void printList() {
        System.out.println("Missions:");

        tasks.forEach( task -> System.out.println(
                (tasks.indexOf(task) + 1) + "." + task
        ));
    }
}
