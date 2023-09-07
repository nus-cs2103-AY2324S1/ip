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

}
