import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        private final String FILE_PATH = "data/meowies.txt";
        private final File file;

        public TaskStorage() {
            this.file = new File(FILE_PATH);
            try {
                this.loadFromFile();
            } catch (FileNotFoundException | WrongFormatException | InvalidFileException e) {
                try {
                    this.file.getParentFile().mkdirs();
                    this.file.createNewFile();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        public String save(String input) {
            Task task;
            try {
                task = Task.createTask(input);
            } catch (WrongCommandException | WrongFormatException e) {
                return e.getMessage();
            }

            this.tasks.add(task);
            this.saveToFile();

            return "added: " + task;
        }

        public String markAsDone(int index) {
            this.tasks.get(index).markAsDone();
            this.saveToFile();
            return "Nice! I've meowrked this task as done:\n"
                    + "    " + this.tasks.get(index);
        }

        public String unmarkAsDone(int index) {
            this.tasks.get(index).unmarkAsDone();
            this.saveToFile();
            return "Oh meow! I've marked this task as undone :( :\n"
                    + "    " + this.tasks.get(index);
        }

        public String delete(int index) {
            Task task = this.tasks.get(index);
            this.tasks.remove(index);
            this.saveToFile();
            return "Noted. I've removed this task:\n"
                    + "    " + task + "\n"
                    + "    " + "Now you have " + this.tasks.size() + " tasks in the list.";
        }

        private void saveToFile() {
            try {
                java.io.FileWriter fw = new java.io.FileWriter(FILE_PATH);
                for (Task task : tasks) {
                    fw.write(task.saveToFileString() + "\n");
                }
                fw.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        private void loadFromFile() throws FileNotFoundException, WrongFormatException, InvalidFileException {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String fileTask = sc.nextLine();
                Task task = Task.loadTask(fileTask);
                this.tasks.add(task);
            }
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

    static abstract class Task {
        protected boolean isDone = false;
        protected String description;
        protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        public static Task createTask(String task) throws WrongCommandException, WrongFormatException {
            TaskType taskType = getTaskType(task);
            if (taskType == null) throw new WrongCommandException("Whopsie daisies! I don't understand that command!");

            switch (taskType) {
                case TODO:
                    return new TodoTask(task);
                case DEADLINE:
                    return new DeadlineTask(task);
                case EVENT:
                    return new EventTask(task);
                default:
                    return null;
            }
        }

        public static Task loadTask(String fileTask) throws WrongFormatException, InvalidFileException {
            String[] taskDetails = fileTask.split(" \\| ");
            try {
                TaskType taskType = TaskType.valueOf(taskDetails[0]);
                boolean isDone = taskDetails[1].equals("1");
                String description = taskDetails[2];

                switch (taskType) {
                    case TODO:
                        return new TodoTask(isDone, description);
                    case DEADLINE:
                        return new DeadlineTask(isDone, description, taskDetails[3]);
                    case EVENT:
                        return new EventTask(isDone, description, taskDetails[3], taskDetails[4]);
                    default:
                        return null;
                }
            } catch (NullPointerException | IllegalArgumentException | DateTimeException e) {
                throw new InvalidFileException("File is corrupted!");
            }
        }

        private static TaskType getTaskType(String input) {
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

        private static String squareBracketWrapper(String input) {
            return "[" + input + "]";
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void unmarkAsDone() {
            this.isDone = false;
        }

        protected abstract String getTaskTypeString();

        protected abstract String getDescription(String input);

        protected abstract String saveToFileString();

        private static final class TodoTask extends Task {
            public TodoTask(String task) throws WrongFormatException {
                String description = getDescription(task);
                if (description == null) throw new WrongFormatException("Whopsie daisies! I don't understand that format!");
                this.description = description;
            }

            public TodoTask(boolean isDone, String description) {
                this.isDone = isDone;
                this.description = description;
            }

            @Override
            protected String getTaskTypeString() {
                return squareBracketWrapper("T");
            }

            @Override
            protected String saveToFileString() {
                return "TODO | " + (isDone ? "1" : "0") + " | " + description;
            }

            @Override
            protected String getDescription(String input) {
                if (input.split(" ", 2).length == 1) {
                    return null;
                }
                return input.split(" ", 2)[1];
            }

            @Override
            public String toString() {
                return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description;
            }
        }

        private static final class DeadlineTask extends Task {
            private LocalDate dateEnd;

            public DeadlineTask(String task) throws WrongFormatException {
                String description = getDescription(task);
                if (description == null) throw new WrongFormatException("Whopsie daisies! I don't understand that format!");
                this.description = description;
            }

            public DeadlineTask(boolean isDone, String description, String dateEnd) {
                this.isDone = isDone;
                this.description = description;
                this.dateEnd = LocalDate.parse(dateEnd);
            }

            @Override
            protected String getTaskTypeString() {
                return squareBracketWrapper("D");
            }

            @Override
            protected String saveToFileString() {
                return "DEADLINE | " + (isDone ? "1" : "0") + " | " + description + " | " + dateEnd;
            }

            @Override
            protected String getDescription(String input) {
                if (input.split(" ", 2).length == 1) {
                    return null;
                }

                String[] split = input.split(" ", 2)[1].split(" /by ");

                if (split.length == 1) {
                    return null;
                }

                try {
                    this.dateEnd = LocalDate.parse(split[1]);
                } catch (DateTimeException e) {
                    return null;
                }

                return split[0];
            }

            @Override
            public String toString() {
                return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description
                        + " (by: " + dateEnd.format(formatter) + ")";
            }
        }

        private static final class EventTask extends Task {
            private LocalDate dateStart;
            private LocalDate dateEnd;

            public EventTask(String task) throws WrongFormatException {
                String description = getDescription(task);
                if (description == null) throw new WrongFormatException("Whopsie daisies! I don't understand that format!");
                this.description = description;
            }

            public EventTask(boolean isDone, String description, String dateStart, String dateEnd) {
                this.isDone = isDone;
                this.description = description;
                this.dateStart = LocalDate.parse(dateStart);
                this.dateEnd = LocalDate.parse(dateEnd);
            }

            @Override
            protected String getTaskTypeString() {
                return squareBracketWrapper("E");
            }

            @Override
            protected String saveToFileString() {
                return "EVENT | " + (isDone ? "1" : "0") + " | " + description + " | " + dateStart + " | " + dateEnd;
            }

            @Override
            protected String getDescription(String input) {
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

                try {
                    this.dateStart = LocalDate.parse(split2[0]);
                    this.dateEnd = LocalDate.parse(split2[1]);
                } catch (DateTimeException e) {
                    return null;
                }

                return split[0];
            }

            @Override
            public String toString() {
                return getTaskTypeString() + squareBracketWrapper(isDone ? "X" : " ") + " " + description
                        + " (from: " + dateStart.format(formatter) + " to: " + dateEnd.format(formatter) + ")";
            }
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
