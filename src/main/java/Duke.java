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

    static class InputHandler {
        private final Scanner scanner = new Scanner(System.in);
        private final Model model;
        private final TaskStorage taskStorage = new TaskStorage();

        private boolean isExit = false;

        public InputHandler(Model model) {
            this.model = model;
        }

        // TODO: Implement a parser instead of hard coding if-elses here
        public void handleInput() {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                isExit = true;
                return;
            }

            if (input.equals("list")) {
                System.out.println(taskStorage);
                return;
            }

            if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                model.echo(taskStorage.markAsDone(index));
                return;
            }

            if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                model.echo(taskStorage.unmarkAsDone(index));
                return;
            }

            if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                model.echo(taskStorage.delete(index));
                return;
            }

            model.echo(taskStorage.save(input));
        }

        public boolean isExit() {
            return isExit;
        }
    }

    static class TaskStorage {
        private final int SIZE = 100;
        private final ArrayList<Task> tasks = new ArrayList<>(SIZE);

        public String save(String input) {
            Task task;
            try {
                task = new Task(input);
            } catch (WrongCommandException | WrongFormatException e) {
                return e.getMessage();
            }

            this.tasks.add(task);

            return "added: " + task;
        }

        public String markAsDone(int index) {
            this.tasks.get(index).markAsDone();
            return "Nice! I've meowrked this task as done:\n"
                    + "    " + this.tasks.get(index);
        }

        public String unmarkAsDone(int index) {
            this.tasks.get(index).unmarkAsDone();
            return "Oh meow! I've marked this task as undone :( :\n"
                    + "    " + this.tasks.get(index);
        }

        public String delete(int index) {
            Task task = this.tasks.get(index);
            this.tasks.remove(index);
            return "Noted. I've removed this task:\n"
                    + "    " + task + "\n"
                    + "    " + "Now you have " + this.tasks.size() + " tasks in the list.";
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            int num = 0;

            for (Task task : tasks) {
                if (task == null) continue;
                
                num++;
                sb.append(num).append(". ").append(task).append("\n");
            }

            return sb.toString();
        }
    }

    static class Task {
        private boolean isDone;
        private final TaskType taskType;
        private String description;
        private String dateStart;
        private String dateEnd;

        public Task(String task) throws WrongCommandException, WrongFormatException {
            this.isDone = false;
            TaskType tmpTaskType = getTaskType(task);
            if (tmpTaskType == null) throw new WrongCommandException("Whopsie daisies! I don't understand that command!");
            this.taskType = tmpTaskType;

            String tmpDescription = getDescription(task);
            if (tmpDescription == null) throw new WrongFormatException("Whopsie daisies! I don't understand that format!");
            this.description = tmpDescription;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

        private String squareBracketWrapper(String input) {
            return "[" + input + "]";
        }

        private String getTaskTypeString() {
            switch (taskType) {
                case TODO:
                    return squareBracketWrapper("T");
                case DEADLINE:
                    return squareBracketWrapper("D");
                case EVENT:
                    return squareBracketWrapper("E");
                default:
                    return "";
            }
        }

        private TaskType getTaskType(String input) {
            if (input.startsWith("todo")) {
                return TaskType.TODO;
            }

            if (input.startsWith("deadline")) {
                return TaskType.DEADLINE;
            }

            if (input.startsWith("event")) {
                return TaskType.EVENT;
            }

            return null;
        }

        private String getDescription(String input) {
            if (this.taskType == TaskType.TODO) {
                if (input.split(" ", 2).length == 1) {
                    return null;
                }
                return input.split(" ", 2)[1];
            }

            if (this.taskType == TaskType.DEADLINE) {
                if (input.split(" ", 2).length == 1) {
                    return null;
                }

                String[] split = input.split(" ", 2)[1].split(" /by ");

                if (split.length == 1) {
                    return null;
                }

                this.dateEnd = split[1];
                return split[0];
            }

            if (this.taskType == TaskType.EVENT) {
                if (input.split(" ", 2).length == 1) {
                    return null;
                }

                String[] split = input.split(" ", 2)[1].split(" /from ");
                if (split.length == 1) {
                    return null;
                }

                String[] split2 = split[1].split(" /to ");
                if (split2.length == 1) {
                    return null;
                }

                this.dateStart = split2[0];
                this.dateEnd = split2[1];
                return split[0];
            }

            return null;
        }

        @Override
        public String toString() {
            return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description
                    + (this.taskType == TaskType.DEADLINE ? " (by: " + dateEnd + ")" : "")
                    + (this.taskType == TaskType.EVENT ? " (from: " + dateStart + " to: " + dateEnd + ")" : "");
        }
    }

    static class ParserException extends Exception {
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
