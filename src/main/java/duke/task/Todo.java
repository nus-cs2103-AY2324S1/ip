package duke.task;

public class Todo extends Task {

    private String addMessage = "Understood. No rest for the weary, eh?";

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getAddMessage() {
        return addMessage;
    }

    /**
     * Searches for a given search term in the name of the to-do.
     * @param searchTerm The term to search for.
     * @return True if the name contains the search term, false otherwise.
     */
    @Override
    public boolean contains(String searchTerm) {

        return super.name.contains(searchTerm);

    }

}
