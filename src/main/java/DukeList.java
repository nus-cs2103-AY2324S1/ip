import java.util.ArrayList;
import java.util.List;

/**
 * DukeList is a class that represents a list of tasks.
 */
public class DukeList {
    private List<String> dukeList;

    /**
     * Constructs a new DukeList object with an empty list of tasks.
     */
    public DukeList() {
        dukeList = new ArrayList<String>(100);
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param input The task to be added.
     */
    public void add(String input) {
        dukeList.add(input);
        System.out.println("added: " + input);
    }

    /**
     * Displays the list of tasks along with their corresponding indices.
     */
    public void display() {
        for (int i = 1; i <= dukeList.size(); i++) {
            System.out.println(i + ". " + dukeList.get(i - 1));
        }
    }
}
