import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    /** name of ChatBot */
    private final String name = "Ken";

    /** store user Input in Task array */
    private ArrayList<Task> lists;

    /**
     * Initialize the fixed sized array.
     */
    public Duke() {
        this.lists = new ArrayList<>();
    }
    /**
     * Return the name of the Duke ChatBot.
     *
     * @return this.name.
     */
    private String getName() {
        return this.name;
    }

    /**
     * Return specific String output based on user Input.
     *
     * @param input User input from main.
     * @return output reply.
     */
    private String replyUser(String input) {
        String output = "";
        switch (input) {
            case "bye":
                output = "Bye. Hope to see you again soon!";
                break;
            case "barbie":
                this.lists.add(new Task(input));
                output = "Hi barbie!";
                break;
            case "list":
                output = outputList();
                break;
            default:
                if (input.startsWith("mark ")) {
                    Integer i = Integer.valueOf(input.substring(5));
                    if (i <= this.lists.size()) {
                        this.lists.get(i - 1).markAsDone();
                        output = "Nice! I've marked this task as done: \n" + this.lists.get(i - 1).toString();
                    }
                } else if(input.startsWith("unmark ")) {
                    Integer i = Integer.valueOf(input.substring(7));
                    if (i <= this.lists.size()) {
                        this.lists.get(i - 1).markAsUndone();
                        output = "OK, I've marked this task as not done yet: \n" + this.lists.get(i - 1).toString();
                    }
                } else {
                    this.lists.add(new Task(input));
                    output = "added: " + input;
                }
                break;
        }

        return output;
    }

    /**
     * Output all the user input.
     *
     * @return this.lists
     */
    private String outputList() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: \n");
        int i = 1;
        for (Task val : this.lists) {
            output.append(i).append(". ").append(val).append("\n");
            i++;
        }

        return output.toString();
    }
    public static void main(String[] args) {

        Duke chatBot = new Duke();
        String horLine = "____________________________________________________________";
        String userInput = "";
        Scanner input = new Scanner(System.in);

        System.out.println(horLine);
        System.out.println("Hello! I'm " + chatBot.getName() + "!");
        System.out.println("What can I do for you?");
        System.out.println(horLine);

        while (!userInput.equals("bye")) {
            userInput = input.nextLine();
            System.out.println(horLine);
            System.out.println(chatBot.replyUser(userInput));
            System.out.println(horLine);
        }
    }
}
