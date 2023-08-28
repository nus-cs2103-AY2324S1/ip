package Bob.task;

import Bob.exception.BobInvalidTaskNumberException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int num) throws BobInvalidTaskNumberException {
        try {
            return tasks.remove(num - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobInvalidTaskNumberException();
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int num) throws BobInvalidTaskNumberException {
        try {
            return this.tasks.get(num);
        } catch (IndexOutOfBoundsException e) {
            throw new BobInvalidTaskNumberException("You are trying to access a non-existent task :O\n" +
                    "Use the command: \"list\" to find out what tasks you have.");
        }
    }
}
