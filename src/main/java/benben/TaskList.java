package benben;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof TaskList)) {
            return false;
        }

        TaskList list = (TaskList) o;
         return this.tasks.equals(list.tasks);
    }

}
