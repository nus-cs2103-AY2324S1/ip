import java.util.ArrayList;

public class Actions {
    private final ArrayList<Task> actions = new ArrayList<>();
    public void add(Task input) {
        actions.add(input);
    }
    public ArrayList<Task> list() {
        return actions;
    }
    public String stringList() {
        StringBuilder sList = new StringBuilder();
        for (int i = 0; i < actions.size(); i++) {
            sList.append(i + 1).append(".").append(actions.get(i)).append("\n");
        } return sList.toString().trim();
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