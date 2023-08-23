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

            // exit
            if (command.equals("bye")) {
                break;
            }

            // show list
            if (command.equals("list")) {
                this.showList();
            }

            // mark as done
            else if (command.split(" ")[0].equals("mark")) {
                int index = this.getTaskIndexFromCommand(command);
                if (index == -1) {
                    continue;
                }
                this.markTaskAsDone(index);
            }

            // mark as not done
            else if (command.split(" ")[0].equals("unmark")) {
                int index = this.getTaskIndexFromCommand(command);
                if (index == -1) {
                    continue;
                }
                this.markTaskAsNotDone(index);
            }

            // add to list
            else {
                this.addToList(command);
            }

            System.out.println(Duke.horizontalLine);
        }
        this.exit();
    }

    private void addToList(String text) {
        Task newTask = new Task(text);
        this.taskList.add(newTask);
        System.out.println("added: " + newTask);
    }

    private void showList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    private void markTaskAsDone(int index) {
        this.taskList.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.taskList.get(index));
    }

    private void markTaskAsNotDone(int index) {
        this.taskList.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.taskList.get(index));
    }

    /**
     * Get task index from command (second element) and validate input.
     * If command is invalid, return the number. Else, return -1.
     * Print out the relevant error message, if there is error.
     * Else, return
     * @param command the command taken from input
     * @return the task index, or -1 if input is invalid
     */
    private int getTaskIndexFromCommand(String command) {
        // check for number of arguments
        String[] args = command.split(" ");
        if (args.length != 2) {
            System.out.println("Quack, you have provided wrong number of arguments!");
            return -1;
        }

        // check if second argument is positive integer
        String indexString = args[1];
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

    public static void main(String[] args) {
        new Duke();
    }
}
