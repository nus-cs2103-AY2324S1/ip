package duke;

import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list;
    private int index;
    private int numOfTasks;

    public TaskManager() {
        this.list = new ArrayList<>();
        this.index = 0;
        this.numOfTasks = 0;
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.list = tasks;
        this.numOfTasks = this.list.size();
        this.index = this.numOfTasks - 1;
    }


    public void add (Task task) {
        this.list.add(task);
        this.numOfTasks += 1;
        UI.printLine();
        System.out.println("Got it. I've added the task:\n" + task);
        if (numOfTasks != 1) {
            System.out.println("Now you have " + numOfTasks + " duke.tasks in your list, just like how I have 5 Ballon d'Ors.");
        } else {
            System.out.println("Now you have " + numOfTasks + " task in your list, just like how I have 5 Ballon d'Ors.");
        }
        UI.printLine();
    }

    public void list() {
        UI.printLine();
        System.out.println("Here are the duke.tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        UI.printLine();
    }

    public void mark(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + "duke.tasks.");
        }
        index -= 1; // since 0 indexed
        Task task = list.get(index);
        task.mark();
    }

    public void unmark(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + " duke.tasks.");
        }
        index -= 1; // since 0 indexed
        Task task = list.get(index);
        task.unmark();
    }

    public void delete(int index) throws InvalidArgumentException {
        if (index > numOfTasks) {
            throw new InvalidArgumentException("I'm sorry but that task does not exist. There are only " + numOfTasks + " duke.tasks.");
        }
        numOfTasks -= 1;
        Task removedTask = list.get(index);
        list.remove(index);
        UI.printLine();
        System.out.println("Removed task:\n" + removedTask);
        UI.printLine();
    }


}
