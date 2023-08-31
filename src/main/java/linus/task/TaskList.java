package linus.task;

import java.util.List;
import java.util.ArrayList;

import linus.util.Ui;
import linus.exception.LinusException;

public class TaskList {
    private List<Task> tasks = null;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public void list() {
        String listOfItems = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); ++i) {
            listOfItems += (i + 1) + "."
                    + tasks.get(i).toString() + "\n";
        }
        Ui.print(listOfItems);
    }

    public void add(Task task) {
        tasks.add(task);
        int numOfTasks = tasks.size();
        Ui.print("Got it. I've added this linus.task:\n"
                + "  " + task + "\n"
                + "Now you have " + numOfTasks + " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public void delete(int index) throws LinusException {
        index -= 1;
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot delete linus.task. Please provide a valid index.");
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        int numOfTasks = tasks.size();
        Ui.print("Noted. I've removed this linus.task:\n"
                + "  " + task + "\n"
                + "Now you have " + numOfTasks + " task" + (numOfTasks > 1 ? "s" : "") + " in the list.");
    }

    public void mark(int index) throws LinusException {
        index -= 1;
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot mark linus.task. Please provide a valid index.");
        }
        tasks.get(index).mark();

    }

    public void unmark(int index) throws LinusException {
        index -= 1;
        if (index < 0 || index >= tasks.size()) {
            throw new LinusException("Cannot unmark linus.task. Please provide a valid index.");
        }
        tasks.get(index).unmark();
    }
}
