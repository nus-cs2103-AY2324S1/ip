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

    public void mark(int index) {
        this.arr[index].complete();
    }

    public void unmark(int index) {
        this.arr[index].incomplete();
    }

    public String list() {
        StringBuilder builder = new StringBuilder();
        if (this.idx == 0) return "";
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
