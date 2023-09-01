import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList(TaskList userList) {
        this.taskList = convert(userList);
    }
    public ArrayList<Task> convert(TaskList userList) {
        for (int i = 0; i < userList.size(); i++) {
            taskList.add(i, userList.get(i));
        }
        return taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int size() { return taskList.size(); }

    public Task get(int i) { return taskList.get(i); }

    public void add(Task task) { taskList.add(task); }

    public Task deleteTask(int i) throws DukeException {
        if (i > this.taskList.size() || i < 1) {
            throw new DukeException("Invalid Index provided.");
        }
        Task removing = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        return removing;
    }
    public Task markTask(int i) throws DukeException {
        if (i > this.taskList.size() || i < 1) {
            throw new DukeException("Invalid Index provided.");
        }
        Task marking = this.taskList.get(i - 1);
        marking.markDone();
        return marking;
    }
    public Task unmarkTask(int i) throws DukeException {
        if (i > this.taskList.size() || i < 1) {
            throw new DukeException("Invalid Index provided.");
        }
        Task unmarking = this.taskList.get(i - 1);
        unmarking.unmarkDone();
        return unmarking;
    }
    public TaskList clear() {
        this.taskList.clear();
        return new TaskList();
    }
}
