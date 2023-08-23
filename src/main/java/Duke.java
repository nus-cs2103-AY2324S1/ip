import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private String[] split;
    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<>();
    }
    public void greet() {
        this.indent();
        System.out.println("Greetings, I am Jarvis. How may I assist you today?");
        this.indent();
    }

    public void indent() {
        System.out.println("_______________________________________________________________");
    }

    public void addCommand() {
        this.indent();
        System.out.println("Added the following task to the list.");
        System.out.println(this.tasks.size() + ") " + this.tasks.get(this.tasks.size() - 1).toString());
        System.out.println("You currently have " + this.tasks.size() + " tasks in your list.");
        this.indent();
    }

    public void markCommand(int index) {
        this.indent();
        System.out.println("The following task is marked as complete:");
        System.out.println(index + ") " + this.tasks.get(index - 1).toString());
        System.out.println("Is there anything else I can assist you with?");
        this.indent();
    }

    public void unmarkCommand(int index) {
        this.indent();
        System.out.println("The following task has been unmarked:");
        System.out.println(index + ") " + this.tasks.get(index - 1).toString());
        System.out.println("Is there anything else I can assist you with?");
        this.indent();
    }

    public void list() {
        this.indent();
        if (this.tasks.size() == 0) {
            System.out.println("Your task list is empty! Add a task to view it here.");
        } else {
            System.out.println("Tasks displayed. Your guidance is requested.");
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ") " + this.tasks.get(i).toString());
        }
        this.indent();
    }

    public void mark(boolean flag) throws DukeException {
        // Check if mark is receiving any input or receiving extra input
        if (this.split.length != 2) {
            throw new DukeException("Please enter a valid mark command!");
        }

        // Check if mark is not receiving a number.
        if (!Character.isDigit(this.split[1].charAt(0))) {
            throw new DukeException("I cannot mark a character! Please enter a number.");
        }

        int index = Character.getNumericValue(this.split[1].charAt(0));

        // Check if index is invalid or the task is already marked
        if (index <= 0 || this.tasks.size() <= index || this.tasks.get(index - 1).isCompleted() == flag) {
            throw new DukeException("The task you are trying to mark either doesnt exist, or is already marked");
        }

        if (flag) {
            this.tasks.get(index - 1).completeTask();
            markCommand(index);
        } else {
            this.tasks.get(index - 1).revertTask();
            unmarkCommand(index);
        }
    }

    public void todo() throws DukeException {

        // Check if task is blank.
        if (this.split.length <= 1 || this.split[1].isBlank()) {
            throw new DukeException("Please enter a valid task.");
        }

        this.tasks.add(new Todo(this.split[1]));
        this.addCommand();
    }

    public void deadline() throws DukeException {

        if (this.split.length <= 1 || !this.split[1].contains(" /by ")) {
            throw new DukeException("Please enter a valid task and deadline");
        }

        String[] task = this.split[1].split(" /by ", 2);

        if (task.length <= 1 || task[1].isBlank()) {
            throw new DukeException("Please enter a valid task and deadline.");
        }

        this.tasks.add(new Deadline(task[0], task[1]));
        this.addCommand();
    }

    public void event() throws DukeException {

        // Check if /from is present
        if (split.length <= 1 || !this.split[1].contains(" /from ")) {
            throw new DukeException("There is no task and/or from command present. Please try again.");
        }

        String[] task = this.split[1].split(" /from ", 2);

        // Check if task entered is empty
        if (task.length <= 1 || task[1].isBlank()) {
            throw new DukeException("Please enter a valid task.");
        }

        // Check if /to is present
        if (!task[1].contains(" /to ")) {
            throw new DukeException("There is no /to command present. Please try again.");
        }

        String[] to = task[1].split(" /to ", 2);

        if (to.length <= 1 || to[1].isBlank() || to[0].isBlank()) {
            throw new DukeException("There enter valid to & from dates");
        }

        this.tasks.add(new Event(task[0], to[0], to[1]));
        this.addCommand();
    }

    public void exit() {
        this.indent();
        System.out.println("I shall now take my leave. Farewell!");
        this.indent();
    }

    public void interact() {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
//                this.updateMessage(input.nextLine());
                this.split = input.nextLine().split(" ", 2);
                switch(this.split[0]) {
                    case "bye":
                        break;
                    case "list":
                        this.list();
                        break;
                    case "mark":
                        this.mark(true);
                        break;
                    case "unmark":
                        this.mark(false);
                        break;
                    case "todo":
                        this.todo();
                        break;
                    case "deadline":
                        this.deadline();
                        break;
                    case "event":
                        this.event();
                        break;
                    default:
                        throw new DukeException("I'm sorry, I couldn't understand that. Please try again!");
                }
                if (this.split[0].equals("bye")) {
                    break;
                }
            } catch (DukeException exc) {
                this.indent();
                System.out.println(exc.toString());
                this.indent();
            }
        }
    }

    public static void main(String[] args) {
        // Create a scanner object to read input
        Duke bot = new Duke();
        bot.greet();
        bot.interact();
        bot.exit();
    }
}
