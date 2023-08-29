package tasks;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exceptions.DukeException;

public class TaskList {
    private static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private ArrayList<Task> userTasks;
    public TaskList() {
        this.userTasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }
    public static DateTimeFormatter getDateFormatOutput() {
        return DATE_FORMAT_OUTPUT;
    }
    public void setTasks(ArrayList<Task> userTasks) {
        this.userTasks = userTasks;
    }

    public void add(Task task) {
        this.userTasks.add(task);

    }

    public void delete(int taskId) {
        this.userTasks.remove(taskId);
    }

    public void clear() {
        this.userTasks.clear();
    }

    public void mark(int taskId) throws DukeException {
        this.userTasks.get(taskId).markAsDone();
    }

    public void unmark(int taskId) throws DukeException {
        this.userTasks.get(taskId).markAsUndone();
    }

    public Task get(int taskID) {
        return this.userTasks.get(taskID);
    }

    public int size() {
        return this.userTasks.size();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < userTasks.size(); i++) {
            output += (i + 1) + ". " + userTasks.get(i).toString() + "\n";
        }
        return output;
    }
}
