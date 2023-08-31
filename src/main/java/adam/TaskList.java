package adam;

import java.util.ArrayList;

import adam.tasks.Deadline;
import adam.tasks.Event;
import adam.tasks.Task;
import adam.exception.OutOfBoundException;
import adam.tasks.ToDo;
public class TaskList {
    private Ui ui = new Ui();
    private boolean isActive = true;
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> saved) {
        tasks = saved;
    }

    public void deleteTask(int num) {
        Task curr = tasks.get(num-1);
        tasks.remove(num-1);
        ui.delete(curr, tasks.size());
    }
    public void addTodo(String text) {
        ToDo curr = new ToDo(text);
        tasks.add(curr);
        ui.addTodo(curr, tasks.size());
    }
    public void addDeadline(String text, String by) {
        Deadline curr = new Deadline(text, by);
        tasks.add(curr);
        ui.addDeadline(curr, tasks.size());
    }
    public void addEvent(String text, String from, String to) {
        Event curr = new Event(text, from, to);
        tasks.add(curr);
        ui.addEvent(curr, tasks.size());
    }
    public void list() {
        ui.list();
        int count = 1;
        for (Task item: tasks) {
            System.out.println(count + ". " + item.toString());
            count++;
        }
    }

    public void markAsDone(int number) {
        if (number > tasks.size()) {
            throw new OutOfBoundException();
        }
        Task curr = tasks.get(number - 1);
        ui.mark();
        curr.markAsDone();
    }
    public void unmarkAsDone(int number) {
        if (number > tasks.size()) {
            throw new OutOfBoundException();
        }
        Task curr = tasks.get(number - 1);
        ui.unmark();
        curr.unmarkAsDone();
    }

    public boolean isRunning() {
        return isActive;
    }

    public void bye() {
        isActive = false;
        ui.bye();
    }

    public void save (Storage storage) {
        storage.write(tasks);
    }
}
