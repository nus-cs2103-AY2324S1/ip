import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String EXIT_PHRASE = "bye";
        String LIST_PHRASE = "list";
        String TODO_PHRASE = "todo";
        String DEADLINE_PHRASE = "deadline";
        String EVENT_PHRASE = "event";
        String DELETE_PHRASE = "delete";

        int LIMIT = 100;
        ArrayList<Task> toDoList = new ArrayList<>();

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
                            toDoList.get(j).toString());
                }
                userInput = myObj.next();
                continue;
            }
//            if (userInput.equals("")) {
//                userInput = myObj.next();
//                continue;
//            }

            if (userInput.equals("mark")) {
                userInput = myObj.next();
                Task curr = toDoList.get(Integer.parseInt(userInput) - 1);
                curr.mark();
                System.out.println( "Nice! I've marked this task as done: \n" + "[X] " + curr.getDescription());
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals("unmark")) {
                userInput = myObj.next();
                Task curr = toDoList.get(Integer.parseInt(userInput) - 1);
                curr.unmark();
                System.out.println( "OK, I've marked this task as not done yet: \n" + "[ ] " + curr.getDescription());
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals(DELETE_PHRASE)) {
                userInput = myObj.next();
                Task curr = toDoList.get(Integer.parseInt(userInput) - 1);
                toDoList.remove(Integer.parseInt(userInput) - 1);
                i--;
                System.out.println("Noted. I've removed this task:");
                System.out.println(curr.toString());
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
                continue;
            }

            if (userInput.equals(TODO_PHRASE)) {
                userInput = myObj.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                toDoList.add(new Todo(userInput));
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList.get(i).toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
                continue;
            }
            if (userInput.equals(DEADLINE_PHRASE)) {
                userInput = myObj.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                System.out.println("To be done by?");
                String by = myObj.nextLine();
                toDoList.add(new Deadlines(userInput, by));
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList.get(i).toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
                continue;
            }
            if (userInput.equals(EVENT_PHRASE)) {
                userInput = myObj.nextLine();
                if (userInput.equals("")) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                System.out.println("From?");
                String from = myObj.nextLine();
                System.out.println("To?");
                String to = myObj.nextLine();

                toDoList.add(new Events(userInput, from, to));
                System.out.println("Got it. I've added this task: ");
                System.out.println(toDoList.get(i).toString());
                i++;
                System.out.println("Now you have " + i + " tasks in the list.");
                userInput = myObj.next();
                continue;
            }
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
