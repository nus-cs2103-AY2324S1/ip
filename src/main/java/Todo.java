import java.util.ArrayList;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
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
        ArrayList<String> desWords = new ArrayList<>();
        for (int i = 1; i < words.length; i++) {
            desWords.add(words[i]);
        }
        String des = String.join(" ", desWords);

        return new Todo(des);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
