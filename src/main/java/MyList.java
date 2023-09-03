import java.util.ArrayList;

public class MyList extends ArrayList<Task> {
    public MyList() {}

    public MyList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public String toString() {
        StringBuffer rt = new StringBuffer();
        for (int i = 0; i < this.size(); i++) {
            rt.append("  " + (i+1) + ". " + this.get(i).toString() + "\n");
        }
        return rt.toString();
    }
}
