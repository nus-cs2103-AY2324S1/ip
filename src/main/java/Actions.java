import java.util.ArrayList;
import java.util.List;

public class Actions {
    private final ArrayList<Task> actions = new ArrayList<>();

    public void add(Task input) {
        actions.add(input);
    }

    public void add(List<Task> tasks) {
        actions.addAll(tasks);
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

    public Task getAction(int idx) throws DukeException {
        if (idx < actions.size() && idx > -1) {
            return actions.get(idx);
        } throw new DukeException(" Task number invalid; please re-examine input.");
    }

    public Task mark(int idx) throws DukeException {
        Task toMark = getAction(idx);
        toMark.markAsDone();
        return toMark;
    }

    public Task unmark(int idx) throws DukeException {
        Task toUnmark = getAction(idx);
        toUnmark.markAsDone();
        return toUnmark;
    }

    public int size(){
        return actions.size();
    }

    public Task delete(int idx) throws DukeException{
        getAction(idx);
        return actions.remove(idx);
    }
}