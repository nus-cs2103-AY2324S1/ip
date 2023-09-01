package simon;

import java.util.ArrayList;
import simon.task.Task;


public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(String inData, boolean markAsDone) throws SimonException {
        int index = parseIndex(inData);
        validateIndex(index);

        if (markAsDone) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsUndone();
        }
        return tasks.get(index);
    }

    public Task deleteTask(String inData) throws SimonException {
        int index = parseIndex(inData);
        validateIndex(index);

        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    private int parseIndex(String inData) throws SimonException {
        String[] split = inData.split(" ");

        try {
            return Integer.parseInt(split[1]) - 1;
        } catch (NumberFormatException e) {
            throw new SimonException("Please provide a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimonException("Please provide a task number.");
        }
    }

    private void validateIndex(int index) throws SimonException {
        if (this.tasks.isEmpty()) {
            throw new SimonException("There are no tasks to modify.");
        }
        if (index < 0 || index >= this.tasks.size()) {
            throw new SimonException("Invalid task number. Choose a number between 1 and " + tasks.size() + ".");
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.tasks);
    }
}
