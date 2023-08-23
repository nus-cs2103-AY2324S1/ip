import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatbot {
    /** The name of the chatbot. */
    private static final String NAME = "Bro";

    /** The scanner for the chatbot. */
    private Scanner scanner;

    /** The taskList for the chatbot. */
    private List<Task> taskList;

    /** Constructor for the chatbot. */
    private Chatbot() {
        this.scanner = new Scanner(System.in);
        this.taskList = new ArrayList<>();
    }

    /** Greets the user. */
    private void greet() {
        System.out.println("Hello! I'm " + NAME + "\n" + "What can I do for you? \n");
    }

    /** Causes chatbot to exit. */
    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    /** Echoes commands entered by user.
     *
     * @param input The command entered by the user.
     */
    private void echo(String input) {
        System.out.println(input + "\n");
    }

    /** Adds task inputted by user to taskList. Prints out confirmation.
     *
     * @param input The task inputted by the user.
     * @return Returns true is successful.
     */
    private boolean add(String input) {
        this.taskList.add(new Task(input));
        System.out.println("added: " + input +"\n");
        return true;
    }

    /** Lists out all the tasks in taskList. */
    private void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            System.out.println(num +". " + taskList.get(i).toString());
        }

        System.out.print("\n");
    }

    /** Marks specified task as done. Prints confirmation.
     *
     * @param n The index of the task in the list to mark as done.
     * @return Returns true if successful.
     */
    private boolean markTaskStatusTrue(int n) {
        Task task = taskList.get(n);
        task.markStatus(true);
        System.out.println("Nice! I've marked this task as done: \n" + task.toString() + "\n");
        return true;
    }

    /** Marks specified task as undone. Prints confirmation.
     *
     * @param n The index of the task in the list to mark as undone.
     * @return Returns true if successful.
     */
    private boolean markTaskStatusFalse(int n) {
        Task task = taskList.get(n);
        task.markStatus(false);
        System.out.println("OK, I've marked this task as not done yet: \n" + task.toString() + "\n");
        return true;
    }

    /** Reads user input. Exits if user input is "bye",
     * lists tasks if input is "list", else, echoes commands. */
    private void readInput() {
        System.out.print("> ");
        String userInput = this.scanner.nextLine();

        if (userInput.equals("list")) {
            this.listTasks();
        } else if (userInput.equals("bye")) {
            this.exit();
        } else if (userInput.startsWith("add ")) {
            this.add(userInput.substring(4));
        } else if (userInput.startsWith("mark ")) {
            int taskIndex = Integer.parseInt(userInput.substring(5));
            this.markTaskStatusTrue(taskIndex - 1);
        } else if (userInput.startsWith("unmark ")) {
            int taskIndex = Integer.parseInt(userInput.substring(7));
            this.markTaskStatusFalse(taskIndex - 1);
        } else {
            this.echo(userInput);
        }

        if (!userInput.equals("bye")) {
            this.readInput();
        }
    }

    public static void main(String[] args) {
        Chatbot chatbot = new Chatbot();
        chatbot.readInput();
    }
}
