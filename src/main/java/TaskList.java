import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public String taskString(int a) {
        return this.taskList.get(a - 1).toString();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /*public void deleteTask(int index) {
        Task tempDelete = taskList.get(index - 1);
        taskList.remove(index - 1);
        Storage.taskListToFile(f, taskList);

    }*/

    public int getSize() {
        return this.taskList.size();
    }

    public void taskIterator() {
        for (int i = 0; i < taskList.size(); i++) {
            Ui.staticTabPrinter(String.format("%d. %s", i + 1,
                    taskList.get(i).toString()));
        }
    }
}
