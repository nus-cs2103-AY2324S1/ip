import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    /**
     * Default tab spacing
     */
    private static final String TAB = "     ";
    /**
     * Default Welcome Message
     */
    private static final String WELCOME_MESSAGE = TAB + "Quack Quack! I am a duck named Quack\n"
            + TAB + "What can I do for you?\n" + TAB + "Quack will remember the Task you give quack!\n"
            + TAB + "Quack understands these commands: list, mark, unmark, delete, todo, deadline, event\n"
            + TAB + "For mark/unmark/delete please provide a number after, like such mark 2\n"
            + TAB + "deadline requires the /by keyword and event requires the /from and /to keyword\n"
            + TAB + "Please provide a valid date and time after the keyword with the following format: YYYY-MM-DD HH:MM\n";

    /**
     * Default Exit Message
     */
    private static final String GOODBYE_MESSAGE = TAB + "Quack Quack! Quack hopes to see you again soon!\n";

    /**
     * Line Break
     */
    private static final String LINE_BREAK = "    ____________________________________________________________\n";

    /**
     * App LOGO
     */
    private static final String LOGO = "\n░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
            +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██████████░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░████░░██████████░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░██░░░░░░░░░░██░░░░░░░░████░░██▒▒▒▒▒▒██░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░██░░██░░░░░░░░██░░░░░░░░░░░░░░██▒▒▒▒▒▒██░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░██░░░░██░░░░░░██░░░░░░░░░░░░░░████████░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░██░░░░░░██░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░████████████░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░██░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░░░░░░░██░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░██████░░░░░░░░░░░░░░░░████░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░████████████████░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░";

    /**
     * Quacks memory
     */
    private final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Path to the storage, default is ./data/data.txt
     */
    private Storage storage;

    /**
     * Construct a new Duke object which uses filePath as the storage
     *
     * @param filePath - path to the storage file
     */
    public Duke(String filePath) {
        print(Duke.LOGO);
        print(Duke.LINE_BREAK + Duke.WELCOME_MESSAGE + Duke.LINE_BREAK);

        // try to establish a connection to the file
        // set this.storage to null if not possible
        try {
            this.storage = new Storage(filePath);
        } catch (IOException e) {
            Duke.print("QUACK QUACK! Quack has some internal problem and is unable to help you today, please contact quacks mum");
            Duke.print(e.getMessage());
            this.storage = null;
        } catch (DukeBadInputException e) {
            Duke.print("QUACK QUACK! " + filePath + " is not a text file, please provide a file!");
            this.storage = null;
        }
    }

    public static void main(String[] args) {
        new Duke("data/data.txt").run();
    }

    /**
     * Handles the formatting of string being printed
     *
     * @param string - the string being printed
     */
    public static void print(String string) {
        if (string.startsWith(Duke.LINE_BREAK)) {
            System.out.println(string);
            return;
        }
        System.out.println(Duke.TAB + string);
    }

    /**
     * Entry point of the software
     */
    private void run() {
        if (this.storage != null) {
            this.loadStorage();
            this.collectCommand();
            this.storage.close();
        }
        // Goodbye Message
        print(Duke.LINE_BREAK + Duke.GOODBYE_MESSAGE + Duke.LINE_BREAK);
    }

    private void loadStorage() {
        List<String> storedInput;

        // retrieve data from storage class
        try {
            storedInput = this.storage.readFile();
        } catch (IOException e) {
            Duke.print("QUACK QUACK, unexpected error: " + e.getMessage());
            return;
        }

        // parse and store data while looking out for data corruption
        boolean corrupted = false;
        for (String s : storedInput) {
            try {
                this.createTask(new Parser(s), s);
            } catch (DukeBadInputException e) {
                corrupted = true;
                Duke.print(e.getMessage());
            }
        }

        if (corrupted) {
            Duke.print("QUACK QUACK, some of quack's memory are corrupted, please contact quack's mum if this continuously happens ");

            // refresh memory to only include non-corrupted tasks
            try {
                if (!this.storage.rewriteAll(this.TASKS)) {
                    Duke.print("QUACK QUACK, not all tasks were successfully written, please contact my mother :( ");
                }
            } catch (IOException e) {
                Duke.print("QUACK QUACK, unexpected error when rewriting to storage: " + e.getMessage());
            }
        }

        // prints to the user an overview of stored tasks
        this.handleList();
        Duke.print(Duke.LINE_BREAK);
    }

    /**
     * Handles the collection and execution of the command
     */
    private void collectCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            print(Duke.LINE_BREAK);
            try {
                Parser command = new Parser(input);
                switch (command.getCommand()) {
                    case LIST:
                        this.handleList();
                        break;
                    case MARK:
                    case UNMARK:
                        this.handleMark(command.getCommand() == Commands.MARK,
                                this.validateIndex(command.getIndex() - 1));
                        break;
                    case DELETE:
                        this.handleDeletion(this.validateIndex(command.getIndex() - 1));
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        this.handleTask(command, input);
                        break;
                    case UNRECOGNISED:
                        Duke.print("Quack does not understand your command!!");
                        Duke.print(
                                "Quack only understands these commands: list, mark, unmark, delete, todo, deadline, event");
                        break;

                }
            } catch (DukeBadInputException e) {
                Duke.print("QUACK QUACK!! " + e.getMessage());
            } catch (NumberFormatException e) {
                Duke.print("QUACK QUACK!! " + e.getMessage()
                        + ", quack only understand numbers, please input a numeric value!");
            } catch (DateTimeParseException e) {
                Duke.print("QUACK QUACK!! " + e.getMessage());
                Duke.print("Quack only understands date in this format: YYYY-MM-DD HH:MM, do give the hours in 24hours format");
            }
            Duke.print(Duke.LINE_BREAK);
            input = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Handles the execution of list
     */
    private void handleList() {
        if (this.TASKS.size() == 0) {
            Duke.print("Quack Quack, you have not entered any tasks yet!");
            Duke.print("Create new tasks with the todo, deadline or event command");
            return;
        }
        Duke.print("Quack Quack, here are the tasks in quack's memory:");
        for (int i = 0; i < this.TASKS.size(); i++) {
            Duke.print((i + 1) + "." + this.TASKS.get(i));
        }
    }

    /**
     * validate the index to ensure it is within range
     *
     * @param index - the index of the task in question
     * @return the validated index
     * @throws DukeBadInputException if the index given is not within range
     */
    private int validateIndex(int index) throws DukeBadInputException {

        // validate input
        if (this.TASKS.size() == 0) {
            throw new DukeBadInputException(
                    "Quack currently has no task remembered and cannot execute your command, add one now??");
        }

        // validate input
        if (index >= this.TASKS.size()) {
            throw new DukeBadInputException("Quack does not remember having a task: " + (index + 1) + "\n" + Duke.TAB +
                    "Quack only remember till task " + (this.TASKS.size()));
        }
        return index;
    }

    /**
     * Handles the deletion command
     *
     * @param index the index of the task being deleted
     */
    private void handleDeletion(int index) {
        Task removed = this.TASKS.remove(index);
        try {
            if (!this.storage.rewriteAll(this.TASKS)) {
                Duke.print("QUACK QUACK, not all tasks were successfully written, please contact my mother :( ");
            }
        } catch (IOException e) {
            Duke.print("QUACK QUACK, unexpected error when writing to storage: " + e.getMessage());
        }
        Duke.print("Quack! I have removed this task:");
        Duke.print(removed.toString());
        Duke.print("Quack! Quack is currently remembering " + this.TASKS.size() + " tasks.");

    }

    /**
     * Handles the mark/unmark command
     *
     * @param mark  - true if it is a mark command else false
     * @param index - index of the task in question
     */
    private void handleMark(boolean mark, int index) {
        Task task = this.TASKS.get(index);
        // only toggle if mark != completed as if they are the same then there is no
        // effect
        String resp;
        if (mark != task.isCompleted()) {
            task.toggleCompleted();
            resp = mark ? "Quack! Congrats for finishing the task!" : "Quack, I've marked this task as not done yet :(";
        } else {
            resp = mark ? "Quack! This task is already done QUACK!"
                    : "Quack! you cant unmark something that isn't done yet!!";
        }
        Duke.print(resp);
        Duke.print(task.toString());
    }

    /**
     * Creates a new task with the given param
     *
     * @param param - parser object containing information on the new task.
     * @param input - user input used to generate param
     */
    public Task createTask(Parser param, String input) throws DukeBadInputException {
        Commands type = param.getCommand();
        Task newTask;
        if (type == Commands.UNRECOGNISED) {
            throw new DukeBadInputException("Quack must have some memory issue, unable to recall this task: " + input);
        }
        if (type == Commands.TODO) {
            newTask = new Todo(param.getParam(), input);
        } else if (type == Commands.DEADLINE) {
            newTask = new Deadline(param.getFlag("/by"), param.getParam(), input);
        } else {
            newTask = new Event(param.getFlag("/from"), param.getFlag("/to"), param.getParam(), input);
        }
        this.TASKS.add(newTask);
        return newTask;
    }

    /**
     * Handles the creation of new tasks
     *
     * @param param - parser object containing information on the new task.
     * @param input - user input used to generate param
     * @throws DukeBadInputException if quack cannot remember anymore tasks
     */
    private void handleTask(Parser param, String input) throws DukeBadInputException {
        if (this.TASKS.size() >= 100) {
            throw new DukeBadInputException("quack cannot remember any more tasks!!");
        }

        Task newTask = this.createTask(param, input);


        if (!this.storage.writeToFile(input)) {
            Duke.print("QUACK QUACK! unexpected error unable to write to storage");
            return;
        }
        Duke.print("Quack! I have added this task:");
        Duke.print(newTask.toString());
        Duke.print("Quack! Quack is currently remembering " + this.TASKS.size() + " tasks.");

    }
}
