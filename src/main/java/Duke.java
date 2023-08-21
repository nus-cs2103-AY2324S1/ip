import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String EXIT_PHRASE = "bye";
        String LIST_PHRASE = "list";

        int LIMIT = 100;
        Task[] toDoList = new Task[LIMIT];
        int i = 0;
        System.out.println(
                "Hello! I'm Sunacchi!\n" +
                        "What can I do for you?\n"
        );

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        String userInput = myObj.next();
        while (i < LIMIT) {
            if (userInput.equals(EXIT_PHRASE)) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }

            if (userInput.equals(LIST_PHRASE)) {
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + "." +
                            "[" + toDoList[j].getStatusIcon() + "]" + " " +
                            toDoList[j].getDescription());

                }
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals("")) {
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals("mark")) {
                userInput = myObj.next();
                Task curr = toDoList[Integer.parseInt(userInput) - 1];
                curr.mark();
                System.out.println( "Nice! I've marked this task as done: \n" + "[X] " + curr.getDescription());
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals("unmark")) {
                userInput = myObj.next();
                //check int
                Task curr = toDoList[Integer.parseInt(userInput) - 1];
                curr.unmark();
                System.out.println( "OK, I've marked this task as not done yet: \n" + "[ ] " + curr.getDescription());
                userInput = myObj.next();
                continue;
            }

            toDoList[i] = new Task(userInput);
            System.out.println("added: " + userInput);
            i++;
            userInput = myObj.next();
        }
    }
}
