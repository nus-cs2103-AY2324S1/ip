package frodo.tasks;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int i) {
        return taskList.remove(i);
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public void clear() {
        taskList.clear();
    }

    public String toString() {
        String str = "There are " + taskList.size() + " task[s] in your list" + "\n";
        str += "Here are the tasks in your list:" + "\n";
        for (int i= 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            str += (i + 1) + ". " + task.toString() + "\n";
        }
        return str;
    }
}
