package duke.taskmanagement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    final String PATH = "/Users/jjtiong/Desktop/ip/data/duke.txt";
    private List<Task> ls = new ArrayList<>();
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for TaskList class
     * @param ui The Ui object
     * @param ls The list of task
     * @param storage The Storage object
     */
    public TaskList(Ui ui, List<Task> ls, Storage storage) {
        this.ui = ui;
        this.ls = ls;
        this.storage = storage;
    }

    /**
     * To delete a certain task in both the
     * list and text file when the user asks to
     * @param index The index of task to delete in both
     *              text file and list.
     */
    public void deleteTask(int index) {
        Task task = ls.get(index - 1);
        ls.remove(index - 1);
        int size = ls.size();
        ui.printRemoveTask(task.toString(), size);
        storage.deleteLine(index);
    }

    /**
     * To add a todo task in both the
     * list and text file when the user asks to
     * @param task The todo task to add in both
     *              text file and list.
     */
    public void addToDoTask(Task task) {
        ls.add(task);
        int size = ls.size();
        ui.printAddTask(task.toString(), size);
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
    }

    /**
     * To add a deadline task in both the
     * list and text file when the user asks to
     * @param task The deadline task to add in both
     *              text file and list.
     */
    public void addDeadlineTask(Task task) {
        ls.add(task);
        int size = ls.size();
        ui.printAddTask(task.toString(), size);
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
    }

    /**
     * To add an event task in both the
     * list and text file when the user asks to
     * @param task The event task to add in both
     *              text file and list.
     */
    public void addEventTask(Task task) {
        ls.add(task);
        int size = ls.size();
        ui.printAddTask(task.toString(), size);
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
    }

    /**
     * To mark a task to undone in
     * both text file and list.
     * @param index The index of task to mark as undone.
     */
    public void unmark(int index) {
        Task task = ls.get(index - 1);
        ls.remove(index - 1);
        task.unmark();
        ls.add(index-1, task);
        ui.printMarkUndone(task.toString());
        storage.changeToUnDone(index);
    }

    /**
     * To mark a task to done in
     * both text file and list.
     * @param index The index of task to mark as done.
     */
    public void mark(int index) {
        Task task = ls.get(index-1);
        ls.remove(index-1);
        task.markAsDone();
        ls.add(index-1, task);
        ui.printMarkDone(task.toString());
        storage.changeToDone(index);
    }

    public List<Task> find(String str) {
        List<Task> tmpList = new ArrayList<>();
        tmpList = ls;
        tmpList = tmpList.stream().filter(string -> string.contains(str)).collect(Collectors.toList());
        return tmpList;
    }

    /**
     * Returns the list of task.
     * @return A list of task.
     */
    public List<Task> getList() {
        return this.ls;
    }

    /**
     * Returns the size of the list of task.
     * @return The size of the list of task.
     */
    public int getListSize() {
        return this.ls.size();
    }

}
