import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    ArrayList<Task> getAllTasks() {
        return taskList;
    }

    Task get(int index) {
        return taskList.get(index);
    }

    void markTask(int index) {
        taskList.get(index).changeStatus();
    }

    void add(Task task) {
        taskList.add(task);
    }

    void delete(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to remove is invalid!");
        }

        taskList.remove(index);
    }

    int size() {
        return taskList.size();
    }
}
