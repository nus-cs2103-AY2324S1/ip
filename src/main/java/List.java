public class List {
    private String[] list;
    private int usedCapacity;

    public List() {
        this.list = new String[100];
        this.usedCapacity = 0;
    }

    public void addToList(String s) {
        this.list[this.usedCapacity] = s;
        this.usedCapacity++;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < this.usedCapacity; i++) {
            s.append((i + 1) + ". " + this.list[i]);
            if (i < this.usedCapacity - 1) {
                s.append("\n");
            }
        }
        String display = s.toString();
        return display;
    }
}
