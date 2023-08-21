import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String EXIT_PHRASE = "bye";
        String LIST_PHRASE = "list";
        String TODO_PHRASE = "todo";
        String DEADLINE_PHRASE = "deadline";
        String EVENT_PHRASE = "event";

        int LIMIT = 100;
        Task[] toDoList = new Task[LIMIT];
        int i = 0;
        System.out.println(
                "Hello! I'm Sunacchi!\n" +
                        "What can I do for you?\n"
        );

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        String userInput = myObj.next();
        while (i < LIMIT && !userInput.equals(EXIT_PHRASE)) {

            if (userInput.equals(LIST_PHRASE)) {
                System.out.println("Here are the tasks in your list:");
                for (int j = 0; j < i; j++) {
                    System.out.println(j + 1 + "." +
                            toDoList[j].toString());

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

            if (userInput.equals(TODO_PHRASE)) {
                userInput = myObj.nextLine();
                toDoList[i] = new Todo(userInput);
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList[i].toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
            }
            if (userInput.equals(DEADLINE_PHRASE)) {
                userInput = myObj.nextLine();
                String by = myObj.nextLine();
                toDoList[i] = new Deadlines(userInput, by);
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList[i].toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
            }
            if (userInput.equals(EVENT_PHRASE)) {
                userInput = myObj.nextLine();
                String from = myObj.nextLine();
                String to = myObj.nextLine();

                toDoList[i] = new Events(userInput, from, to);
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList[i].toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
            }
//            toDoList[i] = new Task(userInput);
//            System.out.println("added: " + userInput);
//            i++;
//            userInput = myObj.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
