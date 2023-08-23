import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String horizontalLine = "____________________________________________________________";
    private String myName = "Quack-NKN";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> taskList = new ArrayList<>();

    public Duke() {
        interact();
    }

    private void start() {
        System.out.println(Duke.horizontalLine);
        System.out.print("Hello from ");
        System.out.println(this.myName);
        System.out.println("What can I do for you?");
        System.out.println(Duke.horizontalLine);
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Duke.horizontalLine);
    }

    private void interact() {
        this.start();
        while (true) {
            // receive input
            System.out.print("In: ");
            String command = this.scanner.nextLine();
            System.out.println(Duke.horizontalLine);
            String[] commandArgs = command.split(" ", 2);

            // exit
            if (commandArgs[0].equals("bye")) {
                break;
            }

            // show list
            else if (commandArgs[0].equals("list")) {
                this.showList();
            }

            // mark as done
            else if (commandArgs[0].equals("mark")) {
                this.markTaskAsDone(commandArgs);
            }

            // mark as not done
            else if (commandArgs[0].equals("unmark")) {
                this.markTaskAsNotDone(commandArgs);
            }

            // add to-do
            else if (commandArgs[0].equals("todo")) {
                this.addToDoToList(commandArgs);
            }

            // add event
            else if (commandArgs[0].equals("event")) {
                this.addEventToList(commandArgs);
            }

            // add deadline
            else if (commandArgs[0].equals("deadline")) {
                this.addDeadlineToList(commandArgs);
            }

            // anything else
            else {
                this.echo(command);
            }

            System.out.println(Duke.horizontalLine);
        }
        this.exit();
    }

    /**
     * Perform input checking and add the to-do task to task list.
     * @param commandArgs the arguments provided in the command
     */
    private void addToDoToList(String[] commandArgs) {
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the to-do task!");
            return;
        }

        Task newTask = new ToDo(commandArgs[1]);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        showTaskCount();
    }

    /**
     * Perform input checking and add the event to the task list.
     * @param commandArgs the arguments provided in the command
     */
    private void addEventToList(String[] commandArgs) {
        // number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the event!");
            return;
        }

        // /from keyword
        String[] separateByFrom = commandArgs[1].split("/from", 2);
        if (separateByFrom.length != 2) {
            System.out.println("Quack, keyword '/from' not found. " +
                    "It must be present for me to mark the start time!");
            return;
        }

        // /to keyword
        String[] separateByTo = separateByFrom[1].split("/to", 2);
        if (separateByTo.length != 2) {
            System.out.println("Quack, keyword '/to' not found. " +
                    "It must be present after the '/from' keyword for me to mark the end time!");
            return;
        }

        Task newTask = new Event(separateByFrom[0], separateByTo[0], separateByTo[1]);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        this.showTaskCount();
    }

    private void addDeadlineToList(String[] commandArgs) {
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the deadline!");
            return;
        }

        String[] separateByBy = commandArgs[1].split("/by", 2);
        if (separateByBy.length != 2) {
            System.out.println("Quack, keyword '/by' not found." +
                    "It must be present for me to mark the deadline time!");
            return;
        }

        Task newTask = new Deadline(separateByBy[0], separateByBy[1]);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        this.showTaskCount();
    }

    private void showTaskCount() {
        System.out.println("Now you have " + this.taskList.size() + " in the list.");
    }

    private void showList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    private void markTaskAsDone(String[] commandArgs) {
        int index = this.getTaskIndexFromCommand(commandArgs);
        if (index == -1) {
            return;
        }
        this.taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.taskList.get(index));
    }

    private void markTaskAsNotDone(String[] commandArgs) {
        int index = this.getTaskIndexFromCommand(commandArgs);
        if (index == -1) {
            return;
        }
        this.taskList.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.taskList.get(index));
    }

    /**
     * Get task index from command (second element) and validate input.
     * If command is invalid, return the number. Else, return -1.
     * Print out the relevant error message, if there is error.
     * Else, return
     * @param commandArgs the list of command arguments separated by space
     * @return the task index, or -1 if input is invalid
     */
    private int getTaskIndexFromCommand(String[] commandArgs) {
        // check for number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you have provided wrong number of arguments!");
            return -1;
        }

        // check if second argument is positive integer
        String indexString = commandArgs[1];
        if (indexString.matches("0+") || !indexString.matches("\\d+")) {
            System.out.println("Quack, you need to provide a positive integer!");
            return -1;
        }

        // check if index is in range
        int index = Integer.parseInt(indexString);
        if (index > this.taskList.size()) {
            System.out.println("Quack, the task number you provide does not exist!");
            return -1;
        }

        return index - 1;
    }

    /**
     * Echo command back to the user.
     * @param command the command from the user
     */
    private void echo(String command) {
        System.out.println(command);
    }

    public static void main(String[] args) {
        new Duke();
    }
}
