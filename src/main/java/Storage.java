public class Storage {
    private final Task[] arr;
    private int idx;
    public Storage(int size) {
        this.arr = new Task[size];
        this.idx = 0;
    }

    public void add(Task t) {
        arr[idx] = t;
        this.idx++;
    }

    public void mark(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.idx) {
            throw new InvalidTaskIndexException(index + 1, this.idx);
        }
        this.arr[index].complete();
    }

    public void unmark(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= this.idx) {
            throw new InvalidTaskIndexException(index + 1, this.idx);
        }
        this.arr[index].incomplete();
    }

    public String getTaskDescription(int index) {
        return this.arr[index].toString();
    }

    public int getStorageSize() {
        return idx;
    }

    public String list() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < idx; i++) {
            if (i == idx - 1) {
                builder.append(String.format("%d.%s", i + 1, this.arr[i]));
            } else {
                builder.append(String.format("%d.%s\n", i + 1, this.arr[i]));
            }
        }
        return builder.toString();
    }
}
