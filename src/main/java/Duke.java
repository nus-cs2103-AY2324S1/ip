import java.util.Scanner;
public class Duke {

    /** name of ChatBot */
    private final String name = "Ken";

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
        if (input.equals("bye")) {
            output = "Bye. Hope to see you again soon!";
        } else if (input.equals("barbie")) {
            output = "Hi barbie!";
        }else {
            output = input;
        }

        return output;
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
            userInput = input.next();
            System.out.println(horLine);
            System.out.println(chatBot.replyUser(userInput));
            System.out.println(horLine);
        }
    }
}
