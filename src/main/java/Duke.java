import java.util.ArrayList;
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
            + TAB + "What can I do for you?\n";

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

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Entry point of the software
     */
    private void run() {
        // Welcome Message
        print(Duke.LOGO);
        print(Duke.LINE_BREAK + Duke.WELCOME_MESSAGE + Duke.LINE_BREAK);

        this.collectCommand();

        // Goodbye Message
        print(Duke.LINE_BREAK + Duke.GOODBYE_MESSAGE + Duke.LINE_BREAK);
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
                        this.handleTask(command);
                        break;
                    case UNRECOGNISED:
                        this.print("Quack does not understand your command!!");
                        this.print(
                                "Quack only understands these commands: list, mark, unmark, delete, todo, deadline, event");
                        break;

                }
            } catch (BadInputException e) {
                this.print("QUACK QUACK!! " + e.getMessage());
            } catch (NumberFormatException e) {
                this.print("QUACK QUACK!! " + e.getMessage()
                        + ", quack only understand numbers, please input a numeric value!");
            }
            this.print(Duke.LINE_BREAK);
            input = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Handles the execution of list
     */
    private void handleList() {
        if (this.TASKS.size() == 0) {
            this.print("Quack Quack, you have not entered anything yet!");
            this.print("Create new tasks with the todo, deadline or event command");
            return;
        }
        this.print("Quack Quack, here are the tasks in quack's memory:");
        for (int i = 0; i < this.TASKS.size(); i++) {
            this.print((i + 1) + "." + this.TASKS.get(i));
        }
    }

    /**
     * validate the index to ensure it is within range
     *
     * @param index - the index of the task in question
     * @return the validated index
     * @throws BadInputException if the index given is not within range
     */
    private int validateIndex(int index) throws BadInputException {

        // validate input
        if (this.TASKS.size() == 0) {
            throw new BadInputException(
                    "Quack currently has no task remembered and cannot execute your command, add one now??");
        }

        // validate input
        if (index >= this.TASKS.size()) {
            throw new BadInputException("Quack does not remember having a task: " + (index + 1) + "\n" + Duke.TAB +
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
        this.print("Quack! I have removed this task:");
        this.print(removed.toString());
        this.print("Quack! Quack is currently remembering " + this.TASKS.size() + " tasks.");
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
        this.print(resp);
        this.print(task.toString());
    }

    /**
     * Handles the creation of new tasks
     *
     * @param param - parser object containing information on the new task.
     * @throws BadInputException if quack cannot remember anymore tasks
     */
    private void handleTask(Parser param) throws BadInputException {
        if (this.TASKS.size() >= 100) {
            throw new BadInputException("QUACK!! quack cannot remember any more tasks!!");
        }

        Commands type = param.getCommand();
        Task newTask;
        if (type == Commands.TODO) {
            newTask = new Todo(param.getParam());

        } else if (type == Commands.DEADLINE) {
            newTask = new Deadline(param.getFlag("/by"), param.getParam());
        } else {
            newTask = new Event(param.getFlag("/from"), param.getFlag("/to"), param.getParam());
        }

        this.TASKS.add(newTask);
        this.print("Quack! I have added this task:");
        this.print(newTask.toString());
        this.print("Quack! Quack is currently remembering " + this.TASKS.size() + " tasks.");

    }

    /**
     * Handles the formatting of string being printed
     *
     * @param string - the string being printed
     */
    public void print(String string) {
        if (string.startsWith(Duke.LINE_BREAK)) {
            System.out.println(string);
            return;
        }
        System.out.println(Duke.TAB + string);
    }
}
