package duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
public class TaskManager {

    private ArrayList<Task> taskList;
    public TaskManager(ArrayList<Task> taskList) {

        this.taskList = taskList;
    }
    public ArrayList<Task> displayList() {
        return taskList;
    }
    public void mark(int index) {
        taskList.get(index).markAsDone();
    }

    public void unmark(int index) {
        taskList.get(index).unmark();
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public void todo(String description) {
        taskList.add(new Todo(description));
    }

    public void deadline(String description, LocalDateTime by) {
        taskList.add(new Deadline(description, by));
    }

    public void event(String description, LocalDateTime from, LocalTime to) {
        taskList.add(new Event(description, from, to));
    }
}
