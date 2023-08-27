import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jarvis");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String command = userInput.nextLine();

        while (!command.split(" ")[0].equals("bye")) {
            switch (command.split(" ")[0]) {
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    int counter = 0;
                    while (counter != list.size()) {
                        counter++;
                        System.out.println(" " + counter + "." + list.get(counter - 1).toString());
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    list.get(Integer.valueOf(command.split(" ")[1]) - 1).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("  " + list.get(Integer.valueOf(command.split(" ")[1]) - 1).toString());
                    System.out.println("____________________________________________________________");
                    break;

                default:
                    list.add(new Task(command));
                    System.out.println("____________________________________________________________");
                    System.out.println(" added: " + command);
                    System.out.println("____________________________________________________________");
                    break;
            }
            command = userInput.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
