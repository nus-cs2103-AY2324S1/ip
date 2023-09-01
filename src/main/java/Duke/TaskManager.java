package Duke;

import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasklist;
    public TaskManager(ArrayList<Task> tasklist) {

        this.tasklist = tasklist;
    }
    public ArrayList<Task> displayList() {
        return tasklist;
    }
    public void mark(int index) {
        tasklist.get(index).markAsDone();
    }

    public void unmark(int index) {
        tasklist.get(index).unmark();
    }

    public void delete(int index) {
        tasklist.remove(index);
    }

    public void todo(String description) {
        tasklist.add(new Todo(description));
    }

    public void deadline(String description, LocalDateTime by) {
        tasklist.add(new Deadline(description, by));
    }

    public void event(String description, LocalDateTime from, LocalTime to) {
        tasklist.add(new Event(description, from, to));
    }
}
