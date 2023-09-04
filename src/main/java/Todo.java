public class Todo extends Task {
    private static String LINE = "-----------------------------------------\n";
    public Todo(String description) {
        super(description);
    }

    public static Todo todoParse(String input) {
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            return new Todo(input);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
