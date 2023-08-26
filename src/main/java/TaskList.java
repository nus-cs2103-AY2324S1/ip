import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskIndex) {
        return this.tasks.get(taskIndex);
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int taskIndex) {
        Task deletedTask = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        return deletedTask;
    }

    public void markTask(int taskIndex) throws RobertException {
        if (taskIndex < 0 || this.getTaskCount() <= taskIndex) {
            throw new RobertException("Index is out of bounds.\n"
                    + "Please choose a valid index.");
        }
        this.tasks.get(taskIndex).markAsDone();
    }

    public void unmarkTask(int taskIndex) throws RobertException {
        if (taskIndex < 0 || this.getTaskCount() <= taskIndex) {
            throw new RobertException("Index is out of bounds.\n"
                    + "Please choose a valid index.");
        }
        this.tasks.get(taskIndex).markAsUndone();
    }

    public void clearTasks() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public Iterator<Task> iterator() {
        return new TaskIterator();
    }

    private class TaskIterator implements Iterator<Task> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < tasks.size();
        }

        @Override
        public Task next() {
            return tasks.get(currentIndex++);
        }
    }
}
