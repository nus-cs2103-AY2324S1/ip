import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

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

    /**
     * All exceptions that arise when parsing user input.
     */
    static class ParserException extends RuntimeException {
        public ParserException(String message) {
            super(message);
        }
    }

    static class WrongCommandException extends ParserException {
        public WrongCommandException(String message) {
            super(message);
        }
    }

    static class WrongFormatException extends ParserException {
        public WrongFormatException(String message) {
            super(message);
        }
    }

    static class InvalidFileException extends RuntimeException {
        public InvalidFileException(String message) {
            super(message);
        }
    }

    static class UI {
        Model model;
        boolean isExit = false;
        InputHandler inputHandler;

        public UI() {
            this.model = new Model();
            this.inputHandler = new InputHandler(model);
        }

        public void run() {
            while (!isExit) {
                inputHandler.handleInput();
                isExit = inputHandler.isExit();
            }

            model.bye();
        }
    }

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }
}
