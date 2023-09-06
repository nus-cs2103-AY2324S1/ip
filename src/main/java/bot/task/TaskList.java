package bot.task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private final ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
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

    /**
     * Returns TaskList object containing all the tasks that contain the pattern/keyword
     *
     * @param pattern keyword/pattern that is to be searched for
     * @return TaskList object containing all the tasks that contain the pattern/keyword
     */
    public TaskList find(String pattern) {
        TaskList newList = new TaskList();
        Iterator<Task> iterator = this.iterator();
        Task task;
        while (iterator.hasNext()) {
            task = iterator.next();
            if (task.hasPattern(pattern)) {
                newList.add(task);
            }
        }
        return newList;
    }
    

    public Iterator<Task> iterator() {
        return this.lst.iterator();
    }

    public String list() {
        String str = "";
        Iterator<Task> iterator = this.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            str += Integer.toString(i) + ". " + iterator.next() + "\n";
            i++;
        }
        return str;
    }
}
