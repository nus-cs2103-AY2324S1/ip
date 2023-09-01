package smolbrain.task;

import java.io.IOException;
import java.util.ArrayList;

import smolbrain.Storage;
import smolbrain.Ui;

/**
 * List of tasks for chatbot.
 */
public class TaskList {

    private ArrayList<Task> tasklist;

    /**
     * Creates a tasklist with the given ArrayList of tasks.
     *
     * @param tasklist Tasklist using given ArrayList.
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Adds the given task into tasklist.
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasklist.add(task);
    }

    /**
     * Deletes the given task from tasklist.
     * @param id Id of task to delete.
     */
    public void deleteTask(int id) {
        tasklist.remove(id);
    }

    /**
     * Marks the given task in tasklist.
     * @param id Id of task to mark.
     */
    public void markTask(int id) {
        tasklist.get(id).mark();
    }

    /**
     * Unmarks the given task in tasklist.
     * @param id Id of task to unmark.
     */
    public void unmarkTask(int id) {
        tasklist.get(id).unmark();
    }

    /**
     * Returns the number of tasks in tasklist.
     *
     * @return Number of tasks in tasklist.
     */
    public int getSize() {
        return tasklist.size();
    }

    /**
     * Displays all the tasks in tasklist.
     *
     * @param ui Ui manager of chatbot.
     */
    public void displayTasks(Ui ui) {
        for (int i = 0; i < tasklist.size(); i++) {
            ui.showMessage((i + 1) + ". " + tasklist.get(i));
        }
    }

    /**
     * Returns the specified task in tasklist.
     * @param id Id of task to retrieve.
     * @return Task at the specified id.
     */
    public Task getTask(int id) {
        return tasklist.get(id);
    }

    /**
     * Saves the tasks in tasklist into save file.
     *
     * @param storage Storage manager of chatbot.
     */
    public void updateTasks(Storage storage) {
        try {
            if (tasklist.size() == 0) {
                storage.writeToFile("");
            } else {
                for (int i = 0; i < tasklist.size(); i++) {
                    if (i == 0) {
                        storage.writeToFile(tasklist.get(i).encode());
                    } else {
                        storage.appendToFile(System.lineSeparator() + tasklist.get(i).encode());
                    }
                }
            }
        } catch (IOException e) {
            new Ui().showError(e);
        }
    }

    /**
     * Finds and prints all the tasks in tasklist that contain the keyword.
     *
     * @param keyword Keyword to check.
     * @param ui Ui manager of chatbot.
     */
    public void findTasks(String keyword, Ui ui) {
        if (tasklist.size() > 0) {
            for (int i = 0; i < tasklist.size(); i++) {
                if (tasklist.get(i).contain(keyword)) {
                    ui.showMessage((i + 1) + ". " + tasklist.get(i));
                }
            }
        }
    }

}
