public class List {
    private Task[] list;
    private int usedCapacity;

    public List() {
        this.list = new Task[100];
        this.usedCapacity = 0;
    }

    public void addToList(Task s) {
        this.list[this.usedCapacity] = s;
        this.usedCapacity++;
    }

    public Task getTaskAt(int index) {
        return this.list[index];
    }

    public int getNumberOfTasks() {
        return this.usedCapacity;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("Here are the tasks in your list:\n");
        for (int i = 0; i < this.usedCapacity; i++) {
            s.append((i + 1) + "." + this.list[i]);
            if (i < this.usedCapacity - 1) {
                s.append("\n");
            }
        }
        String display = s.toString();
        return display;
    }
}
