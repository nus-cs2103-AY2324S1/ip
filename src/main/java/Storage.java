public class Storage {
    private final String[] arr;
    private int idx;
    public Storage(int size) {
        this.arr = new String[size];
        this.idx = 0;
    }

    public void add(String s) {
        arr[idx] = s;
        this.idx++;
    }

    public String list() {
        StringBuilder builder = new StringBuilder();
        if (this.idx == 0) return "";
        for (int i = 0; i < idx; i++) {
            if (i == idx - 1) {
                builder.append(String.format("%d. %s", i + 1, this.arr[i]));
            } else {
                builder.append(String.format("%d. %s\n", i + 1, this.arr[i]));
            }
        }
        return builder.toString();
    }
}
