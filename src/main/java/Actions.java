import java.util.ArrayList;

public class Actions {
    private final ArrayList<String> actions = new ArrayList<>();

    public void add(String input) {
        actions.add(input);
    }

    public ArrayList<String> list() {
        return actions;
    }
}
