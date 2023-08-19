import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list;

    public ToDoList() {
        list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void mark(int index) {
        list.get(index - 1).markDone();
    }

    public void unmark(int index) {
        list.get(index - 1).markUndone();
    }

    public Task get(int index) {
        return list.get(index - 1);
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append((i + 1) + ". " + list.get(i));
            if (i + 1 < list.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
