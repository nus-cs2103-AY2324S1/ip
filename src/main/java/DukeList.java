import java.util.ArrayList;
import java.io.IOException;




/**
 * DukeList stores tasks given by users when communicating with the main Duke chatbot.
 */
public class DukeList {
    //The ArrayList used to store all tasks accordingly.
    private ArrayList<Task> tasks;
    //The path directory to test.s
    private String path ;

    //The DataSaver class used to transmit and store information during the use of the Duke class
    private DataSaver middleman;

    /**
     * Instantiates a new DukeList.
     * @param path path directory for the test.s file.
     */
    public DukeList(String path) {
        this.path = path;
        try {
            this.middleman = new DataSaver(path);
            this.tasks = middleman.load();
        } catch (IOException | ClassNotFoundException e) {
            UI.errorMessage(e.getMessage());
        }
    }

    /**
     * Saves the data stored in the DukeList into the data.s file.
     */
    public void save() {
        middleman.store(this.tasks);
    }



    /**
     * Helps to store user todo tasks into the list.
     *
     * @param task takes in the task given by the user.
     */
    public void store(String task) {
        Task todo = new ToDo(task);
        tasks.add(todo);
        int index = tasks.size();
        UI.store(task, index);
    }

    /**
     * Helps to store user deadline tasks and deadlines into the list.
     *
     * @param task takes in the task given by the user.
     * @param by takes in the deadline of the deadline task.
     */
    public void store(String task, String by) {
        Task todo = new Deadline(task, by);
        tasks.add(todo);
        int index = tasks.size();
        UI.store(task, index);
    }

    /**
     * Helps to store user event tasks and start / end dates into the list.
     *
     * @param task takes in the task given by the user.
     * @param start takes in the start of the deadline task.
     * @param end takes in the end date of the store task.
     */
    public void store(String task, String start, String end) {
        Task todo = new Event(task, start, end);
        tasks.add(todo);
        int index = tasks.size();
        UI.store(task, index);
    }
    /**
     * Displays all contents of the list stored within an instance of DukeList.
     */
    public void display() {
        UI.display(tasks.toArray());
    }

    /**
     * Marks the i-th task as done.
     * @param i takes in the index of the task to be set as done.
     */
    public void mark(int i) {
        Task task = tasks.get(i - 1);
        task.done();

        UI.mark(task.toString());
    }

    /**
     * Marks the i-th task as undone.
     * @param i takes in the index of the task to be set as undone.
     */
    public void unmark(int i) {
        Task task = tasks.get(i - 1);
        task.undo();

        UI.unMark(task.toString());
    }

    /**
     * Removes the i-th task.
     * @param i takes in the index of the task to be removed.
     */
    public void delete(int i) {
        Task task = tasks.remove(i - 1);
        int index = tasks.size();

        UI.delete(task.toString(), index);
    }
}
