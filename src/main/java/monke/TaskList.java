package monke;

import monke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) throws MonkeException {
        this.tasks.add(task);
    }

    public void delete(String taskNum) throws MonkeException {
        try {
            int n = Integer.parseInt(taskNum);
            if (n > this.tasks.size() || n <= 0) {
                throw new MonkeException("OOGA BOOGA!! Your number is out of range. :(");
            }
            this.tasks.remove(n - 1);
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! You must provide a number from the list. :(");
        }
    }

    public Task getTask(String taskNum) throws MonkeException {
        try {
            int n = Integer.parseInt(taskNum);
            if (n > this.tasks.size() || n <= 0) {
                throw new MonkeException("OOGA BOOGA!! Your number is out of range. :(");
            }
            return tasks.get(n - 1);
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! You must provide a number from the list. :(");
        }
    }

    public List<Task> toList() {
        return this.tasks;
    }

    public int size() {
        return this.tasks.size();
    }

}
