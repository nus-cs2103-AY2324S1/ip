import java.util.ArrayList;

public class Actions {
    private final ArrayList<Task> actions = new ArrayList<>();
    public void add(String input) {
        actions.add(new Task(input));
    }
    public ArrayList<Task> list() {
        return actions;
    }
    public String stringList() {
        StringBuilder sList = new StringBuilder();
        for (int i = 0; i < list().size(); i++) {
            sList.append(i+1).append(".").append(getAction(i).toString()).append("\n");
        } if (sList.length() > 0) {
            sList.setLength(sList.length() - 1);
        } return sList.toString();
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
}