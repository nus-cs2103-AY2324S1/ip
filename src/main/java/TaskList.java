import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task remove(int i) {
        return this.tasks.remove(i - 1);
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public String getTaskString(int i) {
        return this.tasks.get(i - 1).toString();
    }

    public ArrayList<String> getSavedStrings() {
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toSaveFormatString());
        }
        return lines;
    }

    public void markAsDone(int i) {
        this.tasks.get(i - 1).markAsDone();
    }

    public void unmarkAsDone(int i) {
        this.tasks.get(i - 1).unmarkAsDone();
    }
}
