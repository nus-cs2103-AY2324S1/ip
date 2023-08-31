import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> lst;

    public TaskList(ArrayList<Task> list){
        lst = list;
    }

    public TaskList() {
        lst = new ArrayList<>();
    }

    public void add(Task t) {
        lst.add(t);
    }

    public void delete(int index) {
        Task t = lst.get(index);
        lst.remove(t);
    }

    public void markTask(int i){
        lst.get(i).markAsDone();
    }

    public void unmarkTask(int i) {
        lst.get(i).markAsNotDone();
    }
    public Task get(int index) {
        return lst.get(index);
    }

    public int total() {
        return lst.size();
    }

}
