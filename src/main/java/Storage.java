import java.util.ArrayList;

public class Storage {
    private final ArrayList<Task> tasks;
    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void mark(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskIndexException(index + 1, this.tasks.size());
        }
        this.tasks.get(index).complete();
    }

    public void unmark(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new InvalidTaskIndexException(index + 1, this.tasks.size());
        }
        this.tasks.get(index).incomplete();
    }

    public String getTaskDescription(int index) {
        return this.tasks.get(index).toString();
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
