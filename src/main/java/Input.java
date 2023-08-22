import java.util.Objects;

public class Input {
    public static final String EXIT_COMMAND = "bye";
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";
    private String input;

    Input(String input) {
        this.input = input;
    }

    public boolean isExit() {
        return this.input.equals(Input.EXIT_COMMAND);
    }


}
