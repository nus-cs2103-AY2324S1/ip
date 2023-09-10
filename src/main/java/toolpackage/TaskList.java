package toolpackage;

import dukepackage.DukeException;

import taskpackage.Task;
import taskpackage.ToDos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the tasklist in the Duke bot.
 * A TaskList object contains a list of tasks
 * represented by a ArrayList of Task objects.
 */
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
            throw new DukeException(" OOPS!!! There was an error saving data into storage.");
        }
    }

    /**
     * Instructs UI to print the list of tasks.
     *
     * @param ui UI to print the list of tasks.
     * @return String Representation of list.
     */
    public String printList(Ui ui) {
        return ui.printList(listOfTasks);
    }

    /**
     * Specifies which task in the task list to mark as complete or incomplete,
     * and subsequently mark them accordingly.
     *
     * @param id Task ID to toggle between complete and incomplete.
     * @param keyword Word to indicate whether the task was marked as complete or incomplete.
     * @param ui UI to print toggle status.
     * @return String Indicate whether toggling was successful.
     */
    public String toggleDone(String id, String keyword, Ui ui) {
        assert keyword.equals("mark") || keyword.equals("unmark") : "Incorrect keyword.";
        try {
            return this.listOfTasks.get(Integer.parseInt(id) - 1).toggleDone(keyword, ui);
        } catch (NumberFormatException e) {
            if (keyword.equals("mark")) {
                return " OOPS!!! Please indicate the task to mark in numbers.";
            } else {
                return " OOPS!!! Please indicate the task to unmark in numbers.";
            }
        } catch (IndexOutOfBoundsException e) {
            return " OOPS!!! Please indicate an appropriate index within the list range.";
        }
    }

    /**
     * Deletes task from task list.
     *
     * @param id Task ID to delete.
     * @param ui UI to print deletion status.
     * @return String Indicate whether deletion was successful.
     */
    public String removeItem(String id, Ui ui) {
        try {
            Task task = this.listOfTasks.remove(Integer.parseInt(id) - 1);
            return ui.removeItem(task, this.listOfTasks.size());
        } catch (NumberFormatException e) {
            return " OOPS!!! Please indicate the task to delete in numbers.";
        } catch (IndexOutOfBoundsException e) {
            return " OOPS!!! Please indicate an appropriate index within the list range.";
        }
    }

    /**
     * Adds task to the task list.
     *
     * @param task Task to be added to the task list.
     * @param ui UI to print addition status.
     * @return String Representation of task just added.
     */
    public String addItem(Task task, Ui ui) {
        this.listOfTasks.add(task);
        return ui.addItem(task, this.listOfTasks.size());
    }

    /**
     * Finds the tasks that contains the given word.
     *
     * @param word Keyword to find in the task.
     * @param ui UI to print tasks that contains the word.
     * @return String Reply to user with list of tasks found.
     */
    public String findTasks(String word, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : listOfTasks) {
            if (task.matchKeyword(word)) {
                matchingTasks.add(task);
            }
        }
        return ui.printMatchingTasks(matchingTasks);
    }

    /**
     * Reminds users the tasks that are due in 10 days or less.
     *
     * @param ui UI to print tasks that are due soon.
     * @return String Reply to user with list of tasks due soon.
     */
    public String provideReminders(Ui ui) {
        ArrayList<Task> urgentTasks = new ArrayList<>();
        for (Task task : listOfTasks) {
            if (task instanceof ToDos) {
                continue;
            }
            if (task.checkDeadline()) {
                urgentTasks.add(task);
            }
        }
        return ui.printUrgentTasks(urgentTasks);
    }
}
