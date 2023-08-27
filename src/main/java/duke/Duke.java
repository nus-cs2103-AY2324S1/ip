package duke;

import java.util.Scanner;

public class Duke {

    private final UI ui = new UI();

    private final Storage storage = new Storage();

    private final CommandParser parser = new CommandParser();

    private final TaskList taskList = new TaskList(storage.loadFile());

    private void handleUI() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (true) {
            try {
                command = scanner.nextLine();
                ui.printLine();
                Action action = parser.parseCommand(command);
                if (!action.execute(taskList, storage)) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            ui.printLine();
        }
        ui.bye();
        scanner.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.handleUI();
    }





/*    private LinkedList<duke.Task> tasks = new LinkedList<>();

    private final String SAVE_DATA_PATH = "./data/duke.Duke.txt";

    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private void greet() {
        printLine();
        System.out.println("    Hello! I'm duke.Duke");
        System.out.println("    What can I do for you?");
        printLine();
    }

    private void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
        printLine();
    }

    private LocalDate parseTime(String s) throws duke.InvalidDateException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");
            return LocalDate.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new duke.InvalidDateException();
        }
    }

    private duke.Task createTodoTask(String[] words) {
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < words.length; i += 1) {
            taskName.append(words[i]).append(" ");
        }
        return new duke.ToDoTask(taskName.toString().stripTrailing());
    }

    private duke.Task createEventTask(String[] words) throws duke.InvalidDateException {
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

        return new duke.EventTask(taskName.toString().stripTrailing(), parseTime(startDate.toString().trim()), parseTime(endDate.toString().trim()));
    }

    private duke.Task createDeadlineTask(String[] words) throws duke.InvalidDateException {
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
        return new duke.DeadlineTask(taskName.toString().stripTrailing(), parseTime(endDate.toString().trim()));
    }

    private duke.Task createTask(String[] words) throws duke.EmptyBodyException, duke.InvalidDateException {
        if (words.length == 1) {
            throw new duke.EmptyBodyException();
        }

        if (words[0].equals("todo")) {
            return this.createTodoTask(words);
        } else if (words[0].equals("event")) {
            return this.createEventTask(words);
        } else {
            return this.createDeadlineTask(words);
        }
    }

    private void addTask(duke.Task task) {
        tasks.add(task);
        saveFile();
        System.out.println("     " + "Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private void deleteTask(String index) throws duke.WrongIndexException {
        try {
            String regex = "\\d+";
            if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                    || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
                throw new duke.WrongIndexException();
            }
            int i = Integer.parseInt(index, 10) - 1;
            duke.Task task = tasks.remove(i);
            saveFile();
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + task.toString());
            System.out.println("     " + "Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new duke.WrongIndexException();
        }
    }

    private void listTasks() {
        int i = 1;
        System.out.println("     Here are the tasks in your list:");
        for (duke.Task task : tasks) {
            System.out.println("     " + i + "." + task);
            i += 1;
        }
    }

    private void markTask(String index) throws duke.WrongIndexException {
        try {
            String regex = "\\d+";
            if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                    || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
                throw new duke.WrongIndexException();
            }
            int i = Integer.parseInt(index, 10) - 1;
            duke.Task task = tasks.get(i);
            task.markCompleted();
            saveFile();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.println("       " + task.toString());

        } catch (NumberFormatException e) {
            throw new duke.WrongIndexException();
        }
    }

    private void unmarkedTask(String index) throws duke.WrongIndexException {
        try {
            String regex = "\\d+";
            if (!index.matches(regex) || Integer.parseInt(index, 10) - 1 < 0
                    || Integer.parseInt(index, 10) - 1 >= tasks.size()) {
                throw new duke.WrongIndexException();
            }

            int i = Integer.parseInt(index, 10) - 1;
            duke.Task task = tasks.get(i);
            task.markNotCompleted();
            saveFile();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.println("       " + task.toString());
        } catch (NumberFormatException e) {
            throw new duke.WrongIndexException();
        }
    }


    private boolean parseCommand(String command) throws duke.DukeException {
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
            throw new duke.InvalidCommandException();
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
            } catch (duke.DukeException e) {
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
            for (duke.Task task : tasks) {
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
                        tasks.add((new duke.EventTask(taskData[2], parseTime(taskData[3]), parseTime(taskData[4]), isComplete)));
                        break;
                    case "todo":
                        tasks.add(new duke.ToDoTask(taskData[2], isComplete));
                        break;
                    default:
                        tasks.add(new duke.DeadlineTask(taskData[2], parseTime(taskData[3]), isComplete));
                }
            }
        } catch (IOException e) {
            System.out.println("     Unable to load/find file");
        } catch (duke.InvalidDateException e) {
            System.out.println("     Error parsing date when loading file");
        }
    }

    public static void main(String[] args) {
        duke.Duke duke = new duke.Duke();
        duke.loadFile();
        duke.greet();
        duke.handleUI();
    }*/
}
