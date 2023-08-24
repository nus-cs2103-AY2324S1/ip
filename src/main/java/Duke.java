import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        getUserInput();
        exit();
    }

    public static void greet() {
        lines();
        System.out.println("Hello! I'm Max");
        System.out.println("What can I do for you?");
        lines();
    }

    public static void exit() {
        lines();
        System.out.println("Bye. Hope to see you again soon!");
        lines();
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
                list.addToList(new Task(userInput));
                display("added: " + userInput);
            }
        }
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
