package adam;

import adam.tasks.Deadlines;
import adam.tasks.Events;
import adam.tasks.Task;
import adam.exception.OutOfBoundsException;
import adam.tasks.ToDos;
import java.util.ArrayList;

public class TaskList {
    Ui ui = new Ui();
    boolean running = true;
    private ArrayList<Task> array;


    public TaskList(ArrayList<Task> saved) {

        array = saved;
    }

    public void deleteTask(int num) {
        Task curr = array.get(num-1);
        array.remove(num-1);
        ui.delete(curr, array.size());
    }
    public void addTodo(String text) {
        ToDos curr = new ToDos(text);
        array.add(curr);
        ui.addTodo(curr, array.size());
    }
    public void addDeadline(String text, String by) {
        Deadlines curr = new Deadlines(text, by);
        array.add(curr);
        ui.addDeadline(curr,array.size());
    }
    public void addEvent(String text, String from, String to) {
        Events curr = new Events(text, from, to);
        array.add(curr);
        ui.addEvent(curr,array.size());
    }
    public void list() {
        ui.list();
        int count = 1;
        for (Task item: array) {
            System.out.println(count + ". " + item.toString());
            count++;
        }
    }

    public void markAsDone(int number) {
        if (number > array.size()) {
            throw new OutOfBoundsException();
        }
        Task curr = array.get(number - 1);
        ui.mark();
        curr.markAsDone();
    }
    public void unMarkAsDone(int number) {
        if (number > array.size()) {
            throw new OutOfBoundsException();
        }
        Task curr = array.get(number - 1);
        ui.unmark();
        curr.unmarkAsDone();
    }

    public boolean isRunning() {
        return running;
    }

    public void bye() {
        running = false;
        ui.bye();
    }

    public void save (Storage storage) {
        storage.write(array);
    }

    /**
     * Finds the task that contains the same word as the param.
     *
     * @param item String that is being searched.
     */
    public void find(String item) {
        ArrayList<Task> matches = new ArrayList<>();
        int count = 1;
        for (Task task : array) {
           if (task.search(item)) {
               matches.add(task);
           }
        }
        if (matches.size() == 0) {
            ui.apologize();
        } else {
            ui.search();
            for (Task match : matches) {
                System.out.println(count + ". " + match.toString());
                count++;
            }
        }
    }
}
