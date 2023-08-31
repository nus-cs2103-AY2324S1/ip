package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        items = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    public ArrayList<Task> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }
    
    public ArrayList<Task> searchFor(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task item : items) {
            if (item.getName().contains(keyword)) {
                result.add(item);
            }
        }
        return result;
    }

    public Task mark(int number) {
        items.get(number - 1).markDone();
        return items.get(number - 1);
    }

    public Task unmark(int number) {
        items.get(number - 1).markUndone();
        return items.get(number - 1);
    }

    public Task delete(int number) {
        return items.remove(number - 1);
    }

    public Task addToDo(String name) {
        items.add(new ToDo(name, false));
        return items.get(items.size() - 1);
    }

    public Task addDeadline(String name, LocalDateTime by) {
        items.add(new Deadline(name, by, false));
        return items.get(items.size() - 1);
    }

    public Task addEvent(String name, LocalDateTime from, LocalDateTime to) {
        items.add(new Event(name, from, to, false));
        return items.get(items.size() - 1);
    }

}
