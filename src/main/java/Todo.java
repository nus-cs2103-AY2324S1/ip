import java.util.ArrayList;

public class Todo extends Task {
    private static String LINE = "-----------------------------------------\n";
<<<<<<< HEAD
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public static Todo todoParse(String input, String mark) {
=======
    public Todo(String description) {
        super(description);
    }

    public static Todo todoParse(String input) {
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
<<<<<<< HEAD
            return new Todo(input, (mark.equals("1")));
        }
    }

    @Override
    public String toSave() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

=======
            return new Todo(input);
        }
    }

>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
