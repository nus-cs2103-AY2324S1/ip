package bellcurvegod.task;

import bellcurvegod.exception.EmptyTodoDescriptionException;
import bellcurvegod.ui.Ui;

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
     * @param input Input entered by user.
     * @return A Todo.
     * @throws EmptyTodoDescriptionException If the description is missing.
     */
    public static Todo generateTodoFromInput(String input) throws EmptyTodoDescriptionException {
        String[] words = input.split(" ");
        if (words.length == 1) {
            throw new EmptyTodoDescriptionException(
                    Ui.getLine() + "\n" +
                    "You did not provide any description to this Todo.\n" +
                    "To add a Todo, enter \"todo <description>\".\n" +
                            Ui.getLine());
        }
        ArrayList<String> desWords = new ArrayList<>(Arrays.asList(words).subList(1, words.length));
        String des = String.join(" ", desWords);

        return new Todo(des);
    }

    @Override
    public String getDataRepresentation() {
        return "T|" + super.getDataRepresentation();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
