import java.util.ArrayList;
import java.util.Arrays;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Generates a Todo with description given in the input.
     * @param input input entered by user.
     * @return a Todo.
     */
    public static Todo generateTodoFromInput(String input) throws EmptyTodoDescriptionException {
        String[] words = input.split(" ");
        if (words.length == 1) {
            throw new EmptyTodoDescriptionException(
                    Action.HORIZONTAL_LINE + "\n" +
                    "You did not provide any description to this Todo.\n" +
                    "To add a Todo, enter \"todo <description>\".\n" +
                    Action.HORIZONTAL_LINE);
        }
        ArrayList<String> desWords = new ArrayList<>(Arrays.asList(words).subList(1, words.length));
        String des = String.join(" ", desWords);

        return new Todo(des);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
