package duke;

import duke.exception.InvalidTaskException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> array;

    public TaskList() {
        this.array = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        // add new task into our array
        this.array.add(newTask);
    }

    public Task getTask(int taskNumber) throws InvalidTaskException {
        if (taskNumber > this.array.size() || taskNumber == 0) {
            throw new InvalidTaskException();
        }
        return this.array.get(taskNumber - 1);
    }

    public int getLength() {
        return this.array.size();
    }

    public void deleteTask(Task task) {
        for (int i = 0; i < 100; i++) {
            if (this.array.get(i).equals(task)) {
                this.array.remove(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        // display in numerical pointers
        String list = "";
        for (int i = 0; i < this.array.size(); i++) {
            Task task = this.array.get(i);
            if (task == null) {
                break;
            } else {
                list += (i + 1) + "." + task + "\n";
            }
        }
        return list;
    }
}
