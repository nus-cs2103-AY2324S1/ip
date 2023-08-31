package toolpackage;

import dukepackage.DukeException;

import taskpackage.Task;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    /**
     * Constructs a new and empty TaskList.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList, given a existing list of tasks.
     *
     * @param listOfTasks List of tasks to fill the task list with.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Saves tasks into storage.
     *
     * @param fileWriter File writer object to write tasks into storage.
     * @return boolean Indicate whether save was successful.
     * @throws DukeException if there is an error writing data to the file.
     */
    public boolean saveStorage(FileWriter fileWriter) throws DukeException {
        try {
            for (Task listOfTask : listOfTasks) {
                fileWriter.write(listOfTask.addToStorage());
            }
            return true;
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! There was an error saving data into storage.");
        }
    }

    /**
     * Instructs UI to print the list of tasks.
     *
     * @param ui UI to print the list of tasks.
     */
    public void printList(Ui ui) {
        ui.printList(listOfTasks);
    }

    /**
     * Specifies which task in the task list to mark as complete or incomplete,
     * and subsequently mark them accordingly.
     *
     * @param id Task ID to toggle between complete and incomplete.
     * @param keyword Word to indicate whether the task was marked as complete or incomplete.
     * @param ui UI to print toggle status.
     * @return boolean Indicate whether toggling was successful.
     */
    public boolean toggleDone(String id, String keyword, Ui ui) {
        try {
            this.listOfTasks.get(Integer.parseInt(id) - 1).toggleDone(keyword, ui);
            return true;
        } catch (NumberFormatException e) {
            if (keyword.equals("mark")) {
                System.out.println("☹ OOPS!!! Please indicate the task to mark in numbers.");
            } else {
                System.out.println("☹ OOPS!!! Please indicate the task to unmark in numbers.");
            }
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate an appropriate index within the list range.");
            return false;
        }
    }

    /**
     * Deletes task from task list.
     *
     * @param id Task ID to delete.
     * @param ui UI to print deletion status.
     * @return boolean Indicate whether deletion was successful.
     */
    public boolean removeItem(String id, Ui ui) {
        try {
            Task task = this.listOfTasks.remove(Integer.parseInt(id) - 1);
            ui.removeItem(task, this.listOfTasks.size());
            return true;
        } catch (NumberFormatException e) {
            System.out.println("☹ OOPS!!! Please indicate the task to delete in numbers.");
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! Please indicate an appropriate index within the list range.");
            return false;
        }
    }

    /**
     * Adds task to the task list.
     *
     * @param task Task to be added to the task list.
     * @param ui UI to print addition status.
     */
    public void addItem(Task task, Ui ui) {
        this.listOfTasks.add(task);
        ui.addItem(task, this.listOfTasks.size());
    }

    /**
     * Finds the tasks that contains the given word.
     *
     * @param word Keyword to find in the task.
     * @param ui UI to print tasks that contains the word.
     */
    public void findTasks(String word, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : listOfTasks) {
            if (task.matchKeyword(word)) {
                matchingTasks.add(task);
            }
        }
        ui.printMatchingTasks(matchingTasks);
    }
}
