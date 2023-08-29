package duke.taskmanagement;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    String PATH = "/Users/jjtiong/Desktop/ip/data/duke.txt";
    List<Task> ls = new ArrayList<>();
    Storage storage;
    Ui ui;

    public TaskList(Ui ui, List<Task> ls, Storage storage) {
        this.ui = ui;
        this.ls = ls;
        this.storage = storage;
    }

    public void deleteTask(int i) {
        Task task = ls.get(i - 1);
        ls.remove(i - 1);
        int size = ls.size();
        ui.printRemoveTask(task.toString(), size);
        storage.deleteLine(i);
    }

    public void addToDoTask(Task task) {
        ls.add(task);
        int size = ls.size();
        ui.printAddTask(task.toString(), size);
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
    }

    public void addDeadlineTask(Task task) {
        ls.add(task);
        int size = ls.size();
        ui.printAddTask(task.toString(), size);
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
    }

    public void addEventTask(Task task) {
        ls.add(task);
        int size = ls.size();
        ui.printAddTask(task.toString(), size);
        storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
    }

    public void unmark(int i) {
        Task task = ls.get(i - 1);
        ls.remove(i - 1);
        task.unmark();
        ls.add(i-1, task);
        ui.printMarkUndone(task.toString());
        storage.changeToUnDone(i);
        //storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
    }

    public void mark(int i) {
        Task task = ls.get(i-1);
        ls.remove(i-1);
        task.markAsDone();
        ls.add(i-1, task);
        ui.printMarkDone(task.toString());
        storage.changeToDone(i);
        //storage.writeToFile_exceptionThrown(PATH, task.saveToFileString());
    }

    public List<Task> getList() {
        return this.ls;
    }

    public int getListSize() {
        return this.ls.size();
    }

}
