import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String getTaskAsString(int index) {
        return taskList.get(index).toString();
    }

    public void printTasksAsList() {
        int index = 1;
        for (Task task : taskList) {
            System.out.println(index + "." + task.toString());
            index++;
        }
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public void markTaskAsDone(int index, boolean done) {
        getTask(index).markTaskCompleted(done);
    }
}
