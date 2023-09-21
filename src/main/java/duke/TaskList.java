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
    private Storage storage;
    /**
     * Instantiates a new TaskList.
     * @param path path directory for the test.s file.
     */
    public TaskList(String path) {
        this.path = path;
        try {
            this.storage = new Storage(path);
            this.tasks = storage.loadTasks();
        } catch (IOException | ClassNotFoundException e) {
            Ui.errorMessage(e.getMessage());
        }
    }

    /**
     * Saves the data stored in the DukeList into the data.s file.
     */
    public void save() {
        storage.storeTasks(this.tasks);
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
     * Displays all contents of the list stored within an instance of TaskList.
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

        assert i <= tasks.size() : "Ehh? Make sure the index is valid";
        Task task = tasks.get(i - 1);
        task.done();

        return Ui.mark(task.toString());
    }

    /**
     * Marks the i-th task as undone.
     * @param i takes in the index of the task to be set as undone.
     */
    public String unmark(int i) {
        assert i <= tasks.size() : "Ehh? Make sure the index is valid";

        Task task = tasks.get(i - 1);
        task.undo();

        return Ui.unMark(task.toString());
    }

    /**
     * Removes the i-th task.
     * @param i takes in the index of the task to be removed.
     */
    public String delete(int i) {
        assert i <= tasks.size() : "Ehh? Make sure the index is valid";

        Task task = tasks.remove(i - 1);
        int index = tasks.size();
        return Ui.delete(task.toString(), index);
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
            return Ui.showMatchesMessage() + "\n" + Ui.display(foundTasks);
        }
    }
}
