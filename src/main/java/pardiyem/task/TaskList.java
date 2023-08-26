package pardiyem.task;

import com.sun.source.util.TaskListener;

import java.util.ArrayList;
public class TaskList {
    protected final ArrayList<Task> tasklist;
    public TaskList() {
        tasklist = new ArrayList<Task>();
    }

    public void add(Task newTask) {
        tasklist.add(newTask);
    }

    public int size() {
        return tasklist.size();
    }

    public String delete(String ind) {
        ind = ind.trim();
        try {
            int i = Integer.parseInt(ind) - 1;
            if (i < 0 || i >= tasklist.size()) {
                throw new ArrayIndexOutOfBoundsException("Whoops, that number is not an index in the list. Please select a valid index");
            }
            Task curr = tasklist.get(i);
            tasklist.remove(i);
            return String.format("\nNoted. I've removed this task:\n%s\nNow you have %d task(s) on the list\n\n", curr.toString(), tasklist.size());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Whoops, you need to type in a valid integer");
        }
    }

    public String mark(String ind) {
        ind = ind.trim();
        try {
            int i = Integer.parseInt(ind) - 1;
            if (i < 0 || i >= tasklist.size()) {
                throw new ArrayIndexOutOfBoundsException("Whoops, that number is not an index in the list. Please select a valid index");
            }
            return String.format("\n%s\n%s\n\n", this.tasklist.get(i).markAsDone(), tasklist.get(i).toString());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Whoops, you need to type in a valid integer");
        }
    }

    public String unmark(String ind) {
        ind = ind.trim();
        try {
            int i = Integer.parseInt(ind) - 1;
            if (i < 0 || i >= tasklist.size()) {
                throw new ArrayIndexOutOfBoundsException("Whoops, that number is not an index in the list. Please select a valid index");
            }
            return String.format("\n%s\n%s\n\n", this.tasklist.get(i).markAsUndone(), tasklist.get(i).toString());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Whoops, you need to type in a valid integer");
        }
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < tasklist.size(); i++) {
            out.append(String.format("\n%d.%s", i + 1, tasklist.get(i).toString()));
        }
        return out.toString();
    }
}
