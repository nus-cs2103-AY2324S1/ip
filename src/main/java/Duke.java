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
