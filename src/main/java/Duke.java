import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /** The name of the chatbot. */
    private static final String NAME = "Bro";

    /** The scanner for the chatbot. */
    private Scanner scanner;

    /** The taskList for the chatbot. */
    private List<String> taskList;

    /** Constructor for the chatbot. */
    public Duke() {
        this.scanner = new Scanner(System.in);
        this.taskList = new ArrayList<>();
    }

    /** Greets the user. */
    public void greet() {
        System.out.println("Hello! I'm " + NAME + "\n" + "What can I do for you? \n");
    }

    /** Causes chatbot to exit. */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    /** Echoes commands entered by user.
     *
     * @param input The command entered by the user.
     */
    public void echo(String input) {
        System.out.println(input + "\n");
    }

    /** Adds task inputted by user to taskList. Prints out confirmation.
     *
     * @param input The task inputted by the user.
     */
    public void add(String input) {
        this.taskList.add(input);
        System.out.println("added: " + input +"\n");
    }

    /** Lists out all the tasks in taskList. */
    public void listTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            int num = i + 1;
            System.out.println(num +". " + taskList.get(i));
        }

        System.out.print("\n");
    }

    /** Reads user input. Exits if user input is "bye",
     * lists tasks if input is "list", else, echoes commands. */
    public void readInput() {
        System.out.print("> ");
        String userInput = this.scanner.nextLine();

        switch (userInput) {
            case "list":
                this.listTasks();
                this.readInput();
                break;
            case "bye":
                this.exit();
                break;
            default:
                this.add(userInput);
                this.readInput();
        }
    }

    public static void main(String[] args) {
        Duke chatbot = new Duke();
        chatbot.readInput();
    }
}
