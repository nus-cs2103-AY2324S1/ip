package duke.utility;

import duke.exception.InvalidTaskException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        // add new task into our tasks
        this.tasks.add(newTask);
    }

    public Task getTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > this.tasks.size() || taskNumber == 0) {
            throw new InvalidTaskException();
        }
        return this.tasks.get(taskNumber - 1);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public void deleteTask(Task task) {
        for (int i = 0; i < 100; i++) {
            if (this.tasks.get(i).equals(task)) {
                this.tasks.remove(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        // display in numerical pointers
        String list = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task == null) {
                break;
            } else {
                list += (i + 1) + "." + task + "\n";
            }
        }
        return list;
    }
}
