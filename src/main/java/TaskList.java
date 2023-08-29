import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task markTask(int index) throws DuckyInvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DuckyInvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toMark = this.tasks.get(index);
        toMark.complete();
        return toMark;
    }

    public Task unmarkTask(int index) throws DuckyInvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DuckyInvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toUnmark = this.tasks.get(index);
        toUnmark.complete();
        return toUnmark;
    }

    public Task deleteTask(int index) throws DuckyInvalidTaskIndexException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new DuckyInvalidTaskIndexException(index + 1, this.tasks.size());
        }
        Task toDelete = this.tasks.get(index);
        this.tasks.remove(index);
        return toDelete;
    }

    public int getStorageSize() {
        return this.tasks.size();
    }

    public String getPrintableList() {
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

    public String getSaveableList() {
        StringBuilder text = new StringBuilder();
        for (Task t : this.tasks) {
            text.append(String.format("%s\n", t.getSaveFormat()));
        }
        return text.toString();
    }
}
