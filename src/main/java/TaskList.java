import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task mark(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toMark = this.tasks.get(index);
        toMark.complete();
        return toMark;
    }

    public Task unmark(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toUnmark = this.tasks.get(index);
        toUnmark.complete();
        return toUnmark;
    }

    public Task delete(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toDelete = this.tasks.get(index);
        this.tasks.remove(index);
        return toDelete;
    }

    public int getStorageSize() {
        return this.tasks.size();
    }

    public String list() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                builder.append(String.format("%d.%s", i + 1, this.tasks.get(i)));
            } else {
                builder.append(String.format("%d.%s\n", i + 1, this.tasks.get(i)));
            }
        }
        return builder.toString();
    }
}
