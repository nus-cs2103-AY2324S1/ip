public class ToDo extends Task {

    public ToDo(String msg) {
        super("T",false, msg);
    }

    public static ToDo newToDo(String input) {
        if (!input.startsWith("todo ")) {
            throw new IllegalArgumentException(
                    String.format("Hey genius, did you mean \"todo %s\"...", input.substring(4)));
        }
        return new ToDo(input.substring(5));
    }
}



