package duke;

import java.io.IOException;
import java.util.ArrayList;


/**
 * DukeList stores tasks given by users to the main Dukebot.
 */
public class TaskList {
    //The ArrayList used to store all tasks accordingly.
    private ArrayList<Task> tasks;
    //The path directory to test.s
    private String path;
    //The DataSaver class used to transmit and store information during the use of the Duke class
    private Storage middleman;

    /**
     * Instantiates a new TaskList.
     * @param path path directory for the test.s file.
     */
    public TaskList(String path) {
        this.path = path;
        try {
            this.middleman = new Storage(path);
            this.tasks = middleman.load();
        } catch (IOException | ClassNotFoundException e) {
            Ui.errorMessage(e.getMessage());
        }
    }

    /**
     * Saves the data stored in the DukeList into the data.s file.
     */
    public void save() {
        middleman.store(this.tasks);
    }



    /**
     * Helps to store user tasks.
     * @param task takes in the task given by the user.
     */
    public String store(Task task) {
        tasks.add(task);
        int index = tasks.size();
        return Ui.store(task.toString(), index);
    }


    /**
     * Displays all contents of the list stored within an instance of DukeList.
     */
    public String display() {
        if (tasks.size() == 0) {
            return Ui.emptyListMessage();
        } else {
            Task[] taskArray = tasks.toArray(new Task[0]);
            return Ui.display(taskArray);
        }
    }

    /**
     * Marks the i-th task as done.
     * @param i takes in the index of the task to be set as done.
     */
    public String mark(int i) {
        if (this.assertEquality(i)) {
            Task task = tasks.get(i - 1);
            task.done();

            return Ui.mark(task.toString());
        } else {
            return Ui.indexError(tasks.size());
        }
    }

    /**
     * Marks the i-th task as undone.
     * @param i takes in the index of the task to be set as undone.
     */
    public String unmark(int i) {
        if (this.assertEquality(i)) {
            Task task = tasks.get(i - 1);
            task.undo();

            return Ui.unMark(task.toString());
        } else {
            return Ui.indexError(tasks.size());
        }
    }

    /**
     * Removes the i-th task.
     * @param i takes in the index of the task to be removed.
     */
    public String delete(int i) {
        if (this.assertEquality(i)) {
            Task task = tasks.remove(i - 1);
            int index = tasks.size();

            return Ui.delete(task.toString(), index);
        } else {
            return Ui.indexError(tasks.size());
        }
    }

    /**
     * Searches and prints all task that contains the keyword substring.
     * @param keyword String used for searching
     */
    public String search(String keyword) {
        ArrayList<Task> taskMatches = new ArrayList<>();
        Task[] taskArray = tasks.toArray(new Task[0]);

        for (int i = 0; i < taskArray.length; i++) {
            if (taskArray[i].getTask().contains(keyword)) {
                taskMatches.add(taskArray[i]);
            }
        }

        Task[] foundTasks = taskMatches.toArray(new Task[0]);

        if (foundTasks.length == 0) {
            return Ui.emptyListMessage();
        } else {
            return Ui.showMatchesMessage() + "\n"+ Ui.display(foundTasks);
        }
    }

    /**
     * Checks if the index is within the task size.
     * @param index the index of a specific task
     * @return whether the index is within the taskList size.
     */
    private boolean assertEquality(int index) {
        return index <= tasks.size() && index > 0;
    }
}
