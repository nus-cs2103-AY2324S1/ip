import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        greet();
        getUserInput();
        exit();
    }

    public static void greet() {
        display("Hello! I'm Max\n" + "What can I do for you?");
    }

    public static void exit() {
        display("Bye. Hope to see you again soon!");
    }

    public static void getUserInput() {
        Scanner sc = new Scanner(System.in);
        List list = new List();

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                display(list.toString());
            } else if (userInput.startsWith("mark")) {
                // get index by splitting user input and get task at that index from list
                list.getTaskAt(Integer.parseInt(userInput.split(" ")[1]) - 1).mark();
            } else if (userInput.startsWith("unmark")) {
                // get index by splitting user input and get task at that index from list
                list.getTaskAt(Integer.parseInt(userInput.split(" ")[1]) - 1).unmark();
            } else {
                Task add = getTask(userInput);
                list.addToList(add);
                display("Got it. I've added this task:\n" + add.toString()
                        + "\nNow you have " + list.getNumberOfTasks() + " tasks in the list.");
            }
        }
    }

    @SuppressWarnings("DuplicateExpressions")
    private static Task getTask(String userInput) {
        Task add;
        if (userInput.startsWith("todo")) {
            add = new Todo(userInput.substring(userInput.indexOf(' ') + 1));
        } else if (userInput.startsWith("deadline")) {
            add = new Deadline(userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1)
                    , userInput.substring(userInput.indexOf("/by") + 4));

        } else if (userInput.startsWith("event")) {
            add = new Event(userInput.substring(userInput.indexOf(' ') + 1, userInput.indexOf('/') - 1)
                    , userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1)
                    , userInput.substring(userInput.indexOf("/to") + 4));
        } else {
            add = new Task(userInput);
        }
        return add;
    }

    public static void display(String message) {
        lines();
        System.out.println(message);
        lines();
    }

    public static void lines() {
        System.out.println("");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
}
