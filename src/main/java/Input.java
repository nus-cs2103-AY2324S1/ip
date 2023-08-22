
public class Input {
    public static final String EXIT_COMMAND = "bye";

    public static final String LIST_COMMAND = "list";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    private String input;


    Input(String input) {
        this.input = input;
    }

    public boolean isExit() {
        return this.input.equals(Input.EXIT_COMMAND);
    }

    public String get() {
        return this.input;
    }

    public boolean isList() {
        return this.input.equals(Input.LIST_COMMAND);
    }


}
