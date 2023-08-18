import java.util.ArrayList;

public class ToDoList {
    private ArrayList<String> list;

    public ToDoList() {
        list = new ArrayList<>();
    }

    public void add(String task) {
        list.add(task);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append((i + 1) + ". " + list.get(i));
            if (i + 1 < list.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
