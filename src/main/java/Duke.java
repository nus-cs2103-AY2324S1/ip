import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    private LinkedList<Task> tasks = new LinkedList<>();

    private final String SAVE_DATA_PATH = "./data/Duke.txt";

    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private void greet() {
        printLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();
    }

    private void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    private LocalDate parseTime(String s) throws InvalidDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");
            return LocalDate.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    private Task createTodoTask(String[] words) {
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < words.length; i += 1) {
            taskName.append(words[i]).append(" ");
        }
        return new ToDoTask(taskName.toString().stripTrailing());
    }

    private Task createEventTask(String[] words) throws InvalidDateException {
        StringBuilder taskName = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        StringBuilder endDate = new StringBuilder();

        int i = 1;
        while (i < words.length && !words[i].equals("/from")) {
            taskName.append(words[i]).append(" ");
            i += 1;
        }
        i += 1;
        while (i < words.length && !words[i].equals("/to")) {
            startDate.append(words[i]).append(" ");
            i += 1;
        }
        i += 1;
        while (i < words.length) {
            endDate.append(words[i]).append(" ");
            i += 1;
        }

        return new EventTask(taskName.toString().stripTrailing(), parseTime(startDate.toString().trim()), parseTime(endDate.toString().trim()));
    }

    private Task createDeadlineTask(String[] words) throws InvalidDateException {
        StringBuilder taskName = new StringBuilder();
        StringBuilder endDate = new StringBuilder();

        int i = 1;
        while (i < words.length && !words[i].equals("/by")) {
            taskName.append(words[i]).append(" ");
            i += 1;
        }
        i += 1;
        while (i < words.length) {
            endDate.append(words[i]).append(" ");
            i += 1;
        }
        return new DeadlineTask(taskName.toString().stripTrailing(), parseTime(endDate.toString().trim()));
    }

    private Task createTask(String[] words) throws EmptyBodyException, InvalidDateException {
        if (words.length == 1) {
            throw new EmptyBodyException();
        }

        if (words[0].equals("todo")) {
            return this.createTodoTask(words);
        } else if (words[0].equals("event")) {
            return this.createEventTask(words);
        } else {
            return this.createDeadlineTask(words);
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        saveFile();
        System.out.println("     " + "Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private void deleteTask(String index) throws WrongIndexException {
        try {
            String regex = "\\d+";
            if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                    || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
                throw new WrongIndexException();
            }
            int i = Integer.parseInt(index, 10) - 1;
            Task task = tasks.remove(i);
            saveFile();
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + task.toString());
            System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new WrongIndexException();
        }
    }

    private void listTasks() {
        int i = 1;
        System.out.println("     Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("     " + i + "." + task);
            i += 1;
        }
    }

    private void markTask(String index) throws WrongIndexException {
        try {
            String regex = "\\d+";
            if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                    || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
                throw new WrongIndexException();
            }
            int i = Integer.parseInt(index, 10) - 1;
            Task task = tasks.get(i);
            task.markCompleted();
            saveFile();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + task.toString());

        } catch (NumberFormatException e) {
            throw new WrongIndexException();
        }
    }

    private void unmarkedTask(String index) throws WrongIndexException {
        try {
            String regex = "\\d+";
            if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                    || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
                throw new WrongIndexException();
            }

            int i = Integer.parseInt(index, 10) - 1;
            Task task = tasks.get(i);
            task.markNotCompleted();
            saveFile();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + task.toString());
        } catch (NumberFormatException e) {
            throw new WrongIndexException();
        }
    }


    private boolean parseCommand(String command) throws DukeException {
        String[] words = command.trim().split("\\s");
        if (words[0].equals("bye") && words.length == 1) {
            return false;
        } else if (words[0].equals("list") && words.length == 1) {
            this.listTasks();
        } else if (words[0].equals("delete") && words.length == 2) {
            this.deleteTask(words[1]);
        } else if (words[0].equals("mark") && words.length == 2) {
            this.markTask(words[1]);
        } else if (words[0].equals("unmark") && words.length == 2) {
            this.unmarkedTask(words[1]);
        } else if ((words[0].equals("deadline") || words[0].equals("todo") || words[0].equals("event"))) {
            this.addTask(createTask(words));
        } else {
            throw new InvalidCommandException();
        }
        return true;
    }

    private void handleUI() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (true) {
            try {
                command = scanner.nextLine();
                printLine();
                boolean continueLoop = parseCommand(command);
                if (!continueLoop) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            printLine();
        }
        bye();
        scanner.close();
    }

    private void saveFile() {
        try {
            FileWriter fw = new FileWriter(SAVE_DATA_PATH);
            for (Task task : tasks) {
                fw.write(task.saveData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("     File not found, unable to save");
        }
    }

    private void loadFile() {
        try {
            File f = new File(SAVE_DATA_PATH);
            f.createNewFile();
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                char delimiter = 31;
                String[] taskData = s.split(String.valueOf(delimiter), -1);
                boolean isComplete = taskData[1].equals("1");
                switch (taskData[0]) {
                    case "event":
                        tasks.add((new EventTask(taskData[2], parseTime(taskData[3]), parseTime(taskData[4]), isComplete)));
                        break;
                    case "todo":
                        tasks.add(new ToDoTask(taskData[2], isComplete));
                        break;
                    default:
                        tasks.add(new DeadlineTask(taskData[2], parseTime(taskData[3]), isComplete));
                }
            }
        } catch (IOException e) {
            System.out.println("     Unable to load/find file");
        } catch (InvalidDateException e) {
            System.out.println("     Error parsing date when loading file");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.loadFile();
        duke.greet();
        duke.handleUI();
    }
}
