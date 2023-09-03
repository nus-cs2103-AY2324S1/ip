package duke.main;

import duke.exceptions.InvalidArgumentException;
import duke.main.UI;
import duke.tasks.Task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list;
    private int index;
    private int numOfTasks;
    private UI ui;

    public TaskManager() {
        this.list = new ArrayList<>();
        this.index = 0;
        this.numOfTasks = 0;
        this.ui = new UI();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.list = tasks;
        this.numOfTasks = this.list.size();
        this.index = this.numOfTasks - 1;
        this.ui = new UI();
    }


    public void add (Task task) {
        this.list.add(task);
        this.numOfTasks += 1;
        String taskName = task.getTaskName();
        ui.addTask(taskName, numOfTasks);
    }

    public void list() {
        ui.displayList(list, numOfTasks);
    }

    public void mark(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + "duke.tasks.");
        }
        index -= 1; // since 0 indexed
        Task task = list.get(index);
        task.mark();
        ui.markTask(task.getTaskName());
    }

    public void unmark(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + " duke.tasks.");
        }
        index -= 1; // since 0 indexed
        Task task = list.get(index);
        task.unmark();
        ui.unMarkTask(task.getTaskName());
    }

    public void delete(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + " duke.tasks.");
        }
        numOfTasks -= 1;
        Task removedTask = list.get(index);
        list.remove(index);
        ui.deleteTask(removedTask.getTaskName(), numOfTasks);
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
