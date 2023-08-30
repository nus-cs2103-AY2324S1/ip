import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addToList(Task s) {
        this.list.add(s);
    }

    public Task getTaskAt(int index) {
        return this.list.get(index);
    }

    public int getNumberOfTasks() {
        return this.list.size();
    }

    public void deleteTaskAt(int index) {
        this.list.remove(index);
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.list.size(); i++) {
            s.append((i + 1) + "." + this.list.get(i));
            if (i < this.list.size() - 1) {
                s.append("\n");
            }
        }
        String display = s.toString();
        return display;
    }
}
