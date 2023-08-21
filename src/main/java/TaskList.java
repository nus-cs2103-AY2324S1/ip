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

    public void delete(int taskNumber) {
        Task task = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        System.out.println("Noted. I've removed this task:\n" + task);
        message();
    }
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return result;
    }
    public void message() {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}