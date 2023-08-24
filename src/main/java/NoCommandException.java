public class NoCommandException extends Exception {
    public NoCommandException(String input) {
        super(
                " ☹ OOPS!!! A valid Task is required. Your current input \"" + input + "\" is not a valid task.\n" +
                        "\nPlease enter your input starting with :\n" +
                        "todo\n" + "deadline\n" + "event\n" +
                        "\n After the command word, leave a space before typing your task.\n"
        );
    }
}