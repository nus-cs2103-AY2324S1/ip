import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        taskList.add(task);
    }

    public void mark(int taskNumber) {
        Task task = taskList.get(taskNumber);
        task.mark();
        System.out.println(task.toString());
    }

    public void unmark(int taskNumber) {
        Task task = taskList.get(taskNumber);
        task.unmark();
        System.out.println(task.toString());
    }
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return result;
    }
}