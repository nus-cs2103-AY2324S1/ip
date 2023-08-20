import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> lst;

    public TaskList() {
        lst = new ArrayList<String>();
    }
    public void add(String input) {
        lst.add(input);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lst.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(lst.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
