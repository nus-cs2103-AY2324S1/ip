import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chatbot {
    /** The name of the Chatbot. */
    private static final String NAME = "Bro";

    /** The scanner for the Chatbot. */
    private Scanner scanner;

    /** The taskList for the Chatbot. */
    private List<Task> taskList;

    /** Constructor for the Chatbot. */
    private Chatbot() {
        this.scanner = new Scanner(System.in);
        this.taskList = new ArrayList<>();
    }

    /** Greets the user. */
    private void greet() {
        System.out.println("Hello! I'm " + NAME + "\n" + "What can I do for you? \n");
    }

    /** Causes Chatbot to exit. */
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
     */
    private void add(String input) {
        Task newTask = null;

        if (input.startsWith("todo ")) {
            newTask = new Todo(input.substring(5));
        } else if (input.startsWith("deadline ")) {
            int by = input.indexOf("/by");
            if (by != -1) {
                String description = input.substring(9, by - 1);
                String deadline = input.substring(by + 4);
                newTask = new Deadline(description, deadline);
            }
        } else if (input.startsWith("event ")) {
            int from = input.indexOf("/from");
            int to = input.indexOf("/to");
            if (from != -1 && to != -1) {
                String description = input.substring(6, from - 1);
                String start = input.substring(from + 6, to - 1);
                String end = input.substring(to + 4);
                newTask = new Event(description, start, end);
            }
        } else {
            newTask = new Task(input);
        }

        if (newTask != null) {
            this.taskList.add(newTask);
            System.out.println("Got it. I've added this task: \n" + newTask);
            System.out.println("Now you have " + this.taskList.size() + " tasks in the list.\n");
        }
    }

    /** Lists out all the tasks in taskList. */
    private void listTasks() {
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            System.out.println(num +". " + taskList.get(i).toString());
        }
        System.out.print("\n");
    }

    /** Marks specified task as done. Prints confirmation.
     *
     * @param n The index of the task in the list to mark as done.
     */
    private void markTaskStatusTrue(int n) {
        Task task = taskList.get(n);
        task.markStatus(true);
        System.out.println("Nice! I've marked this task as done: \n" + task + "\n");
    }

    /** Marks specified task as undone. Prints confirmation.
     *
     * @param n The index of the task in the list to mark as undone.
     */
    private void markTaskStatusFalse(int n) {
        Task task = taskList.get(n);
        task.markStatus(false);
        System.out.println("OK, I've marked this task as not done yet: \n" + task + "\n");
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
        chatbot.greet();
        chatbot.readInput();
    }
}
