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
        private final Storage storage = new Storage();

        private boolean isExit = false;

        public InputHandler(Model model) {
            this.model = model;
        }

        // TODO: Implement a parser instead of hardcodoing if elses here
        public void handleInput() {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                isExit = true;
                return;
            }

            if (input.equals("list")) {
                System.out.println(storage);
                return;
            }

            if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                model.echo(storage.markAsDone(index));
                return;
            }

            if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                model.echo(storage.unmarkAsDone(index));
                return;
            }

            model.echo(storage.save(input));
        }

        public boolean isExit() {
            return isExit;
        }
    }

    static class Storage {
        private final int SIZE = 100;
        private final String[] storage = new String[SIZE];
        private final boolean[] isDone = new boolean[SIZE];
        private final Task[] tasks = new Task[SIZE];
        private int pointer = 0;


        public String save(String input) {
            Task task = new Task(input);
            tasks[pointer] = task;
            pointer++;
            return "added: " + input;
        }

        public String markAsDone(int index) {
            tasks[index].markAsDone();
            return "Nice! I've meowrked this task as done:\n"
                    + "    " + tasks[index];
        }

        public String unmarkAsDone(int index) {
            tasks[index].unmarkAsDone();
            return "Oh meow! I've marked this task as undone :( :\n"
                    + "    " + tasks[index];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            int count = 0;

            for (Task task : tasks) {
                if (task == null) {
                    break;
                }
                count++;
                sb.append(count).append(". ").append(task).append("\n");
            }

            return sb.toString();
        }
    }

    static class Task {
        boolean isDone;
        String description;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + (isDone ? "X" : " ") + "] " + description;
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
