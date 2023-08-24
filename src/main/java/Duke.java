import java.util.Scanner;

public class Duke {
    static class Model {
        public Model() {
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

    static class InputHandler {
        private final Scanner scanner = new Scanner(System.in);
        private final Model model;

        private boolean isExit = false;

        public InputHandler(Model model) {
            this.model = model;
        }

        public void handleInput() {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                isExit = true;
                return;
            }

            model.echo(input);
        }

        public boolean isExit() {
            return isExit;
        }
    }

    public static void main(String[] args) {
        Model model = new Model();
        boolean isExit = false;
        InputHandler inputHandler = new InputHandler(model);

        while (!isExit) {
            inputHandler.handleInput();
            isExit = inputHandler.isExit();
        }

        model.bye();
    }
}
