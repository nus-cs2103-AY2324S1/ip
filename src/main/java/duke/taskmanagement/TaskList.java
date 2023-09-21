package duke.taskmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TaskList class manages a list of tasks and provides methods for performing operations on the list,
 * such as adding, deleting, marking as done, and finding tasks.
 */
public class TaskList {
    final String PATH = "./data/duke.txt";
    private List<Task> listOfTasks = new ArrayList<>();
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for TaskList class
     * @param ui The Ui object
     * @param list The list of task
     * @param storage The Storage object
     */
    public TaskList(Ui ui, List<Task> list, Storage storage) {
        this.ui = ui;
        this.listOfTasks = list;
        this.storage = storage;
    }

    /**
     * To delete a certain task in both the
     * list and text file when the user asks to
     * @param index The index of task to delete in both
     *              text file and list.
     */
    public String deleteTask(int index) {
        if (index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            int size = listOfTasks.size();
            storage.deleteLine(index);
            return ui.printRemoveTask(task.toString(), size);
        } else {
            return "Omg:(( index invalid.";
        }
    }

    /**
     * To add a todo task in both the
     * list and text file when the user asks to
     * @param task The todo task to add in both
     *              text file and list.
     */
    public String addToDoTask(Task task) {
        listOfTasks.add(task);
        int size = listOfTasks.size();
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
        return ui.printAddTask(task.toString(), size);
    }

    /**
     * To add a deadline task in both the
     * list and text file when the user asks to
     * @param task The deadline task to add in both
     *              text file and list.
     */
    public String addDeadlineTask(Task task) {
        listOfTasks.add(task);
        int size = listOfTasks.size();
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
        return ui.printAddTask(task.toString(), size);

    }

    /**
     * To add an event task in both the
     * list and text file when the user asks to
     * @param task The event task to add in both
     *              text file and list.
     */
    public String addEventTask(Task task) {
        listOfTasks.add(task);
        int size = listOfTasks.size();
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
        return ui.printAddTask(task.toString(), size);
    }


    /**
     * To mark a task to undone in
     * both text file and list.
     * @param index The index of task to mark as undone.
     */
    public String unmark(int index) {
        if (index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            task.unmark();
            listOfTasks.add(index - 1, task);
            storage.changeToUnDone(index);
            return ui.printMarkUndone(task.toString());
        } else {
            return "OMG INVALID INDEX! ;((";
        }
    }

    /**
     * To mark a task to done in
     * both text file and list.
     * @param index The index of task to mark as done.
     */
    public String mark(int index) {
        if (index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            task.markAsDone();
            listOfTasks.add(index - 1, task);
            storage.changeToDone(index);
            return ui.printMarkDone(task.toString());
        } else {
            return "OMG invalid index :(";
        }
    }

    /**
     * Finds tasks containing a specified string in their descriptions.
     *
     * @param str The string to search for.
     * @return A list of tasks containing the specified string.
     */
    public List<Task> find(String str) {
        List<Task> tmpList = new ArrayList<>();
        tmpList = listOfTasks;
        tmpList = tmpList.stream().filter(string -> string.contains(str)).collect(Collectors.toList());
        return tmpList;
    }

    /**
     * Returns the list of task.
     * @return A list of task.
     */
    public List<Task> getList() {
        return this.listOfTasks;
    }

    /**
     * Returns the size of the list of task.
     * @return The size of the list of task.
     */
    public int getListSize() {
        return this.listOfTasks.size();
    }
}
