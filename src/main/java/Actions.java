import java.util.ArrayList;
import java.util.List;

public class Actions {
    private final ArrayList<Task> actions = new ArrayList<>();

    public void add(Task input) {
        actions.add(input);
    }

    public ArrayList<Task> list() {
        return actions;
    }

    public String stringList() {
        List<String> formattedTasks = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            String formattedIndex = String.format("%2d", i + 1);
            formattedTasks.add(formattedIndex + ". " + actions.get(i));
        } return String.join("\n", formattedTasks);
    }

    public Task getAction(int idx) {
        return actions.get(idx);
    }

    public Task mark(int idx) {
        if (idx < actions.size() && idx > -1) {
            actions.get(idx).markAsDone();
            return getAction(idx);
        } return null;
    }

    public Task unmark(int idx) {
        if (idx < actions.size() && idx > -1) {
            actions.get(idx).unMark();
            return getAction(idx);
        } return null;
    }

    public int size(){
        return actions.size();
    }
}