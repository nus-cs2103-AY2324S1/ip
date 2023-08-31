package duke;

/**
 * The Duke.UI class is the driver that runs the input handler and output handler.
 */
class UI {
    private OutputUI outputUI;
    private boolean isExit = false;
    private InputHandler inputHandler;

    public UI() {
        this.outputUI = new OutputUI();
        this.inputHandler = new InputHandler(outputUI);
    }

    public void run() {
        while (!isExit) {
            inputHandler.handleInput();
            isExit = inputHandler.isExit();
        }

        outputUI.bye();
    }

    /**
     * Handles all output to the user. This was initially in the main Duke.Duke class and was moved
     * here after the Duke.UI class was made. Ideally this is integrated into the Duke.UI class not as an inner
     * class but on second thought, it is fine as is because it kinda makes it neater to
     * separate the output from the input.
     */
    static class OutputUI {
        public OutputUI() {
            // Greeting
            greet();
        }

        public void greet() {
            printer("Hello! I'm Meowies\n    What can I do for you?");
        }

        public void bye() {
            printer("Bye. Hope to see you again soon!");
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
}
