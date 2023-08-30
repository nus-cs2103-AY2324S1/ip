package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> l = new ArrayList<>();

    /**
     * Empty list.
     */
    public TaskList() {};

    /**
     * Init Tasklist with a given arraylist of tasks.
     * @param list the tasklist
     */
    public TaskList(ArrayList<Task> list) {
        this.l = list;
    }

    public void add(Task task) {
        this.l.add(task);
    }

    public void del(int idx) {
        this.l.remove(idx-1);
    }

    public int getSize() {
        return l.size();
    }

    public Task get(int idx) {
        return this.l.get(idx-1);
    }


    /**
     * Make the string to be saved in the textfile upon termination.
     * @return the string
     */
    public String format() {
        String res = "";
        for(int i = 0; i < l.size(); ++i) {
            res += l.get(i).makeFormat();
        }
        return res;
    }

    /**
     * The list string representation.
     * @return the string
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 1; i <= l.size(); ++i) {
            res.append(String.format("%d. %s\n" , i, l.get(i-1)));
        }
        return res.toString();
    }
}
