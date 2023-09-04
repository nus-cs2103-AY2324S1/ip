import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public boolean isEmpty() {
        return this.taskArrayList.isEmpty();
    }

    public Task get(int i)  {
        return this.taskArrayList.get(i);
    }

    public int size() {
        return this.taskArrayList.size();
    }

    public void add(Task task) {
        this.taskArrayList.add(task);
    }

    public void delete(int index) throws IndexOutOfBoundsException {
        if (index > (this.size() - 1)) {
            throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
        } else {
            this.taskArrayList.remove(index);
        }
    }

    public void mark(int index) throws IndexOutOfBoundsException {
        if (index > (this.size() - 1)) {
            throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
        } else {
            this.taskArrayList.get(index).markAsDone();
        }
    }

    public void unmark(int index) throws IndexOutOfBoundsException {
        if (index > (this.size() - 1)) {
            throw new IndexOutOfBoundsException("There is currently no task " + (index + 1));
        } else {
            this.taskArrayList.get(index).unmarkAsDone();
        }
    }
}
