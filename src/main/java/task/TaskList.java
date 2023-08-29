package task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.lst.add(task);
    }

    public Task get(int idx) {
        return this.lst.get(idx);
    }

    public int length() {
        return this.lst.size();
    }

    public void delete(int idx) {
        this.lst.remove(idx);
    }
    

    public Iterator<Task> iterator() {
        return this.lst.iterator();
    }

    public String list() {
        String str = "";
        Iterator<Task> iter = this.lst.iterator();
        int i = 1;
        while (iter.hasNext()) {
            str += Integer.toString(i) + ". " + iter.next() + "\n";
            i++;
        }
        return str;
    }
}
