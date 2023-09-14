package duke;

/**
 * Handles all output to the user. This was initially in the main Duke.Duke class and was moved
 * here after the Duke.UI class was made. Ideally this is integrated into the Duke.UI class not as an inner
 * class but on second thought, it is fine as is because it kinda makes it neater to
 * separate the output from the input.
 */
class CliOutputUi {
    public CliOutputUi() {
        // Greeting
        greet();
    }

    public void greet() {
        printer("Hello! I'm Meowies\n    What can I do for you?");
    }

    public void echo(String input) {
        printer(input);
    }

    private void printer(String input) {
        System.out.println("    ------------------------------------------");
        System.out.println("    " + input);
        System.out.println("    ------------------------------------------");
    }
}
